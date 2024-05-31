package Entities;

import java.sql.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author houss
 */
public class Commande {
    public int id,user_id,cc_last;
    public String status,card,payment_method,nom_user,payement_brand,payment_status;
    public Date date_creation;
    public User user;
 
   
    

    public Commande() {
    }

    public Commande(int id, int user_id,Date date_creation, int cc_last, String status, String card, String payement_brand, String payment_status,String payment_method) {
        this.id = id;
        this.user_id = user_id;
        this.cc_last = cc_last;
        this.status = status;
        this.card = card;
        this.payment_method=payment_method;
        this.payement_brand = payement_brand;
        this.payment_status = payment_status;
        this.date_creation= date_creation;
    }

    public Commande(int user_id,Date date_creation, int cc_last, String status, String card, String payement_brand, String payment_status,String payment_method) {
        this.user_id = user_id;
        this.cc_last = cc_last;
        this.status = status;
        this.card = card;
        this.payement_brand = payement_brand;
        this.payment_method=payment_method;
        this.payment_status = payment_status;
        this.date_creation= date_creation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getNom_user() {
        return nom_user;
    }

    public void setNom_user(String nom_user) {
        this.nom_user = nom_user;
    }

    
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getCc_last() {
        return cc_last;
    }

    public void setCc_last(int cc_last) {
        this.cc_last = cc_last;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getPayement_brand() {
        return payement_brand;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }
    
    

    public void setPayement_brand(String payement_brand) {
        this.payement_brand = payement_brand;
    }

    public String getPayment_status() {
        return payment_status;
    }

    public void setPayment_status(String payment_status) {
        this.payment_status = payment_status;
    }

    public Date getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(Date date_creation) {
        this.date_creation = date_creation;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    

    @Override
    public String toString() {
        return "Commande{" + "id=" + id + ", user_id=" + user_id + ", cc_last=" + cc_last + ", status=" + status + ", card=" + card + ", payement_brand=" + payement_brand + ", payment_status=" + payment_status + '}';
    }

    
    
    
    
}
