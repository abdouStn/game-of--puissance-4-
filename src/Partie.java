package puissance4;

import java.util.Scanner;

public class Partie {
	private static int round = 0;
	protected Joueur joueur1, joueur2;
	protected Grille grille;
	
	
	public Partie(){
		round++;
		grille = new Grille(6,7);
		
		joueur1 = new Joueur("abdou", 1);    // noir
		joueur2 = new Joueur("red", 2);     // rouge
	}
	
	@Override
	public String toString() {
		return "Partie " + round + " : [joueur1=" + joueur1 + ", joueur2=" + joueur2 + "]";
	}
	
	
	public boolean grillePleine(){   // renvoie faux si la grille n'est pas pleine
		for(int i=0 ;i<grille.getNbLignes();i++){
			if(grille.coordonnees[0][i] == 0)
				return false;
		}
		System.out.println("La grille est pleine Partie Terminée ---- MATCH NUL");
		return true;
	}
	
	public boolean gagnerVertical(int x, int y, int etats){
		int nb = 1;
		
		for(int i=x+1;i<grille.getNbLignes();i++){
			if(grille.coordonnees[i][y]!= etats){
				break;
			}
			else{
				nb++;
			}
		}
		if(nb>=4){
			System.out.println("FELICITATION VOUS AVEZ GAGNÉ \n "
					+ "\t\tVerticalement a la case[ "+(x+1)+","+(y+1)+" ]");
					return true;
		}
		return false;
	}
	
	public boolean gagnerHorizontal(int x, int y, int etats){
		int nbG = 0, nbD = 1;
		int k=y-1;
		
		for(int j=y+1;j<grille.getNbColonnes();j++){
			if(grille.coordonnees[x][j]!= etats){
				break;
			}
			else{
				nbD++;
			}
		}
		
		while(k>=0){
			if(grille.coordonnees[x][k]!= etats){
				break;
			}
			else{
				
				nbG++;
				
				k--;
			}
		}
		if((nbD+nbG)>=4){
			System.out.println("FELICITATION VOUS AVEZ GAGNÉ \n "
					+ "\t\tHorizontalement a la case[ "+(x+1)+","+(y+1)+" ]");
					return true;
		}
		return false;
	}
	
	public boolean gagnerDiagonal(int x, int y, int etats){
		int nbGB = 0, nbGH = 0, nbDB = 0, nbDH = 0; // Gauche, Droite, Bas, Haut
		int i =0, j=0;
		i= x-1; j=y+1;
		while(i>=0 && j<grille.getNbColonnes()){  // on verifie le coté haut droit
			if(grille.coordonnees[i][j] == etats){
				nbDH++;
				i--; j++;
			}
			else{
				break;
			}
		}
		
		i= x+1; j=y-1; nbGB = 0; nbDB = 0;
		while(i<grille.getNbLignes() && j>=0){  // on verifie le coté bas Gauche
			if(grille.coordonnees[i][j] == etats){
				//System.out.println(nbGB);
				nbGB++;
				i++; j--;
			}
			else{
				break;
			}
		}
		if((nbDH + nbGB +1)>=4){
			System.out.println("FELICITATION VOUS AVEZ GAGNÉ \n "
					+ "\t\tDiagonalement (<-) a la case[ "+(x+1)+","+(y+1)+" ] ");
			return true;
		}
		
		i= x-1; j=y-1; nbGH = 0; nbDB = 0;
		while(i>=0 && j>=0){  // on verifie le coté haut gauche
			if(grille.coordonnees[i][j]== etats){
				nbGH++;
				i--; j--;
			}
			else{
				break;
			}
		}
		
		i= x+1; j=y+1;
		while(i<grille.getNbLignes() && j<grille.getNbColonnes()){  // on verifie le coté bas Droit
			if(grille.coordonnees[i][j]== etats){
				nbDB++;
				i++; j++;
			}
			else{
				break;
			}
		}
		if((nbGH + nbDB +1)>=4) {
			System.out.println("FELICITATION VOUS AVEZ GAGNÉ \n "
					+ "\t\tDiagonalement (->) a la case[ "+(x+1)+","+(y+1)+" ]");
			return true;
		}
		
	return false;
		
	}
	
	public boolean gagner(int x, int y, int etats){
		return (gagnerVertical(x,y,etats) || gagnerHorizontal(x,y,etats) || gagnerDiagonal(x,y,etats) );
	}

	
	public int jouer(int numColonne,Joueur joueurCourant){  // jouer graphiquement (return 0 si gagné, 1 si grille pleine et 2 si R.A.S
		assert ((numColonne < grille.getNbColonnes()) && (numColonne >= 0)) : "Erreur sur numColonne"; 
		
		
		System.out.println("tour du joueur : "+ joueurCourant.getNom());
		if(joueurCourant.getNumero() == 1){  // le joueur 1 qui joue
			for(int i=0;i<grille.getNbLignes();i++){
				if((grille.coordonnees[i][numColonne] != 0 ) || (i==grille.getNbLignes()-1)){    // quand on trouve une case non remplie ou qu'on est en bas  
					if(grille.coordonnees[i][numColonne]!= 0){     // si case remplie on joue a la case du rang plus haut
						try{
							if(i-1 >= 0 ){
								grille.coordonnees[i-1][numColonne] = 1;
								
							}
							else{ 
								throw new ColonnePleineException("Colonne "+numColonne+" pleine");
							}
						
						}
						catch(ColonnePleineException c){
							System.out.println("Attention "+ c.getMessage());
							continue;

						}
						if (gagner(i-1, numColonne, 1))  return 0;
						if(grillePleine()) return 1;
					
					}
					else if (i==grille.getNbLignes()-1){ // si on est en bas on joue a cette case
						grille.coordonnees[i][numColonne] = 1;
						if (gagner(i, numColonne, 1))  return 0;
						if(grillePleine()) return 1;
					}
					
				}
			}
		}
		else if(joueurCourant.getNumero() == 2){    // le joueur 2 qui joue
			for(int i=0;i<grille.getNbLignes();i++){
				if((grille.coordonnees[i][numColonne] != 0 ) || (i==grille.getNbLignes()-1)){    // quand on trouve une case non remplie ou qu'on est en bas  
					if(grille.coordonnees[i][numColonne]!= 0){     // si case remplie on joue a la case du rang plus haut
						try{
							if(i-1 >= 0 ) grille.coordonnees[i-1][numColonne] = 2;
							else{ 
								throw new ColonnePleineException("Colonne "+numColonne+" pleine");
							}
						
						}
						catch(ColonnePleineException c){
							System.out.println("Attention "+ c.getMessage());
							continue;

						}
						if (gagner(i-1, numColonne, 2))  return 0;
						if(grillePleine()) return 1;
					}
					else if (i==grille.getNbLignes()-1){ // si on est en bas on joue a cette case
						grille.coordonnees[i][numColonne] = 2;
						if (gagner(i, numColonne, 2))  return 0;
						if(grillePleine()) return 1;
					}
					else{
						System.out.println("!!!Colonne "+numColonne+" pleine");
					}
				}
			}
		}
		else{
			System.out.println("on connait pas ce joueur");
		}
		return 2;
	}
	
public void jouer(){  // permet de jouer en console (sans interface graphique)
	   
		Joueur joueurCourant = joueur1;
		int numColonne = grille.getNbColonnes();
		assert ((numColonne < grille.getNbColonnes()) && (numColonne >= 0)); 
		
		while(true){
			System.out.println("tour du joueur : "+ joueurCourant.getNom());
			numColonne = grille.getNbColonnes()+1;
			
			Scanner sc = new Scanner(System.in);
			while(numColonne > grille.getNbColonnes() || numColonne <=0 ){
				System.out.println("Saisir le numero de la colonne a jouer");
				numColonne  = sc.nextInt();
			}
			
			if(joueurCourant.getNumero() == 1){  // le joueur 1 qui joue
				for(int i=0;i<grille.getNbLignes();i++){
					if((grille.coordonnees[i][numColonne-1] != 0 ) || (i==grille.getNbLignes()-1)){    // quand on trouve une case non remplie ou qu'on est en bas  
						if(grille.coordonnees[i][numColonne-1]!= 0){     // si case remplie on joue a la case du rang plus haut
							try{
								if(i-1 >= 0 ){
									grille.coordonnees[i-1][numColonne-1] = 1;
									
								}
								else{ 
									throw new ColonnePleineException("Colonne "+numColonne+" pleine");
								}
							
							}
							catch(ColonnePleineException c){
								System.out.println("Attention "+ c.getMessage());
								continue;

							}
							if(grillePleine() == true || gagner(i-1, numColonne-1, 1)) return;
						
						}
						else if (i==grille.getNbLignes()-1){ // si on est en bas on joue a cette case
							grille.coordonnees[i][numColonne-1] = 1;
							
							if(grillePleine() == true || gagner(i, numColonne-1, 1)) return;
						}
						
					}
				}
			}
			else if(joueurCourant.getNumero() == 2){    // le joueur 2 qui joue
				for(int i=0;i<grille.getNbLignes();i++){
					if((grille.coordonnees[i][numColonne-1] != 0 ) || (i==grille.getNbLignes()-1)){    // quand on trouve une case non remplie ou qu'on est en bas  
						if(grille.coordonnees[i][numColonne-1]!= 0){     // si case remplie on joue a la case du rang plus haut
							try{
								if(i-1 >= 0 ) grille.coordonnees[i-1][numColonne-1] = 2;
								else{ 
									throw new ColonnePleineException("Colonne "+numColonne+" pleine");
								}
							
							}
							catch(ColonnePleineException c){
								System.out.println("Attention "+ c.getMessage());
								continue;

							}
							if(grillePleine() == true || gagner(i-1, numColonne-1, 2)) return;
						}
						else if (i==grille.getNbLignes()-1){ // si on est en bas on joue a cette case
							grille.coordonnees[i][numColonne-1] = 2;
							
							if(grillePleine() == true || gagner(i, numColonne-1, 2)) return;
						}
						else{
							System.out.println("!!!Colonne "+numColonne+" pleine");
						}
					}
				}
			}
			else{
				System.out.println("on connait pas ce joueur");
			}
			
			/* Passer la main au joueur suivant */
			if(joueurCourant.getNumero() == 1){
				joueurCourant = joueur2;
			}
			else if(joueurCourant.getNumero() == 2){
				joueurCourant = joueur1;
			}
			else{
				System.out.println("erreur lors de la permutation des joueurs");
			}
			
		}
		
	}
	
	
	
}
