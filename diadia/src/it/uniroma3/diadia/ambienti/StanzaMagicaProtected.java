package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Versione magica di StanzaProtected, con campi protetti.
 */
public class StanzaMagicaProtected extends StanzaProtected {
    private static final int SOGLIA_MAGICA_DEFAULT = 3;
    private int contatoreAttrezziPosati;
    private int sogliaMagica;

    public StanzaMagicaProtected(String nome) {
        this(nome, SOGLIA_MAGICA_DEFAULT);
    }
    public StanzaMagicaProtected(String nome, int soglia) {
        super(nome);
        this.contatoreAttrezziPosati = 0;
        this.sogliaMagica = soglia;
    }

    @Override
    public boolean addAttrezzo(Attrezzo attrezzo) {
        contatoreAttrezziPosati++;
        Attrezzo daPosare = attrezzo;
        if (contatoreAttrezziPosati > sogliaMagica) {
            daPosare = modificaAttrezzo(attrezzo);
        }
        // Accesso diretto al campo protetto "attrezzi"
        if (this.numeroAttrezzi < NUMERO_MASSIMO_ATTREZZI) {
            this.attrezzi[numeroAttrezzi] = daPosare;
            this.numeroAttrezzi++;
            return true;
        }
        return false;
    }

    private Attrezzo modificaAttrezzo(Attrezzo att) {
        String nomeInvertito = new StringBuilder(att.getNome()).reverse().toString();
        int pesoRaddoppiato = att.getPeso() * 2;
        return new Attrezzo(nomeInvertito, pesoRaddoppiato);
    }
}
