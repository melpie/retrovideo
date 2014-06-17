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

public class VideoDAO extends AbstractDAO {
	
	private static final String FIND_GENRES_SQL =
		"select id,naam from genres order by naam";
	
	private static final String FIND_FILM_BY_GENRE_SQL =
	    "select id,genreid,titel,voorraad,gereserveerd,prijs from films where genreid=?";	
	
	private static final String FIND_FILM_BY_ID_SQL =
		    "select id,genreid,titel,voorraad,gereserveerd,prijs from films where id=?";	
	
	public Iterable<Genre> getGenres() {
		try (Connection connection = getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(FIND_GENRES_SQL);) {
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
        try (Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(FIND_FILM_BY_GENRE_SQL);) {
            List<Film> films = new ArrayList<>();
            statement.setLong(1, genreid);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    films.add(resultSetRijNaarFilm(resultSet));
                }
                return films;
            }
        } catch (SQLException ex) {
            throw new DAOException("Kan films niet lezen uit database", ex);
        }
    }
	
	public Film findFilmByID(long filmid) {
        try (Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(FIND_FILM_BY_ID_SQL);) {
            Film film = null;
            statement.setLong(1, filmid);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    film = resultSetRijNaarFilm(resultSet);
                }
                return film;
            }
        } catch (SQLException ex) {
            throw new DAOException("Kan film niet lezen uit database", ex);
        }
	}
	
	
	private Film resultSetRijNaarFilm(ResultSet resultSet) throws SQLException {
		return new Film(resultSet.getLong("id"), resultSet.getLong("genreid"), resultSet.getString("titel"), resultSet.getLong("voorraad")
				, resultSet.getLong("gereserveerd"), resultSet.getDouble("prijs"));
	}

}
