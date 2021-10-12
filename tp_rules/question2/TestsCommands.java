package question2;

import java.util.Iterator;

public class TestsCommands extends junit.framework.TestCase {

    public static class Inc extends Command<String,Integer>{
        private static final boolean T = true;
        public Integer execute(String v,Integer i){
            if(T)System.out.println(this.toString() + ": "+ i);
            return new Integer(i+1);
        }

        public String toString(){
            return "inc";
        }
    }

    public static class Dec extends Command<String,Integer>{
        private static final boolean T = true;
        public Integer execute(String v,Integer i){
            if(T)System.out.println(this.toString() + ": " + i);
            if(i==0) throw new RuntimeException("i==0");
            return new Integer(i-1);
        }

        public String toString(){
            return "dec";
        }
    }


    public static class Print implements CommandI<String,Integer>{

        public Integer execute(String s,Integer r) throws Exception{
            System.out.println("print: "+ r);
            return r;
        }

        public String toString(){
            return "print";
        }
    }


    public void testSimpleAvecDesEntiers() throws Exception{
        CommandI<String,Integer> inc    = new Inc();
        CommandI<String,Integer> dec    = new Dec();
        CommandI<String,Integer> print  = new Print();

        Integer i = new Integer(2);
        assertEquals(new Integer(3), inc.execute("",i));
        assertEquals(new Integer(1), dec.execute("",i));

        MacroCommand<String,Integer> plus4 = new MacroCommand<>();
        CommandI<String,Integer>[] tab = new CommandI[]{print,inc,inc,print,inc,inc,print};
        plus4.setCommands(tab);
        Integer res = new Integer(0);
        assertEquals(new Integer(4), plus4.execute("",res));
        assertEquals("MacroCommand(print/inc/inc/print/inc/inc/print)", plus4.toString());

        MacroCommand<String,Integer> plus2 = new MacroCommand<String,Integer>();
        plus2.add(inc).add(inc);

        MacroCommand<String,Integer> plus6 = new MacroCommand<String,Integer>();
        plus6.add(plus2).add(plus2).add(plus2);
        assertEquals("MacroCommand(MacroCommand(inc/inc)/MacroCommand(inc/inc)/MacroCommand(inc/inc))", plus6.toString());
    }

    public void testAvecUneException() throws Exception{
        CommandI<String,Integer> dec    = new Dec();
        CommandI<String,Integer> print  = new Print();
        CompositeCommand<String,Integer> moins4 = new MacroCommand<String,Integer>();
        moins4.add(print).add(dec).add(dec).add(print).add(dec).add(dec).add(print);

        try{
            int result = moins4.execute("",2);
            fail("une exception est attendue ???");
        }catch(Exception exc){
            assertTrue(exc instanceof RuntimeException);
        }

    }
    
    public void testMacroCommandException() throws Exception{
        CommandI<String,Integer> inc    = new Inc();
        CommandI<String,Integer> dec    = new Dec();
        CommandI<String,Integer> print  = new Print();
        MacroCommandException<String,Integer> moins4 = new MacroCommandException<String,Integer>();
        
        moins4.add(dec).add(dec).add(dec).add(dec);
        CompositeCommand<String,Integer> exception = new MacroCommand<String,Integer>();
        exception.add(inc).add(print);
        moins4.setException(exception);
        try{
            int result = moins4.execute("",2);
            assertEquals(1,result); 
        }catch(Exception exc){
            fail("aucune exception ne doit être transmise ???");

        }

    }

    public void testAvecTransaction() throws Exception{
        CommandI<String,Integer> dec    = new Dec();
        CommandI<String,Integer> print  = new Print();
        MacroCommand<String,Integer> moins4 = new MacroCommand<String,Integer>();

        moins4.add(print).add(dec).add(dec).add(dec).add(dec);
        TransactionCommand<String,Integer> transaction = new TransactionCommand<>();
        transaction.setCommand(moins4);
        int res = transaction.execute("",2);
        assertEquals(2, res);

    }

    public void testAvecIntrospection() throws Exception{
        ReflectCommand dec    = new ReflectCommand();
        dec.setCommand("question2.TestsCommands$Dec");
        ReflectCommand print  = new ReflectCommand();
        print.setCommand("question2.TestsCommands$Print");

        //print.execute("",10);

        MacroCommand<String,Integer> moins4 = new MacroCommand<>();

        moins4.add(dec).add(dec).add(dec).add(dec).add(print);
        int res = moins4.execute("",10);
        assertEquals(6, res);

    }

    public void testMacroCommand() throws Exception{
         CommandI<String,Integer> dec    = new Dec();
 
        MacroCommand<String,Integer> moins5 = new MacroCommand<>();
        moins5.add(dec).add(dec).add(dec).add(dec).add(dec);
        int nombre=moins5.execute("",-10).intValue();
        assertEquals(-15,nombre);
        MacroCommand<String,Integer> moins10 = new MacroCommand<>();
        moins10.add(dec).add(dec).add(dec).add(dec);
        moins10.add(moins5).add(dec);
    
        assertEquals(-25,moins10.execute("",nombre).intValue());

    }
}
