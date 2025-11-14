package vues;

import java.awt.EventQueue;

import java.awt.Dimension;

import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextArea;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import controleurs.*;

public class Arene extends JFrame implements Global {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel jpnMurs;
	private JPanel jpnJeu;
	private JTextField txtSaisie;
	private JTextArea txtChat;
	
	private Controle controle;

	/**
	 * evenement touche appuyée en utilisant txtSaisie
	 * si la touche est entrée, vérifier le contenu de txtSaisie
	 * et l'envoyer au serveur
	 * @param e evenement contenant la touche appuyée
	 */
	private void txtSaisie_KeyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (!txtSaisie.getText().isBlank()) {
				
				controle.evenementArene(txtSaisie.getText());
				txtSaisie.setText("");
			}
		}
	}
	
	/**
	 * Create the frame.
	 */
	public Arene(Controle controle, String typeJeu) {
		setTitle("Arena");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setPreferredSize(new Dimension(800, 600 + 25 + 140));
		this.pack();
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		jpnJeu = new JPanel();
		jpnJeu.setBounds(0, 0, LARGEURARENE, HAUTEURARENE);
		jpnJeu.setOpaque(false);
		jpnJeu.setLayout(null);
		contentPane.add(jpnJeu);
		
		jpnMurs = new JPanel();
		jpnMurs.setBounds(0, 0, LARGEURARENE, HAUTEURARENE);
		jpnMurs.setOpaque(false);
		jpnMurs.setLayout(null);
		contentPane.add(jpnMurs);
		
		JLabel lblFond = new JLabel("");
		lblFond.setBounds(0, 0, 800, 600);
		String chemin = "fonds/fondarene.jpg";
		URL ressource = getClass().getClassLoader().getResource(chemin);
		lblFond.setIcon(new ImageIcon(ressource));
		contentPane.add(lblFond);
		
		if (typeJeu == CLIENT) {
			txtSaisie = new JTextField();
			txtSaisie.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					txtSaisie_KeyPressed(e);
				}
			});
			txtSaisie.setBounds(0, 600, 800, 25);
			contentPane.add(txtSaisie);
			txtSaisie.setColumns(10);
		}
		
		JScrollPane jspChat = new JScrollPane();
		jspChat.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		jspChat.setBounds(0, 625, 800, 140);
		contentPane.add(jspChat);
		
		txtChat = new JTextArea();
		txtChat.setEditable(false);
		jspChat.setViewportView(txtChat);
		
		this.controle = controle;
	}
	
	public void ajoutMur(Object mur) {
		jpnMurs.add((JLabel)mur);
		jpnMurs.repaint();
	}
	
	public JPanel getJpnMurs() {
		return jpnMurs;
	}
	
	public void setJpnMurs(JPanel jpnMurs) {
		this.jpnMurs.add(jpnMurs);
		this.jpnMurs.repaint();
	}
	
	public void ajoutJLabelJeu(JLabel label) {
		jpnJeu.add(label);
		jpnJeu.repaint();
	}
	
	public JPanel getJpnJeu() {
		return jpnJeu;
	}
	
	public void setJpnJeu(JPanel jpnJeu) {
		this.jpnJeu.removeAll();
		this.jpnJeu.add(jpnJeu);
		this.jpnJeu.repaint();
	}
	
	/**
	 * getter sur le contenu de txtChat
	 * @return le contenu (String) de txtChat
	 */
	public String getTxtChatText(){
		return this.txtChat.getText();
	}
	
	/**
	 * setter sur le contenu de txtChat
	 * @param text nouveau contenu de txtChat
	 */
	public void setTxtChatText(String text) {
		this.txtChat.setText(text);
		this.txtChat.setCaretPosition(this.txtChat.getDocument().getLength());
	}
	
	/**
	 * ajoute une phrase au tchat côté serveur
	 * @param text texte à ajouter au tchat côté serveur
	 */
	public void ajoutTchat(String text) {
		String newText = this.txtChat.getText() + text + "\r\n";
		this.txtChat.setText(newText);
		this.txtChat.setCaretPosition(this.txtChat.getDocument().getLength());
	}
}
