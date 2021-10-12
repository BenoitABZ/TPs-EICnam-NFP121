package question4;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PushbackInputStream;

/**
 *Bibliographie: doc Oracle, StackOverFlow...
 *
 *Conclusion: Nous avons mis en pratique, au fil de ce TP, l'implémentation du pattern decorator.
 *
 *Ce dernier nous a permis d'ajouter, de manière dynamique, des fonctionalités supplémentaires à nos classes concrètes.
 *
 *Nous pouvons conclure qu'il s'agit d'une véritable alternative à l'héritage.
 *
 */
public class UpperCaseInputStreamTest extends junit.framework.TestCase {

	public void testAcces_README_TXT() {
		try {
			InputStream is = new BufferedInputStream(new FileInputStream(new File("README.TXT")));
			int c = is.read();
			assertTrue(" erreur de lecture ???", c != -1);
			is.close();
		} catch (Exception e) {
			fail(" Erreur sur ce fichier : README.TXT ??? " + e.getMessage());
		}
	}

	public void testUpperCase_README_TXT() throws Exception {
		//décoration avec UpperCaseInputStream
		InputStream is = new UpperCaseInputStream(new BufferedInputStream(new FileInputStream(new File("README.TXT"))));

		int c = is.read();
		while (c != -1) {
			assertTrue("erreur !, '" + Character.valueOf((char) c) + "' ne semble pas être une majuscule ...",
					Character.isUpperCase((char) c) || (char) c == ' ');
			c = is.read();
		}

		is.close();
	}

	public void testPushPackUpperCase_README_TXT() throws Exception {

		try {
			//décoration avec PushbackInputStream
			//la classe PushBackInputStream permet d'accéder à un flux de données avec la possibilité de revenir en arrière d'un octet
			InputStream is = new PushbackInputStream(new UpperCaseInputStream(new BufferedInputStream(new FileInputStream(new File("README.TXT")))));

			int c = is.read();

			assertTrue(" erreur de lecture ???", c != -1);

		} catch (Exception e) {

			fail(" Erreur sur ce fichier : README.TXT ??? " + e.getMessage());
		}
	}
}
