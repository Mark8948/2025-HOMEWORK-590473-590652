package it.uniroma3.diadia.ambienti;

//import it.uniroma3.diadia.attrezzi.Attrezzo; non serve in realtà

class StanzaBuia extends Stanza {
	
	protected String nomeAttrezzoIlluminaStanza;
	
	public StanzaBuia(String nome, String nomeAttrezzoIlluminaStanza) {
		super(nome);
		this.nomeAttrezzoIlluminaStanza = nomeAttrezzoIlluminaStanza;
	}
	
	@Override
	public String getDescrizione(){
		if(!this.hasAttrezzo(nomeAttrezzoIlluminaStanza)) return ("Qui c'e' un buio pesto ...");
		return super.getDescrizione();	
	}
	
	
}
