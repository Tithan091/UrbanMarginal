package modeles;

import javax.swing.JPanel;

import controleurs.*;

import outils.connexion.Connection;

/**
 * Gestion du jeu côté client
 *
 */
public class JeuClient extends Jeu implements Global {
	
	private Connection connexionServeur;
	
	/**
	 * Controleur
	 */
	public JeuClient(Controle controle) {
		this.controle = controle;
	}

	@Override
	public void connexion(Connection connection) {
		this.connexionServeur = connection;
	}

	@Override
	public void reception(Connection connection, Object info) {
		if (info instanceof JPanel) {
			controle.evenementJeuClient(AJOUTPANELMURS, info);
		}
	}

	@Override
	public void deconnexion() {
	}

	/**
	 * Envoi d'une information vers le serveur fais appel une fois à l'envoi dans la
	 * classe Jeu
	 */
	public void envoi(String info) {
		this.envoi(connexionServeur, info);
	}

}
