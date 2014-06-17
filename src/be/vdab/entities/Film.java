package be.vdab.entities;

public class Film {
	
	private long id;
	private long genreid;
	private String titel;
	private long voorraad;
	private long gereserveerd;
	private double prijs;
	
	public Film(long id, long genreid, String titel, long voorraad,
			long gereserveerd, double prijs) {
		super();
		this.id = id;
		this.genreid = genreid;
		this.titel = titel;
		this.voorraad = voorraad;
		this.gereserveerd = gereserveerd;
		this.prijs = prijs;
	}

	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public long getGenreid() {
		return genreid;
	}
	
	public void setGenreid(long genreid) {
		this.genreid = genreid;
	}
	
	public String getTitel() {
		return titel;
	}
	
	public void setTitel(String titel) {
		this.titel = titel;
	}
	
	public long getVoorraad() {
		return voorraad;
	}
	
	public void setVoorraad(long voorraad) {
		this.voorraad = voorraad;
	}
	
	public long getGereserveerd() {
		return gereserveerd;
	}
	
	public void setGereserveerd(long gereserveerd) {
		this.gereserveerd = gereserveerd;
	}
	
	public double getPrijs() {
		return prijs;
	}
	
	public void setPrijs(double prijs) {
		this.prijs = prijs;
	}
		
}
