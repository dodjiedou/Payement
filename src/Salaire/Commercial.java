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
public class Commercial extends Employe {
    
    private double somme_fixe;
    private double chiff_aff;

    public Commercial( String nom,double somme_fixe, double chiff_aff) {
        super(nom);
        this.somme_fixe = somme_fixe;
        this.chiff_aff = chiff_aff;
    }
    
    public double getSommeFixe(){
        return somme_fixe;

    }
     
      public double getChiffAff(){
        return chiff_aff;
    }
      
      
   
    
    public double getSalaire(){
        return somme_fixe + 0.01*chiff_aff;
    }

    public Commercial(String nom) {
        super(nom);
    }

    
    public void setInfoSalaire(double somme_fixe, double chiff_aff){
        this.somme_fixe = somme_fixe;
        this.chiff_aff = chiff_aff;
    }
    
     public Object[] toObject() {
        return new Object[]{ nom , getSalaire(), "Commercial"};
    }
    
}
