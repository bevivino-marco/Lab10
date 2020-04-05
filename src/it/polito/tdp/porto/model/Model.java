package it.polito.tdp.porto.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.porto.db.PortoDAO;

public class Model {
   private Map <Integer , Author> mappaA;
   private List <Author> listaA;
   private Map <Integer , Paper> mappaP;
   private List <Paper> listaP;
   private Map <CoAuthor> mappaC;
public Model() {
	PortoDAO dao = new PortoDAO();
  mappaA= dao.getAutori(mappaA);
  listaA = new LinkedList<> (mappaA.values());
  mappaP = dao.getArticoli(mappaP);
  listaP = new LinkedList<>(mappaP.values());
}
   
}
