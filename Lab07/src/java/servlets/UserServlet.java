package servlets;

import dataaccess.UserDB;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import models.User;
import services.UserService;

public class UserServlet extends HttpServlet {

    UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        UserDB userDB = new UserDB();
        try {
            List<User> users = userDB.getAll();
            request.setAttribute("users", users);
        } catch (Exception ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        UserDB userDB = new UserDB();

        switch (action) {
            case "saveNewUser": {
                insertNewUser(request, userDB, response);
                break;

            }
        }

    }

    private void insertNewUser(HttpServletRequest request, UserDB userDB, HttpServletResponse response) throws NumberFormatException, ServletException, IOException {
        String email = request.getParameter("email");
        //boolean active = Boolean.parseBoolean(request.getParameter("email"));
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String password = request.getParameter("password");
        int role = Integer.parseInt(request.getParameter("account_type"));

        User user = userService.addNewUser(email, true, firstName, lastName, password, role);

        try {
            userDB.insert(user);
        } catch (Exception ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
//        getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
        response.sendRedirect("user");
    }

}
