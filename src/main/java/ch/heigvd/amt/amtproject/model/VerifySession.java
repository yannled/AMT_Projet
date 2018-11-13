package ch.heigvd.amt.amtproject.model;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by patrickneto on 05.11.18.
 */
public class VerifySession {
    private final HttpSession session;
    private final HttpServletRequest request;
    private final HttpServletResponse response;

    private static String PROJECTS = "/WEB-INF/pages/applications.jsp";
    private static String LOGIN = "/WEB-INF/pages/login.jsp";

    public VerifySession(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.session = session;
        this.request = request;
        this.response = response;
    }

    public void redirectIfNoAdmin() throws ServletException, IOException {
        redirectIfNoUser();
        User currentUser = (User) session.getAttribute("user");
        if (!currentUser.isAdmin()) {
            request.getRequestDispatcher(PROJECTS).forward(request, response);
        }
    }

    public void redirectIfNoUser() throws ServletException, IOException {
        if (session.getAttribute("user") == null) {
            request.getRequestDispatcher(LOGIN).forward(request, response);
        }
    }
}
