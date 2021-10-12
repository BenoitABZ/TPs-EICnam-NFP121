package exemple_instruction.instructions;

import container.ApplicationContext;
import question1.*;

public class InstructionsTests extends junit.framework.TestCase{
    private static final boolean T = true;

    public static class Entier{
        private int elt;

        public Entier(int elt){
            this.elt = elt;
        }

        public Entier(){}

        public void setValue(Integer elt){
            this.elt = elt;
        }

        public Integer getValue(){
            return this.elt;
        }

        public String toString(){
            return Integer.toString(elt);
        }
    }

    public static class Inc extends Instruction<String,Memory<Entier>>{
        private static  boolean T = false;//true;
        public void setTrace(boolean trace){
            T = T || trace;
        }

        public Memory execute(String name,Memory<Entier> memory)throws Exception{
            Entier e = memory.get(name);
            if(T)System.err.println("inc.execute(" + name + "/" + e.getValue()+")");
            e.setValue(e.getValue()+1);
            return memory;
        }

        public String toString(){
            return "inc()";
        }

    }

    public static class Factoriel extends Instruction<String,Memory<Entier>>{
        private static  boolean T = false;//true;
        private static int fact(int n){
            if(n==0) return 1;
            else return n *fact(n-1);
        }

        public void setTrace(boolean trace){
            T = T || trace;
        }

        public Memory execute(String name,Memory<Entier> memory)throws Exception{
            Entier e = memory.get(name);
            if(T)System.err.println("factoriel.execute(" + name + "/" + e.getValue()+")");
            e.setValue(fact(e.getValue()));
            return memory;
        }

        public String toString(){
            return "factoriel()";
        }

    }

    public static class Inf implements SpecificationI<String>{
        private static boolean T = false;//true;
        private Memory<Entier> memory;
        private int operand;
        
        public void setTrace(boolean trace){
            T = T || trace;
        }

        public void setMemory(Memory<Entier> memory){
            this.memory = memory;
        }

        public void setOperand(int operand){
            this.operand = operand;
        }

        public boolean isSatisfiedBy(String name) {
            Entier e = memory.get(name);
            if(T)System.err.println("inf(" + name + "/" + e.getValue() + " < " + operand +")");
            return e.getValue() < operand;
        }

        public String toString(){
            return "x < " + operand;
        }

    }

    private static class VisitorStringPlus extends VisitorString{

        public String visit(Inc inc){
            return inc.toString() +";";
        }

        public String visit(Factoriel fact){
            return fact.toString() +";";
        }
    }

    public void testSansInjection() throws Exception{
        Entier e = new Entier();
        e.setValue(1);
        Memory<Entier> m = new Memory<Entier>();
        m.set("i", e);

        Instruction<String,Memory<Entier>> inc = new Inc();
        if(T)System.err.println("m: " + m);
        Memory m1 = inc.execute("i",m);
        if(T)System.err.println("m1: " + m1);

        Sequence<String,Memory<Entier>> seq = new Sequence<>();
        seq.setInstruction1(inc);
        seq.setInstruction2(inc);
        Memory m2 = seq.execute("i",m1);
        if(T)System.err.println("m2: " + m2);

        IfThen<String,Memory<Entier>> ifThen = new IfThen<>();
        Inf inf = new Inf();
        inf.setMemory(m2);
        inf.setOperand(10);
        ifThen.setSpecification(inf);
        ifThen.setInstruction(seq);
        Memory m3 = seq.execute("i",m2);
        if(T)System.err.println("m3: " + m3);

        While<String,Memory<Entier>> whil = new While<>();
        Inf infBis = new Inf();
        infBis.setMemory(m3);
        infBis.setOperand(16);
        whil.setSpecification(inf);
        whil.setInstruction(inc);
        if(T)System.err.println(whil.accept(new VisitorStringPlus()));
        Memory m4 = whil.execute("i",m3);
        if(T)System.err.println("m4: " + m4);

    }

    public void testAvecInjection() throws Exception{
        ApplicationContext ctx = null;
        ctx = container.Factory.createApplicationContext("./exemple_instruction/instructions/README.TXT");
        Entier e = new Entier();
        e.setValue(1);
        Memory<Entier> m = ctx.getBean("m");
        m.set("i", e);

        if(T)System.err.println("m : mémoire avant, m': mémoire après");
        Instruction<String,Memory<Entier>> inc = ctx.getBean("inc");
        if(T)System.err.println();
        if(T)System.err.println("m: " + m);
        Memory m1 = inc.execute("i",m);
        if(T)System.err.println(inc.accept(new VisitorStringPlus()));
        if(T)System.err.println("m': " + m1);

        Sequence<String,Memory<Entier>> seq = ctx.getBean("seq");
        if(T)System.err.println();
        if(T)System.err.println("m: " + m1);
        Memory m2 = seq.execute("i",m1);
        if(T)System.err.println(seq.accept(new VisitorStringPlus()));
        if(T)System.err.println("m': " + m2);

        IfThen<String,Memory<Entier>> ifThen =ctx.getBean("ifThen");
        if(T)System.err.println();
        if(T)System.err.println("m: " + m2);
        Memory m3 = ifThen.execute("i",m2);
        if(T)System.err.println(ifThen.accept(new VisitorStringPlus()));
        if(T)System.err.println("m': " + m3);

        Instruction<String,Memory<Entier>> whil = ctx.getBean("while");
        if(T)System.err.println();
        if(T)System.err.println("m: " + m3);
        Memory m4 = whil.execute("i",m3);
        if(T)System.err.println(whil.accept(new VisitorStringPlus()));
        if(T)System.err.println("m': " + m4);

        Instruction<String,Memory<Entier>> composite = ctx.getBean("composite");
        if(T)System.err.println();
        e.setValue(3);
        m.set("i", e);
        if(T)System.err.println("m: " + m);
        m1 = composite.execute("i",m);
        if(T)System.err.println(composite.accept(new VisitorStringPlus()));
        if(T)System.err.println("m': " + m1);
    }

}

