package it.polito.tdp.porto.model;

import java.util.LinkedList;
import java.util.List;

public class TestModel {

	public static void main(String[] args) {
		
		Model model = new Model();

		model.creaGrafo();
		List<Author> l = new LinkedList<>(model.getAutori());
		System.out.println("\n Autore1 : "+l.get(0));
		System.out.println("\n Autore2 : "+l.get(5));
		//model.camminoMinimo(l.get(0), l.get(5));
		model.getCamminoMinimo(l.get(0), l.get(5));
	}

}
