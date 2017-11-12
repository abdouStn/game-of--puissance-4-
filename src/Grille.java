package puissance4;



public class Grille{
	private int nbColonnes;
	private int nbLignes;
	//protected Case coordonnees[][];
	protected int coordonnees[][];
	
	public Grille(int nbLignes,int nbColonnes) {
		this.nbColonnes = nbColonnes;
		this.nbLignes = nbLignes;
		coordonnees = new int[nbLignes][nbColonnes];
		for(int i=0;i<nbLignes;i++)
			for(int j=0;j<nbColonnes;j++)
				coordonnees[i][j] = 0;//new Case();
	}
	

	public int getNbColonnes() {
		return nbColonnes;
	}


	public void setNbColonnes(int nbColonnes) {
		this.nbColonnes = nbColonnes;
	}


	public int getNbLignes() {
		return nbLignes;
	}


	public void setNbLignes(int nbLignes) {
		this.nbLignes = nbLignes;
	}
	
	public void reInit(){
		for(int i=0;i<nbLignes;i++)
			for(int j=0;j<nbColonnes;j++)
				coordonnees[i][j] = 0;
	}
	
	
	
	
}
