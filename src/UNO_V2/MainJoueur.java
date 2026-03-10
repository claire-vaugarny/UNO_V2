package UNO_V2;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;

public class MainJoueur {

	private final List<Carte> mainJoueur;

	// Constructeur : main vide au départ
	public MainJoueur() {
		mainJoueur = new ArrayList<>();
	}

	// Récupérer la main
	public List<Carte> getMainJoueur() {
		return mainJoueur;
	}

	// Ajouter une carte
	public void ajouterCarte(Carte carte) {
		mainJoueur.add(carte);
	}

	// Retirer une carte
	public void retirerCarte(Carte carte) {
		mainJoueur.remove(carte);
	}

	// Vérifie si la main contient la carte
	public boolean contientCarte(Carte carte) {
		return mainJoueur.contains(carte);
	}

	// Trier la main
	public void trierMain() {
		Collections.sort(mainJoueur, new Comparator<Carte>() {
			@Override
			public int compare(Carte c1, Carte c2) {
				// Comparer par couleur
				int couleurCompare = c1.getCouleur().ordinal() - c2.getCouleur().ordinal();
				if (couleurCompare != 0)
					return couleurCompare;

				// Comparer par action
				return c1.getAction().ordinal() - c2.getAction().ordinal();
			}
		});
	}

	// Affiche la main du joueur sur une seule ligne, triée, avec l'option "pioche"
	public void afficherMain() {
		if (mainJoueur.isEmpty()) {
			System.out.println("La main est vide.");
			return;
		}

		this.trierMain();

		System.out.print("Cartes du joueur : ");

		for (int i = 0; i < mainJoueur.size(); i++) {
			System.out.print(mainJoueur.get(i) + " || ");
		}

		// Ajoute l'option pioche
		System.out.print("pioche");

		System.out.println();
	}

	public boolean isEmpty() {
	    return mainJoueur.isEmpty();
	}
	
	public int calculerPointsMain() {
		int score = 0;
		for (Carte carte : mainJoueur) {
			score += carte.getAction().getPoint();
		}
		return score;
	}
}
