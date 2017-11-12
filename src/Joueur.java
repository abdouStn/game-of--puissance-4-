package puissance4;

//import java.util.Vector;

public class Joueur {
	private String nom;
	private int numero;
	private String typeJeton;  // carre ou cercle // peut etre supprimé
	
	
	public Joueur(String nom, int num){
		this.nom = nom;
		numero = num;
		this.typeJeton = typeJeton;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getTypeJeton() {
		return typeJeton;
	}

	public void setTypeJeton(String typeJeton) {
		this.typeJeton = typeJeton;
	}

	@Override
	public String toString() {
		return "Joueur [nom=" + nom + ", numero=" + numero + ", typeJeton="
				+ typeJeton + "]";
	}

	
	
}
