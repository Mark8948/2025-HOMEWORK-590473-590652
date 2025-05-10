package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoGuarda implements Comando {
	
	//private String guardaStanza;
	IO interazione;
	
	@Override
	public void esegui(Partita partita) {
		// TODO Auto-generated method stub
		interazione.mostraMessaggio(toString(partita));
	}
	
	public String toString(Partita partita){
		StringBuilder s = new StringBuilder();

		s.append("Informazioni partita: (Stanza: " + partita.getStanzaCorrente() + " Cfu rimasti: " + partita.getCfu() + "CFU)");
			
		return s.toString();
	}

	@Override
	public void setParametro(String guardaStanza, IO interazione) {
		// TODO Auto-generated method stub
		//this.guardaStanza = guardaStanza;
		this.interazione = interazione;
	}

}
