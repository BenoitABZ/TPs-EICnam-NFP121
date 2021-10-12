package question1;

import java.io.*;

/**
 * Décrivez votre classe JAVASerialiseDeserialise ici.
 * 
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class JAVASerialiseDeserialise {
	public static void serialjava(IProgr p, String nomDuFichier) throws FileNotFoundException, IOException {

		ObjectOutputStream oos = null;

		try {
			File fichier = new File(nomDuFichier);

			// ouverture d'un flux sur un fichier
			oos = new ObjectOutputStream(new FileOutputStream(fichier));

			// sérialization de l'objet
			oos.writeObject(p);
		} finally {

			oos.close();
		}
	}

	public static IProgr deserialjava(String nomDuFichier) throws FileNotFoundException, IOException, ClassNotFoundException {
		
		ObjectInputStream ois = null;

		try {
			File fichier = new File(nomDuFichier);

			// ouverture d'un flux sur un fichier
			ois = new ObjectInputStream(new FileInputStream(fichier));

			// désérialization de l'objet
			IProgr p = (IProgr) ois.readObject();

			return p;

		} finally {

			ois.close();

		}

	}
}
