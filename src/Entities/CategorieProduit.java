/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author BILEL
 */
public class CategorieProduit {
    private int id;
    private String descriptionCat,nomCat;
    
    public CategorieProduit(int id, String nomCat, String descriptionCat) {
        this.id = id;
        this.nomCat = nomCat;
        this.descriptionCat = descriptionCat;
    }

    public CategorieProduit(String nomCat, String descriptionCat) {
        this.nomCat = nomCat;
        this.descriptionCat = descriptionCat;
    }

    public CategorieProduit() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getnomCat() {
        return nomCat;
    }

    public void setnomCat(String nomCat) {
        this.nomCat = nomCat;
    }

    public String getDescriptionCat() {
        return descriptionCat;
    }

    public void setDescriptionCat(String descriptionCat) {
        this.descriptionCat = descriptionCat;
    }

    @Override
    public String toString() {
        return "Task{" + "id=" + id + ", nomCat=" + nomCat + ", descriptionCat=" + descriptionCat + "\n";
    }
    
    
}

