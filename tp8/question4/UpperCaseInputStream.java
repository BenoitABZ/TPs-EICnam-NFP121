package question4;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Cette classe "décore" un fichier (InputSream) par la conversion de tous les
 * caractères Minuscule en Majuscule
 * 
 * FilterInputStream est le décorateur abstrait
 * 
 * InputStream est le Component abstrait
 * 
 * Redéfinition nécéssaire des 3 méthodes read() afin d'avoir le comportement souhaité
 * 
 * @author benoit(votre nom)
 */
public class UpperCaseInputStream extends FilterInputStream {

	protected UpperCaseInputStream(InputStream stream) {
		super(stream);

	}

	@Override
	public int read() throws IOException {

		int c = super.read();
		return c == -1 ? c : Character.toUpperCase((char) (c));

	}

	@Override
	public int read(byte[] b, int offset, int len) throws IOException {
		int result = super.read(b, offset, len);
		for (int i = 0; i < result; i++) {
			b[i] = (byte) Character.toUpperCase((char) (b[i]));
		}

		return result;
	}

	@Override
	public int read(byte[] b) throws IOException {
		int result = super.read(b);
		for (int i = 0; i < result; i++) {
			b[i] = (byte) Character.toUpperCase((char) (b[i]));
		}

		return result;
	}

}