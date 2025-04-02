package it.uniroma3.diadia.giocatore;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * La classe Giocatore modella il giocatore del gioco, gestendo i CFU e la Borsa degli attrezzi.
 */
public class Giocatore {
    
	private int CFU_INIZIALI = 20;
	
    private int cfu; // Crediti Formativi Universitari (energia del giocatore)
    private Borsa borsa; // Inventario del giocatore
    
    /**
     * Costruttore del Giocatore con CFU iniziali.
     */
    public Giocatore() {
        this.cfu = CFU_INIZIALI; // Valore predefinito dei CFU inizia
        this.borsa = new Borsa(); // Istanza della borsa
    }
    
    /**
     * Restituisce il numero di CFU del giocatore.
     * 
     * @return i CFU attuali
     */
    public int getCfu() {
        return this.cfu;
    }
    
    /**
     * Imposta il numero di CFU del giocatore.
     * 
     * @param cfu il nuovo valore di CFU
     */
    public void setCfu(int cfu) {
        this.cfu = cfu;
    }
    
    /**
     * Restituisce la borsa del giocatore.
     * 
     * @return l'oggetto Borsa associato al giocatore
     */
    public Borsa getBorsa() {
        return this.borsa;
    }
    
    /**
     * Aggiunge un attrezzo alla borsa del giocatore.
     * 
     * @param attrezzo l'attrezzo da aggiungere
     * @return true se l'attrezzo è stato aggiunto, false altrimenti
     */
    public boolean addAttrezzo(Attrezzo attrezzo) {
        return this.borsa.addAttrezzo(attrezzo);
    }
    
    /**
     * Rimuove un attrezzo dalla borsa del giocatore.
     * 
     * @param nomeAttrezzo il nome dell'attrezzo da rimuovere
     * @return l'attrezzo rimosso, oppure null se non presente
     */
    public Attrezzo removeAttrezzo(String nomeAttrezzo) {
        return this.borsa.removeAttrezzo(nomeAttrezzo);
    }
    
    /**
     * Metodo per muovere il giocatore in una nuova stanza, aggiornando la stanza corrente e decrementando i CFU.
     * 
     * @param nuovaStanza la stanza in cui il giocatore deve spostarsi
     */
    public void vai(Stanza nuovaStanza) {
        if (nuovaStanza != null) {
            this.cfu--; // Il movimento costa 1 CFU
        }
    }
}
