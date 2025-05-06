package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaBloccataTest {

    private StanzaBloccata stanzaBloccata;
    private Stanza    stanzaDiDestinazione;
    private Attrezzo  piedeDiPorco;

    @BeforeEach
    void setUp() {
        // creo una stanza bloccata a est, sbloccabile con "piedeDiPorco"
        stanzaBloccata       = new StanzaBloccata("corridoio", "est", "piedeDiPorco");
        stanzaDiDestinazione = new Stanza("cella adiacente");
        stanzaBloccata.impostaStanzaAdiacente("est", stanzaDiDestinazione);
        piedeDiPorco = new Attrezzo("piedeDiPorco", 5);
    }

    @Test
    void testGetStanzaAdiacenteSenzaAttrezzo() {
        Stanza result = stanzaBloccata.getStanzaAdiacente("est");
        assertSame(stanzaBloccata, result,
                   "Senza il piedeDiPorco non ci si deve spostare, ma restare sulla stessa stanza");
    }

    @Test
    void testGetStanzaAdiacenteConAttrezzo() {
        stanzaBloccata.addAttrezzo(piedeDiPorco);
        Stanza result = stanzaBloccata.getStanzaAdiacente("est");
        assertSame(stanzaDiDestinazione, result,
                   "Con il piedeDiPorco ci si deve spostare nella stanza adiacente");
    }

    @Test
    void testDescrizioneSenzaAttrezzo() {
        String desc = stanzaBloccata.getDescrizione();
        assertTrue(desc.toLowerCase().contains("direzione 'est'"),
                   "La descrizione deve menzionare la direzione bloccata");
        assertTrue(desc.toLowerCase().contains("piedediporco"),
                   "Deve indicare quale attrezzo serve per sbloccare");
    }

    @Test
    void testDescrizioneConAttrezzo() {
        stanzaBloccata.addAttrezzo(piedeDiPorco);
        String desc = stanzaBloccata.getDescrizione();
        assertFalse(desc.toLowerCase().contains("bloccata"),
                    "Con l'attrezzo non deve più comparire la nota di blocco");
        assertTrue(desc.toLowerCase().contains("corridoio"),
                   "Deve restituire la descrizione normale (contenente il nome della stanza)");
    }
}
