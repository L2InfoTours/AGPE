package liaisonappliBDopta;

public class Salle {
	
	private String nom;
	private String materiel;
	private int capacite;
	public static int num = 0;
	private int id = 0 ;
	
	public Salle(String nom, String materiel,int capacite){
		this.materiel = materiel;
		this.nom = nom;
		this.id = num;
		this.setCapacite(capacite);
		num++;
	}

	public int getId() {
		return id;
	}
	public void addBD() {
		String requete = "INSERT INTO salles(SallesNom, SallesCapacite) VALUES ('"+nom+"',"+capacite+")";
		Connexion a = new Connexion();
		a.RequeteDB(requete);
	}


	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getMateriel() {
		return materiel;
	}

	public void setMateriel(String materiel) {
		this.materiel = materiel;
	}

	public int getCapacite() {
		return capacite;
	}

	public void setCapacite(int capacite) {
		this.capacite = capacite;
	}
	

}
