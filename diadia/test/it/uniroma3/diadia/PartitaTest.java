package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import it.uniroma3.diadia.ambienti.Stanza;


class PartitaTest {

    private Partita partita;

    @BeforeEach
    void setUp() {
        partita = new Partita();
    }

    // Test per verificare che all'inizio la partita non sia vinta
    @Test
    void testPartitaNonVintaInizialmente() {
        // All'avvio il gioco comincia nell'atrio, che non è la stanza vincente (biblioteca)
        assertFalse(partita.vinta(), "All'inizio la partita non dovrebbe essere vinta");
    }

    // Test per verificare il comportamento di vinta() quando si raggiunge la stanza vincente
    @Test
    void testVinta() {
        // Impostiamo manualmente la stanza corrente uguale a quella vincente
        partita.setStanzaCorrente(partita.getStanzaVincente());
        assertTrue(partita.vinta(), "La partita dovrebbe essere vinta se la stanza corrente è quella vincente");
    }

    // Test per isFinita() in base a diversi scenari: partita esplicitamente finita, CFU esauriti
    @Test
    void testIsFinita() {
        // Scenario 1: partita non finita all'inizio
        assertFalse(partita.isFinita(), "La partita non dovrebbe essere finita all'inizio");
        
        // Scenario 2: partita terminata esplicitamente
        partita.setFinita();
        assertTrue(partita.isFinita(), "La partita dovrebbe essere finita dopo aver invocato setFinita()");
        
        // Scenario 3: partita finita per CFU esauriti
        partita = new Partita();
        partita.setCfu(0);
        assertTrue(partita.isFinita(), "La partita dovrebbe essere finita se i CFU sono a 0");
    }

    // Test per verificare la gestione dei CFU
    @Test
    void testCfu() {
        int cfuIniziali = partita.getCfu();
        partita.setCfu(cfuIniziali - 5);
        assertEquals(cfuIniziali - 5, partita.getCfu(), "Il numero di CFU dovrebbe essere aggiornato correttamente");
    }

    // Test per verificare il corretto comportamento di getStanzaCorrente e setStanzaCorrente
    @Test
    void testStanzaCorrente() {
        Stanza nuovaStanza = new Stanza("NuovaStanza");
        partita.setStanzaCorrente(nuovaStanza);
        assertEquals(nuovaStanza, partita.getStanzaCorrente(), "La stanza corrente dovrebbe essere aggiornata correttamente");
    }
}