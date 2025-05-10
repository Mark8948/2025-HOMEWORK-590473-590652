package it.uniroma3.diadia;

//import java.util.Scanner;

import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.FabbricaDiComandi;
import it.uniroma3.diadia.comandi.FabbricaDiComandiFisarmonica;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il letodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author docente di POO (da un'idea di Michael Kolling and David J. Barnes)
 * 
 * @version base
 */

public class DiaDia {

	//IOConsole interazione = new IOConsole(); sostituito dalle interfacce nell'HW2

	static final private String MESSAGGIO_BENVENUTO = ""
			+ "Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n"
			+ "Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"
			+ "I locali sono popolati da strani personaggi, " + "alcuni amici, altri... chissa!\n"
			+ "Ci sono attrezzi che potrebbero servirti nell'impresa:\n"
			+ "puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n"
			+ "o regalarli se pensi che possano ingraziarti qualcuno.\n\n"
			+ "Per conoscere le istruzioni usa il comando 'aiuto'.";
    
	private IO interazione;
	private Partita partita;

	public DiaDia(IO interazione) {
        this.interazione = interazione;
        this.partita = new Partita();
    }

	public void gioca() {
		String istruzione;
		// Scanner scannerDiLinee;

		interazione.mostraMessaggio(MESSAGGIO_BENVENUTO);
		// scannerDiLinee = new Scanner(System.in);
		do
			// istruzione = scannerDiLinee.nextLine();
			istruzione = interazione.leggiRiga();
		while (!processaIstruzione(istruzione));

		// scannerDiLinee.close();
	}

	/**
	 * Processa una istruzione
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false
	 *         altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
        Comando comandoDaEseguire;
        FabbricaDiComandi factory = new FabbricaDiComandiFisarmonica();
        comandoDaEseguire = factory.costruisciComando(istruzione, interazione);
        comandoDaEseguire.esegui(this.partita);
        if (this.partita.vinta()) {
            interazione.mostraMessaggio("Hai vinto!");
            this.partita.setFinita();
        }

        if (this.partita.giocatoreIsVivo()) 
            interazione.mostraMessaggio("Hai esaurito i CFU...");

        return this.partita.isFinita();

    }

	public static void main(String[] argc) {
		IO interazione = new IOConsole();
		DiaDia gioco = new DiaDia(interazione);
		gioco.gioca();
	}
}