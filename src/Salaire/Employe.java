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
public  class Employe {
    protected String nom;
    protected double salaire;
    private String type;

    public Employe(String nom,double sal,String type) {
        this.nom = nom;
        this.salaire=sal;
        this.type=type;
    }
    
    public Employe(String nom) {
        this.nom = nom;
        
    }


    public String getNom() {
        return nom;
    }
    
     public String getType() {
        return type;
    }

      public double getSalaire() {
        return salaire;
    }

    
    public Object[] toObject() {
        return new Object[]{nom ,getSalaire(),type};
    }
    
}
