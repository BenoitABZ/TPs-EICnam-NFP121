package question1;

import java.util.*;
import java.util.Map.Entry;

/**
 * @author Votre nom, prénom // a completer
 */
public class Mediateur implements MediateurI {
	private static final boolean T = true;
	private static final boolean REPOK = true;

	Map<String, CommandeI<EvenementI>> map;

	private String nom;

	public boolean repOk() {
		
		if (map != null) {
			
			return true;
		}
		return false;
	}

	public <T> T af() {
		return null;
	}

	public Mediateur() {
		super();
		map = new HashMap<>();
		if (REPOK)
			assert repOk() : "invariant de classe en échec";
	}

	public Mediateur(String nom) {
		this();

		map = new HashMap<>();

		if (nom != null) {

			this.nom = nom;
		}

		if (REPOK)
			assert nom != null && repOk() : "invariant de classe en échec";
	}

	public void setNom(String nom) {
		if (REPOK)
			assert repOk() : "invariant de classe en échec";

		if (nom != null) {

			this.nom = nom;
		}

		if (REPOK)
			assert nom != null && repOk() : "invariant de classe en échec";
	}

	/**
	 * Obtention du nom du médiateur.
	 * 
	 * @return le nom de médiateur, (ce nom ne peut-être égal à null)
	 */
	public String getNom() {
		if (REPOK)
			assert repOk() : "invariant de classe en échec";
		try {
			return this.nom;
		} finally {
			if (REPOK)
				assert repOk() : "invariant de classe en échec";
		}
	}

	/**
	 * Itérateur du médiateur. l'appel de remove est sans effet.
	 * 
	 * @return un itérateur sur les les thèmes presents
	 */
	public Iterator<String> iterator() {
		if (REPOK)
			assert repOk() : "invariant de classe en échec";
		try {

			Set<String> set = map.keySet();

			return set.iterator();

		} finally {
			if (REPOK)
				assert repOk() : "invariant de classe en échec";
		}
	}

	public void publier(EvenementI evenement) throws ThemeAbsentException {
		if (REPOK)
			assert repOk() : "invariant de classe en échec";

		if (!map.containsKey(evenement.getTheme())) {

			throw new ThemeAbsentException();
		}

		for (Entry<String, CommandeI<EvenementI>> entry : map.entrySet())

			if (entry.getKey().equals(evenement.getTheme())) {

				CommandeI<EvenementI> commande = entry.getValue();

				commande.executer(evenement);
			}

		if (REPOK)
			assert repOk() : "invariant de classe en échec";
	}

	/**
	 * Ajout d'un thème de publication.
	 * 
	 * @param theme    le thème à ajouter
	 * @param commande la commande exécutée lors d'une publication sur ce thème
	 * @throws ThemeDejaPresentException si le thème a déjà été ajouté
	 * @throws NullPointerException      si le thème ou commande == null
	 */
	public void ajouterTheme(String theme, CommandeI<EvenementI> commande) throws ThemeDejaPresentException {
		if (REPOK)
			assert repOk() : "invariant de classe en échec";

		if (map.containsKey(theme)) {

			throw new ThemeDejaPresentException();
		}
		
		if(theme == null || commande == null) {
			
			throw new NullPointerException();
		}

		map.put(theme, commande);

		if (REPOK)
			assert repOk() : "invariant de classe en échec";
	}

	/**
	 * Retrait d'un thème de ce médiateur.
	 * 
	 * @param theme le thème à retirer
	 * @throws ThemeAbsentException si le thème est absent du médiateur
	 */
	public void retirerTheme(String theme) throws ThemeAbsentException {
		if (REPOK)
			assert repOk() : "invariant de classe en échec";

		if (!map.containsKey(theme)) {

			throw new ThemeAbsentException();
		}

		map.remove(theme);

		if (REPOK)
			assert repOk() : "invariant de classe en échec";
	}
}
