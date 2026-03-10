package UNO_V2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PaquetCartes {

    private final List<Carte> paquetCartes;

    // Constructeur : paquet complet mélangé
    public PaquetCartes() {
        paquetCartes = new ArrayList<>();
        initialiserPaquet();
        melanger();
    }

    // Initialise le paquet complet ordonné
    private void initialiserPaquet() {
        for (Couleur couleur : Couleur.values()) {
            if (couleur.estNoire()) continue;

            // ZERO (1 seule)
            paquetCartes.add(new Carte(Action.ZERO, couleur));

            // 1 à 9 et cartes spéciales couleur (2 fois)
            Action[] cartesAAjouter = {Action.UN, Action.DEUX, Action.TROIS, Action.QUATRE,
                                       Action.CINQ, Action.SIX, Action.SEPT, Action.HUIT, Action.NEUF,
                                       Action.PLUS_DEUX, Action.CHANGE_SENS, Action.INTERDICTION_JOUER};

            for (int i = 0; i < 2; i++) {
                for (Action a : cartesAAjouter) {
                    paquetCartes.add(new Carte(a, couleur));
                }
            }
        }

        // Cartes noires : 4 × CHANGE_COULEUR et 4 × PLUS_QUATRE
        for (int i = 0; i < 4; i++) {
            paquetCartes.add(new Carte(Action.CHANGE_COULEUR, Couleur.NOIR));
            paquetCartes.add(new Carte(Action.PLUS_QUATRE, Couleur.NOIR));
        }
    }

    // Mélange le paquet
    public void melanger() {
        Collections.shuffle(paquetCartes);
    }

    // Pioche une carte (retire du paquet)
    public Carte piocher() {
        if (paquetCartes.isEmpty()) return null;
        return paquetCartes.remove(0);
    }

    public int taille() {
        return paquetCartes.size();
    }

    public boolean estVide() {
        return paquetCartes.isEmpty();
    }

    public List<Carte> getPaquetCartes() {
        return paquetCartes;
    }
}
