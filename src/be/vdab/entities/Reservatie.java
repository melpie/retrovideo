package be.vdab.entities;

public class Reservatie {
	
	private long klantid;
	private long filmid;
	private java.sql.Date reservatieDatum;
	
	public Reservatie(long klantid, long filmid) {
		super();
		this.klantid = klantid;
		this.filmid = filmid;
		java.util.Date today = new java.util.Date();
		this.reservatieDatum = new java.sql.Date(today.getTime());
	}

	public long getKlantid() {
		return klantid;
	}

	public void setKlantid(long klantid) {
		this.klantid = klantid;
	}

	public long getFilmid() {
		return filmid;
	}

	public void setFilmid(long filmid) {
		this.filmid = filmid;
	}

	public java.sql.Date getReservatieDatum() {
		return reservatieDatum;
	}

	public void setReservatieDatum(java.sql.Date reservatieDatum) {
		this.reservatieDatum = reservatieDatum;
	}	
	
}
