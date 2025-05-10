package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

class ComandoVaiTest {

    private ComandoVai comando;
    private Partita partita;
    private Stanza stanzaIniziale;
    private Stanza stanzaAdiacente;

    @BeforeEach
    void setUp() {
        comando = new ComandoVai();
        partita = new Partita();

        // Creiamo stanze manualmente per il test
        stanzaIniziale = new Stanza("Atrio");
        stanzaAdiacente = new Stanza("Biblioteca");
        stanzaIniziale.impostaStanzaAdiacente("nord", stanzaAdiacente);
        stanzaAdiacente.impostaStanzaAdiacente("sud", stanzaIniziale);

        partita.setStanzaCorrente(stanzaIniziale);
        partita.getGiocatore().setCfu(4);
    }

    @Test
    void testNonSiMuoveSeParametroMancante() {
        comando.setParametro(null, new FakeIO());
        comando.esegui(partita);
        assertEquals(stanzaIniziale, partita.getStanzaCorrente(), "La stanza corrente non deve cambiare se manca il parametro");
        assertEquals(4, partita.getCfu(), "I CFU non devono cambiare se manca il parametro");
    }

    @Test
    void testNonSiMuoveSeDirezioneInesistente() {
        comando.setParametro("sud", new FakeIO());
        comando.esegui(partita);
        assertEquals(stanzaIniziale, partita.getStanzaCorrente(), "La stanza corrente non deve cambiare se la direzione non esiste");
        assertEquals(4, partita.getCfu(), "I CFU non devono cambiare se la direzione non esiste");
    }

    @Test
    void testSiMuoveEDiminuisceCfu() {
        comando.setParametro("nord", new FakeIO());
        comando.esegui(partita);
        assertEquals(stanzaAdiacente, partita.getStanzaCorrente(), "La stanza corrente deve cambiare nella direzione specificata");
        assertEquals(3, partita.getCfu(), "I CFU devono diminuire di 1 dopo un movimento valido");
    }

    @Test
    void testPartitaFinisceDopo4Mosse() {
        comando.setParametro("nord", new FakeIO());
        comando.esegui(partita); // 3 CFU
        comando.setParametro("sud", new FakeIO());
        comando.esegui(partita); // 2 CFU
        comando.setParametro("nord", new FakeIO());
        comando.esegui(partita); // 1 CFU
        comando.setParametro("sud", new FakeIO());
        comando.esegui(partita); // 0 CFU
        assertTrue(partita.isFinita(), "La partita deve finire quando i CFU raggiungono zero");
    }

    // Classe finta per evitare NullPointer su interazione
    private static class FakeIO implements IO {
        @Override
        public void mostraMessaggio(String messaggio) {
            // Non fare nulla nei test
        }

		@Override
		public String leggiRiga() {
			// TODO Auto-generated method stub
			return null;
		}
    }
}