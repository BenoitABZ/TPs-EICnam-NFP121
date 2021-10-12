package question1;

public class TableMethodesJavaLangMathTest extends junit.framework.TestCase{


	public void test_obtentionDeLaListe(){
	  try{
  		question1.TableMethodesJavaLangMath t = question1.TableMethodesJavaLangMath.getInstance();
  		assertNotNull(t);
  		String[] liste = t.listeDesMethodes();
  		assertNotNull(liste);
  		assertEquals(liste[0],"IEEEremainder(double, double)");
		}catch(Exception e){
			e.printStackTrace();
		  fail("inattendue!!!");
		}  		
	}
	
	public void test_invocation() throws Exception{
	  try{
		  question1.TableMethodesJavaLangMath table = question1.TableMethodesJavaLangMath.getInstance();
		  assertNotNull(table);
		  assertEquals(table.invoquer("sqrt(double)",9.0),3.0,0.1);
		}catch(Exception e){
		  fail("inattendue!!!");
		}  
	}
}
