package it.uniroma3.diadia;

public class IOSimulator implements IO {

    private String[] righeDaLeggere;     // Array per iniettare le righe di input
    private int indiceLettura;            // Indice corrente di lettura nell'array

    private String[] messaggiStampati;    // Array per conservare i messaggi mostrati
    private int numeroMessaggi;           // Numero di messaggi stampati

    /**
     * Costruttore di default: nessuna riga di input, capacity di messaggi iniziale a 10.
     */
    public IOSimulator() {
        this.righeDaLeggere = new String[0];
        this.indiceLettura = 0;
        this.messaggiStampati = new String[10];
        this.numeroMessaggi = 0;
    }

    /**
     * Costruttore che riceve un array di righe da iniettare come input.
     */
    public IOSimulator(String[] righeDaLeggere) {
        this.righeDaLeggere = righeDaLeggere;
        this.indiceLettura = 0;
        this.messaggiStampati = new String[righeDaLeggere.length > 0 ? righeDaLeggere.length : 10];
        this.numeroMessaggi = 0;
    }

    /**
     * Mostra un messaggio: lo aggiunge all'array interno, espandendo dinamicamente se necessario.
     */
    @Override
    public void mostraMessaggio(String messaggio) {
        if (numeroMessaggi >= messaggiStampati.length) {
            // espandi capacity (raddoppio)
            String[] nuovo = new String[messaggiStampati.length * 2];
            for (int i = 0; i < messaggiStampati.length; i++) {
                nuovo[i] = messaggiStampati[i];
            }
            messaggiStampati = nuovo;
        }
        messaggiStampati[numeroMessaggi++] = messaggio;
    }

    /**
     * Legge la riga successiva dall'array di input iniettato.
     * Restituisce null se esaurita.
     */
    @Override
    public String leggiRiga() {
        if (indiceLettura < righeDaLeggere.length) {
            return righeDaLeggere[indiceLettura++];
        } else {
            return null;
        }
    }

    /**
     * Restituisce una copia dei messaggi "stampati" finora.
     */
    public String[] getMessaggiStampati() {
        String[] copia = new String[numeroMessaggi];
        for (int i = 0; i < numeroMessaggi; i++) {
            copia[i] = messaggiStampati[i];
        }
        return copia;
    }

    /**
     * Imposta un nuovo array di righe da leggere e resetta l'indice.
     */
    public void setRigheDaLeggere(String[] righe) {
        this.righeDaLeggere = righe;
        this.indiceLettura = 0;
    }
}