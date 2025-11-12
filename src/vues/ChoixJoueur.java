package vues;

import java.awt.EventQueue;

import java.awt.Cursor;
import java.awt.Dimension;

import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import controleurs.Controle;
import controleurs.Global;
import javax.swing.SwingConstants;

public class ChoixJoueur extends JFrame implements Global{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblPersonnage;
	private JTextField txtPseudo;
	
	private Arene frmArene;
	private Controle controle;
	
	private int numPerso = 1;
	
	/**
	 * Change le curseur de la souris en curseur par défault
	 */
	private void sourisNormale() {
		contentPane.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}
	
	/**
	 * Change le curseur de la souris en doigt pointé
	 */
	private void sourisDoigt() {
		contentPane.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
	
	/**
	 * Clic sur lblPrecedent
	 * Sélection du personnage précedent
	 */
	private void lblPrecedent_clic() {
		numPerso--;
		if (numPerso < 1) {
			numPerso = MAXPERSO;
		}
		affichePerso();
	}
	
	/**
	 * Clic sur lblSuivant
	 * Sélection du personnage suivant
	 */
	private void lblSuivant_clic() {
		numPerso++;
		if (numPerso > MAXPERSO) {
			numPerso = 1;
		}
		affichePerso();
	}
	
	/**
	 * Clic sur lblGo
	 * Vérifier pseudo et ouverture de la frame Arene
	 */
	private void lblGo_clic() {
		if (!txtPseudo.getText().isBlank()) {
			controle.evenementChoixJoueur(txtPseudo.getText(), numPerso);
		}
		else {
			JOptionPane.showMessageDialog(null, "La saisie du pseudo est obligatoire.");
			txtPseudo.requestFocus();
		}
	}
	
	/**
	 * Affiche dans lblPersonnage le personnage actuellement sélectionné
	 */
	private void affichePerso() {
		String chemin = PERSOPATH+numPerso+"marche1d1.gif";
		URL ressource = getClass().getClassLoader().getResource(chemin);
		System.out.println(ressource);
		lblPersonnage.setIcon(new ImageIcon(ressource));
	}
	
	/**
	 * Create the frame.
	 */
	public ChoixJoueur(Controle controle) {
		setTitle("Choice");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setPreferredSize(new Dimension(400, 275));
		this.pack();
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblPersonnage = new JLabel("");
		lblPersonnage.setHorizontalAlignment(SwingConstants.CENTER);
		lblPersonnage.setBounds(141, 113, 121, 120);
		contentPane.add(lblPersonnage);
		
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
			@Override
			public void mouseEntered(MouseEvent e) {
				sourisDoigt();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				sourisNormale();
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
			@Override
			public void mouseEntered(MouseEvent e) {
				sourisDoigt();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				sourisNormale();
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
			@Override
			public void mouseEntered(MouseEvent e) {
				sourisDoigt();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				sourisNormale();
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
		
		this.controle = controle;
		
		affichePerso();
	}
}
