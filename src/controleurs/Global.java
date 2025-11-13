package controleurs;

public interface Global {
	public static final int PORT = 6666;
	
	/**
	 * séparateur utilisé lors d'envois d'informations à un serveur distant
	 */
	public static final String SEPARATOR = "~";
	
	/**
	 * utilisé lors d'un envoi d'informations à un serveur distant
	 * pour préciser le type d'informations
	 */
	public static final String PSEUDOINFO = "pseudo";
	
	public static final String AJOUTMUR = "ajout_mur";
	public static final String AJOUTPANELMURS = "ajout_panel_murs";
	
	public static final String CHEMINSEPARATOR = "/";
	
	/**
	 * chemin pour accéder à une image de personnage
	 * exemple pour chemin valide : PERSOPATH"1marche1d1.gif"
	 */
	public static final String PERSOPATH = "personnages"+CHEMINSEPARATOR+"perso";
	
	public static final int MAXPERSO = 3;
	
	/**
	 * vie de départ pour tous les joueurs
	 */
	public static final int MAXVIE = 10 ;
	/**
	 * gain de points de vie lors d'une attaque
	 */
	public static final int GAIN = 1 ;
	/**
	 * perte de points de vie lors d'une attaque
	 */
	public static final int PERTE = 2 ;
	
	public static final int NBMURS = 20;
	
	public static final int HAUTEURARENE = 600;
	public static final int LARGEURARENE = 800;
	public static final int HAUTEURMUR = 35;
	public static final int LARGEURMUR = 34;
	
	public static final String CHEMINMURS = "murs"+CHEMINSEPARATOR;
	public static final String MUR = CHEMINMURS+"mur.gif";
}
