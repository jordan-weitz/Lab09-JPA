package servlets;

import dataaccess.UserDB;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import models.User;
import services.RoleService;
import services.UserService;

public class UserServlet extends HttpServlet {

    UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        UserDB userDB = new UserDB();
        generateUsers(userDB, request);

        refreshPage(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        UserDB userDB = new UserDB();

        String deleteButton = request.getParameter("deleteUser");
        String editButton = request.getParameter("editButton");
        String saveUserNewInfo = request.getParameter("saveUserInfo");
        String saveUserButton = request.getParameter("saveNewUser");
        String clearFields = request.getParameter("clearFields");

        generateUsers(userDB, request);
        if (deleteButton != null) {

            deleteUser(request, userDB, response);
            return;

        } else if (editButton != null) {

            editUserInfo(request, userDB, response);
            return;

        } else if (saveUserNewInfo != null) {

            saveUserNewInfo(request, userDB, response);

            return;

        } else if (saveUserButton != null) {

            insertNewUser(request, userDB, response);
            return;

        } else if (clearFields != null) {

            refreshPage(request, response);
            return;
        }

    }

    private void editUserInfo(HttpServletRequest request, UserDB userDB, HttpServletResponse response) throws IOException, ServletException {
        String userToEditEmail;
        userToEditEmail = request.getParameter("editButton").toString();
        UserService.editUserInfo(request, userToEditEmail, userDB);
        refreshPage(request, response);
    }

    private void saveUserNewInfo(HttpServletRequest request, UserDB userDB, HttpServletResponse response) throws IOException, ServletException {
        String emailToBeUpdated = request.getParameter("saveUserInfo");

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");

        if (emailToBeUpdated != null && firstName != null && lastName != null) {

            UserService.saveUserNewInfo(emailToBeUpdated, userDB, firstName, lastName, request);
        }
        generateUsers(userDB, request);
        refreshPage(request, response);
    }

    private void deleteUser(HttpServletRequest request, UserDB userDB, HttpServletResponse response) throws ServletException, IOException {
        String userEmailToDelete;
        userEmailToDelete = request.getParameter("deleteUser").toString();
        UserService.deleteUser(userEmailToDelete, userDB);
        generateUsers(userDB, request);
        refreshPage(request, response);

    }

    private void generateUsers(UserDB userDB, HttpServletRequest request) {
        RoleService.generateAllUsers(userDB, request);

    }

    private void insertNewUser(HttpServletRequest request, UserDB userDB, HttpServletResponse response) throws NumberFormatException, ServletException, IOException {

        String email = request.getParameter("email");

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String password = request.getParameter("password");
        int role = Integer.parseInt(request.getParameter("account_type"));

        userService.addNewUser(email, firstName, lastName, password, userDB, request, role);
        generateUsers(userDB, request);
        refreshPage(request, response);
    }

    private void refreshPage(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
    }

}
