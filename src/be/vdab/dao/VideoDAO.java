package be.vdab.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import be.vdab.entities.Film;
import be.vdab.entities.Genre;
import be.vdab.entities.Klant;
import be.vdab.entities.Reservatie;

public class VideoDAO extends AbstractDAO {
	
	private static final String FIND_GENRES_SQL =
		"select id,naam from genres order by naam";
	
	private static final String FIND_FILM_BY_GENRE_SQL =
	    "select id,genreid,titel,voorraad,gereserveerd,prijs from films where genreid=?";	
	
	private static final String FIND_FILM_BY_ID_SQL =
		"select id,genreid,titel,voorraad,gereserveerd,prijs from films where id=?";
	
	private static final String FIND_KLANTEN_BY_SEARCHSTRING_SQL = 
		"select id,familienaam,voornaam,straatNummer,postcode,gemeente from klanten where familienaam like ?";
	
	private static final String FIND_KLANT_BY_ID_SQL = 
		"select id,familienaam,voornaam,straatNummer,postcode,gemeente from klanten where id=?";
	
	private static final String CREATE_RESERVATIE_SQL =
		"insert into reservaties(klantid,filmid,reservatieDatum) values (?,?,?)";
	
	private static final String UPDATE_FILMS_SQL =
		"update films set gereserveerd=gereserveerd+1 where id=? and voorraad>gereserveerd";
	
	private static final String FIND_RESERVATIES_SQL =
			"select titel,familienaam,voornaam,reservatieDatum from films " 
			+ "inner join (klanten inner join reservaties on klanten.id=reservaties.klantid) " 
			+ "on films.id=reservaties.filmid order by titel";
	
	public Iterable<Genre> getGenres() {
		try (Connection connection = getConnection()) {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(FIND_GENRES_SQL);
			List<Genre> genres = new ArrayList<>();
			while (resultSet.next()) {
				genres.add(resultSetRijNaarGenre(resultSet));
			}
			return genres;
		} catch (SQLException ex) {
			throw new DAOException("Kan genres niet lezen uit database", ex);
		}
	}
	
	private Genre resultSetRijNaarGenre(ResultSet resultSet) throws SQLException {
		return new Genre(resultSet.getLong("id"), resultSet.getString("naam"));
	}
	
	public Iterable<Film> findFilmsByGenre(long genreid) {
        try (Connection connection = getConnection()) {
        	PreparedStatement statement = connection.prepareStatement(FIND_FILM_BY_GENRE_SQL);
            List<Film> films = new ArrayList<>();
            statement.setLong(1, genreid);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                films.add(resultSetRijNaarFilm(resultSet));
            }
            return films;
        } catch (SQLException ex) {
            throw new DAOException("Kan films niet lezen uit database", ex);
        }
    }
	
	public Film findFilmByID(long filmid) {
        try (Connection connection = getConnection()) {
        	PreparedStatement statement = connection.prepareStatement(FIND_FILM_BY_ID_SQL);
            Film film = null;
            statement.setLong(1, filmid);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                film = resultSetRijNaarFilm(resultSet);
            }
            return film;
        } catch (SQLException ex) {
            throw new DAOException("Kan film niet lezen uit database", ex);
        }
	}
		
	private Film resultSetRijNaarFilm(ResultSet resultSet) throws SQLException {
		return new Film(resultSet.getLong("id"), resultSet.getLong("genreid")
				, resultSet.getString("titel"), resultSet.getLong("voorraad")
				, resultSet.getLong("gereserveerd"), resultSet.getDouble("prijs"));
	}
	
	public Iterable<Klant> findKlantenBySearchString(String searchstring) {
        try (Connection connection = getConnection()) {
        	PreparedStatement statement = connection.prepareStatement(FIND_KLANTEN_BY_SEARCHSTRING_SQL);
            List<Klant> klanten = new ArrayList<>();
            statement.setString(1, "%"+searchstring+"%");
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    klanten.add(resultSetRijNaarKlant(resultSet));
                }
                return klanten;
            }
        } catch (SQLException ex) {
            throw new DAOException("Kan klanten niet lezen uit database", ex);
        }
    }
	
	public Klant findKlantByID(long klantid) {
        try (Connection connection = getConnection()) {
        	PreparedStatement statement = connection.prepareStatement(FIND_KLANT_BY_ID_SQL);
        	Klant klant = null;
            statement.setLong(1, klantid);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                klant = resultSetRijNaarKlant(resultSet);
            }
            return klant;
        } catch (SQLException ex) {
            throw new DAOException("Kan klant niet lezen uit database", ex);
        }
	}
	
	private Klant resultSetRijNaarKlant(ResultSet resultSet) throws SQLException {
		return new Klant(resultSet.getLong("id"), resultSet.getString("familienaam")
				, resultSet.getString("voornaam"), resultSet.getString("straatNummer")
				, resultSet.getString("postcode"), resultSet.getString("gemeente"));
	}
	
	public void createReservatie(long klantid,long filmid) {
		try (Connection connection = getConnection()) {
			PreparedStatement statementCreate = connection.prepareStatement(CREATE_RESERVATIE_SQL);
			PreparedStatement statementUpdate = connection.prepareStatement(UPDATE_FILMS_SQL);
			PreparedStatement statementSelect = connection.prepareStatement(FIND_FILM_BY_ID_SQL);
			connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			connection.setAutoCommit(false);
			statementSelect.setLong(1, filmid);
			ResultSet resultSet = statementSelect.executeQuery();
			if (resultSet.next()) {
				Long voorraad = resultSet.getLong("voorraad");
				Long gereserveerd = resultSet.getLong("gereserveerd");
				if (voorraad>gereserveerd) {
					statementCreate.setLong(1, klantid);
					statementCreate.setLong(2, filmid);
					java.util.Date today = new java.util.Date();
					statementCreate.setDate(3, new java.sql.Date(today.getTime()));
					statementUpdate.setLong(1, filmid);
					statementUpdate.execute();
					statementCreate.execute();
					connection.commit();
				}
			}	
		}
		catch (SQLException ex) {
			throw new DAOException("Kan reservatie niet toevoegen aan database", ex);
		}
	}
	
	public Iterable<Reservatie> getReservaties() {
		try (Connection connection = getConnection()) {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(FIND_RESERVATIES_SQL);
			List<Reservatie> reservaties = new ArrayList<>();
			while (resultSet.next()) {
				reservaties.add(resultSetRijNaarReservatie(resultSet));
			}
			return reservaties;
		} catch (SQLException ex) {
			throw new DAOException("Kan reservaties niet lezen uit database", ex);
		}
	}
	
	private Reservatie resultSetRijNaarReservatie(ResultSet resultSet) throws SQLException {
		return new Reservatie(resultSet.getString("titel"), resultSet.getString("familienaam")
				, resultSet.getString("voornaam"), resultSet.getDate("reservatieDatum"));
	}
	
}
