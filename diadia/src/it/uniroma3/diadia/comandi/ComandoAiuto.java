package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoAiuto implements Comando {
	
	IO interazione;
	static final private String[] elencoComandi = { "vai", "aiuto", "fine", "prendi", "posa", "guarda" };
	
	@Override
	public void esegui(Partita partita) {
		// TODO Auto-generated method stub
		for (int i = 0; i < elencoComandi.length; i++)
			interazione.mostraMessaggio(elencoComandi[i] + " ");
		interazione.mostraMessaggio("");
	}

	@Override
	public void setParametro(String parametro, IO interazione) {
		// TODO Auto-generated method stub
		this.interazione = interazione;
	}

}
