package question3;

import java.io.File;
// http://best-practice-software-engineering.ifs.tuwien.ac.at/patterns/dao.html

public class DAOFactory{

    public static DAO<Auditeur,Integer> getAuditeurDAO(String type) throws Exception{
        if(type.equals("file"))
            return new FileAuditeurDAO("."+File.separator+"tp9_question3"+File.separator, "auditeurs.txt");

        if(type.equals("jdbc"))// cf. http://hsqldb.org/
            return new DBAuditeurDAO("org.hsqldb.jdbcDriver","jdbc:hsqldb:auditeurs_base");

        return null;
    }

    
    public static DAO<Auditeur,Integer> getAuditeurDAO() throws Exception{
        return getAuditeurDAO("file");
        
        // getAuditeurDAO("jdbc");
        // a remplacer par getAuditeurDAO("file");
    }

}
