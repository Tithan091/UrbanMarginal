package controleurs;

import modeles.*;
import vues.*;

import outils.connexion.*;

public class Controle implements AsyncResponse, Global {
	
	private EntreeJeu frmEntreeJeu;
	private Arene frmArene;
	private ChoixJoueur frmChoixJoueur;
	
	private ServeurSocket serverSocket;
	private ClientSocket clientSocket;
	
	private Jeu leJeu;
	
	/**
	 * Méthode d'entrée dans l'application
	 * @param args non utilisé
	 */
	public static void main(String[] args) {
		new Controle();
	}
	
	/**
	 * Constructeur
	 */
	private Controle() {
		this.frmEntreeJeu = new EntreeJeu(this);
		this.frmEntreeJeu.setVisible(true);
	}
	
	/**
	 * appelée lorsque le type de jeu a été choisi
	 * @param info indique si l'application est utilisée en tant que serveur ou client
	 */
	public void evenementEntreeJeu(String info) {
		if (info == "serveur") {			
			serverSocket = new ServeurSocket(this, PORT);
			leJeu = new JeuServeur(this);
			
			frmEntreeJeu.dispose();
			frmArene = new Arene();
			frmArene.setVisible(true);
		}
		else {
			clientSocket = new ClientSocket(this, info, PORT);
		}
	}
	
	/**
	 * appelée lorsqu'un joueur a choisi son pseudo et son avatar
	 * @param pseudo nom du joueur
	 * @param perso avatar utilisé
	 */
	public void evenementChoixJoueur(String pseudo, int perso) {
		JeuClient client = (JeuClient)leJeu;
		client.envoi(PSEUDOINFO+SEPARATOR+pseudo+SEPARATOR+perso);
		
		frmChoixJoueur.dispose();
		frmArene.setVisible(true);
	}
	
	public void envoi(Connection connection, Object info) {
		connection.envoi(info);
	}

	@Override
	public void reception(Connection connection, String ordre, Object info) {
		switch(ordre) {
		case "connexion":
			if (!(leJeu instanceof JeuServeur)) {
				frmEntreeJeu.dispose();
				frmArene = new Arene();
				frmChoixJoueur = new ChoixJoueur(this);
				frmChoixJoueur.setVisible(true);
				
				leJeu = new JeuClient(this);
			}
			leJeu.connexion(connection);
			break;
		case "reception":
			leJeu.reception(connection, info);
			break;
		case "deconnexion":
			break;
		}
	}
}
