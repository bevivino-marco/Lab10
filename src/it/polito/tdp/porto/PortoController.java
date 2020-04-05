package it.polito.tdp.porto;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.porto.model.Author;
import it.polito.tdp.porto.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class PortoController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Author> boxPrimo;

    @FXML
    private ComboBox<Author> boxSecondo;

    @FXML
    private TextArea txtResult;

	private Model model;

    @FXML
    void handleCoautori(ActionEvent event) {
    	txtResult.clear();
       try {if (boxPrimo.getValue()!=null ) {
    	   txtResult.appendText(model.getCoautori(boxPrimo.getValue()).toString());
       }else  txtResult.appendText("selezionare il primo autore");
    	   }catch (Exception e ) {
    	   txtResult.appendText("l autore non ha coautori");
       }
       boxSecondo.getItems().addAll(model.getAutoriNonCoAutori(boxPrimo.getValue()));
    }

    @FXML
    void handleSequenza(ActionEvent event) {
       try { if (boxSecondo.getValue()!=null) {
    	   txtResult.appendText(model.getCamminoMinimo(boxPrimo.getValue(), boxSecondo.getValue()).toString());
       }else {
    	   txtResult.appendText("selezionare il secondo autore");
       }}catch (Exception e ) {
    	   txtResult.appendText("gli autori non hanno un cammino");
       }
    }

    @FXML
    void initialize() {
        assert boxPrimo != null : "fx:id=\"boxPrimo\" was not injected: check your FXML file 'Porto.fxml'.";
        assert boxSecondo != null : "fx:id=\"boxSecondo\" was not injected: check your FXML file 'Porto.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Porto.fxml'.";

    }

	public void setModel(Model m) {
		this.model=m;
		boxPrimo.getItems().addAll(model.getAutori());
		
	}
}
