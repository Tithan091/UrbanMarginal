package modeles;
import java.util.ArrayList;

import java.util.Hashtable;

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
		lesJoueurs.put(connection, new Joueur());
	}

	@Override
	public void reception(Connection connection, Object info) {
		String[] message = ((String)info).split(SEPARATOR);
		switch(message[0]) {
		case PSEUDOINFO:
			String pseudo = message[1];
			Integer numPerso = Integer.parseInt(message[2]);
			this.lesJoueurs.get(connection).initPerso(pseudo, numPerso);
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
	 * Génération des murs
	 */
	public void constructionMurs() {
	}

}
