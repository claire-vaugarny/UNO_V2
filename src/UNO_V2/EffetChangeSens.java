package UNO_V2;

public class EffetChangeSens implements Effet {

    @Override
    public void appliquer(Partie partie) {
        partie.inverserSens();
    }
}
