package UNO_V2;

import java.util.Random;

public class InitialisationJeu {

    private EnsembleJoueurs ensembleJoueurs;
    private PaquetCartes pioche = new PaquetCartes();
    private Defausse defausse = new Defausse();
    private Joueur joueurQuiCommence;

    public InitialisationJeu(int nbTotalJoueurs) {
        ensembleJoueurs = new EnsembleJoueurs(nbTotalJoueurs);

        // Distribution des cartes
        for (Joueur joueur : ensembleJoueurs.getEnsembleJoueurs()) {
            for (int i = 0; i < 7; i++) {
                Carte carte = pioche.piocher();
                joueur.getMainJoueur().ajouterCarte(carte);
            }
        }

        // Première carte de la défausse
        initialiserPremiereCarte();

        // Détermination aléatoire du premier joueur
        Random random = new Random();
        int nbAlea = random.nextInt(nbTotalJoueurs);
        joueurQuiCommence = ensembleJoueurs.getEnsembleJoueurs().get(nbAlea);
        System.out.println("Le Joueur " + (nbAlea + 1) + " commence !");
    }

    // Getters pour Main
    public PaquetCartes getPioche() {
        return pioche;
    }

    public Defausse getDefausse() {
        return defausse;
    }

    public Joueur getJoueurQuiCommence() {
        return joueurQuiCommence;
    }

    public EnsembleJoueurs getEnsembleJoueurs() {
        return ensembleJoueurs;
    }
    private void initialiserPremiereCarte() {
        Carte premiereCarte;

        do {
            premiereCarte = pioche.piocher();

            // Si c'est un +4, on le remet dans la pioche et on mélange
            if (premiereCarte.getAction() == Action.PLUS_QUATRE) {
                pioche.getPaquetCartes().add(premiereCarte);
                pioche.melanger();
            }

        } while (premiereCarte.getAction() == Action.PLUS_QUATRE);

        defausse.ajouterCarteDefausse(premiereCarte);
    }

}
