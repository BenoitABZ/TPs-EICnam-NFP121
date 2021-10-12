package question3;

import java.util.*;
import java.io.*;

public class AuditeurDAOTests extends junit.framework.TestCase{

    public static class DAOFactoryTests{
        public static DAO<Auditeur,Integer> getAuditeurDAO(String type) throws Exception{
            if(type.equals("file"))
                return new FileAuditeurDAO("."+File.separator+"tp9_question3"+File.separator, "auditeurs.txt");

            if(type.equals("jdbc"))// cf. http://hsqldb.org/
                return new DBAuditeurDAO("org.hsqldb.jdbcDriver","jdbc:hsqldb:auditeurs_base");

            return null;
        }

        public static DAO<Auditeur,Integer> getAuditeurDAO() throws Exception{
            return getAuditeurDAO("jdbc");
            // a remplacer par getAuditeurDAO("file");
        }

    }
    public void testClear() throws Exception{
        try{
            DAO<Auditeur,Integer> dao = DAOFactoryTests.getAuditeurDAO();
            for(Auditeur a : dao.findAll()){ 
                dao.delete(a.getId()); // clear
            }
            assertEquals(0, dao.findAll().size());
        }catch(Exception e){
            fail("Exception inattendue ? " + e.getMessage()); 
        } 
    }

    public void testAjoutDePlusieursAuditeurs() throws Exception{
        try{
            DAO<Auditeur,Integer> dao = DAOFactoryTests.getAuditeurDAO();
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
            //System.out.println("list: " + list);
            assertNotNull("dao.findAll() retourne null ???",list);
            assertEquals(10,list.size());

            Auditeur a = dao.retrieve(t[5].getId());
            a.setEmail("auditeur_5@lecnam.net");
            dao.update(a);

            list = dao.findAll();
            assertEquals(10,list.size());

            Auditeur a1 = new Auditeur("Fowler","martin","martin.fowler@cnam.fr");
            dao.create(a1);

            Auditeur a2 = new Auditeur("Grand","mark","mark.grand@cnam.fr");
            dao.create(a2);

            list = dao.findAll();
            assertEquals(12,list.size());

            dao.delete(a1.getId());
            list = dao.findAll();
            assertEquals(11,list.size());

        }catch(Exception e){
            fail("Exception inattendue ? " + e.getClass().getSimpleName() + " " +e.getMessage()); 
        }
    }

    public void testDeLaListeObtenueParFindAll()throws Exception{

        DAO<Auditeur,Integer> dao = DAOFactoryTests.getAuditeurDAO();
        int size = dao.findAll().size();

        Auditeur a0 = new Auditeur("nom_0","prenom_0","nom_0@cnam.fr");
        dao.create(a0);
        assertEquals("Après un ajout la taille obtenue par findAll n'a pas crû ???", size+1, dao.findAll().size()); 
        int idA0 = a0.getId();

        List<Auditeur> list = dao.findAll();
        Auditeur a = list.get(size); // lecture d'un élément de la liste
        a.setEmail("a@b.fr"); // pas de modification de ce même élément sur fichier
        Auditeur a1 = dao.retrieve(a.getId());
        //assertFalse(a.equals(a1)); //ok avec une copie en profondeur du cache
        //       Auditeur a1 = dao.retrieve(idA0);
    }

    public void testAjoutDeDeuxAuditeurs() throws Exception{
        try{
            DAO<Auditeur,Integer> dao = DAOFactoryTests.getAuditeurDAO();
            for(Auditeur a : dao.findAll()){ 
                dao.delete(a.getId()); // clear
            }

            assertEquals(0, dao.findAll().size());

            Auditeur a0 = new Auditeur("nom_0","prenom_0","nom_0@cnam.fr");
            dao.create(a0);
            Auditeur a1 = new Auditeur("nom_1","prenom_1","nom_1@cnam.fr");
            dao.create(a1);

            List<Auditeur> list = dao.findAll();
            assertEquals(2,list.size());

            dao.delete(a0.getId());

            assertEquals(1, dao.findAll().size());

            Auditeur a2 = new Auditeur("nom_2","prenom_2","nom_2@cnam.fr");
            dao.create(a2);

            assertEquals(2,list.size());

        }catch(Exception e){
            fail("Exception inattendue ? " + e.getMessage()); 
        }
    }

    public void testAjoutDePlusieursAuditeursRetraitDeLUnDEntreEux() throws Exception{
        try{
            DAO<Auditeur,Integer> dao = DAOFactoryTests.getAuditeurDAO();
            int size = dao.findAll().size();
            Auditeur[] t = new Auditeur[10];
            for(int i=0; i< t.length;i++){
                t[i] = new Auditeur("nom_"+(i+10),"prenom_"+(i+10),"nom_"+(i+10)+"@cnam.fr");
                dao.create(t[i]);
            }
            List<Auditeur> list = dao.findAll();
            assertEquals(size+10,list.size());

            Auditeur a = dao.retrieve(t[0].getId());

            a = dao.retrieve(t[5].getId());

            a.setEmail("auditeur_15@lecnam.net");
            dao.update(a);

            list = dao.findAll();
            assertEquals(size+10,list.size());

            Auditeur a1 = new Auditeur("Fowler","martin","martin.fowler@cnam.fr");
            dao.create(a1);

            Auditeur a2 = new Auditeur("Grand","mark","mark.grand@cnam.fr");
            dao.create(a2);

            list = dao.findAll();
            assertEquals(size+12,list.size());

            int aiId = a1.getId();
            dao.delete(a1.getId());
            list = dao.findAll();
            assertEquals(size+11,list.size());
            try{
                a = dao.retrieve(aiId);
                fail("Appel de retrieve d'un auditeur supprimé reste possible ? " );
            }catch(Exception e){
            }
        }catch(Exception e){
            fail("Exception inattendue ? " + e.getMessage()); 
        }

    }

    public void testAjoutEtRetraitDePlusieursAuditeurs() throws Exception{
        try{
            DAO<Auditeur,Integer> dao = DAOFactoryTests.getAuditeurDAO();

            int size = dao.findAll().size();

            Auditeur a1 = new Auditeur("Gates","bill","bill.gates@cnam.fr");
            dao.create(a1);
            int idA1 = a1.getId();
            Auditeur a2 = new Auditeur("Ellroy","james","james.ellroy@cnam.fr");
            dao.create(a2);
            int idA2 = a2.getId();
            assertEquals("la taille de la liste en échec après 2 ajouts ???",size+2,dao.findAll().size());

            Auditeur a = dao.retrieve(idA1);
            assertEquals("Un auditeur préalablement créé n'est pas restitué ???", a1, a);
            assertEquals("Un auditeur préalablement créé n'est pas restitué ???", a1.getNom(), a.getNom());    
            assertEquals("Un auditeur préalablement créé n'est pas restitué ???", a1.getPrenom(), a.getPrenom());    
            assertEquals("Un auditeur préalablement créé n'est pas restitué ???", a1.getEmail(), a.getEmail()); 

            assertEquals("Après un retrieve, la taille est incorrecte ???", size+2, dao.findAll().size());
            dao.delete(a1.getId());
            assertEquals("Après un retrait, la taille est incorrecte ???", size+1,dao.findAll().size());

            a = dao.retrieve(a2.getId());
            assertEquals("Un auditeur préalablement créé n'est pas restitué ???", a2, a);
            assertEquals("Un auditeur préalablement créé n'est pas restitué ???", a2.getNom(), a.getNom());    
            assertEquals("Un auditeur préalablement créé n'est pas restitué ???", a2.getPrenom(), a.getPrenom());    
            assertEquals("Un auditeur préalablement créé n'est pas restitué ???", a2.getEmail(), a.getEmail());    
            assertEquals("Après un retrieve, la taille est incorrecte ???", size+1,dao.findAll().size());

            List<Auditeur> list = dao.findAll();
            if(dao.findAll().size()>=5){
                Auditeur a0 = dao.retrieve(list.get(3).getId());
                dao.delete(a0.getId());
                try{
                    a = dao.retrieve(a0.getId());
                    fail("Appel de retrieve d'un auditeur supprimé reste possible ? " );
                }catch(Exception e){
                }
            }

        }catch(Exception e){
            fail("Exception inattendue ? " + e.getMessage()); 
        }
    }

    public void testRemoveSurLaListeRetourneeParFindAll() throws Exception{
        try{
            DAO<Auditeur,Integer> dao = DAOFactoryTests.getAuditeurDAO();
            int size = dao.findAll().size();

            Auditeur a1 = new Auditeur("DD","bill","bill.gates@cnam.fr");
            dao.create(a1);
            int idA1 = a1.getId();
            Auditeur a2 = new Auditeur("EE","james","james.ellroy@cnam.fr");
            dao.create(a2);
            int idA2 = a2.getId();
            List<Auditeur> list = dao.findAll();
            assertEquals("la taille de la liste en échec après 2 ajouts ???",size+2,list.size());
            list.remove(1);
            assertEquals("la liste obtenue par findAll, influe sur le DAO ???",size+2,dao.findAll().size());

        }catch(Exception e){
            fail("Exception inattendue ? " + e.getMessage()); 
        } 

    }

    public void testPlusieursCreatePlusieursDelete() throws Exception{
        try{
            DAO<Auditeur,Integer> dao = DAOFactoryTests.getAuditeurDAO();
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

            for(int i=5; i<15;i++){
                try{
                    Auditeur a = dao.retrieve(t[i].getId());
                    assertEquals("Le champ nom n'est pas restitué ???",t[i].getNom(),a.getNom());
                    assertEquals("Le champ prenom n'est pas restitué ???",t[i].getPrenom(),a.getPrenom());
                    assertEquals("Le champ email n'est pas restitué ???",t[i].getEmail(),a.getEmail());

                }catch(Exception e){
                    fail("Après un delete, un des éléments présent n'est plus accessible ???");
                }
            }

        }catch(Exception e){
            fail("Exception inattendue ? " + e.getMessage()); 
        } 

    }

    public void testPlusieursCreatePlusieursDeleteBis() throws Exception{
        try{
            DAO<Auditeur,Integer> dao = DAOFactoryTests.getAuditeurDAO();
            int size = dao.findAll().size();

            Auditeur a0 = new Auditeur("nom_0","prenom_0","nom_0@cnam.fr");
            Auditeur a1 = new Auditeur("nom_1","prenom_1","nom_1@cnam.fr");
            Auditeur a2 = new Auditeur("nom_1","prenom_2","nom_2@cnam.fr");
            Auditeur a3 = new Auditeur("nom_2","prenom_3","nom_3@cnam.fr");
            dao.create(a0);
            dao.create(a1);
            int idA1 = a1.getId();

            dao.create(a2);
            dao.create(a3);
            List<Auditeur> list = dao.findAll();
            assertEquals("La liste n'a pas crû par l'ajout de 4 auditeurs ?",size+4,list.size());

            dao.delete(idA1);
            list = dao.findAll();
            assertEquals("La liste n'a pas décru par le retrait d'un auditeur ???",size+3,list.size());

            dao.create(a1);
            assertFalse("Après un retrait, puis un ajout l'identifiant reste identique ???, curieux...",idA1==a1.getId());
            //System.out.println(" a1 : " + a1);

            try{
                Auditeur a = dao.retrieve(a1.getId());
                //System.out.println(" a : " + a);
                assertEquals("Le champ Id n'est pas restitué ???",a1.getId(),a.getId());
                assertEquals("Le champ nom n'est pas restitué ???",a1.getNom(),a.getNom());
                assertEquals("Le champ prenom n'est pas restitué ???",a1.getPrenom(),a.getPrenom());
                assertEquals("Le champ email n'est pas restitué ???",a1.getEmail(),a.getEmail());

            }catch(Exception e){
                fail("Après un delete, un des éléments présent n'est plus accessible ???");
            }

        }catch(Exception e){
            fail("Exception inattendue ? " + e.getMessage()); 
        } 

    }

    public void testModificationAuditeur() throws Exception{
        try{
            DAO<Auditeur,Integer> dao = DAOFactoryTests.getAuditeurDAO();
            int size = dao.findAll().size();

            Auditeur a0 = new Auditeur("nom_0","prenom_0","nom_0@cnam.fr");
            Auditeur a1 = new Auditeur("nom_1","prenom_1","nom_1@cnam.fr");
            dao.create(a0);
            dao.create(a1);

            a0.setEmail("a@b.fr");

            List<Auditeur> list = dao.findAll();
            Auditeur a = dao.retrieve(a0.getId());
            assertFalse("L'appel de setMail sur un auditeur en mémoire change-t-il l'e-mail de ce même auditeur sur fichier ???",a.getEmail().equals(a0.getEmail()));
            a.setEmail("a@b.fr");
            dao.update(a);
            Auditeur a2 = dao.retrieve(a0.getId());
            assertEquals("L'appel de update est-il sans effet ???",a.getEmail(),a2.getEmail());

        }catch(Exception e){
            fail("Exception inattendue ? " + e.getMessage()); 
        } 

    }

  
    public void testUniciteIdPlusieursAuditeurs() throws Exception{
        try{
            DAO<Auditeur,Integer> dao = DAOFactoryTests.getAuditeurDAO();

            int size = dao.findAll().size();

            Auditeur[] t = new Auditeur[10];
            for(int i=0; i< t.length;i++){
                t[i] = new Auditeur("auditeur_"+(i+10),"prenom_"+(i+10),"nom_"+(i+10)+"@cnam.fr");
                dao.create(t[i]);
            }
            List<Auditeur> list = dao.findAll();
            Set<Integer> set = new TreeSet<Integer>();

            for(Auditeur a : list){
                set.add(a.getId());
            }
            assertEquals(" L'unicité des id est-il respecté ???",set.size(), list.size());
            //+10
            // après 3 retraits
            dao.delete(t[2].getId());
            dao.delete(t[1].getId());
            dao.delete(t[0].getId());
            try{
                Auditeur a = dao.retrieve(t[2].getId());
            }catch(Exception e){
            }
            list = dao.findAll();
            set = new TreeSet<Integer>();
            for(Auditeur a : list){
                set.add(a.getId());
            }
            assertEquals(" L'unicité des id est-il respecté ???" ,set.size(), list.size());
        }catch(Exception e){
            fail("Exception inattendue ? " + e.getMessage()); 
        }
    }

    public void testAvecUnCritereDeNom(){
        try{
            DAO<Auditeur,Integer> dao = DAOFactoryTests.getAuditeurDAO();

            int size = dao.findAll().size();

            Auditeur[] t = new Auditeur[10];
            for(int i=0; i< t.length;i++){
                t[i] = new Auditeur("auditeur_cnam_"+(i+10),"prenom_"+(i+10),"nom_"+(i+10)+"@cnam.fr");
                dao.create(t[i]);
            }
            DAO.Criteria<Auditeur> c = new DAO.Criteria<Auditeur>(){
                    public boolean isTrue(Auditeur a){
                        return a.getNom().startsWith("auditeur_cnam");
                    }
                };
            List<Auditeur> list = dao.filter(c);
            assertEquals("liste retournée par filter est erronée",10, list.size());
        }catch(Exception e){
            fail("Exception inattendue ? " + e.getMessage()); 
        }
    }

}