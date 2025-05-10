package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;

public interface FabbricaDiComandi {

	//Comando costruisciComando(String istruzione);

	Comando costruisciComando(String istruzione, IO interazione);
	
}
