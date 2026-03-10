package UNO_V2;

import java.util.ArrayList;
import java.util.List;

public class Defausse {
	private List<Carte> defausse;
	
	public Defausse() {
		defausse = new ArrayList<>();
	}
	
    // Récupérer la défausse complète
    public List<Carte> getDefausse() {
        return defausse;
    }

    // Ajouter une carte
    public void ajouterCarteDefausse(Carte carte) {
        defausse.add(carte);
    }
    
    //Renvoie la dernière carte de la défausse
    public Carte getDerniereCarteDefausse() {
    	if (defausse.isEmpty()) return null;
    	return defausse.get(defausse.size()-1);
    }
    
    //Affiche la dernière carte de la défausse
    public void afficherDerniereCarteDefausse() {
    	Carte derniereCarte = getDerniereCarteDefausse();
    	if(derniereCarte == null) {
    		System.out.println("Défausse vide : aucune carte.");
    	}else {
    		System.out.println("Dernière carte de la défausse : " + derniereCarte);
    	}
    }
    
    //Renvoie la défausse complète, sauf la dernière carte
    //et supprime ces cartes de la défausse
    public List<Carte> supprimerDefausse(){
    	//renvoie une List vide s'il y a 0 ou 1 carte
    	if (defausse.size()<=1) {return new ArrayList<>();};
    	List<Carte> cartesDefausseSupprimees = new ArrayList<>(defausse.subList(0, defausse.size()-1));
    	defausse.subList(0, defausse.size()-1).clear();
    	return cartesDefausseSupprimees;
    }
}