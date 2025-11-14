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
	
	private boolean mursOk = false;
	
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
			if (!this.mursOk) {
				controle.evenementJeuClient(AJOUTPANELMURS, info);
				this.mursOk = true;
			}
			else {
				controle.evenementJeuClient(MODIFPANELJEU, info);
			}
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
		super.envoi(connexionServeur, info);
	}
	
	public Connection getConnexionServeur() {
		return this.connexionServeur;
	}
}
