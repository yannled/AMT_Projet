package ch.heigvd.amt.amtproject.presentation;

import ch.heigvd.amt.amtproject.business.DAO.UserDAOLocal;
import ch.heigvd.amt.amtproject.model.User;
import ch.heigvd.amt.amtproject.model.VerifySession;

import javax.ejb.EJB;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

@WebServlet("/home")
public class HomeServlet extends javax.servlet.http.HttpServlet {

    public static String HOME = "/WEB-INF/pages/home.jsp";

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws ServletException, IOException {
        new VerifySession(request.getSession(), request, response).redirectIfNoUser();

        HttpSession session = request.getSession();
        User currentUser = (User)session.getAttribute("user");

        request.setAttribute("isAdmin", currentUser.isAdmin());
        request.setAttribute("currentUser", currentUser);
        request.getRequestDispatcher(HOME).forward(request, response);
    }
}
