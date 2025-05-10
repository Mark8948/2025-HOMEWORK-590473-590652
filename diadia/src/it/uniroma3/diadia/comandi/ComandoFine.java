package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoFine implements Comando {
	
	IO interazione;
	
	@Override
	public void esegui(Partita partita) {
		// TODO Auto-generated method stub
		interazione.mostraMessaggio("Grazie di aver giocato!"); // si desidera smettere
		partita.setFinita();
	}

	@Override
	public void setParametro(String parametro, IO interazione) {
		// TODO Auto-generated method stub
		this.interazione = interazione;
	}

}
