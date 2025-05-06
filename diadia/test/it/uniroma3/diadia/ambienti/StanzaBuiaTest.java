package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Test unitari per la classe StanzaBuia
 */
class StanzaBuiaTest {

    private StanzaBuia stanzaBuia;
    private Attrezzo lanterna;
    private Attrezzo torcia;

    @BeforeEach
    void setUp() {
        // creo una stanza buia che richiede "lanterna"
        stanzaBuia = new StanzaBuia("cripta", "lanterna");
        lanterna   = new Attrezzo("lanterna", 1);
        torcia     = new Attrezzo("Torcia", 2);
    }

    @Test
    void testDescrizioneSenzaLanterna() {
        String desc = stanzaBuia.getDescrizione();
        assertTrue(desc.toLowerCase().contains("buio pesto"),
                   "Senza la lanterna deve comparire 'buio pesto'");
    }

    @Test
    void testDescrizioneConLanterna() {
        stanzaBuia.addAttrezzo(lanterna);
        String desc = stanzaBuia.getDescrizione();
        assertFalse(desc.toLowerCase().contains("buio pesto"),
                    "Con la lanterna non deve esserci più buio pesto");
        assertTrue(desc.toLowerCase().contains("cripta"),
                   "Deve contenere il nome della stanza nella descrizione");
    }

    @Test
    void testDescrizioneConAttrezzoDiverso() {
        // aggiungo un attrezzo diverso da quello richiesto
        stanzaBuia.addAttrezzo(torcia);
        String desc = stanzaBuia.getDescrizione();
        // dovrebbe ancora essere buio pesto perché il nome richiesto è diverso (case-sensitive)
        assertTrue(desc.toLowerCase().contains("buio pesto"),
                   "Con un attrezzo diverso da 'lanterna' deve restare buio pesto");
    }

    @Test
    void testDescrizioneCaseInsensitive() {
        // aggiungo un attrezzo con nome diverso solo per maiuscole
        Attrezzo lanternaMaiuscola = new Attrezzo("Lanterna", 1);
        stanzaBuia.addAttrezzo(lanternaMaiuscola);
        String desc = stanzaBuia.getDescrizione();
        // se hasAttrezzo è case-sensitive, rimane buio; qui verifichiamo che richiesta sia esatta
        assertTrue(desc.toLowerCase().contains("buio pesto"),
                   "L'attrezzo 'Lanterna' (diverso case) non dovrebbe sbloccare se il codice è case-sensitive");
    }

    @Test
    void testAggiuntaMultipliAttrezzi() {
        // aggiungo prima torcia poi lanterna
        stanzaBuia.addAttrezzo(torcia);
        stanzaBuia.addAttrezzo(lanterna);
        String desc = stanzaBuia.getDescrizione();
        assertFalse(desc.toLowerCase().contains("buio pesto"),
                    "Quando lanterna è presente, anche con altri attrezzi, non deve esserci buio pesto");
    }
}
