package UNO_V2;

public class Joueur {
    private final MainJoueur main;
    private int score;

    public Joueur() {
        this.main = new MainJoueur();
        this.score = 0;
    }

    public MainJoueur getMainJoueur() { return main; }
    public int getScore() { return score; }
    public void ajouterPoints(int points) { score += points; }
}

