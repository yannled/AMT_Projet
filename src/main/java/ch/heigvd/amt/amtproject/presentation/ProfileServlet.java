package ch.heigvd.amt.amtproject.presentation;

import ch.heigvd.amt.amtproject.business.DAO.UserDAOLocal;
import ch.heigvd.amt.amtproject.business.EmailSender;
import ch.heigvd.amt.amtproject.business.KeyGenerator;
import ch.heigvd.amt.amtproject.business.PasswordUtils;
import ch.heigvd.amt.amtproject.model.User;
import ch.heigvd.amt.amtproject.model.VerifySession;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.imageio.ImageIO;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.Part;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import javax.transaction.UserTransaction;

public class ProfileServlet extends javax.servlet.http.HttpServlet {

    public static String PROFILE = "/WEB-INF/pages/profile.jsp";

    @EJB
    private EmailSender emailSender;
    @EJB
    private UserDAOLocal userDAO;

    @Resource
    private UserTransaction userTransaction;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws ServletException, IOException {
        new VerifySession(request.getSession(), request, response).redirectIfNoUser();

        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("user");

        //TODO get session user id
        long currentUserId = currentUser.getId();

        //TODO Get the current user
        if (request.getParameter("modify") != null) {
            request.setAttribute("modify", true);
        } else {
            request.setAttribute("modify", false);
        }

        try {
            User user = userDAO.findById(currentUserId);
            int numberOfApplications = userDAO.countNumbersApplications(user.getEmail());
            String base64Avatar = userDAO.getAvatar(currentUserId);
            user.setBase64Avatar(base64Avatar);

            request.setAttribute("isAdmin", user.isAdmin());
            request.setAttribute("currentUser", user);
            request.setAttribute("nbrApplications", numberOfApplications);
            request.getRequestDispatcher(PROFILE).forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", "There was a problem when we get the user and his informations from the database");
            request.setAttribute("errorContent", e.getMessage());
            request.getRequestDispatcher(ErrorServlet.ERROR).forward(request, response);
        }
    }

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("user");
        long currentUserId = currentUser.getId();

        String action = request.getParameter("action");


        switch (action) {
            case "RESET":
                try {
                    // reset le mot de passe, l'envoie par mail et le change dans la base de donnée.
                    String randPassword = KeyGenerator.generateRandomPassword(10);
                    String hashPassword = PasswordUtils.generatePasswordHash(randPassword);

                    userDAO.updatePassword(currentUserId, hashPassword);

                    emailSender.sendNewPassword(currentUser, randPassword);

                    currentUser.setState(2);
                    userDAO.updateState(currentUser);


                } catch (Exception e) {
                    request.setAttribute("error", "There was a problem when the user reset his password");
                    request.setAttribute("errorContent", e.getMessage());
                    request.getRequestDispatcher(ErrorServlet.ERROR).forward(request, response);
                }

                //
                break;
            case "MODIFY":
                try {
                    String firstName = request.getParameter("firstName");
                    String lastName = request.getParameter("lastName");
                    String email = request.getParameter("email");

                    Boolean changeAvatar = false;
                    InputStream is = null;
                    Part filePart = request.getPart("avatar");
                    String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                    InputStream fileContent = filePart.getInputStream();

                    if (fileName != null && !fileName.isEmpty()) {

                        BufferedImage sourceImage = ImageIO.read(fileContent);
                        ByteArrayOutputStream os = new ByteArrayOutputStream();
                        ImageIO.write(sourceImage, "jpeg", os);
                        long imgSize = os.size();

                        is = new ByteArrayInputStream(os.toByteArray());

                        if (imgSize < 50000) {
                            changeAvatar = true;
                        }
                    }

                    // if new inserted email already exists we prevent a runtime error at database insert and inform the user to change it.
                    if (!userDAO.isExist(email) || currentUser.getEmail().equals(email)) {
                        userDAO.updateProfil(currentUser, is, changeAvatar, email, firstName, lastName);
                    } else {

                        User user = userDAO.findById(currentUserId);
                        user.setEmail(email);
                        request.setAttribute("currentUser", user);

                        String emailError = "This email already exists";
                        request.setAttribute("emailError", true);
                        request.setAttribute("emailErrorText", emailError);
                        request.setAttribute("modify", true);
                        request.getRequestDispatcher(PROFILE).forward(request, response);
                    }

                    //Mettre à jour la session en fonction du changement de profil
                    currentUser.setName(firstName);
                    currentUser.setLastName(lastName);
                    currentUser.setEmail(email);
                    session.setAttribute("user", currentUser);
                } catch (Exception e) {
                    request.setAttribute("error", "There was a problem when the user modify his profile");
                    request.setAttribute("errorContent", e.getMessage());
                    request.getRequestDispatcher(ErrorServlet.ERROR).forward(request, response);
                }
                break;
            default:
                // TODO handle no vallue when post
                break;
        }


        doGet(request, response);
    }
}
