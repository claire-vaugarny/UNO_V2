package UNO_V2;

import java.util.List;
import java.util.Scanner;

public class Partie {

    private EnsembleJoueurs ensembleJoueurs; // stocke des Joueurs
    private PaquetCartes pioche;
    private Defausse defausse;
    private SensDuJeu sensDuJeu;

    private int indiceJoueurActuel;
    private Couleur couleurActive;

    private boolean premiereCarteAEteTraitee = false;
    private int cartesAPiocher = 0;
    private boolean sauterProchainJoueur = false;

    public Partie(int nbJoueurs) {
        InitialisationJeu init = new InitialisationJeu(nbJoueurs);

        this.ensembleJoueurs = init.getEnsembleJoueurs(); // EnsembleJoueurs gère maintenant List<Joueur>
        this.pioche = init.getPioche();
        this.defausse = init.getDefausse();
        this.sensDuJeu = new SensDuJeu();

        this.indiceJoueurActuel =
                ensembleJoueurs.getEnsembleJoueurs().indexOf(init.getJoueurQuiCommence());

        this.couleurActive =
                defausse.getDerniereCarteDefausse().getCouleur();
    }

    // ------------------- Joueur actuel -------------------
    public MainJoueur getJoueurActuel() {
        return ensembleJoueurs.getMainActuelle(indiceJoueurActuel);
    }

    public void passerAuJoueurSuivant() {
        int nbJoueurs = ensembleJoueurs.getEnsembleJoueurs().size();
        indiceJoueurActuel = (indiceJoueurActuel + sensDuJeu.getSens()) % nbJoueurs;
        if (indiceJoueurActuel < 0) {
            indiceJoueurActuel += nbJoueurs;
        }
    }

    public void inverserSens() {
        sensDuJeu.inverserSens();
        System.out.println("Le sens du jeu est maintenant : " + sensDuJeu);
    }

    public void setCouleurActive(Couleur couleur) {
        this.couleurActive = couleur;
    }

    public Couleur getCouleurActive() {
        return couleurActive;
    }

    public void setCartesAPiocher(int nb) {
        this.cartesAPiocher = nb;
    }

    public void setSauterProchainJoueur(boolean valeur) {
        this.sauterProchainJoueur = valeur;
    }

    public void fairePiocher(MainJoueur joueur, int nbCartes) {
        for (int i = 0; i < nbCartes; i++) {
            if (pioche.estVide()) reconstituerPioche();
            Carte carte = pioche.piocher();
            if (carte != null) joueur.ajouterCarte(carte);
        }
    }

    private void reconstituerPioche() {
        System.out.println("La pioche est vide. Reconstitution...");
        List<Carte> cartes = defausse.supprimerDefausse();
        pioche.getPaquetCartes().addAll(cartes);
        pioche.melanger();
    }

    // ------------------- Lancer la partie -------------------
    public void lancerPartie() {
        traiterPremiereCarte();

        while (!estFinie()) {
            jouerTour();
        }

        int gagnantIndice = getIndiceGagnant();
        Joueur gagnant = ensembleJoueurs.getEnsembleJoueurs().get(gagnantIndice);

        // Calcul des points de la manche
        calculerPointsManche(gagnant, ensembleJoueurs.getEnsembleJoueurs());

        System.out.println("Le joueur " + (gagnantIndice + 1) + " a gagné !");
        System.out.println("Il reçoit " + gagnant.getScore() + " points pour cette manche.");
    }

    private void traiterPremiereCarte() {
        if (premiereCarteAEteTraitee) return;

        Carte premiereCarte = defausse.getDerniereCarteDefausse();
        System.out.println("Première carte : " + premiereCarte);

        cartesAPiocher = 0;
        sauterProchainJoueur = false;

        if (premiereCarte.getCouleur().estNoire()) {
            Scanner scanner = Main.SCANNER;
            System.out.println("Choisissez une couleur initiale : (rouge, bleu, vert, jaune)");

            while (true) {
                String input = scanner.nextLine();
                Couleur couleur = Couleur.fromString(input);
                if (couleur != null && !couleur.estNoire()) {
                    couleurActive = couleur;
                    break;
                }
                System.out.println("Couleur invalide.");
            }

        } else {
            premiereCarte.appliquerEffet(this);
            passerAuJoueurSuivant();

            if (cartesAPiocher > 0) {
                MainJoueur joueurSuivant = getJoueurActuel();
                fairePiocher(joueurSuivant, cartesAPiocher);
                passerAuJoueurSuivant();
            } else if (sauterProchainJoueur) {
                passerAuJoueurSuivant();
            }
        }

        premiereCarteAEteTraitee = true;
    }

    private boolean estFinie() {
        return getIndiceGagnant() != -1;
    }

    private int getIndiceGagnant() {
        List<Joueur> joueurs = ensembleJoueurs.getEnsembleJoueurs();
        for (int i = 0; i < joueurs.size(); i++) {
            if (joueurs.get(i).getMainJoueur().isEmpty()) return i;
        }
        return -1;
    }

    // ------------------- Jouer un tour -------------------
    private void jouerTour() {
        MainJoueur joueur = getJoueurActuel();
        Scanner scanner = Main.SCANNER;

        System.out.println("--------------------------------");
        System.out.println("Tour du joueur " + (indiceJoueurActuel + 1));
        Carte derniereCarte = defausse.getDerniereCarteDefausse();
        defausse.afficherDerniereCarteDefausse();

        if (derniereCarte.getCouleur().estNoire()) {
            System.out.println("Couleur active : " + couleurActive.getLibelle());
        }

        joueur.afficherMain();

        boolean tourValide = false;
        while (!tourValide) {
            System.out.println("Entrez la carte à jouer :");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("pioche")) {
                if (pioche.estVide()) reconstituerPioche();
                Carte cartePiochee = pioche.piocher();
                System.out.println("Carte piochée : " + cartePiochee);
                joueur.ajouterCarte(cartePiochee);

                if (estJouable(cartePiochee)) {
                    System.out.println("Vous pouvez la jouer. La jouer ? (oui/non)");
                    String rep = scanner.nextLine();
                    if (rep.equalsIgnoreCase("oui")) {
                        poserCarte(joueur, cartePiochee);
                        tourValide = true;
                        continue;
                    }
                }
                passerAuJoueurSuivant();
                tourValide = true;

            } else {
                Carte carte = Carte.fromString(input);
                if (carte == null || !joueur.contientCarte(carte) || !estJouable(carte)) {
                    System.out.println("Carte invalide ou non jouable.");
                    continue;
                }
                poserCarte(joueur, carte);
                tourValide = true;
            }
        }
    }

    private boolean estJouable(Carte carte) {
        Carte derniereCarte = defausse.getDerniereCarteDefausse();
        MainJoueur joueur = getJoueurActuel();

        if (carte.getAction() == Action.PLUS_QUATRE) {
            boolean aCouleurActive = joueur.getMainJoueur().stream()
                    .anyMatch(c -> c.getCouleur() == couleurActive);
            if (aCouleurActive) {
                System.out.println("Vous avez une carte de la couleur active, vous ne pouvez pas jouer +4 !");
                return false;
            }
            return true;
        }

        return carte.getCouleur() == couleurActive
                || carte.getAction() == derniereCarte.getAction()
                || carte.getCouleur().estNoire();
    }

    private void poserCarte(MainJoueur joueur, Carte carte) {
        joueur.retirerCarte(carte);
        defausse.ajouterCarteDefausse(carte);

        if (!carte.getCouleur().estNoire()) couleurActive = carte.getCouleur();

        if (joueur.getMainJoueur().size() == 1) System.out.println("UNO !");

        cartesAPiocher = 0;
        sauterProchainJoueur = false;

        if (carte.getCouleur().estNoire()) {
            choisirNouvelleCouleurPourJoker(joueur, carte);
        }

        carte.appliquerEffet(this);

        passerAuJoueurSuivant();

        if (cartesAPiocher > 0) {
            MainJoueur joueurSuivant = getJoueurActuel();
            fairePiocher(joueurSuivant, cartesAPiocher);
            passerAuJoueurSuivant();
        } else if (sauterProchainJoueur) {
            passerAuJoueurSuivant();
        }
    }

    private void choisirNouvelleCouleurPourJoker(MainJoueur joueur, Carte carte) {
        Scanner scanner = Main.SCANNER;
        Couleur couleurAnnoncee = null;

        while (true) {
            System.out.println("Choisissez la couleur à annoncer (rouge, bleu, vert, jaune) :");
            String input = scanner.nextLine();
            couleurAnnoncee = Couleur.fromString(input);

            if (couleurAnnoncee != null && !couleurAnnoncee.estNoire() && couleurAnnoncee != couleurActive) {
                if (carte.getAction() == Action.PLUS_QUATRE) {
                    boolean aCouleurActive = joueur.getMainJoueur().stream()
                            .anyMatch(c -> c.getCouleur() == couleurActive);
                    if (aCouleurActive) {
                        System.out.println("Vous avez une carte de la couleur active, vous ne pouvez pas jouer +4 !");
                        return;
                    }
                }
                couleurActive = couleurAnnoncee;
                break;
            }
            System.out.println("Couleur invalide ou identique à la couleur actuelle !");
        }
    }

    // ------------------- Calcul des points -------------------
    public void calculerPointsManche(Joueur gagnant, List<Joueur> joueurs) {
        int pointsManche = 0;
        for (Joueur j : joueurs) {
            if (!j.equals(gagnant)) {
                pointsManche += j.getMainJoueur().calculerPointsMain();
            }
        }
        gagnant.ajouterPoints(pointsManche);
    }
}
