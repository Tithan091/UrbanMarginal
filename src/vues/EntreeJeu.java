package vues;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EntreeJeu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtIp;
	
	private Arene frmArene;
	private ChoixJoueur frmChoixJoueur;

	/**
	 * Clic sur btnStart
	 * Démarrage du serveur et ouverture de la fenêtre Arene
	 */
	private void btnStart_clic() {
		this.frmArene = new Arene();
		this.frmArene.setVisible(true);
		this.dispose();
	}
	
	/**
	 * Clic sur btnConnect
	 * Connexion au serveur et ouverture de la fenêtre ChoixJoueur
	 */
	private void btnConnect_clic() {
		this.frmChoixJoueur = new ChoixJoueur();
		this.frmChoixJoueur.setVisible(true);
		this.dispose();
	}
	
	/**
	 * Clic sur btnExit
	 * Fermeture de l'application
	 */
	private void btnExit_clic() {
		System.exit(0);
	}
	
	/**
	 * Create the frame.
	 */
	public EntreeJeu() {
		setTitle("Urban Marginal");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 302, 159);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblStart = new JLabel("Start a server :");
		lblStart.setBounds(10, 11, 91, 14);
		contentPane.add(lblStart);
		
		JLabel lblConnect1 = new JLabel("Connect an existing server :");
		lblConnect1.setBounds(10, 36, 155, 14);
		contentPane.add(lblConnect1);
		
		JLabel lblConnect2 = new JLabel("IP server :");
		lblConnect2.setBounds(10, 61, 62, 14);
		contentPane.add(lblConnect2);
		
		txtIp = new JTextField();
		txtIp.setText("127.0.0.1");
		txtIp.setBounds(69, 58, 108, 20);
		contentPane.add(txtIp);
		txtIp.setColumns(10);
		
		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnStart_clic();
			}
		});
		btnStart.setBounds(187, 7, 89, 23);
		contentPane.add(btnStart);
		
		JButton btnConnect = new JButton("Connect");
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnConnect_clic();
			}
		});
		btnConnect.setBounds(187, 57, 89, 23);
		contentPane.add(btnConnect);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnExit_clic();
			}
		});
		btnExit.setBounds(187, 91, 89, 23);
		contentPane.add(btnExit);

	}
}
