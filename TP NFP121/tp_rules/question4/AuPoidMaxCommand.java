package question4;

public class AuPoidMaxCommand extends Command<Panier, String>{

	@Override
	public String execute(Panier p, String result) throws Exception {
          
		result = "Poid MAX du panier atteint";
 
		return result;
	}
	

}
