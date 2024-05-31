package Entities;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author houss
 */
public class DetailCommande {
    public int id,commande_id,produit_id,qte;
    public String nom;
    public double prixUni,total;
    public Commande commande;
    public Produit produit;

    public DetailCommande() {
    }

    public DetailCommande(int id, int commande_id, int produit_id, int qte) {
        this.id = id;
        this.commande_id = commande_id;
        this.produit_id = produit_id;
        this.qte = qte;
    }

    public DetailCommande(int commande_id, int produit_id, int qte) {
        this.commande_id = commande_id;
        this.produit_id = produit_id;
        this.qte = qte;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCommande_id() {
        return commande_id;
    }

    public void setCommande_id(int commande_id) {
        this.commande_id = commande_id;
    }

    public int getProduit_id() {
        return produit_id;
    }

    public void setProduit_id(int produit_id) {
        this.produit_id = produit_id;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getPrixUni() {
        return prixUni;
    }

    public void setPrixUni(double prixUni) {
        this.prixUni = prixUni;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }
    
    
    
    

    @Override
    public String toString() {
        return "DetailCommande{" + "id=" + id + ", commande_id=" + commande_id + ", produit_id=" + produit_id + ", qte=" + qte + '}';
    }
    
    
    
}
