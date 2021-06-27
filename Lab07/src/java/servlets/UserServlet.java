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
        generateUsers(userDB, request);

        getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //String action = request.getParameter("action");
        UserDB userDB = new UserDB();

        String deleteButton = request.getParameter("deleteUser");
        String editButton = request.getParameter("editButton");
        String saveUserNewInfo = request.getParameter("saveUserInfo");
        String saveUserButton = request.getParameter("saveNewUser");
        String clearFields = request.getParameter("clearFields");

        generateUsers(userDB, request);
        if (deleteButton != null) {

            deleteUser(request, userDB);

        } else if (editButton != null) {
            String userToEditEmail;
            userToEditEmail = request.getParameter("editButton").toString();
            try {
                User userToEdit = userDB.get(userToEditEmail);
                request.setAttribute("userEmail", userToEdit.getEmail());
                request.setAttribute("userFirstName", userToEdit.getFirstName());
                request.setAttribute("userLastName", userToEdit.getLastName());
                request.setAttribute("userRole", userToEdit.getRole());
                getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);

            } catch (Exception ex) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (saveUserNewInfo != null) {
            saveUserNewInfo(request, userDB, response);

        } else if (saveUserButton != null) {

            insertNewUser(request, userDB, response);
        } else if (clearFields != null) {

            getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
        }

        response.sendRedirect("user");
    }

    private void saveUserNewInfo(HttpServletRequest request, UserDB userDB, HttpServletResponse response) {
        String emailToBeUpdated = request.getParameter("saveUserInfo");

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");

        if (emailToBeUpdated != null && firstName != null && lastName != null) {

            try {
                User userToBeUpdated = userDB.get(emailToBeUpdated);
                int role = Integer.parseInt(request.getParameter("account_type"));
                userToBeUpdated.setFirstName(firstName);
                userToBeUpdated.setLastName(lastName);
                userToBeUpdated.setRole(role);
                userDB.update(userToBeUpdated);
                getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);

            } catch (Exception ex) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void deleteUser(HttpServletRequest request, UserDB userDB) {
        String userEmailToDelete;
        userEmailToDelete = request.getParameter("deleteUser").toString();
        try {
            userDB.delete(userEmailToDelete);
        } catch (Exception ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void generateUsers(UserDB userDB, HttpServletRequest request) {
        try {
            List<User> users = userDB.getAll();
            request.setAttribute("users", users);
        } catch (Exception ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void insertNewUser(HttpServletRequest request, UserDB userDB, HttpServletResponse response) throws NumberFormatException, ServletException, IOException {
        String email = request.getParameter("email");
        //boolean active = Boolean.parseBoolean(request.getParameter("email"));
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String password = request.getParameter("password");

        if (email != null && firstName != null && lastName != null && password != null) {

            int role = Integer.parseInt(request.getParameter("account_type"));

            User user = userService.addNewUser(email, true, firstName, lastName, password, role);

            try {
                userDB.insert(user);
            } catch (Exception ex) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            request.setAttribute("errorMessage", "Please fill all fields");
        }
//        getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
//        response.sendRedirect("user");
    }

}
