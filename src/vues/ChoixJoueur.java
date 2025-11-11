package vues;

import java.awt.EventQueue;
import java.awt.Dimension;

import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ChoixJoueur extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtPseudo;
	
	private Arene frmArene;
	
	/**
	 * Clic sur lblPrecedent
	 * Sélection du personnage précedent
	 */
	private void lblPrecedent_clic() {
		System.out.println("précedent");
	}
	
	/**
	 * Clic sur lblSuivant
	 * Sélection du personnage suivant
	 */
	private void lblSuivant_clic() {
		System.out.println("suivant");
	}
	
	/**
	 * Clic sur lblGo
	 * Ouverture de la fenêtre Arene et création du personnage
	 */
	private void lblGo_clic() {
		this.frmArene = new Arene();
		this.frmArene.setVisible(true);
		this.dispose();
	}
	
	/**
	 * Create the frame.
	 */
	public ChoixJoueur() {
		setTitle("Choice");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setPreferredSize(new Dimension(400, 275));
		this.pack();
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtPseudo = new JTextField();
		txtPseudo.setBounds(143, 244, 119, 22);
		contentPane.add(txtPseudo);
		txtPseudo.setColumns(10);
		
		JLabel lblPrecedent = new JLabel("");
		lblPrecedent.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblPrecedent_clic();
			}
		});
		lblPrecedent.setBounds(56, 146, 46, 45);
		contentPane.add(lblPrecedent);
		
		JLabel lblSuivant = new JLabel("");
		lblSuivant.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblSuivant_clic();
			}
		});
		lblSuivant.setBounds(291, 146, 46, 45);
		contentPane.add(lblSuivant);
		
		JLabel lblGo = new JLabel("");
		lblGo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblGo_clic();
			}
		});
		lblGo.setBounds(311, 192, 63, 72);
		contentPane.add(lblGo);
		
		JLabel lblFond = new JLabel("");
		lblFond.setBounds(0, 0, 400, 275);
		String chemin = "fonds/fondchoix.jpg";
		URL ressource = getClass().getClassLoader().getResource(chemin);
		lblFond.setIcon(new ImageIcon(ressource));
		contentPane.add(lblFond);
	}

}
