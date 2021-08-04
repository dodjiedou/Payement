/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Salaire;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author GOUNTENI
 */
public class Connexion {
     private static Connection connect;
    private static Statement state;
    private static   PreparedStatement prepare;
    private String username="root";
    private String password="";
    String url = "jdbc:mysql://localhost:3306/payement";
    
    
    private Connexion(){
        
        try{
         Class.forName("com.mysql.jdbc.Driver");
         connect=DriverManager.getConnection(url,username,password);
        /* String url="jdbc:sqlite:C:\\Program Files (x86)\\Paie\\database\\payement.db";
         Class.forName("org.sqlite.JDBC");
         String url="jdbc:sqlite:C:\\database\\payement.db";
         connect=DriverManager.getConnection(url);*/
         
        }catch(SQLException|ClassNotFoundException ex){
           Logger.getLogger(Connexion.class.getName()).log(Level.SEVERE,null,ex);
           
       }
    }
    
    private static Connection getConnection(){
        
        if(connect==null){
            new Connexion();
        } 
        return connect;
            
    }
    
    private static Statement getStatement(){
        try{
            return getConnection().createStatement();
        }catch(SQLException ex){
            Logger.getLogger(Connexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
         
     
     public static Executant  ajouterExec(Executant executan){
        try{
            String sql="INSERT INTO executant (nom_exec,nbre_heure_exec, tarif_exec, salaire_exec) VALUES (?,?,?,?)";
            prepare = getConnection().prepareStatement(sql);
            prepare.setString(1,executan.getNom());
            prepare.setInt(2,executan.getNbHeures());
            prepare.setDouble(3,executan.getPrixHeure());
            prepare.setDouble(4,executan.getSalaire());
            prepare.executeUpdate();
           // prepare.close();
            return executan;
            
          
            
        }catch(SQLException ex){
            Logger.getLogger(Connexion.class.getName()).log(Level.SEVERE,null,ex);
        }
        return null;
    }
     
     public static Commercial  ajouterCom(Commercial  commercial ){
        try{
            String sql="INSERT INTO commercial (nom_com, somme_fixe, chiffre_affaire, salaire_com) VALUES (?,?,?,?)";
            prepare = getConnection().prepareStatement(sql);
            prepare.setString(1,commercial.getNom());
            prepare.setDouble(2,commercial.getSommeFixe());
            prepare.setDouble(3,commercial.getChiffAff());
            prepare.setDouble(4,commercial.getSalaire());
            prepare.executeUpdate();
           // prepare.close();
            return commercial;
            
          
            
        }catch(SQLException ex){
            Logger.getLogger(Connexion.class.getName()).log(Level.SEVERE,null,ex);
        }
        return null;
    }
     
     public static Manager  ajouterMan(Manager manager){
        try{
            String sql="INSERT INTO manager (nom_man, nbre_heure_man, tarif_man, salaire_man) VALUES (?,?,?,?)";
            prepare = getConnection().prepareStatement(sql);
            prepare.setString(1,manager.getNom());
            prepare.setInt(2,manager.getNbHeures());
            prepare.setDouble(3,manager.getPrixHeure());
            prepare.setDouble(4,manager.getSalaire());
            prepare.executeUpdate();
           // prepare.close();
            return manager;
            
          
            
        }catch(SQLException ex){
            Logger.getLogger(Connexion.class.getName()).log(Level.SEVERE,null,ex);
        }
        return null;
    }
     
     
     public static Employe ajouterEmp(Employe employe){
        try{
             int i=0;
          String req ;
            switch (employe.getClass().getName()) {
                case "Salaire.Executant":
                    req="SELECT id_exec FROM executant ";
                    req+= "ORDER BY id_exec DESC ";
                    req+= " LIMIT 0,1";
                    break;
                case "Salaire.Commercial":
                    req="SELECT id_com FROM commercial ";
                    req+= "ORDER BY id_com DESC ";
                    req+= " LIMIT 0,1";
                    break;
                default:
                    req="SELECT id_man FROM manager ";
                    req+= "ORDER BY id_man DESC ";
                    req+= " LIMIT 0,1";
                    break;
            }
                 
            ResultSet result =getStatement().executeQuery(req);
            while (result.next()) {
                i=result.getInt(1);
            }
            String sql="INSERT INTO employe(idEmp,nom,salaire,type) VALUES(?,?,?,?);";  
            
             switch (employe.getClass().getName()) {
                case "Salaire.Executant":
                   prepare = getConnection().prepareStatement(sql);
                   prepare.setInt(1,i);
                   prepare.setString(2,employe.getNom());
                   prepare.setDouble(3,employe.getSalaire());
                   prepare.setString(4,"Executant");
                   prepare.executeUpdate();
                    break;
                case "Salaire.Commercial":
                   prepare = getConnection().prepareStatement(sql);
                   prepare.setInt(1,i);
                   prepare.setString(2,employe.getNom());
                   prepare.setDouble(3,employe.getSalaire());
                   prepare.setString(4,"Commercial");
                   prepare.executeUpdate();
                    break;
                default:
                    prepare = getConnection().prepareStatement(sql);
                    prepare.setInt(1,i);
                    prepare.setString(2,employe.getNom());
                    prepare.setDouble(3,employe.getSalaire());
                    prepare.setString(4,"Manager");
                    prepare.executeUpdate();
                    break;
            }
            
            return employe;
            
          
            
        }catch(SQLException ex){
            Logger.getLogger(Connexion.class.getName()).log(Level.SEVERE,null,ex);
        }
        return null;
    }
     
       
       public static List<Employe> list(){
        List<Employe> employe =new ArrayList<>();
        Employe emp;
       
        try {
            
                String req="SELECT nom,salaire, type FROM employe";
                ResultSet res=getStatement().executeQuery(req);
                while(res.next()){
                    emp=new Employe(
                    res.getString("nom"),
                    res.getDouble("salaire"),
                    res.getString("type"));
                    employe.add(emp);
                }
                return employe;
                    
         
        } catch (SQLException ex) {
            Logger.getLogger(Connexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
       
       public static Executant edit(Executant exec){
        try {
            //Exo concatenez les valeur de numero et fret ... a partir de l'objet navire
            
            String sql="UPDATE executant SET nom=?,nbrHeure=?,prixHoraire=?,salaire=? WHERE nom=?";
            prepare =getConnection().prepareStatement(sql);
            prepare.setString(1, exec.getNom());
            prepare.setInt(2, exec.getNbHeures());
            prepare.setDouble(3, exec.getPrixHeure());
            prepare.setDouble(4, exec.getSalaire());
            prepare.setString(5, exec.getNom());
            prepare.executeUpdate();
            String req="UPDATE employe SET salaire=? WHERE nom=?";
            PreparedStatement p;
            p=getConnection().prepareStatement(req);
            p.setDouble(1, exec.getSalaire());
            p.setString(2, exec.getNom());
            p.executeUpdate();
            return exec;
        } catch (SQLException ex) {
            Logger.getLogger(Connexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
       
       public static boolean delet(String nom){
        try {
            
            String sql="DELETE FROM executant WHERE nom=?";
            prepare =getConnection().prepareStatement(sql);
            prepare.setString(1, nom);
            prepare.executeUpdate();
            String req="DELETE FROM employe WHERE nom=?";
            PreparedStatement p;
            p=getConnection().prepareStatement(req);
            p.setString(1, nom);
            p.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Connexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public static boolean delete(Executant exec){
      return delet(exec.getNom());
    }
}