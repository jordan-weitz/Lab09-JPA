/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.UserDB;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import models.User;
import servlets.UserServlet;

public class UserService {

    public static void deleteUser(String userEmailToDelete, UserDB userDB) {
        try {
            userDB.delete(userEmailToDelete);
        } catch (Exception ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void editUserInfo(HttpServletRequest request, String userToEditEmail, UserDB userDB) {
        try {
            User userToEdit = userDB.get(userToEditEmail);
            request.setAttribute("userEmail", userToEdit.getEmail());
            request.setAttribute("userFirstName", userToEdit.getFirstName());
            request.setAttribute("userLastName", userToEdit.getLastName());
            request.setAttribute("userRole", userToEdit.getRole());

        } catch (Exception ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void saveUserNewInfo(String emailToBeUpdated, UserDB userDB, String firstName, String lastName, HttpServletRequest request) {
        try {
            User userToBeUpdated = userDB.get(emailToBeUpdated);
            int role = Integer.parseInt(request.getParameter("account_type"));
            userToBeUpdated.setFirstName(firstName);
            userToBeUpdated.setLastName(lastName);
            userToBeUpdated.setRole(role);
            userDB.update(userToBeUpdated);

        } catch (Exception ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  

    public void addNewUser(String email, String firstName, String lastName, String password, UserDB userDB, HttpServletRequest request, int role) {
        User user = null;
        if (email != null && firstName != null && lastName != null && password != null) {

            user = new User(email, true, firstName, lastName, password, role);

        } else {
            request.setAttribute("errorMessage", "Please fill all fields");
        }

        try {
            userDB.insert(user);
        } catch (Exception ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
