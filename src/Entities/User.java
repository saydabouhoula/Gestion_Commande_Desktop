/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author user
 */
public class User {

    private int id;
    private String email;
    public String roles;
    private String password;
    private String name;
    private String diplome;
    private String experience;
    private String image;
    private String reset_token;
    private boolean is_blocked;
    private boolean is_approuved;
    private String etat;
    //public static User Current_User;

    public User() {
    }

    public User(int id, String email, String roles, String password, String name, String image, String reset_token, boolean is_blocked, boolean is_approuved, String etat) {
        this.id = id;
        this.email = email;
        this.roles = roles;
        this.password = password;
        this.name = name;
        this.image = image;
        this.reset_token = reset_token;
        this.is_blocked = is_blocked;
        this.is_approuved = is_approuved;
        this.etat = etat;
    }

    public User(int id, String email, String roles, String name, String image, boolean is_blocked, boolean is_approuved, String etat) {
        this.id = id;
        this.email = email;
        this.roles = roles;
        this.name = name;
        this.image = image;
        this.is_blocked = is_blocked;
        this.is_approuved = is_approuved;
        this.etat = etat;
    }

    public User(String email, String password, String name, String image) {
        this.email = email;
        this.password = password;
        this.name = name;

        this.image = image;
    }

    public User(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    /*  public User(String email, String name, String password, String image) {
    this.email = email;
    this.password = password;
    this.name = name;
    this.image = image;
    }*/

    public User(String string, String string0, String string1, String string2, String string3, String string4, String string5) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiplome() {
        return diplome;
    }

    public void setDiplome(String diplome) {
        this.diplome = diplome;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getReset_token() {
        return reset_token;
    }

    public void setReset_token(String reset_token) {
        this.reset_token = reset_token;
    }

    public boolean isIs_blocked() {
        return is_blocked;
    }

    public void setIs_blocked(boolean is_blocked) {
        this.is_blocked = is_blocked;
    }

    public boolean isIs_approuved() {
        return is_approuved;
    }

    public void setIs_approved(boolean is_approuved) {
        this.is_approuved = is_approuved;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", email=" + email + ", roles=" + roles + ", password=" + password + ", name=" + name + ", image=" + image + ", reset_token=" + reset_token + ", is_blocked=" + is_blocked + ", is_approved=" + is_approuved + ", etat=" + etat + '}';
    }

}
