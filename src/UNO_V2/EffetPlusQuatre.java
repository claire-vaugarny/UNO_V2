package UNO_V2;

import java.util.Scanner;

public class EffetPlusQuatre implements Effet {

    @Override
    public void appliquer(Partie partie) {

        System.out.println("Le joueur suivant devra piocher 4 cartes.");
        partie.setCartesAPiocher(4);
    }

    private void choisirNouvelleCouleur(Partie partie) {

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
