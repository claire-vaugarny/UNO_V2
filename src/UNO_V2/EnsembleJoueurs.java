package UNO_V2;

import java.util.ArrayList;
import java.util.List;

public class EnsembleJoueurs {
    private List<Joueur> ensembleJoueurs;

    public EnsembleJoueurs(int nbTotalJoueurs) {
        ensembleJoueurs = new ArrayList<>();
        for (int i = 0; i < nbTotalJoueurs; i++) {
            ensembleJoueurs.add(new Joueur());
        }
    }

    public List<Joueur> getEnsembleJoueurs() {
        return ensembleJoueurs;
    }

    public MainJoueur getMainActuelle(int indiceJoueurActuel) {
        return ensembleJoueurs.get(indiceJoueurActuel).getMainJoueur();
    }

    public MainJoueur getMainSuivante(int indiceJoueurActuel, int sensDuJeu) {
        int nbJoueurs = ensembleJoueurs.size();
        int indiceSuivant = (indiceJoueurActuel + sensDuJeu) % nbJoueurs;
        if (indiceSuivant < 0) indiceSuivant += nbJoueurs;
        return ensembleJoueurs.get(indiceSuivant).getMainJoueur();
    }

    public Joueur getJoueur(int indice) {
        return ensembleJoueurs.get(indice);
    }
}
