package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Stanza con campi protetti.
 */

public class StanzaProtected {
    protected static final int NUMERO_MASSIMO_DIREZIONI = 4;
    protected static final int NUMERO_MASSIMO_ATTREZZI = 10;

    protected String nome;
    protected Attrezzo[] attrezzi;
    protected int numeroAttrezzi;
    protected StanzaProtected[] stanzeAdiacenti;
    protected int numeroStanzeAdiacenti;
    protected String[] direzioni;

    protected StanzaProtected(String nome) {
        this.nome = nome;
        this.numeroStanzeAdiacenti = 0;
        this.numeroAttrezzi = 0;
        this.direzioni = new String[NUMERO_MASSIMO_DIREZIONI];
        this.stanzeAdiacenti = new StanzaProtected[NUMERO_MASSIMO_DIREZIONI];
        this.attrezzi = new Attrezzo[NUMERO_MASSIMO_ATTREZZI];
    }

    /**
     * Imposta una stanza adiacente.
     *
     * @param direzione direzione in cui sara' posta la stanza adiacente.
     * @param stanza stanza adiacente nella direzione indicata dal primo parametro.
     */
    
    public void impostaStanzaAdiacente(String direzione, StanzaProtected stanza) {
        boolean aggiornato = false;
        for(int i = 0; i < this.direzioni.length; i++) {
            if (direzione.equals(this.direzioni[i])) {
                this.stanzeAdiacenti[i] = stanza;
                aggiornato = true;
            }
        }
        if (!aggiornato && this.numeroStanzeAdiacenti < NUMERO_MASSIMO_DIREZIONI) {
            this.direzioni[numeroStanzeAdiacenti] = direzione;
            this.stanzeAdiacenti[numeroStanzeAdiacenti] = stanza;
            this.numeroStanzeAdiacenti++;
        }
    }
    
    /**
     * Restituisce la stanza adiacente nella direzione specificata
     * @param direzione
     */
    
    public StanzaProtected getStanzaAdiacente(String direzione) {
        for(int i = 0; i < this.numeroStanzeAdiacenti; i++) {
            if (this.direzioni[i].equals(direzione))
                return this.stanzeAdiacenti[i];
        }
        return null;
    }

    public String getNome() {
        return this.nome;
    }

    public String getDescrizione() {
        return this.toString();
    }

    public Attrezzo[] getAttrezzi() {
        return this.attrezzi;
    }

    public boolean addAttrezzo(Attrezzo attrezzo) {
        if (this.numeroAttrezzi < NUMERO_MASSIMO_ATTREZZI) {
            this.attrezzi[numeroAttrezzi] = attrezzo;
            this.numeroAttrezzi++;
            return true;
        }
        return false;
    }

    public boolean hasAttrezzo(String nomeAttrezzo) {
        if (nomeAttrezzo == null) return false;
        for (Attrezzo att : this.attrezzi) {
            if (att != null && att.getNome().equals(nomeAttrezzo))
                return true;
        }
        return false;
    }

    public Attrezzo getAttrezzo(String nomeAttrezzo) {
        if (nomeAttrezzo == null) return null;
        for (Attrezzo att : this.attrezzi) {
            if (att != null && att.getNome().equals(nomeAttrezzo))
                return att;
        }
        return null;
    }

    public boolean removeAttrezzo(Attrezzo attrezzo) {
        if (attrezzo == null) return false;
        for (int i = 0; i < this.numeroAttrezzi; i++) {
            if (this.attrezzi[i].equals(attrezzo)) {
                for (int j = i; j < this.numeroAttrezzi - 1; j++) {
                    this.attrezzi[j] = this.attrezzi[j + 1];
                }
                this.attrezzi[numeroAttrezzi - 1] = null;
                this.numeroAttrezzi--;
                return true;
            }
        }
        return false;
    }

    public String[] getDirezioni() {
        String[] result = new String[this.numeroStanzeAdiacenti];
        for(int i = 0; i < this.numeroStanzeAdiacenti; i++) {
            result[i] = this.direzioni[i];
        }
        return result;
    }

    public String toString() {
        StringBuilder risultato = new StringBuilder();
        risultato.append(this.nome);
        risultato.append("\nUscite:");
        for (String direzione : this.direzioni)
            if (direzione != null)
                risultato.append(" " + direzione);
        risultato.append("\nAttrezzi nella stanza: ");
        for (Attrezzo attrezzo : this.attrezzi)
            if (attrezzo != null)
                risultato.append(attrezzo + " ");
        return risultato.toString();
    }
}