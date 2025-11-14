package modeles;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.net.URL;

import controleurs.Global;

/**
 * Gestion des joueurs
 *
 */
public class Joueur extends Objet implements Global {
	
	/**
	 * pseudo saisi
	 */
	private String pseudo ;
	/**
	 * n° correspondant au personnage (avatar) pour le fichier correspondant
	 */
	private int numPerso ; 
	/**
	 * message contenant le pseudo et la vie du joueur,
	 * à afficher en-dessous du personnage
	 */
	private JLabel message;
	/**
	 * instance de JeuServeur pour communiquer avec lui
	 */
	private JeuServeur jeuServeur ;
	/**
	 * numéro d'�tape dans l'animation (de la marche, touché ou mort)
	 */
	private int etape ;
	/**
	 * la boule du joueur
	 */
	private Boule boule ;
	/**
	* vie restante du joueur
	*/
	private int vie ; 
	/**
	* tourné vers la gauche (0) ou vers la droite (1)
	*/
	private int orientation ;
	
	/**
	 * Constructeur
	 * @param  
	 */
	public Joueur(JeuServeur jeuServeur) {
		this.jeuServeur = jeuServeur;
		this.vie = MAXVIE;
		this.etape = 1;
		this.orientation = DROITE;
	}

	/**
	 * Initialisation d'un joueur (pseudo et numéro, calcul de la 1ère position, affichage, création de la boule)
	 */
	public void initPerso(String pseudo, int numPerso, ArrayList<Mur> lesMurs, Collection<Joueur> lesJoueurs) {
		this.pseudo = pseudo;
		this.numPerso = numPerso;
		
		jLabel = new JLabel();
		
		message = new JLabel();
		message.setHorizontalAlignment(SwingConstants.CENTER);
		message.setFont(new Font("Dialog", Font.PLAIN, 8));
		
		premierePosition(lesMurs, lesJoueurs);
		
		jeuServeur.ajoutJLabelJeuArene(jLabel);
		jeuServeur.ajoutJLabelJeuArene(message);
		
		this.affiche(ETATMARCHE, this.etape);
		
		System.out.println("joueur "+pseudo+" - num perso "+numPerso+" créé");
	}

	/**
	 * Calcul de la première position aléatoire du joueur (sans chevaucher un autre joueur ou un mur)
	 */
	private void premierePosition(ArrayList<Mur> lesMurs, Collection<Joueur> lesJoueurs) {
		jLabel.setBounds(0, 0, LARGEURPERSO, HAUTEURPERSO);
		do {
			posX = (int) Math.round(Math.random() * (LARGEURARENE - LARGEURPERSO));
			posY = (int) Math.round(Math.random() * (HAUTEURARENE - HAUTEURPERSO - HAUTEURMESSAGE));
		} while (this.toucheJoueur(lesJoueurs) || this.toucheMur(lesMurs));
	}
	
	/**
	 * Affiche le personnage et son message
	 */
	public void affiche(String etat, int etape) {
		super.jLabel.setBounds(posX, posY, LARGEURPERSO, HAUTEURPERSO);
		String chemin = PERSOPATH + this.numPerso + etat + etape +
				"d" + this.orientation + EXTPERSOFILE;
		URL ressource = getClass().getClassLoader().getResource(chemin);
		super.jLabel.setIcon(new ImageIcon(ressource));
		
		this.message.setBounds(posX - MARGEMESSAGE, posY + HAUTEURPERSO,
				LARGEURPERSO + MARGEMESSAGE*2, HAUTEURMESSAGE);
		this.message.setText(pseudo + " : " + vie);
		
		jeuServeur.envoiJeuATous();
	}

	/**
	 * Gère une action reçue et qu'il faut afficher (déplacement, tire de boule...)
	 */
	public void action() {
	}

	/**
	 * Gère le déplacement du personnage
	 */
	private void deplace() { 
	}

	/**
	 * Contrôle si le joueur touche un des autres joueurs
	 * @return true si deux joueurs se touchent
	 */
	private Boolean toucheJoueur(Collection<Joueur> lesJoueurs) {
		for (Joueur joueur : lesJoueurs) {
			if (!this.equals(joueur) && this.toucheObjet(joueur)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Gain de points de vie après avoir touché un joueur
	 */
	public void gainVie() {
	}
	
	/**
	 * Perte de points de vie après avoir été touché 
	 */
	public void perteVie() {
	}
	
	/**
	* Contrôle si le joueur touche un des murs
	* @return true si un joueur touche un mur
	*/
	private Boolean toucheMur(ArrayList<Mur> lesMurs) {
		for (Objet mur : lesMurs) {
			if (this.toucheObjet(mur)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * vrai si la vie est à 0
	 * @return true si vie = 0
	 */
	public Boolean estMort() {
		return null;
	}
	
	/**
	 * Le joueur se déconnecte et disparait
	 */
	public void departJoueur() {
	}
	
	/**
	 * getter sur pseudo
	 * @return pseudo
	 */
	public String getPseudo() {
		return this.pseudo;
	}
}
