package ch.heigvd.amt.amtproject.presentation;

import ch.heigvd.amt.amtproject.business.DAO.UserDAOLocal;
import ch.heigvd.amt.amtproject.business.PasswordUtils;
import ch.heigvd.amt.amtproject.model.Error;
import ch.heigvd.amt.amtproject.model.User;
import ch.heigvd.amt.amtproject.model.VerifySession;

import javax.ejb.EJB;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.KeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/pswchange")
public class PwdChangeServlet extends javax.servlet.http.HttpServlet {

    public static String PSWCHANGE = "/WEB-INF/pages/pswchange.jsp";
    public static String HOME = "/WEB-INF/pages/home.jsp";

    @EJB
    private UserDAOLocal userDAO;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(PSWCHANGE).forward(request, response);
    }

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws ServletException, IOException {
        new VerifySession(request.getSession(), request, response).redirectIfNoAdmin();

        HttpSession session = request.getSession();
        User currentUser = (User)session.getAttribute("user");
        Long currentUserId = currentUser.getId();

        String oldPassword = request.getParameter("oldpass");
        String password = request.getParameter("pass1");
        String passwordRepeat = request.getParameter("pass2");

        List<Error> errors = new ArrayList<>();
        Error errorOldPassword = new Error();
        Error errorPassword = new Error();
        Error errorPasswordRepeat = new Error();

        boolean syntaxOK = true;

        if (oldPassword.isEmpty()){
            errorOldPassword.setErrorText("password empty !");
            errorOldPassword.setError(true);
            syntaxOK = false;
        }

        // if old password is not validate
        if (!userDAO.isValid(currentUser.getEmail(), oldPassword)){
            errorOldPassword.setErrorText("Wrong password");
            errorOldPassword.setError(true);
            syntaxOK = false;
        }

        if(!password.equals(passwordRepeat)){
            errorPasswordRepeat.setErrorText("not equal to first password");
            errorPasswordRepeat.setError(true);
            syntaxOK = false;
        }


        if (password.isEmpty()){
            errorPassword.setErrorText("password empty !");
            errorPassword.setError(true);
            syntaxOK = false;
        }


        if (passwordRepeat.isEmpty()){
            errorPasswordRepeat.setErrorText("password empty !");
            errorPasswordRepeat.setError(true);
            syntaxOK = false;
        }

        if (syntaxOK) {
                // TODO il pourrait etre judicieux de faire le hash du mot de passe encore plus tot dans le programme
            try{
                userDAO.updatePassword(currentUserId, PasswordUtils.generatePasswordHash(password));
                currentUser.setState(1);
                userDAO.updateState(currentUser);
            }catch(Exception e){
                request.setAttribute("error","There was a problem when we update the user password or his state");
                request.setAttribute("errorContent",e.getMessage());
                request.getRequestDispatcher(ErrorServlet.ERROR).forward(request, response);
            }

            request.getRequestDispatcher(HOME).forward(request, response);
            return;
        }

        errors.add(errorOldPassword);
        errors.add(errorPassword);
        errors.add(errorPasswordRepeat);

        request.setAttribute("errors", errors);
        request.getRequestDispatcher(PSWCHANGE).forward(request, response);
    }
}
