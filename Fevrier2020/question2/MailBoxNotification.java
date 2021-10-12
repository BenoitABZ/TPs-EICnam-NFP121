package question2;

import question1.*;
import java.util.*;

public class MailBoxNotification extends MailBox implements IMailBoxNotification {
	
	Map<String, Set<MailBoxListener>> mapListeners;

	public MailBoxNotification() {
		super();
		
		mapListeners = new HashMap<>();

	}

	private boolean contains(String destinataire) {
		for (String dest : this) {
			if (dest.equals(destinataire))
				return true;
		}
		return false;
	}

	public void addMailBoxListener(String destinataire, MailBoxListener listener) throws Exception {
		if (!contains(destinataire))
			throw new Exception(destinataire + " inconnu...");
		
		Set<MailBoxListener> listeners = mapListeners.get(destinataire);
		
		if(listeners==null) {
			
			listeners = new HashSet<>();
		}
		
		listeners.add(listener);
		
		mapListeners.put(destinataire, listeners);

	}

	@Override
	public void send(String message, String destinataire) throws Exception {
		try {
			super.send(message, destinataire);
			
			Set<MailBoxListener> listenersSend = mapListeners.get(destinataire);
			
			for(MailBoxListener listener : listenersSend) {
				
				listener.onReceive(message);
			}

		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void send(String message, String[] destinataires) throws Exception {
		for (String dest : destinataires) {
			try {
				this.send(message, dest);
				
				Set<MailBoxListener> listenersSend = mapListeners.get(dest);
				
				for(MailBoxListener listener : listenersSend) {
					
					listener.onReceive(message);
				}

				
			} catch (Exception e) {
			}
		}
	}

}
