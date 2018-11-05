package ch.heigvd.amt.amtproject.presentation;

import ch.heigvd.amt.amtproject.business.DAO.UserDAO;
import ch.heigvd.amt.amtproject.business.DAO.UserDAOLocal;
import ch.heigvd.amt.amtproject.model.User;
import sun.misc.IOUtils;

import javax.ejb.EJB;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.sql.Blob;
import java.util.Base64;
import java.util.List;

import static javax.xml.transform.OutputKeys.ENCODING;

public class ProfileServlet extends javax.servlet.http.HttpServlet {

    public static String PROFILE = "/WEB-INF/pages/profile.jsp";

    @EJB
    private UserDAOLocal userDAO;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws ServletException, IOException {
        //TODO get session user id
        long currentUserId = 3;

        //TODO Get the current user
        if(request.getParameter("modify") != null){
            request.setAttribute("modify", true);
        }else{
            request.setAttribute("modify", false);
        }

        User user = userDAO.findById(currentUserId);

        String base64Avatar = userDAO.getAvatar(currentUserId);
        user.setBase64Avatar(base64Avatar);

        request.setAttribute("currentUser", user);
        request.getRequestDispatcher(PROFILE).forward(request, response);
    }

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws ServletException, IOException {
        //TODO get session user id
        long currentUserId = 3;

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");

        Part filePart = request.getPart("avatar");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        InputStream fileContent = filePart.getInputStream();

        if (fileName != null && !fileName.isEmpty()) {

            userDAO.updateAvatar(currentUserId, fileContent);
        }

        userDAO.updateName(currentUserId, firstName, lastName);

        // if new inserted email already exists we prevent a runtime error at database insert and inform the user to change it.
        if(! userDAO.isExist(email)) {
            userDAO.updateEmail(currentUserId, email);
        }else{

            User user = userDAO.findById(currentUserId);
            user.setEmail(email);
            request.setAttribute("currentUser", user);

            String emailError = "This email already exists";
            request.setAttribute("emailError", true);
            request.setAttribute("emailErrorText", emailError);
            request.setAttribute("modify", true);
            request.getRequestDispatcher(PROFILE).forward(request, response);
        }

        doGet(request, response);
    }
}
