/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projecte;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

/**
 *
 * @author alumne
 */
public class PROJECTE {

    //Número de caselles màxim de l'array
    private static final int MAX_CUBS = 5;
    //Array on guardarem la informació dels pilots
    private static Cub[] array = new Cub[MAX_CUBS];
    //Opció triada per l'usuari
    private static int opcio;
    //Fitxer usat per persistir la informació
    private static File fitxer=new File("cubs.db");
    
    public static Cub[] getArray() {
        return array;
    }

    /**
     * @param args the command line arguments
     */
//    public static void main(String[] args) throws IOException {
    public static void main(String[] args){
        // TODO code application logic here
        inicialitzarVariables();
        do {
            demanarOpcio();
            tractarOpcio();
        } while (!opcioFinal());
    }

    public static void inicialitzarVariables() {
        //Índex per recòrrer l'array
        int i=0;
        
        //Busquem el fitxer, i si existix el tractem
        if(fitxer.exists()){
            //L'usem per si no caben els objectes del fitxer a l'array poder finalitzar l'execució
            boolean acabar=false;
            
            //Obrim el fitxer per lectura
            ObjectInputStream lectura=null;
            try{
                //Obrim el fitxer per lectura
                lectura=new ObjectInputStream(new BufferedInputStream(new FileInputStream(fitxer)));
                
                while(true){
                    array[i]=(Cub) lectura.readObject();
                    //Incrementar la i per separat ja que sinó dixem una casella a null
                    i++;
                }
            } catch (ArrayIndexOutOfBoundsException ex) {
                //Si entrem aquí és per que al fitxer hi ha més pilots que els que caben a l'array. 
                //Podríem avisar a l'usuari i dixar que tanque l'aplicació ja que sinó pot perdre dades...
                System.err.println("Atenció, no caben tots els objectes. Si continues pots perdre dades. Vols continuar?(S/N):");
                Scanner ent = new Scanner(System.in);
                char siNo=' ';
                do {                    
                    siNo = ent.skip("[\r\n]*").nextLine().toUpperCase().charAt(0); //usem toUpperCase() que traduix el text introduït per l'usuari a majúscules, 
                    //per tant només haurem de tractar les lletres majúscules
                } while (siNo != 'S' && siNo != 'N');
                if(siNo=='N') acabar=true;
                
            } catch (IOException ex) {
                //Aquí no cal fer res ja que significa que hem arribat al final del fitxer
            } catch (ClassNotFoundException ex) {
                //Aquí tampoc cal fer res ja que significa que el fitxer llegit no conté objectes de la classe Pilot
            }finally{
                try {
                    //Molt important tancar el fitxer de lectura
                    if(lectura!=null) lectura.close();
                } catch (IOException ex) {
                    //No cal mostrar res
                }
                //Si hem decidit acabar parem l'execucuió
                if(acabar) System.exit(0);
            }
        
        }
        //Acabem d'omplir l'array en nous pilots sense dades
        for (; i < array.length; i++) {
            array[i] = new Cub();
            array[i].setOmplit(false);
        }
    }
    public static void demanarOpcio() {
        Scanner ent = new Scanner(System.in);
        do {            
            System.out.println("\nMenú de l'aplicació:");
            System.out.println("0. Sortir.");
            System.out.println("1. Introduïr cub de Rubik.");
            System.out.println("2. Borrar cub de Rubik.");
            System.out.println("3. Modificar cub de Rubik.");
            System.out.println("4. Llistar cubs de Rubik.");
            System.out.println("5. Recuperar Cub de Rubik anterior.\n");
            System.out.println("Tria una opció:");
            try {
                opcio = ent.skip("[\r\n]*").nextInt();
                break;
            } catch (java.util.InputMismatchException e) {
                System.out.println("\nOpció incorrecta!!");
                ent.next();
            }
        } while (true);
        
    }

    public static void tractarOpcio() {

        switch (opcio) {
            case 0:                             //0. Sortir
                System.out.println("Adéu!!");
                break;
            case 1:                             //1. Introduïr cub
                introduirCub();
                break;
            case 2:                             //2. Modificar cub
                modificarCub();
                break;
            case 3:                                     //3. Borrar cub
                borrarCub();
                break;
            case 4:                                     //4. Llistar cubs
                llistarCubs();
                break;
            case 5:                                     //5. Recuperar cub borrat
                recuperarCub();
                break;
            default:
                System.out.println("\nOpció incorrecta!!");
        }
    }

    public static boolean opcioFinal() {
        return opcio == 0;
    }

    public static void introduirCub() {

        Scanner ent = new Scanner(System.in);

        //Primer recorrem l'array fins trobar una casella no omplida o arribar al seu final
        int i;
        for (i = 0; i < array.length && array[i].isOmplit(); i++);
        //Si no hem arribat al final és per que hem trobat una casella buida (no omplida)
        if (i < array.length) {
            System.out.println("\nIntrodueix el nom.");
            array[i].setNom(ent.skip("[\r\n]*").nextLine());
            System.out.println("Introdueix el preu.");
            array[i].setPreu(ent.skip("[\r\n]*").nextDouble());
            char tePegatines;
            do {
                System.out.println("Té pegatines? (S/N)");
                tePegatines = ent.skip("[\r\n]*").next().toUpperCase().charAt(0);  //usem toUpperCase() que traduix el text introduït per l'usuari a majúscules, 
                //per tant només haurem de tractar les lletres majúscules
            } while (tePegatines != 'S' && tePegatines != 'N');
            array[i].setPegatines(tePegatines == 'S');     //si tePegatines conté la 'S' home serà true i sinó false. Fa el mateix que un if_else però és molt més curt
            do {                
                System.out.println("Quants de colors té?");
                try {
                    array[i].setNombreColors(ent.skip("[\r\n]*").nextInt());
                    break;
                } catch (java.util.InputMismatchException e) {
                    System.out.println("\nHas de posar un número!!");
                    ent.next();
                }            
            } while (true);            
            array[i].setOmplit(true);
        } else {
            System.out.println("\nJa has introduït dades, si vols afegir-ne borra-les primer.");
        }
    }

    public static void modificarCub() {
        Scanner ent = new Scanner(System.in);
        //Primer recorrem l'array buscant caselles omplides i preguntant quina volem modificar
        char siNo = 'N';
        int cont = 1, i;
        for (i = 0; i < array.length && siNo != 'S' && siNo != 'F'; i++) {
            if (array[i].isOmplit()) {
                System.out.format("\nCub %d:\n", cont++);
                System.out.println(array[i].toString());
                do {
                    System.out.println("\nVols modificar el cub(S/N) o finalitzar la cerca (F)?:");
                    siNo = ent.skip("[\r\n]*").nextLine().toUpperCase().charAt(0); //usem toUpperCase() que traduix el text introduït per l'usuari a majúscules, 
                    //per tant només haurem de tractar les lletres majúscules
                } while (siNo != 'S' && siNo != 'N' && siNo != 'F');
            }
            if (siNo == 'S') {
                break;
            }
        }
        //Si l'usuari ha contestat que sí és que ha triat un cub per modificar    
        if (siNo == 'S') {

            System.out.println("\nNom: " + array[i].getNom());
            do {
                System.out.println("\nVols modificar el nom?(S/N):");
                siNo = ent.skip("[\r\n]*").nextLine().toUpperCase().charAt(0);
            } while (siNo != 'S' && siNo != 'N');
            if (siNo == 'S') {
                System.out.print("Nou nom: ");
                array[i].setNom(ent.skip("[\r\n]*").nextLine());
            }

            System.out.println("\nPreu: " + array[i].getPreu());
            do {
                System.out.println("\nVols modificar el preu actual?(S/N):");
                siNo = ent.skip("[\r\n]*").nextLine().toUpperCase().charAt(0);
            } while (siNo != 'S' && siNo != 'N');
            if (siNo == 'S') {
                System.out.print("Preu nou: ");
                array[i].setPreu(ent.skip("[\r\n]*").nextDouble());
            }

            if (array[i].isPegatines()) {
                System.out.println("\nTé pegatines.");
            } else {
                System.out.println("\nNo té pegatines.");
            }
            do {
                System.out.println("\nVols modificar-ho?(S/N):");
                siNo = ent.skip("[\r\n]*").nextLine().toUpperCase().charAt(0);
            } while (siNo != 'S' && siNo != 'N');
            if (siNo == 'S') {
                char esHome;
                do {
                    System.out.println("Té pegatines?(S/N):");
                    esHome = ent.skip("[\r\n]*").nextLine().toUpperCase().charAt(0);
                } while (esHome != 'S' && esHome != 'N');
                array[i].setPegatines(esHome == 'S');     //si esPegatines conté la 'S' pegatines serà true i sinó false. Fa el mateix que un if_else però és molt més curt
                if (array[i].isPegatines()) {
                    System.out.println("Té pegatines.");
                } else {
                    System.out.println("No té pegatines.");
                }
            }

            System.out.println("\nNombre de colors: " + array[i].getNombreColors());
            do {
                System.out.println("\nVols modificar el nombre de colors?(S/N):");
                siNo = ent.skip("[\r\n]*").nextLine().toUpperCase().charAt(0);
            } while (siNo != 'S' && siNo != 'N');
            if (siNo == 'S') {
                do {                
                    try {
                        System.out.print("Nou nombre de colors: ");
                        array[i].setNombreColors(ent.skip("[\r\n]*").nextInt());
                        break;
                    } catch (java.util.InputMismatchException e) {
                        System.out.println("\nHas de posar un número!!");
                        ent.next();
                    }            
            } while (true);

            }
            System.out.println("Cub modificat correctament.");

        } else {
            System.out.println("\nNo hi ha cubs per modificar, o no n'has triat cap per modificar.");
        }
    }

    public static void borrarCub() {
        //Variables locals
        Cub c = null;   //l'utilizo per apuntar al Cub de les caselles de l'array
        Scanner ent = new Scanner(System.in);
        char siNo = 'N';
        int i;
        for (i = 0; i < array.length && siNo != 'F'; i++) {
            c = array[i];
            if (c.isOmplit()) {
                System.out.println(c);
                do {
                    System.out.println("\nVols borrar el cub(S/N) o finalitzar la cerca (F)?:");
                    siNo = ent.skip("[\r\n]*").nextLine().toUpperCase().charAt(0); //usem toUpperCase() que traduix el text introduït per l'usuari a majúscules, 
                    //per tant només haurem de tractar les lletres majúscules
                } while (siNo != 'S' && siNo != 'N' && siNo != 'F');
            }
            if (siNo == 'S') {
                break;
            }
        }

        if (siNo == 'S') {
            c.setOmplit(false);
            System.out.println("Cub borrat correctament.");

        } else {
            System.out.println("\nNo s'ha borrat cap cub.");
        }
    }

    public static void llistarCubs() {
        Scanner ent = new Scanner(System.in);

        boolean algun = false;
        char siNo = 'S';
        int i;
        for (i = 0; i < array.length; i++) {
            Cub c = array[i];
            if (c.isOmplit()) {
                algun = true;
                System.out.println(c);
                do {
                    System.out.println("\nVols veure més cubs(S/N)?:");
                    siNo = ent.skip("[\r\n]*").nextLine().toUpperCase().charAt(0); //usem toUpperCase() que traduix el text introduït per l'usuari a majúscules, 
                    //per tant només haurem de tractar les lletres majúscules
                } while (siNo != 'S' && siNo != 'N');
            }
            if (siNo == 'N') {
                break;
            }
        }
        if (!algun) {
            System.out.println("\nNo hi ha cubs per mostrar, si vols, primer crea'n.");
        }
    }

    public static void recuperarCub() {
        Scanner ent = new Scanner(System.in);
        //Primer recorrem l'array buscant caselles buides i preguntant quina volem recuperar
        char siNo = 'N';
        int cont = 0, i;
        for (i = 0; i < array.length && siNo != 'S' && siNo != 'F'; i++) {
            if (!array[i].isOmplit()) {
                System.out.format("\nCub %d:\n", ++cont);
                System.out.println(array[i].toString());
                do {
                    System.out.println("\nVols recuperar el cub(S/N) o finalitzar la cerca (F)?:");
                    siNo = ent.skip("[\r\n]*").nextLine().toUpperCase().charAt(0); //usem toUpperCase() que traduix el text introduït per l'usuari a majúscules, 
                    //per tant només haurem de tractar les lletres majúscules
                } while (siNo != 'S' && siNo != 'N' && siNo != 'F');
            }
            if (siNo == 'S') {
                break;
            }
        }
        //Si l'usuari ha contestat que sí és que ha triat un cub per modificar    
        if (siNo == 'S') {
            array[i].setOmplit(true);
            System.out.println("Cub recuperat correctament.");
        } else {
            if (cont == 0) {
                System.out.println("No hi ha cubs per recuperar.");
            } else {
                System.out.println("Cub no recuperar.");
            }
        }

    }
}
