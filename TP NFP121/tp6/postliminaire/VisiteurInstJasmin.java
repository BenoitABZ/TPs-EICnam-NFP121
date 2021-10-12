package postliminaire;

import question1.*;
import question2.*;
import question3.*;

public class VisiteurInstJasmin extends VisiteurInstruction<Integer>{
    private VisiteurExprJasmin vej;
    private VisiteurBoolJasmin vbj;
    private Code code;

    /** Création d'un visiteur d'instructions
     * @param vbc le visiteur d'expressions arithmétiques
     * @param vb le visiteur d'expression booléennes
     */
    public VisiteurInstJasmin(VisiteurExprJasmin vej, VisiteurBoolJasmin vbj){
        this.vej  = vej;
        this.vbj  = vbj;
        this.code = vej.code();
    }

    /** obtention du contexte, ici celui du visiteur d'expression arithmétiques 
     * @return le contexte ici de vi(le visiteur d'expression)
     */
    public Contexte contexte(){
        return this.vej.contexte();
    }

    /** Visite d'une instance de la classe Affectation.
     * 
     * 
     * @param a  une affectation
     * @return a := exp 
     */
    public Integer visite(Affectation a){
        int h = a.exp().accepter(this.vej);
        code.add("istore"); code.add(code.varIndex(a.v().nom()));
        return h;
    }

    /** Visiste d'une séquence seq(I1,I2) <br>
     * @param seq  une séquence
     * @return i1;i2
     */
    public Integer visite(Sequence seq){
        int h1 = seq.i1().accepter(this);
        int h2 = seq.i2().accepter(this);
        return h1 + h2; // ou le max ?
    }

    public Integer visite(Selection sel){
        int hc = sel.cond().accepter(this.vbj);
        code.add("ifeq");
        int jumpIfAddr = code.currentPosition();
        code.add("labelxxxxx");
        int h1 = sel.i1().accepter(this);
        int h2 = 0;
        if(sel.i2() != null){
            code.add("goto");
            int jumpAddr = code.currentPosition();
            code.add("labelxxxxx");
            code.setLabel(jumpIfAddr,code.currentPosition());
            code.addLabel(code.currentPosition());
            h2 = sel.i2().accepter(this);
            code.setLabel(jumpAddr,code.currentPosition());
            code.addLabel(code.currentPosition());
        }else{
            code.setLabel(jumpIfAddr,code.currentPosition());
            code.addLabel(code.currentPosition());
        }
        return hc + Math.max(h1,h2);
    }

    public Integer visite(TantQue tq){
        int start = code.currentPosition();
        code.addLabel(start);
        int hc = tq.cond().accepter(this.vbj);
        code.add("ifeq");
        int jumpIfAddr = code.currentPosition();
        code.add("labelxxxxx");
        int h = tq.i1().accepter(this);
        code.add("goto");
        int jumpAddr = code.currentPosition();
        code.add("labelxxxxx");
        code.setLabel(jumpAddr,start);
        code.setLabel(jumpIfAddr,code.currentPosition());
        code.addLabel(code.currentPosition());
        return hc + h;
    }

    public Integer visite(Pour pour){
        return new Sequence(pour.init(), new TantQue(pour.cond(),new Sequence(pour.i1(),pour.inc()))).accepter(this);
    }

    public Integer visite(Afficher a){
        code.add("getstatic"); code.add("java/lang/System/out Ljava/io/PrintStream;");
        int h = a.exp().accepter(this.vej);
        code.add("invokevirtual"); code.add("java/io/PrintStream/println(I)V");
        return h;
    }

    public Integer visite(Assertion a){
        int h = a.cond().accepter(this.vbj);
        code.add("ifne");
        int jumpIfAddr = code.currentPosition();
        code.add("labelxxxxx");
        code.add("new");code.add("java/lang/AssertionError");
        code.add("dup");
        h++;
        if(a.message() != null){
            code.add("ldc"); code.add("\"" + a.message() + "\"");
            h++;
            code.add("invokespecial");
            code.add("java/lang/AssertionError/<init>(Ljava/lang/Object;)V");
        }else{
            code.add("invokespecial");
            code.add("java/lang/AssertionError/<init>()V");
        }
        code.add("athrow");
        code.setLabel(jumpIfAddr,code.currentPosition());
        code.addLabel(code.currentPosition());
        return h; 
    }

    /** appel d'une méthode écrite en Java
     * uniquement des paramètres entier (IIII...)V
     */
    // public Integer visite(AppelJava a){
    // int h = 1;
    // for(Expression e : a.exp()){
    // h = Math.max(h, e.accepter(this.vej));
    // }
    // code.add("invokestatic"); code.add(a.nom());
    // return h;
    // }

    // public Integer visite(Vide v){
    // int h = 0;
    // code.add("nop");
    // return 0;
    // }

}
