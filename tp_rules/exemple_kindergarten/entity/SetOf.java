package exemple_kindergarten.entity;

import java.util.Arrays;
/**
 * Un ensemble d'éléments de type T.
 * 
 * Pour femtoContainer afin que l'on puisse 'facilement' ajouter de nouveaux
 * éléments à partir de la configuration.
 * 
 *         bean.id.K=xxx
 *         xxx.class=exemple_kindergarten.entity.SetOf
 *         
 * Soit    xxx.property.N=add
 *         xxx.property.N.param.1=a
 *         
 * ou bien xxx.property.N=addAll
 *         xxx.property.N.param.1=a b c d
 */
public class SetOf<T> extends java.util.HashSet<T>{

    /** Syntaxe de mutateur pour l'appel d'une méthode ordinaire... 
     *  
     */
    public void setAdd(T t){
        super.add(t);
    }
    
    /** Syntaxe de mutateur pour l'appel d'une méthode ordinaire... 
     *  
     */
    public void setAddAll(T[] t){
        super.addAll(Arrays.asList(t));
    }
}
