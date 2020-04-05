package it.polito.tdp.porto.model;

import java.util.List;

public class CoAuthor {
private String id;
private Author autore1;
private Author autore2;
private int idPaper;
public CoAuthor(Author autore1, Author autore2,String id, int idPaper) {
	super();
	this.id=id;
	this.autore1 = autore1;
	this.autore2 = autore2;
	this.idPaper= idPaper;
}

public int getIdPaper() {
	return idPaper;
}

public void setIdPaper(int idPaper) {
	this.idPaper = idPaper;
}

public String getId() {
	return id;
}

public void setId(String id) {
	this.id = id;
}

public Author getAutore1() {
	return autore1;
}
public void setAutore1(Author autore1) {
	this.autore1 = autore1;
}
public Author getAutore2() {
	return autore2;
}
public void setAutore2(Author autore2) {
	this.autore2 = autore2;
}




@Override
public String toString() {
	return String.format("autore1=%s, autore2=%s;\n", autore1, autore2);
}

@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	return result;
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	CoAuthor other = (CoAuthor) obj;
	if (id == null) {
		if (other.id != null)
			return false;
	} else if (!id.equals(other.id))
		return false;
	return true;
}

}
