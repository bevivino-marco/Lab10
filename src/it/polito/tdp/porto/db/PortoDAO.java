package it.polito.tdp.porto.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.porto.model.Author;
import it.polito.tdp.porto.model.CoAuthor;
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
	public CoAuthor getCoAutori(Map <Integer ,Author> mappaA, Author autore) {

		final String sql = "SELECT c2.authorid " + 
				"FROM creator AS c1, creator AS c2 " + 
				"WHERE c1.authorid='?' AND c1.eprintid=c2.eprintid AND c1.authorid!= c2.authorid ";
		
            
		try {
			if (mappaA.containsKey(autore.getId())) {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, autore.getId());
            List <Author> listaC = new LinkedList<Author>();
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				if (mappaA.containsKey(rs.getInt("authorid"))) {
					listaC.add(mappaA.get(rs.getInt("authorid")));
				}
			}
            CoAuthor c = new CoAuthor (autore, listaC);
			return c;} else return null;

		} catch (SQLException e) {
			 e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}
	
}