package modeles;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.net.URL;

import controleurs.Global;

/**
 * Gestion des murs
 *
 */
public class Mur extends Objet implements Global{

	/**
	 * Constructeur
	 */
	public Mur() {
		posX = (int) Math.round(Math.random() * (LARGEURARENE - LARGEURMUR));
		posY = (int) Math.round(Math.random() * (HAUTEURARENE - HAUTEURMUR));
		
		jLabel = new JLabel();
		jLabel.setBounds(posX, posY, LARGEURMUR, HAUTEURMUR);
		URL ressource = getClass().getClassLoader().getResource(MUR);
		jLabel.setIcon(new ImageIcon(ressource));
	}
}
