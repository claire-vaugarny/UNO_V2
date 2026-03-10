package UNO_V2;

public enum Couleur {
    JAUNE("jaune",false),
    VERT("vert",false),
    ROUGE("rouge",false),
    BLEU("bleu",false),
    NOIR("noir",true);

	private final String libelle;
	private final boolean noire;
	
	Couleur(String libelle, boolean noire){
		this.libelle=libelle;
		this.noire=noire;
	}
    
	public String getLibelle() {
		return libelle;
	}
    public boolean estNoire() {
    	return noire;
    }
    
    public static Couleur fromString(String texte) {
        for (Couleur c : Couleur.values()) {
            if (c.getLibelle().equalsIgnoreCase(texte.trim())) {
                return c;
            }
        }
        return null; // si aucune correspondance
    }
}

