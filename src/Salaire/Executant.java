/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Salaire;

/**
 *
 * @author GOUNTENI
 */
public class Executant extends Employe{
    private int nbre_heure;
    private double taux_horaire;

    public Executant(String nom,int nbre_heure, double taux_horaire ) {
        super(nom);
        this.nbre_heure = nbre_heure;
        this.taux_horaire = taux_horaire;
    }
    
    public int getNbHeures(){
        return nbre_heure;
    }
     
      public double getPrixHeure(){
        return taux_horaire;
    }
      
       public void setNbHeures(int heure){
        nbre_heure=heure;
    }
     
      public void setPrixHeure(double prix){
        taux_horaire=prix;
    }



    
    public double getSalaire(){
        
        salaire = taux_horaire * nbre_heure;
        
        if(nbre_heure > 39){
            int nbre;
            nbre = nbre_heure - 39;
            salaire += nbre * taux_horaire * 0.3; 
        }
        return salaire;
    }
        public void setInfoSalaire(int nbre_heure, double taux_horaire){
        this.taux_horaire = taux_horaire;
        this.nbre_heure = nbre_heure;
    }
        
            public Object[] toObject() {
        return new Object[]{ nom , getSalaire(), "Executant"};
    }
}
