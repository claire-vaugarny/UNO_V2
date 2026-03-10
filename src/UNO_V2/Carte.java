package UNO_V2;

import java.util.Objects;

public class Carte {

    private final Action action;
    private final Couleur couleur;

    public Carte(Action action, Couleur couleur) {

        if (action == null || couleur == null) {
            throw new IllegalArgumentException("Action et couleur ne peuvent pas être null");
        }

        if (action.estSpecialNoir() && !couleur.estNoire()) {
            throw new IllegalArgumentException("Cette action doit être noire");
        }

        if (action.estSpecialCouleur() && couleur.estNoire()) {
            throw new IllegalArgumentException("Cette action ne peut pas être noire");
        }

        this.action = action;
        this.couleur = couleur;
    }

    public Action getAction() {
        return action;
    }

    public Couleur getCouleur() {
        return couleur;
    }

    public void appliquerEffet(Partie partie) {
        action.appliquerEffet(partie);
    }

    @Override
    public String toString() {
        return action.getLibelle() + " " + couleur.getLibelle();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Carte)) return false;
        Carte carte = (Carte) o;
        return action == carte.action && couleur == carte.couleur;
    }

    @Override
    public int hashCode() {
        return Objects.hash(action, couleur);
    }

    /**
     * Méthode statique pour créer une carte à partir d'une saisie utilisateur
     * Exemple : "plus deux rouge"
     */
    public static Carte fromString(String input) {
        String[] parts = input.trim().split(" ");
        if (parts.length < 2) return null;

        // La couleur est toujours le dernier mot
        Couleur couleur = Couleur.fromString(parts[parts.length - 1]);
        if (couleur == null) return null;

        // L'action correspond au reste
        StringBuilder actionTexte = new StringBuilder();
        for (int i = 0; i < parts.length - 1; i++) {
            if (i > 0) actionTexte.append(" ");
            actionTexte.append(parts[i]);
        }

        Action action = Action.fromString(actionTexte.toString());
        if (action == null) return null;

        return new Carte(action, couleur);
    }
}
