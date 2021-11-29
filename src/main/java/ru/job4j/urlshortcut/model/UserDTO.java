package ru.job4j.urlshortcut.model;

public class UserDTO {
    private boolean registration = true;
    private String login;
    private String password;

    public static UserDTO of(String site, String pwd) {
        UserDTO userDTO = new UserDTO();
        userDTO.login = site;
        userDTO.password = pwd;
        return userDTO;
    }

    public boolean isRegistration() {
        return registration;
    }

    public void setRegistration(boolean registration) {
        this.registration = registration;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
