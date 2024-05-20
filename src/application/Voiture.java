package application;

public class Voiture {
	private int id;
	private String marque;
	private String modele;
	private int nbV;
	private float tarif;
	private String imgV;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMarque() {
		return marque;
	}
	public void setMarque(String marque) {
		this.marque = marque;
	}
	public String getModele() {
		return modele;
	}
	public void setModele(String modele) {
		this.modele = modele;
	}
	public int getNbV() {
		return nbV;
	}
	public void setNbV(int nbV) {
		this.nbV = nbV;
	}
	public float getTarif() {
		return tarif;
	}
	public void setTarif(float tarif) {
		this.tarif = tarif;
	}
	public String getImgV() {
		return imgV;
	}
	public void setImgV(String imgV) {
		this.imgV = imgV;
	}
	@Override
	public String toString() {
		return "Voiture [id=" + id + ", marque=" + marque + ", modele=" + modele + ", nbV=" + nbV + ", tarif=" + tarif
				+ ", imgV=" + imgV + "]";
	}
	public Voiture(int id, String marque, String modele, float tarif, String imgV) {
		
		this.id = id;
		this.marque = marque;
		this.modele = modele;
		this.tarif = tarif;
		this.imgV = imgV;
	}
	
public Voiture(int id, String marque, String modele, float tarif, String imgV,int nbV) {
		this.nbV=nbV;
		this.id = id;
		this.marque = marque;
		this.modele = modele;
		this.tarif = tarif;
		this.imgV = imgV;
	}
}
