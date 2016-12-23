package com.officeManager.model;

/**
 *
 * @author adrien.pillonel
 */
public class Entreprise {
    private int ID, idCatEntreprise;
    private String denominationEntreprise, nom1Entreprise, nom2Entreprise, rueEntreprise, villeEntreprise, telEntreprise;

    public Entreprise(int ID, int idCatEntreprise, String denominationEntreprise, String nom1Entreprise, String nom2Entreprise, String rueEntreprise, String villeEntreprise, String telEntreprise) {
        this.ID = ID;
        this.idCatEntreprise = idCatEntreprise;
        this.denominationEntreprise = denominationEntreprise;
        this.nom1Entreprise = nom1Entreprise;
        this.nom2Entreprise = nom2Entreprise;
        this.rueEntreprise = rueEntreprise;
        this.villeEntreprise = villeEntreprise;
        this.telEntreprise = telEntreprise;
    }

    public int getID() {
        return ID;
    }
    public void setID(int ID) {
        this.ID = ID;
    }

    public int getIdCatEntreprise() {
        return idCatEntreprise;
    }
    public void setIdCatEntreprise(int idCatEntreprise) {
        this.idCatEntreprise = idCatEntreprise;
    }    
    
    public String getDenominationEntreprise() {
        return denominationEntreprise;
    }
    public void setDenominationEntreprise(String denominationEntreprise) {
        this.denominationEntreprise = denominationEntreprise;
    }
    
    public String getNom1Entreprise() {
        return nom1Entreprise;
    }
    public void setNom1Entreprise(String nom1Entreprise) {
        this.nom1Entreprise = nom1Entreprise;
    }
    
    public String getNom2Entreprise() {
        return nom2Entreprise;
    }
    public void setNom2Entreprise(String nom2Entreprise) {
        this.nom2Entreprise = nom2Entreprise;
    }
    
    public String getRueEntreprise() {
        return rueEntreprise;
    }
    public void setRueEntreprise(String rueEntreprise) {
        this.rueEntreprise = rueEntreprise;
    }

    public String getVilleEntreprise() {
        return villeEntreprise;
    }
    public void setVilleEntreprise(String villeEntreprise) {
        this.villeEntreprise = villeEntreprise;
    }

    public String getTelEntreprise() {
        return telEntreprise;
    }
    public void setTelEntreprise(String telEntreprise) {
        this.telEntreprise = telEntreprise;
    }
}
