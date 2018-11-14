package ch.heigvd.amt.amtproject.presentation;

import ch.heigvd.amt.amtproject.business.DAO.ApplicationDAOLocal;
import ch.heigvd.amt.amtproject.model.Application;
import ch.heigvd.amt.amtproject.model.User;
import ch.heigvd.amt.amtproject.model.Pagination;
import ch.heigvd.amt.amtproject.model.VerifySession;

import javax.ejb.EJB;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


@WebServlet("/applications")
public class ApplicationsServlet extends javax.servlet.http.HttpServlet {

    public static String VUE = "/WEB-INF/pages/applications.jsp";
    private List<Application> applications;
    private Pagination pagination;

    @EJB
    private ApplicationDAOLocal applicationDAO;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws ServletException, IOException {
        new VerifySession(request.getSession(), request, response).redirectIfNoUser();

        HttpSession session = request.getSession();
        User currentUser = (User)session.getAttribute("user");

        // Test if the request come from the admin View, if yes there is some query string defined and that's mean we
        // we want to see the application of an other user.
        // else we take our application
        if(request.getParameterMap().containsKey("showUser") && request.getParameterMap().containsKey("userEmail") && request.getParameter("showUser").equals("SHOWUSER") && currentUser.isAdmin()){

            applications = applicationDAO.getProjectsByUser(request.getParameter("userEmail"));
        }
        else{
          applications = applicationDAO.getProjectsByUser(currentUser.getEmail());
        }

        pagination = new Pagination(1,1);

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

        request.setAttribute("isAdmin", currentUser.isAdmin());
        request.setAttribute("applications", tempList);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", pagination.getCurrentPage());

        // We give the attributes comming from the admin part (there will be tested if empty in the jsp file

        request.setAttribute("showUser", request.getParameter("showUser"));
        request.setAttribute("userEmail", request.getParameter("userEmail"));

        request.getRequestDispatcher(VUE).forward(request, response);
    }

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws ServletException, IOException {
        new VerifySession(request.getSession(), request, response).redirectIfNoUser();

        HttpSession session = request.getSession();
        User currentUser = (User)session.getAttribute("user");
        long currentUserId = currentUser.getId();

        String action = request.getParameter("action");
        String name = "";
        String description = "";
        String apiKey = "";

        if (action.equals("MODIFY")) {
            try {
                apiKey = request.getParameter("apiKey");
                name = request.getParameter("name");
                description = request.getParameter("description");

                Application myApplicationToModify = applicationDAO.findByApiKey(Integer.parseInt(apiKey));

                if (myApplicationToModify != null) {
                    myApplicationToModify.setName(name);
                    myApplicationToModify.setDescription(description);
                    applicationDAO.update(myApplicationToModify);
                } else {
                    //Application to modify don't find.
                    throw new Exception();
                }
            } catch (Exception e) {
                request.setAttribute("error","There was a problem when we send the modify form ");
                request.setAttribute("errorContent",e.getMessage());
                request.getRequestDispatcher(ErrorServlet.ERROR).forward(request, response);
            }
        } else if (action.equals("DELETE")) {
            try {
                apiKey = request.getParameter("apiKey");

                Application myApplicationToModify = applicationDAO.findByApiKey(Integer.parseInt(apiKey));

                if (myApplicationToModify != null) {
                    applicationDAO.delete(myApplicationToModify);
                } else {
                    //Application to delete don't find.
                    throw new Exception();
                }
            } catch (Exception e) {
                request.setAttribute("error","There was a problem when we send the delete form");
                request.setAttribute("errorContent",e.getMessage());
                request.getRequestDispatcher(ErrorServlet.ERROR).forward(request, response);
            }

        } else if (action.equals("ADD")) {
            try {
                name = request.getParameter("name");
                description = request.getParameter("description");
                Application application = new Application(name, description);
                long idApp = applicationDAO.create(application);
                if (idApp > 0) {
                    applicationDAO.bindAppToUser(idApp, currentUserId);
                } else {
                    //Application to delete don't find.
                    throw new Exception();
                }
            } catch (Exception e) {
                request.setAttribute("error","There was a problem when we send the Add form");
                request.setAttribute("errorContent",e.getMessage());
                request.getRequestDispatcher(ErrorServlet.ERROR).forward(request, response);
            }
            doGet(request, response);
        }
        doGet(request, response);
    }
}
