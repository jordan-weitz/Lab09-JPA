/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import models.User;

public class UserService {

    public User addNewUser(String email, boolean active, String firstName, String lastName, String password, int role) {
        boolean validFields = allFieldsAreValid(email, active, firstName, lastName, password);
        if (validFields) {
            return new User(email, true, firstName, lastName, password, role);
        }
        return null;

    }

    private boolean allFieldsAreValid(String email, boolean active, String firstName, String lastName, String password) {

        if (email != null && !email.equals("") && firstName != null && !firstName.equals("") && lastName != null && !lastName.equals("") && password != null && !password.equals("") && active == true) {
            return true;
        } else {
            return false;
        }
    }

}
