package it.polito.tdp.ruzzle;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import it.polito.tdp.ruzzle.model.Board;
import it.polito.tdp.ruzzle.model.Model;
import it.polito.tdp.ruzzle.model.PercorsiTrovati;
import it.polito.tdp.ruzzle.model.Cell;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController 
{	
    @FXML 
    private ResourceBundle resources;

    @FXML 
    private URL location;

    @FXML
    private Button let00; 

    @FXML 
    private Button let01; 

    @FXML 
    private Button let02; 

    @FXML 
    private Button let03; 

    @FXML 
    private Button let10; 

    @FXML 
    private Button let11; 

    @FXML 
    private Button let12; 

    @FXML 
    private Button let13; 

    @FXML 
    private Button let20; 

    @FXML 
    private Button let21; 

    @FXML 
    private Button let22; 

    @FXML 
    private Button let23; 

    @FXML 
    private Button let30; 

    @FXML 
    private Button let31; 

    @FXML 
    private Button let32;

    @FXML 
    private Button let33; 

    @FXML
    private TextField txtParola;

    @FXML
    private Button btnProva;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnRisolviTutto;

    @FXML
    private Label txtStatus;

    @FXML
    private TextArea txtResult;
    
    private Model model; 
	
	//corrispondenza caselle della Board <-> bottoni dell'interfaccia grafica
	private Map<Cell,Button> buttonsByCell;

    @FXML
    void handleProva(ActionEvent event) 
    {
    	//refresh interfaccia grafica
    	for(Button b : this.buttonsByCell.values()) 
    	{
    		b.setDefaultButton(false);
    	}
    		
    	String parola = this.txtParola.getText();
    	
    	if(parola.length() <= 1) 
    	{
    		this.txtResult.setText("Devi inserire parole di almeno 2 lettere");
    		return;
    	}
    	
    	if(parola.length() > 16) 
    	{
    		this.txtResult.setText("Parola troppo lunga!");
    		return;
    	}
    	
    	parola = parola.toUpperCase();
    	//controllo che ci siano solo caratteri A-Z
    	if(!parola.matches("[A-Z]+")) 
    	{
    		this.txtResult.setText("Devi inserire solo caratteri alfabetici!");
    		return ;
    	}
    	
    	PercorsiTrovati percorsi = this.model.trovaParola(parola);
    	
    	if(percorsi == null || percorsi.getPercorsi().isEmpty()) 
    	{
    		this.txtResult.setText("Parola non esistente nella scacchiera!");
    		return;
    	}
    	
    	for(List<Cell> percorso : percorsi.getPercorsi()) 
    		for(Cell p : percorso)
    			this.buttonsByCell.get(p).setDefaultButton(true);
    
    	if(!percorsi.haSenso())
    	{
    		this.txtResult.setText("Parola trovata NON di senso compiuto!");
    		return;
    	}
    	
    	if(percorsi.parolaGiaTrovata())
    	{
    		this.txtResult.setText("Parola già trovata!");
    		return;
    	}
    	
    	this.txtResult.setText("Trovata la nuova parola \""+ parola +"\"!");
    }

    @FXML
    void handleReset(ActionEvent event) 
    {
    	for(Button b : this.buttonsByCell.values()) 
    	{
    		b.setDefaultButton(false);
    	}
    	
    	this.txtResult.clear();
    	this.txtParola.clear();
    	this.model.reset();
    }
    
    @FXML
    void handleRisolviTutto(ActionEvent event) 
    {
    	List<String> tutte = this.model.trovaTutteleParolePresenti();
    	
    	StringBuilder sb = new StringBuilder();
    	sb.append("Ho trovato ").append(tutte.size()).append(" soluzioni:\n\n");
    	
    	for(String s : tutte) 
    	{
    		sb.append(s).append("\n");
    	}
    	
    	this.txtResult.setText(sb.toString());
    }
    
    @FXML 
    void initialize() 
    {
    	assert let00 != null : "fx:id=\"let00\" was not injected: check your FXML file 'Scene_Ruzzle.fxml'.";
        assert let01 != null : "fx:id=\"let01\" was not injected: check your FXML file 'Scene_Ruzzle.fxml'.";
        assert let02 != null : "fx:id=\"let02\" was not injected: check your FXML file 'Scene_Ruzzle.fxml'.";
        assert let03 != null : "fx:id=\"let03\" was not injected: check your FXML file 'Scene_Ruzzle.fxml'.";
        assert let10 != null : "fx:id=\"let10\" was not injected: check your FXML file 'Scene_Ruzzle.fxml'.";
        assert let11 != null : "fx:id=\"let11\" was not injected: check your FXML file 'Scene_Ruzzle.fxml'.";
        assert let12 != null : "fx:id=\"let12\" was not injected: check your FXML file 'Scene_Ruzzle.fxml'.";
        assert let13 != null : "fx:id=\"let13\" was not injected: check your FXML file 'Scene_Ruzzle.fxml'.";
        assert let20 != null : "fx:id=\"let20\" was not injected: check your FXML file 'Scene_Ruzzle.fxml'.";
        assert let21 != null : "fx:id=\"let21\" was not injected: check your FXML file 'Scene_Ruzzle.fxml'.";
        assert let22 != null : "fx:id=\"let22\" was not injected: check your FXML file 'Scene_Ruzzle.fxml'.";
        assert let23 != null : "fx:id=\"let23\" was not injected: check your FXML file 'Scene_Ruzzle.fxml'.";
        assert let30 != null : "fx:id=\"let30\" was not injected: check your FXML file 'Scene_Ruzzle.fxml'.";
        assert let31 != null : "fx:id=\"let31\" was not injected: check your FXML file 'Scene_Ruzzle.fxml'.";
        assert let32 != null : "fx:id=\"let32\" was not injected: check your FXML file 'Scene_Ruzzle.fxml'.";
        assert let33 != null : "fx:id=\"let33\" was not injected: check your FXML file 'Scene_Ruzzle.fxml'.";
        assert txtParola != null : "fx:id=\"txtParola\" was not injected: check your FXML file 'Scene_Ruzzle.fxml'.";
        assert btnProva != null : "fx:id=\"btnProva\" was not injected: check your FXML file 'Scene_Ruzzle.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'Scene_Ruzzle.fxml'.";
        assert btnRisolviTutto != null : "fx:id=\"btnRisolviTutto\" was not injected: check your FXML file 'Scene_Ruzzle.fxml'.";
        assert txtStatus != null : "fx:id=\"txtStatus\" was not injected: check your FXML file 'Scene_Ruzzle.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene_Ruzzle.fxml'.";
    }
    
    public void setModel(Model newModel) 
    {
    	this.model = newModel;
    	Board board = this.model.getBoard();
    	
    	this.buttonsByCell = new HashMap<>();
    	
    	this.buttonsByCell.put(board.getCell(0,0), let00);
    	this.buttonsByCell.put(board.getCell(0,1), let01);
    	this.buttonsByCell.put(board.getCell(0,2), let02);
    	this.buttonsByCell.put(board.getCell(0,3), let03);
    	
    	this.buttonsByCell.put(board.getCell(1,0), let10);
    	this.buttonsByCell.put(board.getCell(1,1), let11);
    	this.buttonsByCell.put(board.getCell(1,2), let12);
    	this.buttonsByCell.put(board.getCell(1,3), let13);

    	this.buttonsByCell.put(board.getCell(2,0), let20);
    	this.buttonsByCell.put(board.getCell(2,1), let21);
    	this.buttonsByCell.put(board.getCell(2,2), let22);
    	this.buttonsByCell.put(board.getCell(2,3), let23);
    	
    	this.buttonsByCell.put(board.getCell(3,0), let30);
    	this.buttonsByCell.put(board.getCell(3,1), let31);
    	this.buttonsByCell.put(board.getCell(3,2), let32);
    	this.buttonsByCell.put(board.getCell(3,3), let33);

    	//BINDNG: associo ad ogni bottone la StringProperty della relativa cella
    	for(Cell cell : board) 
    	{
    		Button b = this.buttonsByCell.get(cell);
    		StringProperty sp = b.textProperty();
    		sp.bind(cell.getStringProperty());
    	}
    	
    	this.txtStatus.textProperty().bind(newModel.statusTextProperty());
    	
    	this.model.reset();
    }
}
