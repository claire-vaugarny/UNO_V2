package UNO_V2;

public class Main {

    // Scanner global accessible depuis toutes les classes
    public static final java.util.Scanner SCANNER = new java.util.Scanner(System.in);

    public static void main(String[] args) {
        Partie partie = new Partie(3);
        partie.lancerPartie();
    }


        // Scanner à fermer seulement à la fin du programme si nécessaire
        // SCANNER.close();
}
