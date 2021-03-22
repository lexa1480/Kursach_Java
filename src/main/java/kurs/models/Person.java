package kurs.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


public class Person {
    private int id;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String name;

    @NotEmpty(message = "Surname should not be empty")
    @Size(min = 2, max = 30, message = "Surname should be between 2 and 30 characters")
    private String surname;

    @NotEmpty(message = "Login should not be empty")
    @Size(min = 2, max = 30, message = "Login should be between 2 and 30 characters")
    private String login;

    @NotEmpty(message = "Password should not be empty")
    @Size(min = 2, max = 30, message = "Password should be between 2 and 30 characters")
    private String password;

    public Person() {

    }

    public Person(int id, String name, String surname, String login, String password) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) { this.surname = surname; }

    public String getLogin() { return login; }

    public void setLogin(String login) { this.login = login; }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}