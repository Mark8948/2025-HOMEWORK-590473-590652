package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test di accettazione che simulano intere partite usando IOSimulator e DiaDia
 */
public class IOSimulatorTest {

    private IOSimulator io;
    private DiaDia gioco;

    @BeforeEach
    public void setup() {
        io = new IOSimulator();
        gioco = new DiaDia(io);   // Inietta IOSimulator nel gioco
    }

    /**
     * Simula una partita terminata con il comando 'fine'
     * Verifica che compaia il messaggio di addio
     */
    @Test
    public void testPartitaTerminaConFine() {
        String[] comandi = {"fine"};
        io.setRigheDaLeggere(comandi);

        gioco.gioca();

        String[] output = io.getMessaggiStampati();
        assertTrue(output.length > 0, "Nessun messaggio stampato");
        String ultimo = output[output.length - 1];
        assertTrue(ultimo.toLowerCase().contains("grazie"),
                   "Il messaggio di addio non è presente: " + ultimo);
    }

    /**
     * Simula il giocatore che va a sud, prende la lanterna e poi termina
     */
    @Test
    public void testPrendiLanternaASud() {
        String[] comandi = {"vai sud", "prendi lanterna", "fine"};
        io.setRigheDaLeggere(comandi);

        gioco.gioca();
        String[] output = io.getMessaggiStampati();

        boolean preso = false;
        for (String msg : output) {
            if (msg.toLowerCase().contains("hai preso: lanterna") || msg.toLowerCase().contains("hai preso la lanterna")) {
                preso = true;
                break;
            }
        }
        assertTrue(preso, "Conferma di prelievo lanterna mancata");
    }

    /**
     * Simula il giocatore che va a nord per vincere la partita
     */
    @Test
    public void testVittoriaConVaiNord() {
        String[] comandi = {"vai nord"};
        io.setRigheDaLeggere(comandi);

        gioco.gioca();
        String[] output = io.getMessaggiStampati();

        boolean vintoMsg = false;
        for (String msg : output) {
            if (msg.toLowerCase().contains("hai vinto")) {
                vintoMsg = true;
                break;
            }
        }
        assertTrue(vintoMsg, "Messaggio di vittoria mancante");
    }
}