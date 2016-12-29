package com.officeManager.model;

import old.Statut;
import com.officeManager.utils.Tools;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author adrien.pillonel
 */
public class Mandat {
    private final StringProperty statutProperty, numProperty, nomProperty, dateDebutProperty, dateArchiveProperty, numCartonProperty;
    private final IntegerProperty idMandat, idEntreprise, idArchitecte, idClient, idStatut;
    
    public Mandat()
    {
        this(0,0,0,0,0,0,0,null,null,null);
    }
            
    public Mandat(int idMandat, int dateDebut, int dateArchive, int idEntreprise, int idArchitecte, int idClient, int idStatut, String numMandat, String nomMandat, String numCarton) {
        if(dateArchive==0)
            this.dateArchiveProperty = new SimpleStringProperty();
        else
            this.dateArchiveProperty = new SimpleStringProperty(Tools.convertIntToString(dateArchive));
        
        this.idMandat = new SimpleIntegerProperty(idMandat);
        this.idEntreprise = new SimpleIntegerProperty(idEntreprise);
        this.idArchitecte = new SimpleIntegerProperty(idArchitecte);
        this.idClient = new SimpleIntegerProperty(idClient);
        this.idStatut = new SimpleIntegerProperty(idStatut);

        this.statutProperty = new SimpleStringProperty(Tools.getStatutById(getIdStatut()));
        this.numProperty = new SimpleStringProperty(numMandat);
        this.nomProperty = new SimpleStringProperty(nomMandat);
        this.dateDebutProperty = new SimpleStringProperty(Tools.ConvertDateToLisible(Tools.convertIntToString(dateDebut)));
        this.numCartonProperty = new SimpleStringProperty(numCarton);        
    }

    //Statut
    public StringProperty statutProperty() {
        return statutProperty;
    }
    public String getStatut() {
        return statutProperty.get();
    }
    public void setStatut(int idStatut) {
        setIdStatut(idStatut);
        this.statutProperty.set(Statut.getStatutById(idStatut));
    }

    //NumMandat
    public StringProperty numMandatProperty() {
        return numProperty;
    }
    public String getNumMandat() {
        return numProperty.get();
    }
    public void setNumMandat(String numMandat) {
        this.numProperty.set(numMandat);
    }

    //NomMandat
    public StringProperty nomMandatProperty() {
        return nomProperty;
    }
    public String getNomMandat() {
        return nomProperty.get();
    }
    public void setNomMandat(String nomMandat) {
        this.nomProperty.set(nomMandat);
    }

    //DateDebut
    public StringProperty dateDebutProperty() {
        return dateDebutProperty;
    }
    public String getDateDebut() {
        return dateDebutProperty.get();
    }
    public String getDateDebutLisible() {
        return Tools.ConvertDateToLisible(dateDebutProperty.get());
    }
    public void setDateDebut(String dateLisible) {
        this.dateDebutProperty.set(Tools.ConvertDateToSecond(dateLisible));
    }

    //DateAchive
    public StringProperty dateArchiveProperty() {
        return dateArchiveProperty;
    }
    public int getDateArchive() {
        return Tools.convertStringToInt(dateArchiveProperty.get());
    }
    public void setDateArchive(String dateArchive) {
        this.dateArchiveProperty.set(dateArchive);
    }

    //NumCarton
    public StringProperty numCartonProperty() {
        return numCartonProperty;
    }
    public String getNumCarton() {
        return numCartonProperty.get();
    }
    public void setNumCarton(String numCarton) {
        this.numCartonProperty.set(numCarton);
    }

    //idStatut
    public IntegerProperty idStatutProperty() {
        return idStatut;
    }
    public int getIdStatut(){
        return idStatut.get();
    }
    private void setIdStatut(int idStatut) {
        this.idStatut.set(idStatut);
    }        
    
    //idMandat
    private IntegerProperty idMandatProperty() {
        return idMandat;
    }
    public int getIdMandat(){
        return idMandat.get();
    }
    private void setIdMandat(int idMandat) {
        this.idMandat.set(idMandat);
    }
    
    //idArchi
    private IntegerProperty idArchitecteProperty() {
        return idArchitecte;
    }
    public int getIdArchitecte(){
        return idArchitecte.get();
    }
    private void setIdArchitecte(int idArchitecte) {
        this.idArchitecte.set(idArchitecte);
    }

    //idEntreprise
    private IntegerProperty idEntrepriseProperty() {
        return idEntreprise;
    }
    public int getIdEntreprise(){
        return idEntreprise.get();
    }
    private void setIdEntreprise(int idEntreprise) {
        this.idEntreprise.set(idEntreprise);
    }    
    
    //idClient
    private IntegerProperty idClientProperty() {
        return idClient;
    }
    public int getIdClient(){
        return idClient.get();
    }
    private void setIdClient(int idClient) {
        this.idClient.set(idClient);
    }    
}
