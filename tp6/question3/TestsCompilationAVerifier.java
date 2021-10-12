package question3;

import question1.*;
import question2.*;

import java.lang.reflect.*;

public class TestsCompilationAVerifier extends junit.framework.TestCase{

    public void test_CompilationDeFactoriel(){
        Contexte m = new Memoire();
        Variable x = new Variable(m,"x",5);
        Variable fact = new Variable(m,"fact",1);

        Instruction inst = 
            new TantQue(
                new Sup(x,new Constante(1)),
                new Sequence(
                    new Affectation(fact,new Multiplication(fact,x)),
                    new Affectation(x,new Soustraction(x,new Constante(1))))
            );

        VisiteurExpression<String> ves = new VisiteurInfixe(m);
        VisiteurExpressionBooleenne<String> vbs = new VisiteurBoolToJava(ves);
        VisiteurInstruction<String> vs = new VisiteurInstToJava(ves,vbs,4);

        ClasseJava cj = new ClasseJava("Fact", "question3", inst, vs);
        System.out.println(cj.sourceComplet());
        try{
            cj.enFichier();
            // côté jnews ce source sera compilé avec javac question3/Fact.java
        }catch(Exception e){
            //e.printStackTrace();
            fail("compilation de Sup, Affectation,Sequence,TantQue ??? " + e.getMessage());
        }
    }     

    public void test_CompilationDeSomme() throws Exception{
        Contexte m = new Memoire();
        Variable n = new Variable(m,"n",100);
        Variable s = new Variable(m,"s",0);
        Variable i = new Variable(m,"i",0);

        Instruction inst =
            new Sequence(
                new Sequence(
                    new TantQue(
                        new Inf(i,n),
                        new Sequence(
                            new Affectation(i,new Addition(i,new Constante(1))),
                            new Affectation(s,new Addition(s,i)))
                    ),
                    new Afficher(s)),
                new Assertion(new Egal(s,new Constante(5050))) //  à voir ........
            );

        VisiteurExpression<String> ves = new VisiteurInfixe(m);
        VisiteurExpressionBooleenne<String> vbs = new VisiteurBoolToJava(ves);
        VisiteurInstruction<String> vs = new VisiteurInstToJava(ves,vbs,4);

        ClasseJava cj = new ClasseJava("Somme", "question3", inst, vs);
        System.out.println(cj.sourceComplet());
        try{
            cj.enFichier();
            // côté jnews ce source sera compilé avec javac question3/Somme.java
        }catch(Exception e){
            //e.printStackTrace();

            fail(" compilation de Inf, Affectation,Sequence,TantQue,Afficher,Assertion ??? " + e.getMessage());
        }
    }

    private static int somme(int n){
        int s=0; int i=0;
        while(i<n){
            i=i+1;
            s=s+i;
        }
        return s;
    }

    public void test_CompilationDUneBouclePour() throws Exception{
        Contexte m = new Memoire();
        Variable i = new Variable(m,"i");
        Variable j = new Variable(m,"j",1);

        Instruction inst = 
            new Sequence(
                new Pour( 
                    new Affectation(i,new Constante(0)), new Inf(i, new Constante(5)), new Affectation(i,new Addition(i,new Constante(1))),
                    new Affectation(j,new Addition(j,new Constante(1)))
                ),
                new Assertion(new Egal(j,new Constante(6)))
            );

        VisiteurExpression<String> ves = new VisiteurInfixe(m);
        VisiteurExpressionBooleenne<String> vbs = new VisiteurBoolToJava(ves);
        VisiteurInstruction<String> vs = new VisiteurInstToJava(ves,vbs,4);

        ClasseJava cj = new ClasseJava("BouclePour", inst, vs);
        System.out.println(cj.sourceComplet());
        try{
            cj.enFichier();
            // côté jnews ce source sera compilé avec javac question3/BouclePour.java
        }catch(Exception e){
            fail(m + inst.accepter(new VisiteurInstToString(ves,vbs))  + e.getMessage() + " ??? ");
        }
    }

	
    public void test_CompilationDeBouclesTantQueImbriquees() throws Exception{
        Contexte m = new Memoire();
        Variable i = new Variable(m,"i");
        Variable j = new Variable(m,"j",1);

        Instruction inst = 
            new TantQue( 
                new Inf(i,new Constante(10)),
                new Sequence(
                    new TantQue(
                        new Inf(j,new Constante(10)),
                        new Affectation(j,new Addition(j,new Constante(1)))
                    ),
                    new Sequence(
                        new Affectation(i,new Addition(i,new Constante(1))),
                        new Affectation(j, new Constante(1))
                    )
                )
            );

        VisiteurExpression<String> ves = new VisiteurInfixe(m);
        VisiteurExpressionBooleenne<String> vbs = new VisiteurBoolToJava(ves);
        VisiteurInstruction<String> vs = new VisiteurInstToJava(ves,vbs,4);

        ClasseJava cj = new ClasseJava("BoucleBoucle", "question3", inst, vs);
        System.out.println(cj.sourceComplet());
        try{
            cj.enFichier();
            // côté jnews ce source sera compilé avec javac question3/BoucleBoucle.java
        }catch(Exception e){
            fail(" compilation de Inf, Affectation,Sequence,TantQue ??? " + e.getMessage());
        }
    }	

    public void test_CompilationDeBouclesPourImbriquees()
    throws Exception{
        question1.Contexte m = new Memoire();
        Variable x = new Variable(m, "x", Integer.valueOf(5));
        Variable k = new Variable(m, "k", Integer.valueOf(0));
        Variable n = new Variable(m, "n", Integer.valueOf(0));
        Variable f = new Variable(m, "f", Integer.valueOf(1));
        Variable g = new Variable(m, "g", Integer.valueOf(0));
        Variable fact = new Variable(m, "fact", Integer.valueOf(1));
        Instruction inst = new Pour(new Affectation(k, n), new Inf(k, x), new Pour(new Affectation(k, n), new Inf(k, x), new Pour(new Affectation(k, n), new Inf(k, x), new Affectation(k, new Addition(k, f)), new Affectation(k, new Addition(k, f))), new Affectation(k, new Addition(k, f))), new Affectation(k, new Addition(k, f)));
        question1.VisiteurExpression ves = new VisiteurInfixe(m);
        question2.VisiteurExpressionBooleenne vbs = new VisiteurBoolToJava(ves);
        VisiteurInstruction vs = new VisiteurInstToJava(ves, vbs, 4);
        ClasseJava cj = new ClasseJava("PourClasseTest", "question3", inst, vs);
        System.out.println(cj.sourceComplet());
        try{
            cj.enFichier();
            // côté jnews ce source sera compilé avec javac question3/BoucleBoucle.java
        }catch(Exception e){
            fail(" compilation de Inf, Affectation,Pour ??? " + e.getMessage());
        }
    }

    public void test_CompilationDeBouclesTantQuePourImbriquees() throws Exception{
        Contexte m = new Memoire();
        Variable i = new Variable(m,"i");
        Variable j = new Variable(m,"j",1);

        Instruction inst = 
            new TantQue( 
                new Inf(i,new Constante(10)),
                new Sequence(
                    new TantQue(
                        new Inf(j,new Constante(10)),
                        new Pour( 
                            new Affectation(i,new Constante(0)), new Inf(i, new Constante(5)), 
                            new Sequence(
                                new Affectation(i,new Addition(i,new Constante(1))),
                                new Afficher(i)), 
                            new Affectation(j,new Addition(j,new Constante(1)))
                        )),
                    new Sequence(
                        new Affectation(i,new Addition(i,new Constante(1))),
                        new Affectation(j, new Constante(1))
                    )
                )
            );

        VisiteurExpression<String> ves = new VisiteurInfixe(m);
        VisiteurExpressionBooleenne<String> vbs = new VisiteurBoolToJava(ves);
        VisiteurInstruction<String> vs = new VisiteurInstToJava(ves,vbs,4);

        ClasseJava cj = new ClasseJava("BoucleTantQuePour", "question3", inst, vs);
        System.out.println(cj.sourceComplet());
        try{
            cj.enFichier();
            // côté jnews ce source sera compilé avec javac question3/BoucleTantQuePour.java
        }catch(Exception e){
            fail(" compilation de Inf, Affectation,Sequence,TantQue,Pour ??? " + e.getMessage());
        }
    }	

    public void test_CompilationDeBouclesTantQuePourImbriquees_bis() throws Exception{
        Contexte m = new Memoire();
        Variable i = new Variable(m,"i");
        Variable j = new Variable(m,"j",1);

        Instruction inst = 
            new TantQue( 
                new Inf(i,new Constante(10)),
                new Sequence(
                    new TantQue(
                        new Inf(j,new Constante(10)),
                        new Pour( 
                            new Affectation(i,new Constante(0)), new Inf(i, new Constante(5)), 
                            new Sequence(
                                new Affectation(i,new Addition(i,new Constante(1))),
                                new Pour( 
                                    new Affectation(i,new Constante(0)), new Inf(i, new Constante(5)), 
                                    new Sequence(
                                        new Affectation(i,new Addition(i,new Constante(1))),
                                        new Afficher(i)), 
                                    new Affectation(j,new Addition(j,new Constante(1)))
                                )), 
                            new Affectation(j,new Addition(j,new Constante(1)))
                        )),
                    new Sequence(
                        new Affectation(i,new Addition(i,new Constante(1))),
                        new Affectation(j, new Constante(1))
                    )
                )
            );

        VisiteurExpression<String> ves = new VisiteurInfixe(m);
        VisiteurExpressionBooleenne<String> vbs = new VisiteurBoolToJava(ves);
        VisiteurInstruction<String> vs = new VisiteurInstToJava(ves,vbs,4);

        ClasseJava cj = new ClasseJava("BoucleTantQuePour2", "question3", inst, vs);
        System.out.println(cj.sourceComplet());
        try{
            cj.enFichier();
            // côté jnews ce source sera compilé avec javac question3/BoucleTantQuePour2.java
        }catch(Exception e){
            fail(" compilation de Inf, Affectation,Sequence,TantQue,Pour ??? " + e.getMessage());
        }
    }	

    public void test_AvecImbricationsMultiples() throws Exception {
        Contexte m = new Memoire();
        Variable i = new Variable(m,"i");
        Variable j = new Variable(m,"j",1);

        Instruction inst =
            new TantQue(
                new Inf(i, new Constante(10)),
                new Sequence(
                    new TantQue(
                        new Inf(j, new Constante(10)),
                        new Affectation(j, new Addition(j,new Constante(1)))
                    ),
                    new Sequence(
                        new Affectation(i, new Addition(i,new Constante(1))),
                        new Affectation(j, new Constante(1))
                    )
                )
            );

        Variable x = new Variable(m,"x",5);
        Instruction inst2 =
            new TantQue(
                new Sup(x, new Constante(2)),
                new Affectation (x, new Soustraction(x, new Constante(1))));   

        Instruction inst3 =
            new TantQue(
                new Inf(i,new Constante(10)),
                new Sequence(
                    new TantQue(
                        new Inf(j,new Constante(10)),
                        new Affectation(j,new Addition(j,new Constante(1)))
                    ),
                    new Sequence(
                        new Affectation(i,new Addition(i,new Constante(1))),
                        inst
                    )
                )
            );

        Instruction k = new Pour(
                new Affectation(x,new Constante(0)),
                new Inf(x,new Constante(10)),
                new Sequence( inst2, inst3 ),
                new Affectation(x,new Addition(x, new Constante(1)))
            );

        Instruction inst4 = new Selection(
                new Inf(x,new Constante(10)),
                k,
                inst3);

        Instruction inst5 = new Selection(
                new Inf(x,new Constante(10)),
                inst4,
                inst3);

        VisiteurExpression<String> ves = new VisiteurInfixe(m);
        VisiteurExpressionBooleenne<String> vbs = new VisiteurBoolToJava(ves);
        VisiteurInstruction<String> vs = new VisiteurInstToJava(ves,vbs,4);

        ClasseJava cj = new ClasseJava("TestsAvecImbricationsMultiples", inst5, vs);
        System.out.println(cj.sourceComplet());

        try{
            cj.enFichier();
            // côté jnews ce source sera compilé avec javac question3/BoucleTantQuePour2.java
        }catch(Exception e){
            fail(" compilation de Inf, Affectation,Sequence,TantQue,Pour ??? " + e.getMessage());
        }
    }

}

