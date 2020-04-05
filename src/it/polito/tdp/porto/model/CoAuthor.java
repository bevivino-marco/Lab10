package it.polito.tdp.porto.model;

import java.util.List;

public class CoAuthor {
private Author autore;
private List <Author> Coautori;
public CoAuthor(Author autore, List<Author> coautori) {
	super();
	this.autore = autore;
	Coautori = coautori;
}
public Author getAutore() {
	return autore;
}
public void setAutore(Author autore) {
	this.autore = autore;
}
public List<Author> getCoautori() {
	return Coautori;
}
public void setCoautori(List<Author> coautori) {
	Coautori = coautori;
}
@Override
public String toString() {
	return String.format("autore=%s:\nCoautori=%s]", autore, Coautori);
}
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((autore == null) ? 0 : autore.hashCode());
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
	if (autore == null) {
		if (other.autore != null)
			return false;
	} else if (!autore.equals(other.autore))
		return false;
	return true;
}

}
