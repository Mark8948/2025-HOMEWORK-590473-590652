package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

class StanzaTest {

    private Stanza stanza;
    private Attrezzo attrezzo1;
    private Attrezzo attrezzo2;

    @BeforeEach
    void setUp() {
        stanza = new Stanza("Biblioteca");
        attrezzo1 = new Attrezzo("Libro", 2);
        attrezzo2 = new Attrezzo("Chiave", 1);
    }

    // Test sul metodo getNome()
    @Test
    void testGetNome() {
        assertEquals("Biblioteca", stanza.getNome(), "Il nome della stanza dovrebbe essere 'Biblioteca'");
    }

    // Test per verificare l'impostazione e il recupero di una stanza adiacente
    @Test
    void testImpostaEGetStanzaAdiacente() {
        Stanza adiacente = new Stanza("Atrio");
        stanza.impostaStanzaAdiacente("nord", adiacente);
        assertEquals(adiacente, stanza.getStanzaAdiacente("nord"), "La stanza adiacente in direzione 'nord' dovrebbe essere 'Atrio'");
    }

    // Test per addAttrezzo, hasAttrezzo e getAttrezzo
    @Test
    void testGestioneAttrezzi() {
        // Aggiunta attrezzo e verifica presenza
        assertTrue(stanza.addAttrezzo(attrezzo1), "L'attrezzo 'Libro' dovrebbe essere aggiunto correttamente");
        assertTrue(stanza.hasAttrezzo("Libro"), "La stanza dovrebbe contenere l'attrezzo 'Libro'");
        assertEquals(attrezzo1, stanza.getAttrezzo("Libro"), "Il recupero dell'attrezzo 'Libro' dovrebbe restituire l'oggetto corretto");
        
        // Verifica che un attrezzo non aggiunto non venga trovato
        assertFalse(stanza.hasAttrezzo("Chiave"), "La stanza non dovrebbe contenere l'attrezzo 'Chiave'");
    }

    // Ulteriore test per il metodo toString(), verificando che contenga almeno il nome e le uscite
    @Test
    void testToStringContenuto() {
        Stanza adiacente = new Stanza("Atrio");
        stanza.impostaStanzaAdiacente("est", adiacente);
        stanza.addAttrezzo(attrezzo1);
        String descrizione = stanza.toString();
        assertTrue(descrizione.contains("Biblioteca"), "La descrizione dovrebbe contenere il nome della stanza");
        assertTrue(descrizione.contains("est"), "La descrizione dovrebbe indicare la presenza di una uscita 'est'");
        assertTrue(descrizione.contains("Libro"), "La descrizione dovrebbe riportare l'attrezzo 'Libro'");
    }

    // Test per removeAttrezzo (attualmente non implementato, ci si aspetta sempre false)
    @Test
    void testRemoveAttrezzo() {
        stanza.addAttrezzo(attrezzo2);
        assertFalse(stanza.removeAttrezzo(attrezzo2), "Il metodo removeAttrezzo, non implementato, dovrebbe restituire false");
    }
}