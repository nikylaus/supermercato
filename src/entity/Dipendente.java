package entity;

import java.util.HashSet;
import java.util.Set;

public class Dipendente {

	private String nome;
	//non essendo specificato se nel futoro ci sia la possibilità di avere altri reparti, quindi che un dipendente possa per esmpio lavorare per 5 reparti
	//ho deciso di usare i set, dato che un Dipendente non potrà mai lavorare 2 volte per lo stesso reparto
	private Set<Reparto> reparto = new HashSet<Reparto>();
	
	public Dipendente(String nome) {
		this.nome = nome;
	}
	
	public Dipendente() {
	}

	public Dipendente(String nome, Set<Reparto> reparto) {
		this.nome = nome;
		this.reparto = reparto;
	}

	public Set<Reparto> getReparto() {
		return reparto;
	}

	public void setReparto(Set<Reparto> reparto) {
		this.reparto = reparto;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	//aggiungiamo un reparto al Dipendente
	public Reparto addReparto(Reparto reparto) {
		if(this.reparto.add(reparto)) {
			return reparto;
		}
		return null;
	}
	//Rimoviamo un reparto dal Dipendente
	public Reparto removeReparto(Reparto reparto) {
		if(this.reparto.remove(reparto)) {
			return reparto;
		}
		return null;
	}
	//Ritorna true se il dipendente ha già il reparto passato tramite paramentro else ritorna falso
	public boolean hasReparto(Reparto reparto) {
		if(this.reparto.contains(reparto)) {
			return true;
		}
		return false;
	}
	
	

}
