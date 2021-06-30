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

/**
 *
 * @author 844568
 */
public class RoleService {

    public static void generateAllUsers(UserDB userDB, HttpServletRequest request) {
        try {
            List<User> users = userDB.getAll();
            request.setAttribute("users", users);
        } catch (Exception ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
