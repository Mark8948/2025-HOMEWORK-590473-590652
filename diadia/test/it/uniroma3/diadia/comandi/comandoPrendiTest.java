package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class ComandoPrendiTest {

    private ComandoPrendi comando;
    private Partita partita;
    private Stanza stanza;
    private Attrezzo attrezzo;

    @BeforeEach
    void setUp() {
        comando = new ComandoPrendi();
        partita = new Partita();
        stanza = new Stanza("Corridoio");
        partita.setStanzaCorrente(stanza);

        // Preparo stanza con un attrezzo
        attrezzo = new Attrezzo("lanterna", 2);
        stanza.addAttrezzo(attrezzo);
    }

    @Test
    void testNonPuoPrendereSenzaParametro() {
        comando.setParametro(null, new FakeIO());
        comando.esegui(partita);
        assertNull(partita.getGiocatore().getBorsa().getAttrezzo("lanterna"));
        assertNotNull(stanza.getAttrezzo("lanterna"));
    }

    @Test
    void testInventarioPieno() {
        // Riempio la borsa con attrezzi fino al peso massimo
        for (int i = 0; i < 10; i++) {
            partita.getGiocatore().addAttrezzo(new Attrezzo("t" + i, 1));
        }
        comando.setParametro("lanterna", new FakeIO());
        comando.esegui(partita);
        // Attrezzo non preso perché inventario pieno
        assertNull(partita.getGiocatore().getBorsa().getAttrezzo("lanterna"));
        assertNotNull(stanza.getAttrezzo("lanterna"));
    }

    private static class FakeIO implements IO {
        @Override public void mostraMessaggio(String messaggio) {}
        @Override public String leggiRiga() { return null; }
    }
}