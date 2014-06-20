package be.vdab.entities;

import java.sql.Date;

public class Reservatie {
	
	private String titel;
	private String familienaam;
	private String voornaam;
	private java.sql.Date reservatieDatum;
	
	public Reservatie(String titel, String familienaam, String voornaam, Date reservatieDatum) {
		super();
		this.titel = titel;
		this.familienaam = familienaam;
		this.voornaam = voornaam;
		this.reservatieDatum = reservatieDatum;
	}

	public String getTitel() {
		return titel;
	}

	public void setTitel(String titel) {
		this.titel = titel;
	}

	public String getFamilienaam() {
		return familienaam;
	}

	public void setFamilienaam(String familienaam) {
		this.familienaam = familienaam;
	}

	public String getVoornaam() {
		return voornaam;
	}

	public void setVoornaam(String voornaam) {
		this.voornaam = voornaam;
	}

	public java.sql.Date getReservatieDatum() {
		return reservatieDatum;
	}

	public void setReservatieDatum(java.sql.Date reservatieDatum) {
		this.reservatieDatum = reservatieDatum;
	}	
	
}
