package postliminaire;
import question1.*;

public class VisiteurExprJasmin extends VisiteurParDefaut<Integer>{
    private Contexte ctxt;
    private Code     code;

    public VisiteurExprJasmin(Contexte ctxt, Code code){
        this.ctxt = ctxt;
        this.code = code;
    }

    public Integer visite(Constante c){
        int valeur = c.valeur();
        if(valeur >= 0 && valeur <= 5){
            code.add("iconst_"+valeur);
        }else if(valeur >= -128 && valeur <= 127){
            code.add("bipush");code.add(valeur);
        }else if(valeur >= -32768 && valeur <= 32767){
            code.add("sipush");code.add(valeur);
        }else if(valeur < -32768 || valeur > 32767){
            code.add("ldc");code.add(valeur);
        }
        return 1; /* hauteur de pile */
    }

    public Integer visite(Variable v){
        code.add("iload"); code.add(code.varIndex(v.nom()));
        return 1; /* hauteur de pile */
    }

    // public Integer visite(FonctionJava f){
    // int h = 1;
    // for(Expression e : f.exp()){
    // h = Math.max(h, e.accepter(this));
    // }
    // code.add("invokestatic"); code.add(f.nom());
    // return h;
    // }

    public Integer visite(Division d){
        int h1 = d.op1().accepter(this);
        int h2 = d.op2().accepter(this);
        code.add("idiv");
        return Math.max(h1,h2);
    }

    public Integer visite(Addition a){
        int h1 = a.op1().accepter(this);
        int h2 = a.op2().accepter(this);
        code.add("iadd");
        return Math.max(h1,h2);
    }

    public Integer visite(Multiplication m){
        int h1 = m.op1().accepter(this);
        int h2 = m.op2().accepter(this);
        code.add("imul");
        return Math.max(h1,h2);  
    }

    public Integer visite(Soustraction s){
        int h1 = s.op1().accepter(this);
        int h2 = s.op2().accepter(this);
        code.add("isub");
        return Math.max(h1,h2);
    }

    public Contexte contexte(){return this.ctxt;}

    public Code code(){return this.code;}
}
