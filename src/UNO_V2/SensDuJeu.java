package UNO_V2;

public class SensDuJeu {

    private int sens; // 1 = horaire, -1 = anti-horaire

    public SensDuJeu() {
        this.sens = 1; // départ : sens horaire
    }

    public int getSens() {
        return sens;
    }

    public void inverserSens() {
        sens *= -1; // inverse le sens
    }

    @Override
    public String toString() {
        return (sens == 1) ? "horaire" : "anti-horaire";
    }
}
