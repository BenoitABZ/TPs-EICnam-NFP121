package question1;

import java.util.Map;

public interface EvenementI extends Comparable<EvenementI>{
    
  public final static String THEME_PAR_DEFAUT = "THEME_PAR_DEFAUT";
  public final static int PRIORITE_PAR_DEFAUT = 5;
  
  public void setTheme(String theme);
  
  public String getTheme();
  
  public void putParametre(String clef, Object valeur);
  
  public <T> T getParametre(String clef);

  public Map<String,Object> getParametres();
  
  public String toString();
  
  public void setPriorite(int priorite);
  
  public int getPriorite();
  
      /** Invariant de classe.
     * @return vrai si l'invariant est satisfait
     */
    public boolean repOk();
    
    /** Fonction d'abstraction.
     * @return une représentation dite abstraite
     */
    public <T> T af();
  
}

