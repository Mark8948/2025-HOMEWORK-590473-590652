package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;

/**
 * Questa classe modella una partita del gioco
 *
 * @author  docente di POO
 * @see Stanza
 * @version base
 */

public class Partita {

	//static final private int CFU_INIZIALI = 20;
    
	private Giocatore giocatore;
	private Stanza stanzaCorrente;
	private Labirinto labirinto;
	//private Stanza stanzaVincente;
	private boolean finita;
	//private int cfu;
	
	public Partita(){
		this.labirinto = new Labirinto();
		this.stanzaCorrente = labirinto.getStanzaIniziale();
		this.finita = false;
		this.giocatore = new Giocatore();
	}

    /**
     * Crea tutte le stanze e le porte di collegamento
     */
    
	public Giocatore getGiocatore() {
	    return this.giocatore;
	}

	public Stanza getStanzaVincente() {
		return labirinto.getStanzaVincente();
	}

	public void setStanzaCorrente(Stanza stanzaCorrente) {
		this.stanzaCorrente = stanzaCorrente;
	}

	public Stanza getStanzaCorrente() {
		return this.stanzaCorrente;
	}
	
	/**
	 * Restituisce vero se e solo se la partita e' stata vinta
	 * @return vero se partita vinta
	 */
	public boolean vinta() {
		return this.getStanzaCorrente()== this.getStanzaVincente();
	}

	/**
	 * Restituisce vero se e solo se la partita e' finita
	 * @return vero se partita finita
	 */
	public boolean isFinita() {
		return finita || vinta() || (this.giocatore.getCfu() == 0);
	}

	/**
	 * Imposta la partita come finita
	 *
	 */
	public void setFinita() {
		this.finita = true;
	}

	public int getCfu() {
		return this.giocatore.getCfu();
	}

	public void setCfu(int cfu) {
		this.giocatore.setCfu(cfu);		
	}	
}
