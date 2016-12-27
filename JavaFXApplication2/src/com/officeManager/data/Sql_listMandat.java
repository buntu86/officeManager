package com.officeManager.data;

import com.officeManager.model.Mandat;
import com.officeManager.utils.Log;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Adrien Pillonel
 */
public class Sql_listMandat {
    
    private Connection conn=null;
    
    private void connectToListMandat() {
        Path pathListMandat = Paths.get("resources/listMandats.db");
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
            case "tous" :        sql = "SELECT * FROM ListMandats WHERE nomMandat LIKE '%" + recherche + "%' ORDER BY numMandat DESC";
                                Log.msg(0, "Sql_listMandat | sql -> all");
                                break;
            case "en cours"  :   sql = "SELECT * FROM ListMandats WHERE idStatut=0 AND nomMandat LIKE '%" + recherche + "%' ORDER BY numMandat DESC";
                                Log.msg(0, "Sql_listMandat | sql -> enCours");
                                break;
            case "archive"  :   sql = "SELECT * FROM ListMandats WHERE idStatut=1 AND nomMandat LIKE '%" + recherche + "%' ORDER BY numMandat DESC";
                                Log.msg(0, "Sql_listMandat | sql -> archive");
                                break;
            default:            sql = "SELECT * FROM ListMandats AND nomMandat LIKE '%" + recherche + "%' ORDER BY numMandat DESC";
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
}
