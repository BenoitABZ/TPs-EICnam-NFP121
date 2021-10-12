package postliminaire;

import question1.*;
import question2.*;

public class VisiteurBoolJasmin extends VisiteurExpressionBooleenne<Integer>{
    private VisiteurExprJasmin vbc;
    private Code code;

    public VisiteurBoolJasmin(VisiteurExprJasmin vbc){
        this.vbc  = vbc;
        this.code = vbc.code();
    }

    public Integer visite(Vrai v){
        code.add("iconst_1");
        return 1;
    }

    public Integer visite(Faux f){
        code.add("iconst_0");
        return 1;
    }

    public Integer visite(Non n){
        int h = n.bop().accepter(this);
        code.add("ifne");
        int jumpIfAddr = code.currentPosition();
        //code.add("address_byte_1"); code.add("address_byte_2");
        code.add("labelxxxxx");
        code.add("iconst_1");
        code.add("goto");
        int jumpAddr = code.currentPosition();
        //code.add("address_byte_1"); code.add("address_byte_2");
        code.add("labelxxxxx");
        //code.setWord(jumpIfAddr, code.currentPosition());
        code.setLabel(jumpIfAddr,code.currentPosition());
        code.add("#_" +code.currentPosition() + ":");
        code.add("iconst_0");
        //code.setWord(jumpAddr, code.currentPosition());
        code.setLabel(jumpAddr,code.currentPosition());
        code.addLabel(code.currentPosition());
        return h;
    }

    public Integer visite(Ou ou){
        int h1 = ou.bop1().accepter(this);
        code.add("ifne");
        int jumpIfAddr = code.currentPosition();
        //code.add("address_byte_1"); code.add("address_byte_2");
        code.add("labelxxxxx");
        int h2 = ou.bop2().accepter(this);
        code.add("ifne");
        int jumpIfAddr2 = code.currentPosition();
        //      code.add("address_byte_1"); code.add("address_byte_2");
        code.add("labelxxxxx");
        code.add("iconst_0");
        code.add("goto");
        int jumpAddr = code.currentPosition();
        code.add("labelxxxxx");
        //      code.add("address_byte_1"); code.add("address_byte_2");
        code.setLabel(jumpIfAddr,code.currentPosition());
        code.setLabel(jumpIfAddr2,code.currentPosition());
        code.addLabel(code.currentPosition());
        //      code.setWord(jumpIfAddr, code.currentPosition());
        //      code.setWord(jumpIfAddr2, code.currentPosition());
        code.add("iconst_1");
        //      code.setWord(jumpAddr, code.currentPosition());
        code.setLabel(jumpAddr,code.currentPosition());
        code.addLabel(code.currentPosition());
        //return "(" + ou.bop1().accepter(this) + " ou " + ou.bop2().accepter(this) + ")";
        return Math.max(h1,h2);
    }

    public Integer visite(Et et){
        int h1 = et.bop1().accepter(this);
        code.add("ifeq");
        int jumpIfAddr = code.currentPosition();
        code.add("labelxxxxx");
        int h2 = et.bop2().accepter(this);
        code.add("ifne");
        int jumpIfAddr2 = code.currentPosition();
        code.add("labelxxxxx");
        code.setLabel(jumpIfAddr,code.currentPosition());
        code.addLabel(code.currentPosition());
        code.add("iconst_0");
        code.add("goto");
        int jumpAddr = code.currentPosition();
        code.add("labelxxxxx");
        code.setLabel(jumpIfAddr2,code.currentPosition());
        code.addLabel(code.currentPosition());
        code.add("iconst_1");
        code.setLabel(jumpAddr,code.currentPosition());
        code.addLabel(code.currentPosition());
        return Math.max(h1,h2);
    }

    public Integer visite(Sup sup){
        int h1 = sup.op1().accepter(this.vbc);
        int h2 = sup.op2().accepter(this.vbc);
        code.add("if_icmple");
        int jumpIfAddr = code.currentPosition();
        code.add("labelxxxxx");
        code.add("iconst_1");
        code.add("goto");
        int jumpAddr = code.currentPosition();
        code.add("labelxxxxx");
        code.setLabel(jumpIfAddr,code.currentPosition());
        code.addLabel(code.currentPosition());
        code.add("iconst_0");
        code.setLabel(jumpAddr,code.currentPosition());
        code.addLabel(code.currentPosition());   
        return Math.max(h1,h2);
    }

    public Integer visite(Egal eg){
        int h1 = eg.op1().accepter(this.vbc);
        int h2 = eg.op2().accepter(this.vbc);
        code.add("if_icmpne");
        int jumpIfAddr = code.currentPosition();
        code.add("labelxxxxx");
        code.add("iconst_1");
        code.add("goto");
        int jumpAddr = code.currentPosition();
        code.add("labelxxxxx");
        code.setLabel(jumpIfAddr,code.currentPosition());
        code.addLabel(code.currentPosition());
        code.add("iconst_0");
        code.setLabel(jumpAddr,code.currentPosition());
        code.addLabel(code.currentPosition());   
        return Math.max(h1,h2);
    }

    public Integer visite(Inf inf){
        int h1 = inf.op1().accepter(this.vbc);
        int h2 = inf.op2().accepter(this.vbc);
        code.add("if_icmpge");
        int jumpIfAddr = code.currentPosition();
        code.add("labelxxxxx");
        code.add("iconst_1");
        code.add("goto");
        int jumpAddr = code.currentPosition();
        code.add("labelxxxxx");
        code.setLabel(jumpIfAddr,code.currentPosition());
        code.addLabel(code.currentPosition());
        code.add("iconst_0");
        code.setLabel(jumpAddr,code.currentPosition());
        code.addLabel(code.currentPosition());   
        return Math.max(h1,h2);

    }
}
