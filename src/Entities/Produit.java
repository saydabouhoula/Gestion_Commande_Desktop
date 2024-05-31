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
public class Produit {
    public int id,taux_remise,stock;
    public CategorieProduit categ_produit;
    public String nom,img,descr,categ,marque,model;
    public Double prix;

    public Produit() {
    }

    public Produit(int id, int taux_remise, int stock, CategorieProduit categ_produit, String nom, String img, String descr, String categ, String marque, String model, Double prix) {
        this.id = id;
        this.taux_remise = taux_remise;
        this.stock = stock;
        this.categ_produit = categ_produit;
        this.nom = nom;
        this.img = img;
        this.descr = descr;
        this.categ = categ;
        this.marque = marque;
        this.model = model;
        this.prix = prix;
    }

    public Produit(int taux_remise, int stock, CategorieProduit categ_produit, String nom, String img, String descr, String categ, String marque, String model, Double prix) {
        this.taux_remise = taux_remise;
        this.stock = stock;
        this.categ_produit = categ_produit;
        this.nom = nom;
        this.img = img;
        this.descr = descr;
        this.categ = categ;
        this.marque = marque;
        this.model = model;
        this.prix = prix;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTaux_remise() {
        return taux_remise;
    }

    public void setTaux_remise(int taux_remise) {
        this.taux_remise = taux_remise;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public CategorieProduit getCateg_produit() {
        return categ_produit;
    }

    public void setCateg_produit(CategorieProduit categ_produit) {
        this.categ_produit = categ_produit;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getCateg() {
        return categ;
    }

    public void setCateg(String categ) {
        this.categ = categ;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Produit{" + "id=" + id + ", taux_remise=" + taux_remise + ", stock=" + stock + ", categ_produit=" + categ_produit + ", nom=" + nom + ", img=" + img + ", descr=" + descr + ", categ=" + categ + ", marque=" + marque + ", model=" + model + ", prix=" + prix + '}';
    }
    
    
}
