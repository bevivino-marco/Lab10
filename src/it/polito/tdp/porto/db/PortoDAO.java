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
	public  Map <String, CoAuthor> getCoAutori(Map <Integer ,Author> mappaA, Map <String, CoAuthor> mappaC) {

		final String sql = "SELECT  DISTINCT c1.authorid, c2.authorid, c1.eprintid " + 
				"FROM creator AS c1, creator AS c2 " + 
				"WHERE  c1.eprintid=c2.eprintid AND c1.authorid != c2.authorid " + 
				"GROUP BY c1.authorid, c2.authorid";
		
            
		try {
			
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
            
			ResultSet rs = st.executeQuery();
            int cont =0;
			while (rs.next()) {
				Author a1 = mappaA.get(rs.getInt("c1.authorid"));
				Author a2 = mappaA.get(rs.getInt("c2.authorid"));
				int idPaper = rs.getInt("c1.eprintid");
				String id =a1.getId()+""+a2.getId();
				String idDaControllare =a2.getId()+""+a1.getId();
				if (!mappaC.containsKey(idDaControllare)) {
					CoAuthor c = new CoAuthor (a1,a2,id, idPaper);
					mappaC.put(id, c);
				//	System.out.println(c.toString());
				//	cont++;
				}
			}
			//System.out.println(cont);
            return mappaC;

		} catch (SQLException e) {
			 e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}

	public Paper getArticoloInComune(Author autore1, Author autore2, Map <Integer , Paper> mappaP ) {
		final String sql = "SELECT c1.eprintid " + 
				"FROM creator AS c1 , creator AS c2 " + 
				"WHERE c1.authorid='?' AND c2.authorid= '?' AND c1.eprintid=c2.eprintid " + 
				"GROUP BY c1.eprintid";

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, autore1.getId());
			st.setInt(2, autore2.getId());
			

			ResultSet rs = st.executeQuery();

			if (rs.next()) {
                return mappaP.get(rs.getInt("c1.eprintid"));
			}
            return null;

		} catch (SQLException e) {
			// e.printStackTrace();
			//throw new RuntimeException("Errore Db");
			return null;
		}
	}
	
}