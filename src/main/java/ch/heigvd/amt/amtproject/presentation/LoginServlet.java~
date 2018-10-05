package ch.heigvd.amt.mvcsimple.presentation;

import ch.heigvd.amt.mvcsimple.model.Error;
import ch.heigvd.amt.mvcsimple.business.UserGenerator;
import ch.heigvd.amt.mvcsimple.model.Page;
import ch.heigvd.amt.mvcsimple.model.User;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/user")
public class LoginServlet extends javax.servlet.http.HttpServlet {

    private UserGenerator service; // we will see later how to replace this with dependency injection
    private List<User> users;
    private Page page;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        service = new UserGenerator();
        users = new ArrayList<>();
        page =  new Page(1,1);
    }

    //source : https://stackoverflow.com/questions/31410007/how-to-do-pagination-in-jsp
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        int recordPerPage = 2;

        if(recordPerPage > users.size())
            recordPerPage = users.size();
        page.setRecordsPerPage(recordPerPage);

        if(request.getParameter("value") != null)
            page.setCurrentPage(Integer.parseInt(request.getParameter("value")));

        int firstUser = (page.getCurrentPage()-1)*page.getRecordsPerPage();
        int lastUser = page.getRecordsPerPage()*page.getCurrentPage();
        if(lastUser > users.size())
            lastUser = users.size();
        List<User> tempList = users.subList(firstUser,lastUser);
        int noOfRecords = users.size();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / page.getRecordsPerPage());
        request.setAttribute("users", tempList);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page.getCurrentPage());

        request.getRequestDispatcher("/WEB-INF/pages/view.jsp?page="+page.getCurrentPage()).forward(request, response);
    }

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String lastname = request.getParameter("lastname");
        String email = request.getParameter("email");

        List<Error> errors = new ArrayList<>();
        Error errorName = new Error();
        Error errorEmail = new Error();
        Error errorLastName = new Error();

        boolean login = true;

        if (!email.contains("@")) {
            errorEmail.setErrorText( "bad email structure !");
            errorEmail.setError(true);
            login = false;
        }
        else {
            errorEmail.setValue(email);
        }

        if (name.equals("")){
            errorName.setErrorText("name empty !");
            errorName.setError(true);
            login = false;
        }
        else {
            errorName.setValue(name);
        }

        if (lastname.isEmpty()){
            errorLastName.setErrorText("lastName empty !");
            errorLastName.setError(true);
            login = false;
        }
        else {
            errorLastName.setValue(lastname);
        }

        if (login) {
            User user = new User(name, lastname, email);
            users.add(user);
            doGet(request, response);
            return;
        }

        errors.add(errorEmail);
        errors.add(errorName);
        errors.add(errorLastName);

        request.setAttribute("errors", errors);
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
