package it.uniroma3.diadia.ambienti;

class StanzaBloccata extends Stanza {

	private String nomeDirezioneBloccata;
	private String nomeAttrezzoSbloccaDirezione;

	public StanzaBloccata(String nome, String nomeDirezioneBloccata, String nomeAttrezzoSbloccaDirezione) {
		super(nome);
		this.nomeDirezioneBloccata = nomeDirezioneBloccata;
		this.nomeAttrezzoSbloccaDirezione = nomeAttrezzoSbloccaDirezione;
	}

	@Override
	public Stanza getStanzaAdiacente(String direzione) {

		if (direzione.equals(nomeDirezioneBloccata)) {
			if (!this.hasAttrezzo(nomeAttrezzoSbloccaDirezione)) {
				return this;
			}
		}
		
		return super.getStanzaAdiacente(direzione);
	}
	
	@Override
	public String getDescrizione() {
		
		if(!this.hasAttrezzo(nomeAttrezzoSbloccaDirezione)) {
			return super.getDescrizione()
	                 + "\nATTENZIONE: la direzione '"
	                 + nomeDirezioneBloccata
	                 + "' è bloccata. Ti serve - "
	                 + nomeAttrezzoSbloccaDirezione
	                 + " - per aprirla.";
		}
		
		return super.getDescrizione();
	}

}
