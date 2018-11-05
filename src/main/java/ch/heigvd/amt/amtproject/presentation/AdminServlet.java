package ch.heigvd.amt.amtproject.presentation;

import ch.heigvd.amt.amtproject.business.DAO.UserDAOLocal;
import ch.heigvd.amt.amtproject.model.Pagination;
import ch.heigvd.amt.amtproject.model.User;
import ch.heigvd.amt.amtproject.model.VerifySession;

import javax.ejb.EJB;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin")
public class AdminServlet extends javax.servlet.http.HttpServlet {

    private List<User> users;
    private static String ADMIN = "/WEB-INF/pages/admin.jsp";
    private Pagination pagination;

    @EJB
    private UserDAOLocal userDAO;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws ServletException, IOException {
        //Vérification de session
        new VerifySession(request.getSession(), request, response).redirectIfNoAdmin();

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

        if(action.equals("MODIFYPrivilege")){
            email = request.getParameter("email");
            privilege = request.getParameter("privilege");

            User userToUpdate = userDAO.findByIdEmail(email);

            if(userToUpdate != null) {
                userToUpdate.setAdmin(Integer.parseInt(privilege) == 1);
                userDAO.update(userToUpdate);
            }
            else{
                //TODO ERREUR : apiApplication envoyé par le formulaire introuvable dans la liste d'applications
            }
        }

        else if(action.equals("MODIFYStatus")){
            email = request.getParameter("email");
            status = request.getParameter("status");

            User userToUpdate = userDAO.findByIdEmail(email);

            if(userToUpdate != null) {
                userToUpdate.setState(Integer.parseInt(status));
                userDAO.update(userToUpdate);
            }
            else{
                //TODO ERREUR : apiApplication envoyé par le formulaire introuvable dans la liste d'applications
            }
        }
        else{

        }
        doGet(request, response);
    }
}