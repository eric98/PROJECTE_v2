/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projecte;

import java.util.Scanner;

/**
 *
 * @author alumne
 */
public class PROJECTE {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner ent = new Scanner(System.in);
        Scanner entString = new Scanner(System.in);
        String nom=null;
        double preu=0.0;
        boolean pegatines=false; //si te pegatines o no
        char tePegatines=' ';
        int nombreColors=0;
        int opcio=-1;
        boolean omplit=false;
        String nomE=null;
        double preuE=0.0;
        boolean pegatinesE=false; //si te pegatines o no
        int nombreColorsE=0;
        boolean cubNou=false;
        
        do {
            System.out.println("\nMenú de l'aplicació:");
            System.out.println("0. Sortir.");
            System.out.println("1. Introduïr cub de Rubik.");
            System.out.println("2. Borrar cub de Rubik.");
            System.out.println("3. Modificar cub de Rubik.");
            System.out.println("4. Llistar cubs de Rubik.");
            System.out.println("5. Recuperar Cub de Rubik anterior.\n");
            
            //System.out.println("\nTria una opció.");
            opcio=ent.nextInt();
            
            switch (opcio) {
                case 1:
                    if (omplit){
                        System.out.println("\nJa has introduït dades,"
                                + " si vols afegir-ne borra-les primer.");
                    }else{
                        System.out.println("\nIntrodueix el nom.");
                        nom=entString.nextLine();
                        System.out.println("Introdueix el preu.");
                        preu=ent.nextDouble();
                        System.out.println("Té pegatines?. (S/N)");
                        do{
                            tePegatines = ent.next().toUpperCase().charAt(0);  //usem toUpperCase() que traduix el text introduït per l'usuari a majúscules, 
                                                                               //per tant només haurem de tractar les lletres majúscules
                        }while(tePegatines != 'S' && tePegatines != 'N');
                        pegatines = (tePegatines == 'S');     //si tePegatines conté la 'S' home serà true i sinó false. Fa el mateix que un if_else però és molt més curt
                        System.out.println("Quants de colors té?");
                        nombreColors=ent.nextInt();
                        omplit=true;
                    }
                    break;
                case 2:
                    if (!omplit){
                        System.out.println("\nNo hi ha dades introduïdes,"
                                + " afegeix-ne primer.");
                    } else {
                        boolean resposta=false;
                        System.out.println("\nVols veure les dades del cub de Rubik?(S/N)");
                        for (char i =' '; i!= 'S'&& i!= 'N';) {
                            i = ent.next().toUpperCase().charAt(0);
                            resposta = (i=='S');
                        }
                        if (resposta){
                            System.out.println("\nDades del cub de Rubik");
                            System.out.println("Nom: "+nom);
                            System.out.println("Preu: "+preu);
                            if (pegatines)System.out.println("Té pegatines");
                            else System.out.println("No té pegatines");
                            System.out.println("Nombre de colors: "+nombreColors);
                        }
                        System.out.println("\nVols borrar les dades?(S/N)");
                        for (char i =' '; i!= 'S'&& i!= 'N';) {
                            i = ent.next().toUpperCase().charAt(0);
                            resposta = (i=='S');
                        }
                        if (resposta){
                            nomE=nom;
                            preuE=preu;
                            pegatinesE=pegatines;
                            nombreColorsE=nombreColors;
                            cubNou=true;
                            
                            nom=null;
                            preu=0.0;
                            pegatines=false;
                            tePegatines=' ';
                            nombreColors=0;
                            omplit=false;
                            System.out.println("\nCub de Rubik borrat correctament!");                            
                        }
                        
                        break;
                    }
                case 3:
                    if (!omplit){
                        System.out.println("\nNo hi ha dades per modificar,"
                                + " afegeix-ne primer.");
                    } else {
                        boolean resposta=false;
                        System.out.println("\nVols veure les dades del cub de Rubik?(S/N)");
                        for (char i =' '; i!= 'S'&& i!= 'N';) {
                            i = ent.next().toUpperCase().charAt(0);
                            resposta = (i=='S');
                        }
                        if (resposta){
                            System.out.println("\nDades del cub de Rubik");
                            System.out.println("Nom: "+nom);
                            System.out.println("Preu: "+preu);
                            if (pegatines)System.out.println("Té pegatines");
                            else System.out.println("No té pegatines");
                            System.out.println("Nombre de colors: "+nombreColors);
                        }
                        System.out.println("\nVols modificar el cub de Rubik?(S/N)");
                        for (char i =' '; i!= 'S'&& i!= 'N';) {
                            i = ent.next().toUpperCase().charAt(0);
                            resposta = (i=='S');
                        }
                        if (resposta){
                            nomE=nom;
                            preuE=preu;
                            pegatinesE=pegatines;
                            nombreColorsE=nombreColors;
                            cubNou=true;
                            System.out.println("\nNom: "+nom);
                            System.out.println("\nVols modificar el nom?(S/N)");
                            for (char i =' '; i!= 'S'&& i!= 'N';) {
                                i = ent.next().toUpperCase().charAt(0);
                                resposta = (i=='S');
                            }
                            if (resposta) {
                                System.out.println("\nNou nom:");
                                nom = entString.nextLine();
                            }
                            System.out.println("\nPreu: "+preu);
                            System.out.println("\nVols modificar el preu?(S/N)");
                            for (char i =' '; i!= 'S'&& i!= 'N';) {
                                i = ent.next().toUpperCase().charAt(0);
                                resposta = (i=='S');
                            }
                            if (resposta) {
                                System.out.println("\nNou preu:");
                                preu=ent.nextDouble();
                            }
                            if (pegatines)System.out.println("\nTé pegatines");
                            else System.out.println("\nNo té pegatines");
                            System.out.println("\nVols modificar si té pegatines?(S/N)");
                            for (char i =' '; i!= 'S'&& i!= 'N';) {
                                i = ent.next().toUpperCase().charAt(0);
                                resposta = (i=='S');
                            }
                            if (resposta) {
                                System.out.println("\nTé pegatines?(S/N)");
                                do{
                                    tePegatines = ent.next().toUpperCase().charAt(0);
                                }while(tePegatines != 'S' && tePegatines != 'N');
                                pegatines = (tePegatines == 'S');
                            }
                            System.out.println("\nNombre de colors: "+nombreColors);
                            System.out.println("\nVols modificar el nombre de colors?(S/N)");
                            for (char i =' '; i!= 'S'&& i!= 'N';) {
                                i = ent.next().toUpperCase().charAt(0);
                                resposta = (i=='S');
                            }
                            if (resposta) {
                                System.out.println("\nNou nombre de colors:");
                                nombreColors=ent.nextInt();
                            }                                                                          
                        }
                    }
                    break;                    
                case 4:
                    if (!omplit){
                        System.out.println("\nNo hi ha dades per mostrar,"
                                + " afegeix-ne primer.");
                    } else {
                        System.out.println("\nDades del cub de Rubik");
                        System.out.println("Nom: "+nom);
                        System.out.println("Preu: "+preu);
                        if (pegatines)System.out.println("Té pegatines");
                        else System.out.println("No té pegatines");
                        System.out.println("Nombre de colors: "+nombreColors);
                    }
                    break;
                case 5:
                    if (!cubNou) System.out.println("No hi ha dades per recuperar!");
                    else {
                        boolean resposta=false;
                        System.out.println("\nVols veure el cub de Rubik afegit anteriorment?(S/N)");
                        for (char i =' '; i!= 'S'&& i!= 'N';) {
                            i = ent.next().toUpperCase().charAt(0);
                            resposta = (i=='S');
                        }
                        if (resposta) {
                                System.out.println("\nDades del cub de Rubik");
                                System.out.println("Nom: "+nomE);
                                System.out.println("Preu: "+preuE);
                                if (pegatinesE)System.out.println("Té pegatines");
                                else System.out.println("No té pegatines");
                                System.out.println("Nombre de colors: "+nombreColorsE);
                        }
                        System.out.println("\nVols recuperar aquestes dades?(S/N)");
                        for (char i =' '; i!= 'S'&& i!= 'N';) {
                            i = ent.next().toUpperCase().charAt(0);
                            resposta = (i=='S');
                        }
                        if (resposta) {
                            nom=nomE;
                            preu=preuE;
                            pegatines=pegatinesE;
                            nombreColors=nombreColorsE;
                        }
                        
                    }
                    break;
                    
                    
                    
            }
        } while (opcio!=0);
    }

}
