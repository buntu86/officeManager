package com.officeManager.data;

import com.officeManager.model.Mandat;
import com.officeManager.utils.Log;
import com.officeManager.utils.Tools;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

/**
 *
 * @author Adrien Pillonel
 */
public class Sql_listMandat {
    
    private Connection conn=null;
    
    private void connectToListMandat() {
        Path pathListMandat = ConfigVar.getListMandat();
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:" + pathListMandat);
        } catch (SQLException e) {
            Log.msg(1, "Sql_listMandat | Fail to create file " + pathListMandat + " | "+ e.getMessage());
        }        

        createStructureListMandat();
    }
    
    private void createStructureListMandat(){
        String sql = "CREATE TABLE IF NOT EXISTS 'ListMandats' (\n"
                + "`ID` INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                + "`numMandat` TEXT,\n"
                + "`nomMandat` TEXT,\n"
                + "`idStatut` INTEGER,\n"
                + "`idEntreprise` INTEGER,\n"
                + "`idArchitecte` INTEGER,\n"
                + "`idClient` INTEGER,\n"
                + "`dateDebut` INTEGER,\n"
                + "`dateArchive` INTEGER,\n"
                + "`numCarton` TEXT\n"
                + ");";

        try {
            Statement stmt  = conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            Log.msg(1, "Sql_listMandat | Fail creation listMandats.db | " + e.getMessage());
        }           
    }
    
    public ObservableList<Mandat> updateListMandats(String tris, String recherche) {
        String sql=null;
        ObservableList<Mandat> mandats = FXCollections.observableArrayList();
        connectToListMandat();
        recherche = recherche.trim();
        recherche = recherche.replace("'", "''");
        
        if(recherche.isEmpty())
            recherche = "";
        
        switch(tris){
            case "tous" :       sql = "SELECT * FROM ListMandats WHERE nomMandat LIKE '%" + recherche + "%' ORDER BY numMandat DESC";
                                Log.msg(0, "Sql_listMandat | sql -> all");
                                break;
            case "en cours"  :   sql = "SELECT * FROM ListMandats WHERE idStatut=0 AND nomMandat LIKE '%" + recherche + "%' ORDER BY numMandat DESC";
                                Log.msg(0, "Sql_listMandat | sql -> enCours");
                                break;
            case "archive"  :   sql = "SELECT * FROM ListMandats WHERE idStatut=1 AND nomMandat LIKE '%" + recherche + "%' ORDER BY numMandat DESC";
                                Log.msg(0, "Sql_listMandat | sql -> archive");
                                break;
            default:            sql = "SELECT * FROM ListMandats WHERE nomMandat LIKE '%" + recherche + "%' ORDER BY numMandat DESC";
                                Log.msg(1, "Sql_listMandat | sql -> all by default");
                                break;                    
        }
        
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            Log.msg(0, "Sql_listMandat | ResultSet sql: " + sql);
            
            while(rs.next()){
                mandats.add(new Mandat(rs.getInt("ID"), 
                        rs.getInt("dateDebut"), 
                        rs.getInt("dateArchive"),
                        rs.getInt("idEntreprise"), 
                        rs.getInt("idArchitecte"), 
                        rs.getInt("idClient"), 
                        rs.getInt("idStatut"), 
                        rs.getString("numMandat"), 
                        rs.getString("nomMandat"), 
                        rs.getString("numCarton")));
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        
        if(mandats.isEmpty())
            Log.msg(1, "Sql_listMandat | mandats is empty");
        
        return mandats;
    }
    
    public Mandat getMandatById(int id){
        Mandat mandat = null;

        if(id>0)
        {
            String sql=null;
            connectToListMandat();
            try{
                Statement stmt = conn.createStatement();
                sql = "SELECT * FROM ListMandats WHERE ID=" + id;
                ResultSet rs = stmt.executeQuery(sql);
                Log.msg(0, "Sql_listMandat | ResultSet sql: " + sql);

                while(rs.next()){
                    mandat = new Mandat(rs.getInt("ID"), 
                            rs.getInt("dateDebut"), 
                            rs.getInt("dateArchive"),
                            rs.getInt("idEntreprise"), 
                            rs.getInt("idArchitecte"), 
                            rs.getInt("idClient"), 
                            rs.getInt("idStatut"), 
                            rs.getString("numMandat"), 
                            rs.getString("nomMandat"), 
                            rs.getString("numCarton"));
                }
            }
            catch(SQLException e){
                System.out.println(e.getMessage());
            }        
        }
        
        return mandat;
    }

    //http://alvinalexander.com/blog/post/jdbc/sample-jdbc-preparedstatement-sql-update
    public boolean update(Mandat mandat) {
        if(mandat!=null && !mandat.getNumMandat().trim().isEmpty()){
            connectToListMandat();
            try{
                PreparedStatement pstmt = conn.prepareStatement("UPDATE ListMandats SET "
                        + "numMandat=?, " //01
                        + "nomMandat=?, " //02
                        + "idStatut=?, " //03
                        + "idEntreprise=?, " //04
                        + "idArchitecte=?, " //05
                        + "idClient=?, " //06
                        + "dateDebut=?, " //07
                        + "dateArchive=?, " //08
                        + "numCarton=? " //09
                        + "WHERE ID=?"); //10
                pstmt.setString(1, mandat.getNumMandat());
                pstmt.setString(2, mandat.getNomMandat());
                pstmt.setInt(3, mandat.getIdStatut());
                pstmt.setInt(4, mandat.getIdEntreprise());
                pstmt.setInt(5, mandat.getIdArchitecte());
                pstmt.setInt(6, mandat.getIdClient());
                pstmt.setInt(7, Tools.convertStringToInt(mandat.getDateDebut()));
                pstmt.setInt(8, mandat.getDateArchive());
                pstmt.setString(9, mandat.getNumCarton());
                pstmt.setInt(10, mandat.getIdMandat());
                pstmt.executeUpdate();
                pstmt.close();
                
                Log.msg(0, "Sql_listMandat | PreparedStatement sql");
                Log.msg(0, "dateDebut : " + mandat.getDateDebut());
                return true;
            }
            catch(SQLException e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Editer mandat");
                alert.setHeaderText(null);
                alert.setContentText("Problème avec la requête SQL : " + e.getMessage());
                alert.showAndWait(); 
                return false;
            }
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Editer mandat");
            alert.setHeaderText(null);
            alert.setContentText("Le numéro de mandat est obligatoire.");
            alert.showAndWait(); 
            return false;
        }
    }

    public boolean add(Mandat mandat) {
        if(mandat!=null && !mandat.getNumMandat().trim().isEmpty()){
            connectToListMandat();
            try{
                PreparedStatement pstmt = conn.prepareStatement("INSERT INTO ListMandats ("
                        + "numMandat, "
                        + "nomMandat, "
                        + "idStatut, "
                        + "idEntreprise, "
                        + "idArchitecte, "
                        + "idClient, "
                        + "dateDebut, "
                        + "dateArchive, "
                        + "numCarton) VALUES("
                        + "?, " //01
                        + "?, " //02
                        + "?, " //03
                        + "?, " //04
                        + "?, " //05
                        + "?, " //06
                        + "?, " //07
                        + "?, " //08
                        + "?)"); //09
                pstmt.setString(1, mandat.getNumMandat());
                pstmt.setString(2, mandat.getNomMandat());
                pstmt.setInt(3, mandat.getIdStatut());
                pstmt.setInt(4, mandat.getIdEntreprise());
                pstmt.setInt(5, mandat.getIdArchitecte());
                pstmt.setInt(6, mandat.getIdClient());
                pstmt.setInt(7, Tools.convertStringToInt(mandat.getDateDebut()));
                pstmt.setInt(8, mandat.getDateArchive());
                pstmt.setString(9, mandat.getNumCarton());
                pstmt.executeUpdate();
                pstmt.close();
                
                Log.msg(0, "Sql_listMandat | PreparedStatement sql");
                Log.msg(0, "dateDebut : " + mandat.getDateDebut());
                return true;
            }
            catch(SQLException e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ajouter mandat");
                alert.setHeaderText(null);
                alert.setContentText("Problème avec la requête SQL : " + e.getMessage());
                alert.showAndWait(); 
                return false;
            }
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ajouter mandat");
            alert.setHeaderText(null);
            alert.setContentText("Le numéro de mandat est obligatoire.");
            alert.showAndWait(); 
            return false;
        }
    }   

    public void del(int idMandat){
        if(idMandat!=0){
            connectToListMandat();
            try{
                PreparedStatement pstmt = conn.prepareStatement("DELETE FROM ListMandats WHERE ID=?"); //01
                pstmt.setInt(1, idMandat);
                pstmt.executeUpdate();
                pstmt.close();  
            }          
            catch(SQLException e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Supprimer mandat");
                alert.setHeaderText(null);
                alert.setContentText("Problème avec la requête SQL : " + e.getMessage());
                alert.showAndWait();                 
            }
        }
    }
}
