package controleurs;

import vues.EntreeJeu;

public class Controle {
	
	private EntreeJeu frmEntreeJeu;
	
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
		this.frmEntreeJeu = new EntreeJeu();
		this.frmEntreeJeu.setVisible(true);
	}
}
