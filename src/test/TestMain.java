package test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

import entity.Dipendente;
import entity.Reparto;

public class TestMain implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		//Creazione oggetti reparto
		Reparto ortofrutta = new Reparto("Ortofrutta");
		Reparto salumeria = new Reparto("Salumeria");
		Reparto magazzino = new Reparto("Magazzino");
		Reparto cassa = new Reparto("Cassa");
		//Creazione di una lista che contiene tutti i reparti ersistenti
		List<Reparto> reparti = new ArrayList<Reparto>(Arrays.asList(ortofrutta, salumeria, magazzino, cassa));
		
		//Creazione oggetti dipendente
		Dipendente marco = new Dipendente("Marco", new HashSet<Reparto>(Arrays.asList(ortofrutta)));
		Dipendente teresa = new Dipendente("Teresa", new HashSet<Reparto>(Arrays.asList(cassa)));
		Dipendente paolo = new Dipendente("Paolo", new HashSet<Reparto>(Arrays.asList(ortofrutta, salumeria)));
		Dipendente lucia = new Dipendente("Lucia", new HashSet<Reparto>(Arrays.asList(magazzino, cassa)));
		Dipendente giorgio = new Dipendente("Giorgio", new HashSet<Reparto>(Arrays.asList(magazzino, salumeria)));
		Dipendente nicola = new Dipendente("Nicola", new HashSet<Reparto>(Arrays.asList(magazzino)));
		Dipendente silvia = new Dipendente("Silvia", new HashSet<Reparto>(Arrays.asList(salumeria)));
		//Creazione una lista che contiene tutti i dipendenti esistenti
		List<Dipendente> dipendenti = new ArrayList<Dipendente>(Arrays.asList(marco,teresa,paolo,lucia,giorgio,nicola,silvia));
		
		//Inseriamo i dipendenti nei reparti 
		ortofrutta.addDipendente(marco);
		ortofrutta.addDipendente(paolo);
		cassa.addDipendente(teresa);
		cassa.addDipendente(lucia);
		salumeria.addDipendente(paolo);
		salumeria.addDipendente(giorgio);
		salumeria.addDipendente(silvia);
		magazzino.addDipendente(lucia);
		magazzino.addDipendente(nicola);
		
		
		//Stampiamo tutti i reparti e dipendenti all'avvio dell'app
//		stampaReparti(reparti);
//		stampaDipendenti(dipendenti);
		
		boolean running = true;
		//Creazioni di un array con tutte le possibili scelte del menu
		String[] scelte = new String[]{"Rimuovi dipendente dal reparto", "Aggiungi dipendente al reparto", "Assumi dipendente", "Stampa reparti", "Stampa dipendenti", "Save", "Exit"};		
		//Creazione del while , l'utente può uscire dall'app selezionando Exit o premendo la x in altro a destra
		while(running) {
			//salviamo la scelta dell'utente
			String scegliere = (String) JOptionPane.showInputDialog(null, "Scegli", "Scegliere", JOptionPane.PLAIN_MESSAGE, null, scelte, scelte[0]);
			//ho messo il questo controllo if per verificare se la stringa non è null, se è null le viene assegnato il valore Exit
			//per evitare il NullPointerException in caso l'utente dovesse premere la x in alto a destra ( in questo modo si salta la scelta e la stringa "scegliere" diventa null)
			if(scegliere == null) {
				scegliere = "Exit";
			}
			//eseguiamo l'operazione scelta dall'utente
			switch(scegliere) {
				case "Rimuovi dipendente dal reparto":
					 rimuoviReparto(dipendenti, reparti);
					 break;
				case "Aggiungi dipendente al reparto":
					aggiungiReparto(dipendenti, reparti);
					break;
				case "Assumi dipendente":
					assumiDipendente(dipendenti, reparti);
					break;
				case "Stampa reparti":
					stampaReparti(reparti);
					break;
				case "Stampa dipendenti":
					stampaDipendenti(dipendenti);	
					break;
				case "Save":
					
				case "Exit":
					running = false;
			}
		}
		
	}
	
	//Rimuove il dipendente scelto dal reparto scelto
	public static void rimuoviReparto(List<Dipendente> dipendenti, List<Reparto> reparti) {
		//Controlla se ci sono dipendenti nel database
		if(dipendenti.toArray().length > 0) {
			//prendiamo i nomi di tutti i dipendenti per mostrarli all'utente
			List<String> dip = dipendenti.stream().map(dipendente -> dipendente.getNome()).collect(Collectors.toList());
			String num = (String) JOptionPane.showInputDialog(null, "Scegli dipendente", "Scegliere", JOptionPane.PLAIN_MESSAGE, null, dip.toArray(), dip.toArray()[0]);
			//Controllo in caso l'utente dovesse premere x senza selezionare nulla, evitando il nullPointerException
			if(num == null) {
				System.exit(0);
			}
			//salviamo il nome del dipendente scelto e andiamo a trovarci l'oggetto di quel determinato dipendente
			Dipendente dipendente = dipendenti.stream().filter(dipe->num.equals(dipe.getNome())).findAny().get();
			//Controlla se il dipendente scelto ha dei reparti salvati
			if(dipendente.getReparto().toArray().length > 0) {
				//prendiamo i nomi di tutti i reparti di un determinato dipendente per mostrarli all'utente
				List<String> rep = dipendente.getReparto().stream().map(reparto -> reparto.getNome()).collect(Collectors.toList());
				String numReparto = (String) JOptionPane.showInputDialog(null, "Scegli reparto da eliminare", "Scegliere", JOptionPane.PLAIN_MESSAGE, null, rep.toArray(), rep.toArray()[0]);
				//Controllo in caso l'utente dovesse premere x senza selezionare nulla, evitando il nullPointerException
				if(numReparto == null) {
					System.exit(0);
				}
				//salviamo il nome del reparto scelto e andiamo a trovarci l'oggetto di quel determinato reparto
				Reparto daEliminare = reparti.stream().filter(reparto -> numReparto.equals(reparto.getNome())).findAny().get();
				//se ci torna indietro il reparto daEliminare significa che il reparto è stato eliminato else si è verificato un errore
				if(dipendente.removeReparto(daEliminare) == daEliminare) {
					JOptionPane.showMessageDialog(null, "Il reparto " + daEliminare.getNome() + " è stato eliminato dal dipendente " + dipendente.getNome());
					//rimuoviamo il dipendente anche dall'oggetto del reparto
					daEliminare.removeDipendente(dipendente);
				} else {
					JOptionPane.showMessageDialog(null, "Impossibile eliminare il reparto scelto");
				}
				//torna al menu se non ci sono reparti per questo utente
			} else {
				JOptionPane.showMessageDialog(null, dipendente.getNome() + " non ha nessun reparto");
			}
			//torna al menu se non ci sono dipendenti el database
		} else {
			JOptionPane.showMessageDialog(null, "Non è stato trovato nessun dipendente");
		}
	}
	
	//Aggiunge il dipendente scelto al reparto scelto
		public static void aggiungiReparto(List<Dipendente> dipendenti, List<Reparto> reparti) {
			//prendiamo i nomi di tutti i dipendenti per mostrarli all'utente
			List<String> dip = dipendenti.stream().map(dipendente -> dipendente.getNome()).collect(Collectors.toList());
			String num = (String) JOptionPane.showInputDialog(null, "Scegli dipendente", "Scegliere", JOptionPane.PLAIN_MESSAGE, null, dip.toArray(), dip.toArray()[0]);
			//Controllo in caso l'utente dovesse premere x senza selezionare nulla, evitando il nullPointerException
			if(num == null) {
				System.exit(0);
			}
			//salviamo il nome del dipendente scelto e andiamo a trovarci l'oggetto di quel determinato dipendente
			Dipendente dipendente = dipendenti.stream().filter(dipe->num.equals(dipe.getNome())).findAny().get();
			//prendiamo i nomi di tutti i reparti per mostrarli all'utente
			List<String> rep = reparti.stream().map(reparto -> reparto.getNome()).collect(Collectors.toList());
			String numReparto = (String) JOptionPane.showInputDialog(null, "Scegli reparto da aggiungere", "Scegliere", JOptionPane.PLAIN_MESSAGE, null, rep.toArray(), rep.toArray()[0]);
			//Controllo in caso l'utente dovesse premere x senza selezionare nulla, evitando il nullPointerException
			if(numReparto == null) {
				System.exit(0);
			}
			//salviamo il nome del reparto scelto e andiamo a trovarci l'oggetto di quel determinato reparto
			Reparto daAggiungere = reparti.stream().filter(reparto -> numReparto.equals(reparto.getNome())).findAny().get();
			//Proviamo a vedere se l'utente ha già associato il reparto scelto dall'utente, poi proviamo ad aggiungere il dipendente il reparto.
			//Dato che i reparti hanno capacità massima di 4 dipendentis e non ci torna indietro il dipendende significa che il reparto è pieno.
			if(!dipendente.hasReparto(daAggiungere) && daAggiungere.addDipendente(dipendente) == dipendente) {
				JOptionPane.showMessageDialog(null, "Il reparto " + daAggiungere.getNome() + " è stato aggiunto al dipendente " + dipendente.getNome());
				//Aggiungiamo il reparto anche all'oggetto del dipendente
				dipendente.addReparto(daAggiungere);
			} else {
				JOptionPane.showMessageDialog(null, "Impossibile aggiungere il reparto scelto assicurarsi che il reparto non abbiamo più di 4 dipendenti");
			}
		}
		
		//Crea un nuovo oggetto Dipendente
		public static void assumiDipendente(List<Dipendente> dipendenti, List<Reparto> reparti) {
			//Chiediamo all'utente di inserire il nome del nuovo assunto per poi chiedere se vuole aggiungere subito un reparto
			String nomeD = JOptionPane.showInputDialog("Inserire nome dipendente");
			String[] scelte = new String[] {"Si", "No"};
			//Creaiamo un oggetto Dipendente con il nome scelto dall'utente
			dipendenti.add(new Dipendente(nomeD));
			String scelta = (String) JOptionPane.showInputDialog(null, "Vuoi aggiungere un reparto all'utente?", "Scegliere", JOptionPane.PLAIN_MESSAGE, null, scelte, scelte[1]);
			//Se l'utente sceglie si lo mandiamo direttamente al pannello per aggiungere il Reparto al dipendente
			//Controllo in caso l'utente dovesse premere x senza selezionare nulla, evitando il nullPointerException
			if(scelta == null) {
				System.exit(0);
			}
			if(scelta.equals("Si")) {
				aggiungiReparto(dipendenti,reparti);
			}
		}
	
	
	
	//Manda a schermo tutti i reparti con il numero di posti occupati , disponibili e i dipendenti che lavorano in quel reparto
	public static void stampaReparti(List<Reparto> reparti) {
		String fnl = "";
		//formattazione della stringa fnl che in fine verrà mandata a schermo attraverso JOptionPane
		//iteriamo ogni oggetto Reparto andando a trovare i posti liberi , occupati e il nome del reparto
		for(int i= 0; i<reparti.size(); i++) {
			fnl += "Nome reparto: " + reparti.get(i).getNome() + " | ";
			fnl += "Posti liberi: " + reparti.get(i).getPostiLiberi() + " | ";
			fnl += "Posti occupati: " + (4-reparti.get(i).getPostiLiberi()) + " | ";
			fnl += "Dipendenti: ";
			//iteriamo ogni dipendende del reparto e aggiungiamo alla Stringa fnl i nomi dei dipendenti
			for(int j= 0; j<4; j++) {
				if(reparti.get(i).getDipendenti()[j] != null) {
					fnl += reparti.get(i).getDipendenti()[j].getNome() + ", ";	
				}	
			}
			fnl += "\n";
		}
		JOptionPane.showMessageDialog(null, fnl);
	}
	
	//Manda a schermo tutti i dipendenti e i reparti in cui lavorano
	public static void stampaDipendenti(List<Dipendente> dipendenti) {
		String fnl = "";
		//Formattazione stringa fnl che in fine verra mandata a schermo attraverso JOptionPane
		//iteriamo ogni dipendente prendendo il suo nome
		for(int i = 0; i<dipendenti.size(); i++) {
			fnl += "Dipendente: " + dipendenti.get(i).getNome() + " | ";
			fnl += "Reparti di competenza: ";
			//Creiamo un oggetto Iterator per iterare ogni elemento del Set per trovare i reparti di ogni dipendente
			Iterator<Reparto> j = dipendenti.get(i).getReparto().iterator();
			while(j.hasNext()) {
				fnl += j.next().getNome() + ", ";	
			}
			fnl += "\n";
		}
		JOptionPane.showMessageDialog(null, fnl);	
	}
	
	

}
