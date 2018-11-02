package ch.heigvd.amt.amtproject.presentation;

import ch.heigvd.amt.amtproject.business.DAO.ApplicationDAO;
import ch.heigvd.amt.amtproject.business.DAO.ApplicationDAOLocal;
import ch.heigvd.amt.amtproject.business.DAO.UserDAO;
import ch.heigvd.amt.amtproject.model.Application;
import ch.heigvd.amt.amtproject.model.User;
import ch.heigvd.amt.amtproject.model.Pagination;

import javax.ejb.EJB;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import java.io.Console;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/projects")
public class ProjectsServlet extends javax.servlet.http.HttpServlet {

    public static String VUE = "/WEB-INF/pages/projects.jsp";
    private List<Application> applications;
    private Pagination pagination;

    @EJB
    private ApplicationDAOLocal applicationDAO;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws ServletException, IOException {
        // TODO: RECUPRER LIST D APPLICATIONS POUR CE USER
        applications = applicationDAO.getProjectsAll();
        pagination = new Pagination(1,1);
        //Application app1 = new Application("nametest1", "description");
        //Application app2 = new Application("nametest2", "description");
        //Application app3 = new Application("nametest3", "description");
        //applications.add(app1);
        //applications.add(app2);
        //applications.add(app3);

        //PAGINATION
        int recordPerPage = 2;

        // define number of applications per page
        pagination.setRecordsPerPage(recordPerPage, applications.size());

        // define if a page is choose
        if(request.getParameter("value") != null)
            pagination.setCurrentPage(Integer.parseInt(request.getParameter("value")));

        // define position of first Element and last element
        int firstElement = pagination.getFirstElement();
        int lastElement = pagination.getLastElement(applications.size());

        // define a sublist with element to show
        List<Application> tempList = applications.subList(firstElement,lastElement);

        int noOfRecords = applications.size();
        int noOfPages = (int)Math.ceil(noOfRecords * 1.0 / pagination.getRecordsPerPage());

        request.setAttribute("applications", tempList);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", pagination.getCurrentPage());

        request.getRequestDispatcher(VUE+"?page="+pagination.getCurrentPage()).forward(request, response);
    }

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String name = "";
        String description = "";
        String apiKey = "";

        if(action.equals("MODIFY")){
            apiKey = request.getParameter("apiKey");
            name = request.getParameter("name");
            description = request.getParameter("description");

            //TODO Recuperer l'application de la db
            //Application myApplicationToModify = applicationDAO.findByApiKey();
            Application myApplicationToModify = new Application("test", "test");

            if(myApplicationToModify != null) {
                myApplicationToModify.setName(name);
                myApplicationToModify.setDescription(description);
                //TODO Faire la modification dans la db
                //applicationDAO.update(myApplicationToModify);
            }
            else{
                //TODO ERREUR : apiApplication envoyé par le formulaire introuvable dans la liste d'applications
            }
        }

        else if(action.equals("DELETE")){
            apiKey = request.getParameter("apiKey");

            //TODO Recuperer l'application de la db
            //Application myApplicationToModify = applicationDAO.findByApiKey();
            Application myApplicationToModify = new Application("test", "test");

            if(myApplicationToModify != null) {
                //TODO Faire la modification dans la db
                //applicationDAO.delete(myApplicationToModify);
            }
            else{
                //TODO ERREUR : apiApplication envoyé par le formulaire introuvable dans la liste d'applications
            }
        }

        else if(action.equals("ADD")){
            name = request.getParameter("name");
            description = request.getParameter("description");
            Application application = new Application(name, description);
            //TODO Faire la modification dans la db
            //applicationDAO.create(application);
        }
        else{

        }
        doGet(request, response);
    }
}
