package models;

public class User {

    private String email;
    private boolean active = true;
    private String firstName;
    private String lastName;
    private String password;
    private int role;

    public User() {
    }

    public User(String email, boolean active, String firstName, String lastName, String password, int role) {
        this.email = email;
        this.active = active;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(int number) {
        if (number == 1) {
            this.active = true;

        } else {
            this.active = false;
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

}
