package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoVai implements Comando{
	
	IO interazione;
	private String direzione;

	@Override
	public void esegui(Partita partita) {
	    Stanza stanzaCorrente = partita.getStanzaCorrente();
	    Stanza prossimaStanza = null;

	    if (direzione == null) {
	        interazione.mostraMessaggio("Dove vuoi andare?\nDevi specificare una direzione");
	        return;
	    }

	    prossimaStanza = stanzaCorrente.getStanzaAdiacente(this.direzione);
	    if (prossimaStanza == null) {
	        interazione.mostraMessaggio("Direzione inesistente");
	        return;
	    }

	    partita.setStanzaCorrente(prossimaStanza);
	    interazione.mostraMessaggio(partita.getStanzaCorrente().getNome());
	    partita.getGiocatore().setCfu(partita.getGiocatore().getCfu() - 1);
	}	


	@Override
	public void setParametro(String direzione, IO interazione) {
		// TODO Auto-generated method stub
		this.direzione = direzione;
		this.interazione = interazione;
	}
	
}
