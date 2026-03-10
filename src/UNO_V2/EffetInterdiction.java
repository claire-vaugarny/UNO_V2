package UNO_V2;

public class EffetInterdiction implements Effet {

	@Override
	public void appliquer(Partie partie) {
	    System.out.println("Le joueur suivant est bloqué !");
	    partie.setSauterProchainJoueur(true);
	}

}
