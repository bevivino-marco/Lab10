package it.polito.tdp.porto.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import it.polito.tdp.porto.model.Author;
import it.polito.tdp.porto.model.Paper;

public class PortoDAO {

	/*
	 * Dato l'id ottengo l'autore.
	 */
	public Map <Integer,Author> getAutori(Map <Integer,Author> mappaA) {

		final String sql = "SELECT * FROM author ";

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			

			ResultSet rs = st.executeQuery();

			while (rs.next()) {
                if (!mappaA.containsKey(rs.getInt("id"))) {
				Author autore = new Author(rs.getInt("id"), rs.getString("lastname"), rs.getString("firstname"));
                mappaA.put(autore.getId(), autore);
                }
			}

			return mappaA;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}

	/*
	 * Dato l'id ottengo l'articolo.
	 */
	public Map <Integer , Paper> getArticoli(Map <Integer , Paper> mappaP) {

		final String sql = "SELECT * FROM paper ";

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			

			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				if (!mappaP.containsKey(rs.getInt("eprintid"))) {
				Paper paper = new Paper(rs.getInt("eprintid"), rs.getString("title"), rs.getString("issn"),
						rs.getString("publication"), rs.getString("type"), rs.getString("types"));
				mappaP.put(paper.getEprintid(), paper);
				}
			}

			return mappaP;

		} catch (SQLException e) {
			 e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}
}