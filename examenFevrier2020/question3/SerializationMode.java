package question3;

import java.io.*;
import java.util.*;
import question1.*;

public class SerializationMode extends PersistentMode {

	public void writeMailBox(IMailBox mb, String fileName) throws Exception {

		FileOutputStream fos = new FileOutputStream("./mailbox.ser");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(mb);
		
		oos.close();

	}

	public IMailBox readMailBox(String fileName) throws Exception {

		IMailBox mb = null;
		
		FileInputStream fis = new FileInputStream("./mailbox.ser");
		ObjectInputStream ois = new ObjectInputStream(fis);
		mb = (IMailBox) ois.readObject();
		
		ois.close();		
		
		return mb;
	}
}
