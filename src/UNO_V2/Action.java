package UNO_V2;

public enum Action {

    ZERO("0", false, false, new EffetAucun(),0),
    UN("1", false, false, new EffetAucun(),1),
    DEUX("2", false, false, new EffetAucun(),2),
    TROIS("3", false, false, new EffetAucun(),3),
    QUATRE("4", false, false, new EffetAucun(),4),
    CINQ("5", false, false, new EffetAucun(),5),
    SIX("6", false, false, new EffetAucun(),6),
    SEPT("7", false, false, new EffetAucun(),7),
    HUIT("8", false, false, new EffetAucun(),8),
    NEUF("9", false, false, new EffetAucun(),9),

    PLUS_DEUX("+2", true, false, new EffetPlusDeux(),20),
    CHANGE_SENS("changement de sens", true, false, new EffetChangeSens(),20),
    INTERDICTION_JOUER("interdiction de jouer", true, false, new EffetInterdiction(),20),

    CHANGE_COULEUR("changement de couleur", false, true, new EffetChangeCouleur(),50),
    PLUS_QUATRE("+4", false, true, new EffetPlusQuatre(),50);

    private final String libelle;
    private final boolean specialCouleur;
    private final boolean specialNoir;
    private final Effet effet;
    private final int point;

    Action(String libelle, boolean specialCouleur, boolean specialNoir, Effet effet, int point) {
        this.libelle = libelle;
        this.specialCouleur = specialCouleur;
        this.specialNoir = specialNoir;
        this.effet = effet;
        this.point= point; 
    }

    public String getLibelle() {
        return libelle;
    }

    public boolean estSpecialCouleur() {
        return specialCouleur;
    }

    public boolean estSpecialNoir() {
        return specialNoir;
    }
    
    public int getPoint() {
    	return point;
    }

    public void appliquerEffet(Partie partie) {
        effet.appliquer(partie);
    }

    public static Action fromString(String texte) {
        for (Action a : Action.values()) {
            if (a.getLibelle().equalsIgnoreCase(texte.trim())) {
                return a;
            }
        }
        return null;
    }
}
