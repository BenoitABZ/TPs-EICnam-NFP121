package question3;

import question1.*;
import question2.*;

public class TestsEvaluationAVerifier extends junit.framework.TestCase{

    private static int fact(int n){
        if(n==0) return 1;
        else return n*fact(n-1);
    }

    public void testFactoriel(){
        Contexte m = new Memoire();
        Variable x = new Variable(m,"x",5);
        Variable fact = new Variable(m,"fact",1);
        VisiteurExpression<Integer> ve = new VisiteurEvaluation(m);
        VisiteurExpressionBooleenne<Boolean> vb = new VisiteurBoolEvaluation(ve);
        VisiteurInstruction<Contexte> vi = new VisiteurInstEvaluation(ve,vb);

        VisiteurExpression<String> ves = new VisiteurInfixe(m);
        VisiteurExpressionBooleenne<String> vbs = new VisiteurBoolToString(ves);
        VisiteurInstruction<String> vs = new VisiteurInstToString(ves,vbs);

        Instruction i = 
            new TantQue(
                new Sup(x,new Constante(1)),
                new Sequence(
                    new Affectation(fact,new Multiplication(fact,x)),
                    new Affectation(x,new Soustraction(x,new Constante(1))))
            );

        i.accepter(vi);
        assertTrue((String)i.accepter(vs) + " ne donne pas le résultat attendu ...", m.lire("fact")== fact(5));
    }     

    private static int somme(int n){
        int s=0; int i=0;
        while(i<n){
            i=i+1;
            s=s+i;
        }
        return s;
    }

    public void testSomme(){
        Contexte m = new Memoire();
        Variable n = new Variable(m,"n",100);
        Variable s = new Variable(m,"s",0);
        Variable i = new Variable(m,"i",0);
        VisiteurExpression<Integer> ve = new VisiteurEvaluation(m);
        VisiteurExpressionBooleenne<Boolean> vb = new VisiteurBoolEvaluation(ve);
        VisiteurInstruction<Contexte> vi = new VisiteurInstEvaluation(ve,vb);

        VisiteurExpression<String> ves = new VisiteurInfixe(m);
        VisiteurExpressionBooleenne<String> vbs = new VisiteurBoolToString(ves);
        VisiteurInstruction<String> vs = new VisiteurInstToString(ves,vbs);

        Instruction inst = 
            new TantQue(
                new Inf(i,n),
                new Sequence(
                    new Affectation(i,new Addition(i,new Constante(1))),
                    new Affectation(s,new Addition(s,i)))
            );

        inst.accepter(vi);
        assertTrue((String)inst.accepter(vs) + " ne donne pas le r�sultat attendu ...", m.lire("s")==somme(100));
    }

    private static int mult(int a, int b){
        int z = 0 ;
        while (b > 0){
            if ((b -(b / 2)*2) == 1){
                z = z + a  ; b = b-1;
            }else{
                a = 2 * a ; b = b / 2;
            }
        }
        return z;
    }

    public void testMult(){

        Contexte m = new Memoire();
        Variable a = new Variable(m,"a",7);
        Variable b = new Variable(m,"b",85);
        Variable z = new Variable(m,"z",0);

        VisiteurExpression<Integer> ve = new VisiteurEvaluation(m);
        VisiteurExpressionBooleenne<Boolean> vb = new VisiteurBoolEvaluation(ve);
        VisiteurInstruction<Contexte> vi = new VisiteurInstEvaluation(ve,vb);

        VisiteurExpression<String> ves = new VisiteurInfixe(m);
        VisiteurExpressionBooleenne<String> vbs = new VisiteurBoolToString(ves);
        VisiteurInstruction<String> vs = new VisiteurInstToString(ves,vbs);

        Instruction i = 
            new TantQue(
                new Sup(b,new Constante(0)),
                new Selection(
                    new Egal(
                        new Soustraction(b,new Multiplication(new Division(b,new Constante(2)),new Constante(2))),new Constante(1)),
                    new Sequence(
                        new Affectation(z,new Addition(z,a)),
                        new Affectation(b,new Soustraction(b,new Constante(1))) 
                    ),
                    new Sequence(
                        new Affectation(a,new Multiplication(a,new Constante(2))),
                        new Affectation(b,new Division(b,new Constante(2)))
                    )
                )
            );

        i.accepter(vi);
        assertTrue((String)i.accepter(vs) + " ne donne pas le r�sultat attendu ...",m.lire("z")==mult(7,85));
    }

    public void testRacine(){
        assertTrue(" racineCarre(9) ???", racineCarre(9)==3);
        assertTrue(" racineCarre (16) ???",racineCarre(16)==4);
        assertTrue(" racineCarre(12) ???",racineCarre(12)==3);
    }

    public static int racineCarre(int n){ 
        int rac = 0;
        int car = 1;
        int v   = 1;
        while(car <= n){
            rac = rac +1;
            v = v + 2;
            car = car + v;
        }
        return rac;
    }
    // calcul  de la racine carr�e � un pr�s par d�faut d'un entier. source jf dazy NFP120
    //          rac := 0 '.,' v := 1 '.,' car := 1 '.,'
    //           while car =< n
    //                     invariant {rac * rac =< n &
    //                               v = rac +rac +1 & w = (rac +1)*(rac +1)}
    //                     variant {n-car +1}
    //           do
    //           (         rac := rac + 1 '.,'
    //                     v := v + 2 '.,'
    //                     car := car + v )
    //           end '.,'
    //        {rac *rac =< n & (rac +1)*(rac +1) > n}
    public void testRacineCarreeDe12(){
        try{
            Contexte m = new Memoire();
            Variable rac = new Variable(m,"rac",0);
            Variable car = new Variable(m,"car",1);
            Variable v   = new Variable(m,"v",1);
            Variable n   = new Variable(m,"n",0);

            VisiteurExpression<String> ves = new VisiteurInfixe(m);
            VisiteurExpressionBooleenne<String> vbs = new VisiteurBoolToJava(ves);
            VisiteurInstruction<String> vs = new VisiteurInstToJava(ves,vbs,4);

            Instruction i =
                new Sequence(
                    new Affectation(n, new Constante(12)),
                    new Sequence(
                        new TantQue(
                            new Ou( new Inf(car,n), new Egal(car,n)),
                            new Sequence(
                                new Affectation(rac, new Addition(rac, new Constante(1))),
                                new Sequence(
                                    new Affectation(v, new Addition(v, new Constante(2))),
                                    new Affectation(car, new Addition(car, v))))),
                        new Sequence(
                            new Assertion(new Egal(rac, new Constante(3))),
                            // rac *rac =< n & (rac +1)*(rac +1) > n en post-assertion
                            new Assertion(new Et(new Ou(new Inf(new Multiplication(rac,rac),n), new Egal(new Multiplication(rac,rac),n)),
                                    new Sup(new Multiplication(new Addition(rac,new Constante(1)),new Addition(rac,new Constante(1))),n)))
                        )
                    )

                );

            String str = vs.contexte().toString() + i.accepter(vs);
            System.out.println(str);

            ClasseJava cj = new ClasseJava("RacineCarree", "question3", i, vs);
            System.out.println(cj.sourceComplet());
            cj.enFichier();	  

        }catch(Exception e){
            fail(" exception inattendue ???" + e.getClass().getName() + e.getMessage());
        }

    }
}
