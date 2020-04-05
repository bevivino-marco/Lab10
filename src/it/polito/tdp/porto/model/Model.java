package it.polito.tdp.porto.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.jgrapht.GraphPath;
import org.jgrapht.Graphs;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import it.polito.tdp.porto.db.PortoDAO;

public class Model {
   private Map <Integer , Author> mappaA;
   private List <Author> listaA;
   private Map <Integer , Paper> mappaP;
   private List <Paper> listaP;
   private Map <String, CoAuthor> mappaC;
   private SimpleGraph<Author, DefaultEdge> grafo;
   private PortoDAO dao;
public Model() {
	dao = new PortoDAO();
  mappaA = new HashMap<Integer , Author>();
  mappaP = new HashMap<Integer , Paper>();
  mappaA= dao.getAutori(mappaA);
  listaA = new LinkedList<> (mappaA.values());
  mappaP = dao.getArticoli(mappaP);
  listaP = new LinkedList<>(mappaP.values());
  mappaC = new HashMap<String, CoAuthor>();
  creaGrafo();
}
public void creaGrafo() {
	// 1. creo il grafo
	this.grafo =new SimpleGraph<>(DefaultEdge.class);
	
	// 2. aggiungo i vertici
	// 3. aggiungo gli archi
	mappaC = dao.getCoAutori(mappaA, mappaC);
	List <CoAuthor> listaC = new LinkedList <CoAuthor>(mappaC.values());
	for (CoAuthor c : listaC) {
		Author a1 = c.getAutore1();
		Author a2= c.getAutore2();
		grafo.addVertex(a1);
		grafo.addVertex(a2);
		grafo.addEdge(a1, a2);
	}
	
	System.out.println(grafo.vertexSet());
	System.out.println(grafo.vertexSet().size());
	System.out.println (grafo.edgeSet().toString());
	System.out.println (grafo.edgeSet().size());
}
public List <Author> getCoautori (Author autore){
	List <Author> coautori = new LinkedList <Author>(Graphs.neighborListOf(grafo, autore));
	return coautori;
}

public List <Author> getAutori(){
	return listaA;
}
public List <Author> getAutoriNonCoAutori(Author autore){
	List <Author> l = new LinkedList <Author> (listaA);
	l.removeAll(Graphs.neighborListOf(grafo, autore));
	return l;
}
public List<Author> camminoMinimo (Author a1 , Author a2) {
	DijkstraShortestPath <Author, DefaultEdge> dijkstra = new DijkstraShortestPath<>(this.grafo);
	GraphPath <Author, DefaultEdge>path = dijkstra.getPath(a1, a2);
	System.out.println(path.getVertexList());
	return path.getVertexList();
} 
public List <Connessioni> getCamminoMinimo(Author a1 , Author a2){
	List <Author> l= new ArrayList <Author>(camminoMinimo(a1,a2));
	List <Connessioni> lista = new LinkedList<>();
	for (int i=0; i<l.size()-2;i++) {
		Author autore1 = l.get(i);
		Author autore2 = l.get(i+1);
		for (CoAuthor c : mappaC.values()) {
			if ((c.getAutore1().equals(autore1) && c.getAutore2().equals(autore2))||
					(c.getAutore1().equals(autore2) && c.getAutore2().equals(autore1))) {
				Connessioni connessione = new Connessioni (autore1, autore2,mappaP.get(c.getIdPaper()));
		        lista.add(connessione);
		        System.out.println(connessione);
			}
		}
		
	}
	
	return lista;
}



}
