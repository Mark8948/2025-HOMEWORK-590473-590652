package it.uniroma3.diadia;

//import java.util.Scanner;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Giocatore;

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

	static final private String[] elencoComandi = { "vai", "aiuto", "fine", "prendi", "posa" };
    
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
		Comando comandoDaEseguire = new Comando(istruzione);

		if (comandoDaEseguire.getNome().equals("fine")) {
			this.fine();
			return true;
		} else if (comandoDaEseguire.getNome().equals("prendi")) {
			this.prendi(comandoDaEseguire.getParametro());
		} else if (comandoDaEseguire.getNome().equals("posa")) {
			this.posa(comandoDaEseguire.getParametro());
		} else if (comandoDaEseguire.getNome().equals("vai"))
			this.vai(comandoDaEseguire.getParametro());
		else if (comandoDaEseguire.getNome().equals("aiuto"))
			this.aiuto();
		else
			interazione.mostraMessaggio("Comando sconosciuto");
		if (this.partita.vinta()) {
			interazione.mostraMessaggio("Hai vinto!");
			return true;
		} else
			return false;
	}

	// implementazioni dei comandi dell'utente:

	private void posa(String nomeAttrezzo) {
		// TODO Auto-generated method stub
		if (nomeAttrezzo == null) {
			interazione.mostraMessaggio("Non hai selezionato un attrezzo esistente!!!!");

			// return null;
		}

		Giocatore giocatore = this.partita.getGiocatore();
		Attrezzo attrezzo = giocatore.removeAttrezzo(nomeAttrezzo);

		if (attrezzo == null)
			interazione.mostraMessaggio("Non hai questo attrezzo!!!");
		else {
			this.partita.getStanzaCorrente().addAttrezzo(attrezzo);
			// giocatore.removeAttrezzo(nomeAttrezzo);
			interazione.mostraMessaggio("Hai posato: " + nomeAttrezzo);
		}
	}

	private void prendi(String nomeAttrezzo) {
		// TODO Auto-generated method stub
		if (nomeAttrezzo == null) {
			interazione.mostraMessaggio("Non hai selezionato un attrezzo esistente!!!!");
			// return null;
		}

		Stanza stanzaCorrente = this.partita.getStanzaCorrente();
		Attrezzo attrezzo = stanzaCorrente.getAttrezzo(nomeAttrezzo);

		if (attrezzo == null)
			interazione.mostraMessaggio(nomeAttrezzo + " non c'e' nella stanza!!");
		else {
			if (this.partita.getGiocatore().addAttrezzo(attrezzo)) {
				stanzaCorrente.removeAttrezzo(attrezzo);
				interazione.mostraMessaggio("Hai preso: " + nomeAttrezzo);
			} else {
				interazione.mostraMessaggio("Inventario pieno !!!!");
			}
		}
	}

	/**
	 * Stampa informazioni di aiuto.
	 */
	private void aiuto() {
		for (int i = 0; i < elencoComandi.length; i++)
			interazione.mostraMessaggio(elencoComandi[i] + " ");
		interazione.mostraMessaggio("");
	}

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra e ne stampa il
	 * nome, altrimenti stampa un messaggio di errore
	 */
	private void vai(String direzione) {
		if (direzione == null)
			interazione.mostraMessaggio("Dove vuoi andare ?");
		Stanza prossimaStanza = null;
		prossimaStanza = this.partita.getStanzaCorrente().getStanzaAdiacente(direzione);
		if (prossimaStanza == null)
			interazione.mostraMessaggio("Direzione inesistente");
		else {
			this.partita.setStanzaCorrente(prossimaStanza);
			int cfu = this.partita.getCfu();
			this.partita.setCfu(cfu - 1);
		}
		interazione.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
	}

	/**
	 * Comando "Fine".
	 */
	private void fine() {
		interazione.mostraMessaggio("Grazie di aver giocato!"); // si desidera smettere
	}

	public static void main(String[] argc) {
		IO io = new IOConsole();
		DiaDia gioco = new DiaDia(io);
		gioco.gioca();
	}
}