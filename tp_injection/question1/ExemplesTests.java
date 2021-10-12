package question1;


import container.*;

import java.util.*;
import java.io.File;
import java.io.InputStream;
import java.io.FileInputStream;

public class ExemplesTests extends junit.framework.TestCase{
    
    public void testAvecInjection() throws Exception{
        ApplicationContext ctx = container.Factory.createApplicationContext("./question1/README.TXT");
        Table table = ctx.getBean("table1");
        assertEquals(7, table.taille());
        
        ListeDeTables listeDeTables = ctx.getBean("listeDeTables");
        assertEquals(2, listeDeTables.taille());
       
        Factory fabrique = ctx.getBean("fabrique");
        Collection<Object> c = fabrique.getInstance();
        c.add(3);c.add(3);
        assertEquals(1, c.size());
        
    }
    

    public void testSansInjection() throws Exception{
   
        Table table = new Table();     
        table.setCollection(new HashSet<Integer>());
        table.setInt(3);
        table.setInt(4);
        table.setInt(3);
        assertEquals(2, table.taille());
        
        // table.setCollection(new ArrayList<Integer>());
        // table.setInt(3);
        // table.setInt(4);
        // table.setInt(3);
        // assertEquals(3, table.taille());
        
        Factory fabrique = new Factory();
        fabrique.setInstance(java.util.HashSet.class);
        Collection<Object> c = fabrique.getInstance();
        c.add(3);c.add(3);
        assertEquals(1, c.size());
        
        ListeDeTables listeDeTables = new ListeDeTables();
        listeDeTables.setTable(table);
        listeDeTables.setTables(new Table[]{table,table,table});
        assertEquals(4, listeDeTables.taille());
        
    }
}

