/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projecte;

/**
 *
 * @author alumne
 */
public class Cub {
        private String nom;
        private double preu;
        private boolean pegatines;
        private int nombreColors;
        private boolean omplit;
        
        
    public Cub() {
    }
    
    public Cub(String nom) {
        this.nom=nom;
    }
    
    public Cub(String nom, int preu) {
        this.nom=nom;
        this.preu=preu;
    }

    @Override
    public String toString() {
        return "\nNom=" + nom +
               "\nPreu=" + preu +
               "\nNombreColors=" + nombreColors+
                (pegatines?"\nTé pegatines":"\nNo té pegatines");
    }

    

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getPreu() {
        return preu;
    }

    public void setPreu(double preu) {
        if(preu<0) System.out.println("El preu no pot ser negatiu!!");
        else this.preu = preu;
    }

    public boolean isPegatines() {
        return pegatines;
    }

    public void setPegatines(boolean pegatines) {
        this.pegatines = pegatines;
    }

    public int getNombreColors() {
        return nombreColors;
    }

    public void setNombreColors(int nombreColors) {
        this.nombreColors = nombreColors;
    }

    public boolean isOmplit() {
        return omplit;
    }

    public void setOmplit(boolean omplit) {
        this.omplit = omplit;
    }

        
        
        
        
}