package UNO_V2;

import java.util.Scanner;

public class EffetChangeCouleur implements Effet {

    @Override
    public void appliquer(Partie partie) {

        Scanner scanner = Main.SCANNER;

        System.out.println("Choisissez une nouvelle couleur : (rouge, bleu, vert, jaune)");

        while (true) {
            String input = scanner.nextLine();
            Couleur couleur = Couleur.fromString(input);

            if (couleur != null && !couleur.estNoire()) {
                partie.setCouleurActive(couleur);
                break;
            }

            System.out.println("Couleur invalide.");
        }
    }
}
