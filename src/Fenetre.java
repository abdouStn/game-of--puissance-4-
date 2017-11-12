package puissance4;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class Fenetre extends JFrame{
	private JMenuBar menuBar;
	private JMenu partie;
	private JMenu jeu;
	private JMenu aide;
	private JMenuItem nouvellePartie, reprendre, quitter;
	
	private JTextField texte;
	
	private JPanel panHaut, panBas;
	
	Partie partieJeu;
	Joueur joueur1, joueur2, joueurCourant;
	
	private JButton[] boutonshaut = new JButton[7];
	private JLabel[] vide = new JLabel[6*7];
	private JLabel[] carre = new JLabel[6*7];
	private JLabel[] cercle = new JLabel[6*7];
	
	
	public Fenetre(){
		setTitle("Jeu Puissance 4");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout()); // par defaut pour une JFrame
		setSize(400,200);
		
		Partie partieJeu = new Partie();
		joueur1 = new Joueur("joueur1", 1);    // noir
		joueur2 = new Joueur("joueur2", 2); 
		joueurCourant = joueur1;
		
		menuBar = new JMenuBar();
		partie = new JMenu("Partie");
		jeu = new JMenu("Jeu");
		aide = new JMenu("?");
		nouvellePartie = new JMenuItem("Nouvelle Partie"); 
		reprendre = new JMenuItem("Reprendre");
		quitter = new JMenuItem("Quitter");
		partie.add(nouvellePartie); partie.add(reprendre); partie.add(quitter);
		
		Ecouteur e = new Ecouteur(this,partieJeu);
		
		
		panHaut = new JPanel();
		panHaut.setLayout(new GridLayout(1,7));//getNbColonne()
		for (int i=0; i<7;i++) {
			boutonshaut[i] = new JButton("");
			boutonshaut[i].setIcon(new ImageIcon( "image/fleche.gif"));
			boutonshaut[i].setBorder(BorderFactory.createLineBorder(Color.lightGray));
			boutonshaut[i].addActionListener(e);
			
			panHaut.add(boutonshaut[i]);
		}
		
		this.add(panHaut, "North");
		
		panBas = new JPanel();
		panBas.setLayout(new GridLayout(6,7));
		for (int i=0; i<6*7;i++){
			carre[i] = new JLabel( new ImageIcon( "image/carre.gif"));
			cercle[i] = new JLabel( new ImageIcon( "image/cercle.GIF"));
			
			vide[i] = new JLabel( new ImageIcon( "image/vide.GIF")); 
			vide[i].setBorder(BorderFactory.createLineBorder(Color.lightGray));
			
			panBas.add(vide[i]);
			
		}
		
		this.add(panBas);
		
		texte = new JTextField("tour de : "+joueurCourant.getNom());
		this.add(texte,"South");
		
		/*nouvellePartie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				partieJeu.grille.reInit();
				panBas.validate();
			}
			
		});*/
		
				
		menuBar.add(partie);
		menuBar.add(jeu);
		menuBar.add(aide);
		setJMenuBar(menuBar);
		
		pack();
		
	}
	void passerLaMain(){
		if(joueurCourant.getNumero() == 1){
			joueurCourant = joueur2;
		}
		else if(joueurCourant.getNumero() == 2){
			joueurCourant = joueur1;
		}
		else{
			System.out.println("erreur lors de la permutation des joueurs");
		}
		texte.setText("tour de : "+joueurCourant.getNom());
		panBas.validate();

	}
	
	class Ecouteur implements ActionListener{
		private Fenetre fen;
		private Partie partie;

		public Ecouteur(Fenetre fen, Partie partie) {
			this.fen = fen;
			this.partie = partie;
		}
				
		public void affichageGraphiqueAJour(){
			for(int i= 0; i<partie.grille.getNbColonnes()*partie.grille.getNbLignes();i++){
				fen.panBas.remove(vide[i]);
				fen.panBas.remove(carre[i]);
				fen.panBas.remove(cercle[i]);

			}
			int k=0;//partie.grille.getNbLignes()*partie.grille.getNbColonnes()-1;
			for(int i= 0; i<partie.grille.getNbLignes();i++){
				for(int j= 0; j<partie.grille.getNbColonnes();j++){
					if(partie.grille.coordonnees[i][j]==0){ // vide
						fen.panBas.add(vide[k]); //continue;
					}
					else if(partie.grille.coordonnees[i][j]==1){ // carre
						fen.panBas.add(carre[k]); //continue;
					}
					else if (partie.grille.coordonnees[i][j] == 2){ // cercle
						fen.panBas.add(cercle[k]); //continue;
					}
					k++;
				}
			}
			passerLaMain();
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {

			if(e.getSource() == fen.boutonshaut[0]){
				int res = partie.jouer(0,joueurCourant);
				affichageGraphiqueAJour();
				if(res==0){
					JOptionPane.showMessageDialog(null,"Felicitation vous avez gagne "+joueurCourant.getNom(),"message", JOptionPane.INFORMATION_MESSAGE );
					return;
				}
				else if(res==1){
					JOptionPane.showMessageDialog(null,"Match NULL","message", JOptionPane.INFORMATION_MESSAGE );
					return;
				}
			}
			else if(e.getSource() == fen.boutonshaut[1]){
				int res = partie.jouer(1,joueurCourant);
				affichageGraphiqueAJour();
				if(res==0){
					JOptionPane.showMessageDialog(null,"Felicitation vous avez gagne "+joueurCourant.getNom(),"message", JOptionPane.INFORMATION_MESSAGE );
					return;
				}
				else if(res==1){
					JOptionPane.showMessageDialog(null,"Match NULL","message", JOptionPane.INFORMATION_MESSAGE );
					return;
				}
			}
			else if(e.getSource() == fen.boutonshaut[2]){
				int res = partie.jouer(2,joueurCourant);
				affichageGraphiqueAJour();
				if(res==0){
					JOptionPane.showMessageDialog(null,"Felicitation vous avez gagne "+joueurCourant.getNom(),"message", JOptionPane.INFORMATION_MESSAGE );
					return;
				}
				else if(res==1){
					JOptionPane.showMessageDialog(null,"Match NULL","message", JOptionPane.INFORMATION_MESSAGE );
					return;
				}
				
			}
			else if(e.getSource() == fen.boutonshaut[3]){
				int res = partie.jouer(3,joueurCourant);
				affichageGraphiqueAJour();
				if(res==0){
					JOptionPane.showMessageDialog(null,"Felicitation vous avez gagne "+joueurCourant.getNom(),"message", JOptionPane.INFORMATION_MESSAGE );
					return;
				}
				else if(res==1){
					JOptionPane.showMessageDialog(null,"Match NULL","message", JOptionPane.INFORMATION_MESSAGE );
					return;
				}
				
			}
			else if(e.getSource() == fen.boutonshaut[4]){
				int res = partie.jouer(4,joueurCourant);
				affichageGraphiqueAJour();
				if(res==0){
					JOptionPane.showMessageDialog(null,"Felicitation vous avez gagne "+joueurCourant.getNom(),"message", JOptionPane.INFORMATION_MESSAGE );
					return;
				}
				else if(res==1){
					JOptionPane.showMessageDialog(null,"Match NULL","message", JOptionPane.INFORMATION_MESSAGE );
					return;
				}
			}
			else if(e.getSource() == fen.boutonshaut[5]){
				int res = partie.jouer(5,joueurCourant);
				affichageGraphiqueAJour();
				if(res==0){
					JOptionPane.showMessageDialog(null,"Felicitation vous avez gagne "+joueurCourant.getNom(),"message", JOptionPane.INFORMATION_MESSAGE );
					return;
				}
				else if(res==1){
					JOptionPane.showMessageDialog(null,"Match NULL","message", JOptionPane.INFORMATION_MESSAGE );
					return;
				}
				
			}
			else if(e.getSource() == fen.boutonshaut[6]){
				int res = partie.jouer(6,joueurCourant);
				affichageGraphiqueAJour();
				if(res==0){
					JOptionPane.showMessageDialog(null,"Felicitation vous avez gagne "+joueurCourant.getNom(),"message", JOptionPane.INFORMATION_MESSAGE );
					return;
				}
				else if(res==1){
					JOptionPane.showMessageDialog(null,"Match NULL","message", JOptionPane.INFORMATION_MESSAGE );
					return;
				}
				
			}
			
			fen.panBas.validate();
		}
		
		
	}
}
