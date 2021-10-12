package question3;

import java.io.*;
import java.util.*;

public class TestFactoryDAO extends junit.framework.TestCase{
  public void testClear() throws Exception{
    DAO<Auditeur,Integer> dao = DAOFactory.getAuditeurDAO("file");
    for(Auditeur a : dao.findAll()){ 
      dao.delete(a.getId()); // clear
    }
    
    assertEquals(0, dao.findAll().size());
  }  
  
  public void testAjoutDePlusieursAuditeurs() throws Exception{
    DAO<Auditeur,Integer> dao = DAOFactory.getAuditeurDAO("file");
    for(Auditeur a : dao.findAll()){ 
      dao.delete(a.getId()); // clear
    }
    
    assertEquals(0, dao.findAll().size());
    
    Auditeur[] t = new Auditeur[10];
    for(int i=0; i< t.length;i++){
      t[i] = new Auditeur("nom_"+i,"prenom_"+i,"nom_"+i+"@cnam.fr");
      dao.create(t[i]);
    }
    List<Auditeur> list = dao.findAll();
    assertEquals(10,list.size());
    for(Auditeur a : list){
      System.out.println(a.toString());
    }
    
    Auditeur a = dao.retrieve(0);
    assertEquals(0,a.getId());
    
    a = dao.retrieve(5);
    assertEquals(5,a.getId());
    
    a.setEmail("auditeur_5@lecnam.net");
    dao.update(a);
 
    list = dao.findAll();
    assertEquals(10,list.size());
    
    Auditeur a1 = new Auditeur("Fowler","martin","martin.fowler@cnam.fr");
    dao.create(a1);
    assertEquals(10,a1.getId());

    Auditeur a2 = new Auditeur("Grand","mark","mark.grand@cnam.fr");
    dao.create(a2);
    assertEquals(11,a2.getId());
    
    list = dao.findAll();
    assertEquals(12,list.size());
    
    dao.delete(a1.getId());
    list = dao.findAll();
    assertEquals(11,list.size());
    
  }
  
   public void testPlusieursCreatePlusieursDelete() throws Exception{
    try{
      DAO<Auditeur,Integer> dao = DAOFactory.getAuditeurDAO("file");
      int size = dao.findAll().size();
      
      Auditeur[] t = new Auditeur[20];
      for(int i=0; i< t.length;i++){
        t[i] = new Auditeur("nom_"+(i+100),"prenom_"+(i+10),"nom_"+(i+10)+"@cnam.fr");
        dao.create(t[i]);
      }
      List<Auditeur> list = dao.findAll();
      assertEquals("La liste n'a pas crû ?",size+20,list.size());
      // retrait de 5 éléments 
      for(int i=0; i< 5;i++){
        dao.delete(t[i].getId());
      }
      list = dao.findAll();
      assertEquals("La liste n'a pas décru ???",size+15,list.size());
      for(int i=0; i< 5;i++){
        try{
          dao.retrieve(t[i].getId());
          fail("Un des éléments retirés est encore accessible ???");
        }catch(Exception e){
        }
      }
      
      for(int i=5; i<15;i++){
        try{
          Auditeur a = dao.retrieve(t[i].getId());
          assertEquals("Le champ Id n'est pas restitué ???",t[i].getId(),a.getId());
          assertEquals("Le champ nom n'est pas restitué ???",t[i].getNom(),a.getNom());
          assertEquals("Le champ prenom n'est pas restitué ???",t[i].getPrenom(),a.getPrenom());
          assertEquals("Le champ email n'est pas restitué ???",t[i].getEmail(),a.getEmail());
          
        }catch(Exception e){
          fail("Après un delete, un des éléments présent n'estplus accessible ???");
        }
      }
      
      
    }catch(Exception e){
      fail("Exception inattendue ? " + e.getMessage()); 
    } 
 
  }
  
  
}