package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class ComandoPosaTest {

    private ComandoPosa comando;
    private Partita partita;
    private Stanza stanza;
    private Attrezzo attrezzo;

    @BeforeEach
    void setUp() {
        comando = new ComandoPosa();
        partita = new Partita();
        stanza = new Stanza("Sala");
        partita.setStanzaCorrente(stanza);

        // Preparo giocatore con un attrezzo in borsa
        attrezzo = new Attrezzo("chiave", 1);
        partita.getGiocatore().addAttrezzo(attrezzo);
    }

    @Test
    void testNonPuoPosareSenzaParametro() {
        comando.setParametro(null, new FakeIO());
        comando.esegui(partita);
        // Attrezzo ancora nella borsa e non nella stanza
        assertTrue(partita.getGiocatore().getBorsa().hasAttrezzo("chiave"));
        assertNull(stanza.getAttrezzo("chiave"));
    }

    @Test
    void testPosaAttrezzoEsistente() {
        comando.setParametro("chiave", new FakeIO());
        comando.esegui(partita);
        // Attrezzo rimosso dalla borsa e aggiunto in stanza
        assertFalse(partita.getGiocatore().getBorsa().hasAttrezzo("chiave"));
        assertNotNull(stanza.getAttrezzo("chiave"));
    }

    @Test
    void testPosaAttrezzoNonPresente() {
        comando.setParametro("spada", new FakeIO());
        comando.esegui(partita);
        // Nessuna modifica
        assertFalse(partita.getGiocatore().getBorsa().hasAttrezzo("spada"));
        assertNull(stanza.getAttrezzo("spada"));
    }

    private static class FakeIO implements IO {
        @Override public void mostraMessaggio(String messaggio) {}
        @Override public String leggiRiga() { return null; }
    }
}