package ch.heigvd.amt.amtproject.presentation;

import ch.heigvd.amt.amtproject.business.DAO.ApplicationDAOLocal;
import ch.heigvd.amt.amtproject.business.DAO.UserDAO;
import ch.heigvd.amt.amtproject.business.DAO.UserDAOLocal;
import ch.heigvd.amt.amtproject.model.Application;
import ch.heigvd.amt.amtproject.model.User;
import ch.heigvd.amt.amtproject.model.VerifySession;

import javax.ejb.EJB;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

@WebServlet("/details_project")
public class DetailsProjectServlet extends javax.servlet.http.HttpServlet {
    private Application application = null;
    private List<User> users;
    private static String DETAILS_PROJECT = "/WEB-INF/pages/details_project.jsp";

    @EJB
    private ApplicationDAOLocal applicationDAO;

    @EJB
    private UserDAOLocal userDAO;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws ServletException, IOException {
        //Vérifier l'existecne de session
        new VerifySession(request.getSession(), request, response).redirectIfNoAdmin();

        //Récupération du projet correspondant à la requête
        String idRequest = request.getParameter("idProject");

        //Vérifier qu'il ait paramètre
        if(idRequest != "") {
            try {
                Long idProject = Long.parseLong(idRequest);
                System.out.println("idProject : " + idProject);
                application = applicationDAO.findById(idProject);
                users = userDAO.findAllByProjectId(idProject);
            }catch(Exception e) {
                request.setAttribute("error","There was a problem when we get the details of an application");
                request.setAttribute("errorContent",e.getMessage());
                request.getRequestDispatcher(ErrorServlet.ERROR).forward(request, response);
            }
        }

        System.out.println("Users doGet : " + users);
        request.setAttribute("application", application);
        request.setAttribute("users", users);
        request.getRequestDispatcher(DETAILS_PROJECT).forward(request, response);
    }

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}