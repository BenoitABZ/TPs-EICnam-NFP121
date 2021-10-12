package question1;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.Iterator;

public class MailBox implements IMailBox {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Set<String> messagers;

	Map<String, List<String>> mailBoxRead;

	Map<String, List<String>> mailBoxUnRead;

	public MailBox() {

		messagers = new TreeSet<>();

		mailBoxRead = new HashMap<>();

		mailBoxUnRead = new HashMap<>();
	}

	public MailBox(MailBox mb) {

		this.mailBoxRead = mb.mailBoxRead;
		this.mailBoxUnRead = mb.mailBoxUnRead;
		this.messagers = mb.messagers;
	}

	public void add(String dest) throws Exception {

		boolean add = true;

		add = messagers.add(dest);

		if (add == false) {

			throw new RuntimeException("utilisateur déjà présent");
		}

		mailBoxRead.put(dest, new ArrayList<>());

		mailBoxUnRead.put(dest, new ArrayList<>());

	}

	public void send(String message, String dest) throws Exception {

		try {

			List<String> messages = mailBoxUnRead.get(dest);

			messages.add(message);

			mailBoxUnRead.put(dest, messages);

		} catch (NullPointerException npe) {

			throw new RuntimeException();
		}

	}

	public void send(String message, String[] destinataires) throws Exception {

		boolean error = false;

		for (String destinataire : destinataires) {

			try {

				List<String> messages = mailBoxUnRead.get(destinataire);

				messages.add(message);

				mailBoxUnRead.put(destinataire, messages);

			} catch (NullPointerException npe) {

				error = true;
			}
		}

		if (error == true) {

			throw new RuntimeException();
		}

	}

	public List<String> read(String dest) throws Exception {

		List<String> list = new ArrayList<String>();

		list = mailBoxUnRead.get(dest);

		if (list == null) {

			return new ArrayList<>();
		}

		mailBoxRead.get(dest).addAll(list);

		mailBoxUnRead.replace(dest, list, new ArrayList<>());

		return list;
	}

	public Iterator<String> iterator() {
		return messagers.iterator();
	}

	public List<String> getAllMessages(String dest) throws Exception {
		List<String> list = new ArrayList<String>();

		List<String> listRead = mailBoxRead.get(dest);

		List<String> listUnRead = mailBoxUnRead.get(dest);

		list.addAll(listRead);

		list.addAll(listUnRead);

		return list;
	}

	public List<String> getAllUnreadMessages(String dest) throws Exception {
		List<String> list = new ArrayList<String>();

		list = mailBoxUnRead.get(dest);

		return list;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();

		return sb.toString();
	}

	public boolean equals(Object o) {
		if (!(o instanceof MailBox))
			return false;
		MailBox mb = (MailBox) o;

		if (mb.mailBoxRead.equals(this.mailBoxRead) && mb.mailBoxUnRead.equals(this.mailBoxUnRead)
				&& mb.messagers.equals(this.messagers)) {

			return true;

		} else {

			return false;
		}
	}

	public int hashCode() {
		return this.toString().hashCode();
	}
}
