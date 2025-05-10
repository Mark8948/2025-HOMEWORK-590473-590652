package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendi implements Comando {

    IO interazione;
    private String nomeAttrezzo;


    /** Il parametro comando è solo il nome dell’attrezzo */
    @Override
    public void setParametro(String nomeAttrezzo, IO interazione) {
        this.nomeAttrezzo = nomeAttrezzo;
        this.interazione = interazione;
    }

    @Override
    public void esegui(Partita partita) {
        
        if (this.nomeAttrezzo == null) {
            interazione.mostraMessaggio("Non hai selezionato un attrezzo!");
            return;
        }

        
        Stanza stanzaCorrente = partita.getStanzaCorrente();
        Attrezzo attrezzo = stanzaCorrente.getAttrezzo(this.nomeAttrezzo);

        
        if (attrezzo == null) {
            interazione.mostraMessaggio(this.nomeAttrezzo + " non c'è nella stanza!");
            return;
        }

        
        if (partita.getGiocatore().addAttrezzo(attrezzo)) {
            stanzaCorrente.removeAttrezzo(attrezzo);
            interazione.mostraMessaggio("Hai preso: " + this.nomeAttrezzo);
        } else {
            interazione.mostraMessaggio("Inventario pieno!");
        }
    }
}