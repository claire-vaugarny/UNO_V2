package UNO_V2;

public class EffetPlusDeux implements Effet {

    @Override
    public void appliquer(Partie partie) {

        System.out.println("Le joueur suivant devra piocher 2 cartes.");
        partie.setCartesAPiocher(2);
    }
}
