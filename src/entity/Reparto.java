package entity;


public class Reparto {

	private String nome;
	private Dipendente[] dipendenti = new Dipendente[4];
	
	public Reparto(String nome) {
		this.nome = nome;
	}
	
	public Reparto() {
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Dipendente[] getDipendenti() {
		return dipendenti;
	}

	public void setDipendenti(Dipendente[] dipendenti) {
		this.dipendenti = dipendenti;
	}
	
	//Aggiungiamo un dipendente ad un reparto , ritorna null se il reparto è pieno else ritorna il dipendente inserito
	public Dipendente addDipendente(Dipendente dipendente) {
		for(int i = 0; i<dipendenti.length; i++) {
			if(dipendenti[i] == null) {
				dipendenti[i] = dipendente;
				return dipendente;
			}
		}
		return null;
	}
	
	//Eliminiamo un dipendente da un reparto, ritorna null se il dipendente non è presente nel reparto else ritorna il dipendente eliminato
	public Dipendente removeDipendente(Dipendente dipendente) {
		for(int i = 0; i<dipendenti.length;i++) {
			if(dipendenti[i]== dipendente) {
				dipendenti[i] = null;
				return dipendente;
			}
		}	
		return null;
	}
	
	//Troviamo numero posti liberi nel reparto
	public int getPostiLiberi() {
		int c = 0;
		for (int i = 0; i < dipendenti.length; i++) {
			if(dipendenti[i] == null) {
				c++;
			}
		}
		return c;
	}
	
	
	
	
	
	
	
}
