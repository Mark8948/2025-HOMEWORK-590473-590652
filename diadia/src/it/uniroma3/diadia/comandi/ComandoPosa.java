package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Giocatore;

public class ComandoPosa implements Comando {
	
	IO interazione;
	private String nomeAttrezzo;
	
	
	@Override
	public void esegui(Partita partita) {
		// TODO Auto-generated method stub
		if (nomeAttrezzo == null) {
			interazione.mostraMessaggio("Non hai selezionato un attrezzo esistente!!!!");

			// return null;
		}

		Giocatore giocatore = partita.getGiocatore();
		Attrezzo attrezzo = giocatore.removeAttrezzo(nomeAttrezzo);

		if (attrezzo == null)
			interazione.mostraMessaggio("Non hai questo attrezzo!!!");
		else {
			partita.getStanzaCorrente().addAttrezzo(attrezzo);
			// giocatore.removeAttrezzo(nomeAttrezzo);
			interazione.mostraMessaggio("Hai posato: " + nomeAttrezzo);
		}
	}

	@Override
	public void setParametro(String nomeAttrezzo, IO interazione) {
		// TODO Auto-generated method stub
		this.nomeAttrezzo = nomeAttrezzo;
		this.interazione = interazione;
	}

}
