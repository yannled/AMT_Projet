package ch.heigvd.amt.amtproject.presentation;

import ch.heigvd.amt.amtproject.business.DAO.UserDAOLocal;
import ch.heigvd.amt.amtproject.business.KeyGenerator;
import ch.heigvd.amt.amtproject.business.PasswordUtils;
import ch.heigvd.amt.amtproject.model.Pagination;
import ch.heigvd.amt.amtproject.model.User;
import ch.heigvd.amt.amtproject.model.VerifySession;
import ch.heigvd.amt.amtproject.business.EmailSender;


import javax.ejb.EJB;
import javax.mail.MessagingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;


@WebServlet("/admin")
public class AdminServlet extends javax.servlet.http.HttpServlet {

    private List<User> users;
    private static String ADMIN = "/WEB-INF/pages/admin.jsp";
    private Pagination pagination;

    @EJB
    private EmailSender emailSender;
    @EJB
    private UserDAOLocal userDAO;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws ServletException, IOException {
        //Vérification de session
        new VerifySession(request.getSession(), request, response).redirectIfNoAdmin();

        HttpSession session = request.getSession();
        User currentUser = (User)session.getAttribute("user");


        users = userDAO.findAll();
        //TODO use pagination structure to get a users list
        pagination = new Pagination(1,1);

        //PAGINATION
        int recordPerPage = 10;

        // define number of applications per page
        pagination.setRecordsPerPage(recordPerPage, users.size());

        // define if a page is choose
        if(request.getParameter("value") != null)
            pagination.setCurrentPage(Integer.parseInt(request.getParameter("value")));

        // define position of first Element and last element
        int firstElement = pagination.getFirstElement();
        int lastElement = pagination.getLastElement(users.size());

        // define a sublist with element to show
        List<User> tempList = users.subList(firstElement,lastElement);

        int noOfRecords = users.size();
        int noOfPages = (int)Math.ceil(noOfRecords * 1.0 / pagination.getRecordsPerPage());

        request.setAttribute("isAdmin", currentUser.isAdmin());
        request.setAttribute("users", tempList);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", pagination.getCurrentPage());

        request.getRequestDispatcher(ADMIN+"?page="+pagination.getCurrentPage()).forward(request, response);
    }

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String privilege = "";
        String email = "";
        String status = "";
        User userToUpdate;

        switch (action) {

            case "MODIFYPrivilege":
              email = request.getParameter("email");
              privilege = request.getParameter("privilege");
              userToUpdate = userDAO.findByIdEmail(email);

              if (userToUpdate != null) {
                userToUpdate.setAdmin(Integer.parseInt(privilege) == 1);
                userDAO.updateAdmin(userToUpdate);
              } else {
                //TODO ERREUR : apiApplication envoyé par le formulaire introuvable dans la liste d'applications
              }
              break;

            case "MODIFYStatus":
              email = request.getParameter("email");
              status = request.getParameter("status");

              userToUpdate = userDAO.findByIdEmail(email);

              if (userToUpdate.getState() == 2) {
                //Blocking force modification of a user by administrateur
                // when user is in instance to change password
              }
              else if (userToUpdate != null) {
                userToUpdate.setState(Integer.parseInt(status));
                userDAO.updateState(userToUpdate);
              } else {
                //TODO ERREUR : apiApplication envoyé par le formulaire introuvable dans la liste d'applications
              }
              break;

          case "RESET":
              try {
                  // reset le mot de passe, l'envoie par mail et le change dans la base de donnée.
                  String randPassword = KeyGenerator.generateRandomPassword(10);
                  String hashPassword = PasswordUtils.generatePasswordHash(randPassword);

                  email = request.getParameter("email");
                  userToUpdate = userDAO.findByIdEmail(email);

                  userDAO.updatePassword(userToUpdate.getId(), hashPassword);

                  emailSender.sendNewPassword(userToUpdate, randPassword);

                  userToUpdate.setState(2);
                  userDAO.updateState(userToUpdate);


                } catch (MessagingException | NoSuchAlgorithmException | InvalidKeySpecException e) {
                    throw new RuntimeException(e);
                }

              //
              break;
          default:
              // TODO handle no vallue when post
              break;
    }
        doGet(request, response);
    }
}