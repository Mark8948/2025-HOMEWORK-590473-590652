package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Labirinto {

    private Stanza stanzaIniziale;
    private Stanza stanzaVincente;
    
    public Labirinto() {
        creaLabirinto();
    }
    
    void creaLabirinto() {
        // Creazione degli attrezzi
        Attrezzo lanterna = new Attrezzo("lanterna", 3);
        Attrezzo osso = new Attrezzo("osso", 1);
        
        // Creazione delle stanze
        Stanza atrio = new Stanza("Atrio");
        Stanza aulaN11 = new Stanza("Aula N11");
        Stanza aulaN10 = new Stanza("Aula N10");
        Stanza laboratorio = new Stanza("Laboratorio Campus");
        Stanza biblioteca = new Stanza("Biblioteca");
        
        // Collegamento delle stanze
        atrio.impostaStanzaAdiacente("nord", biblioteca);
        atrio.impostaStanzaAdiacente("est", aulaN11);
        atrio.impostaStanzaAdiacente("sud", aulaN10);
        atrio.impostaStanzaAdiacente("ovest", laboratorio);
        aulaN11.impostaStanzaAdiacente("est", laboratorio);
        aulaN11.impostaStanzaAdiacente("ovest", atrio);
        aulaN10.impostaStanzaAdiacente("nord", atrio);
        aulaN10.impostaStanzaAdiacente("est", aulaN11);
        aulaN10.impostaStanzaAdiacente("ovest", laboratorio);
        laboratorio.impostaStanzaAdiacente("est", atrio);
        laboratorio.impostaStanzaAdiacente("ovest", aulaN11);
        biblioteca.impostaStanzaAdiacente("sud", atrio);
        
        // Posizionamento degli attrezzi
        aulaN10.addAttrezzo(lanterna);
        atrio.addAttrezzo(osso);
        
        // Impostazione della stanza iniziale e vincente
        this.stanzaIniziale = atrio;
        this.stanzaVincente = biblioteca;
    }
    
    public Stanza getStanzaIniziale() {
        return stanzaIniziale;
    }
    
    public Stanza getStanzaVincente() {
        return stanzaVincente;
    }
}

