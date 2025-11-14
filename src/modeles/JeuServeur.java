package modeles;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.JLabel;

import controleurs.Controle;
import controleurs.Global;

import outils.connexion.Connection;

/**
 * Gestion du jeu côté serveur
 *
 */
public class JeuServeur extends Jeu implements Global {

	/**
	 * Collection de murs
	 */
	private ArrayList<Mur> lesMurs = new ArrayList<Mur>();
	/**
	 * Collection de joueurs
	 */
	private Hashtable<Connection, Joueur> lesJoueurs = new Hashtable<Connection, Joueur>();

	/**
	 * Constructeur
	 */
	public JeuServeur(Controle controle) {
		this.controle = controle;
	}

	@Override
	public void connexion(Connection connection) {
		lesJoueurs.put(connection, new Joueur(this));
	}

	@Override
	public void reception(Connection connection, Object info) {
		String[] message = ((String)info).split(SEPARATOR);
		switch(message[0]) {
		case DEMANDEPANELMURS:
			controle.evenementJeuServeur(AJOUTPANELMURS, connection);
			break;
		case PSEUDOINFO:
			String pseudo = message[1];
			Integer numPerso = Integer.parseInt(message[2]);
			this.lesJoueurs.get(connection).initPerso(pseudo, numPerso, this.lesMurs, this.lesJoueurs.values());
			break;
		}
	}

	@Override
	public void deconnexion() {
	}

	/**
	 * Envoi d'une information vers tous les clients fais appel plusieurs fois à
	 * l'envoi de la classe Jeu
	 */
	public void envoi() {
	}
	
	/**
	 * Envoi du nouveau panel de jeu vers tous les clients
	 */
	public void envoiJeuATous() {
		for (Connection connection : this.lesJoueurs.keySet()) {
			this.controle.evenementJeuServeur(MODIFPANELJEU, connection);
		}
	}

	/**
	 * Génération des murs
	 */
	public void constructionMurs() {
		Mur mur;
		for (int k = 0; k < NBMURS; k++) {
			mur = new Mur();
			lesMurs.add(mur);
			controle.evenementJeuServeur(AJOUTMUR, ((Objet)mur).getJLabel());
		}
	}
	
	public void ajoutJLabelJeuArene(JLabel label) {
		controle.evenementJeuServeur(AJOUTLABELJEU, label);
	}
}
