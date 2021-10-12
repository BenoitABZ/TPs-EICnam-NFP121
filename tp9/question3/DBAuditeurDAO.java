package question3;
import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class DBAuditeurDAO implements DAO<Auditeur, Integer>{
    private final String DB_URL;
    private final String DB_USER     = "sa";
    private final String DB_PASSWORD = "";

    private PreparedStatement createStmt;
    private PreparedStatement retrieveStmt;
    private PreparedStatement updateStmt;
    private PreparedStatement deleteStmt;
    private PreparedStatement findAllStmt;
    private Connection conn;

    public DBAuditeurDAO(final String driverJDBC, final String DB_URL) throws Exception{
        try{
            Class.forName(driverJDBC);
        }catch(Exception e){
            e.printStackTrace();
            throw e;
        }
        this.DB_URL = DB_URL;

        this.conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        createTable();
        this.createStmt      = conn.prepareStatement("INSERT INTO auditeurs (nom,prenom,email) VALUES(?,?,?)");
        this.retrieveStmt    = conn.prepareStatement("SELECT id,nom,prenom,email FROM auditeurs WHERE id=?");
        this.updateStmt      = conn.prepareStatement("UPDATE auditeurs SET nom=?,prenom=?,email=? WHERE id=?");
        this.deleteStmt      = conn.prepareStatement("DELETE FROM auditeurs WHERE id=?");
        this.findAllStmt     = conn.prepareStatement("SELECT * FROM auditeurs order by id; ");

    }

    private void createTable() throws Exception{
        try{
            //Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            Statement stmt = conn.createStatement();
            //stmt.executeUpdate("CREATE TABLE auditeurs (id IDENTITY, nom VARCHAR, prenom VARCHAR, email VARCHAR );");

            stmt.executeUpdate("CREATE TABLE auditeurs (id INT IDENTITY, nom VARCHAR, prenom VARCHAR, email VARCHAR );");
            stmt.close();
            //conn.close();
        }catch(java.sql.SQLException e){
            if(!(e.getMessage().startsWith("Table already exists"))){
                throw e;
            }
        }
    }

    public void create(Auditeur a) throws Exception{
        // conn.prepareStatement("INSERT INTO auditeurs (nom,prenom,email) VALUES(?,?,?)");
        createStmt.setString(1, a.getNom());
        createStmt.setString(2, a.getPrenom());
        createStmt.setString(3, a.getEmail());     
        createStmt.executeUpdate();
        Statement stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery("CALL IDENTITY();");
        if (result.next())
            a.setId(result.getInt(1));
        stmt.close();

    }

    public Auditeur retrieve(Integer id) throws Exception{
        // conn.prepareStatement("SELECT id,nom,prenom,email FROM auditeurs WHERE id=?");
        retrieveStmt.setInt(1, id.intValue());
        ResultSet result = retrieveStmt.executeQuery();
        if(!result.next()) 
            throw new Exception(" pas de resultat pour " + id + " ???");
        Auditeur a = new Auditeur(result.getString("nom"),result.getString("prenom"),result.getString("email"));
        a.setId(result.getInt("id"));
        return a;
    }

    public List<Auditeur> findAll() throws Exception{
        // conn.prepareStatement("SELECT * FROM auditeurs order by id; ");
        List<Auditeur> list = new ArrayList<Auditeur>();
        ResultSet result = findAllStmt.executeQuery();
        while(result.next()){
            Auditeur a = new Auditeur(result.getString("nom"),result.getString("prenom"),result.getString("email"));
            a.setId(result.getInt("id"));
            list.add(a);
        }
        return list;
    }

    public void update(Auditeur a) throws Exception{
        // conn.prepareStatement("UPDATE auditeurs SET nom=?,prenom=?,email=? WHERE id=?");
        updateStmt.setString(1, a.getNom());
        updateStmt.setString(2, a.getPrenom());
        updateStmt.setString(3, a.getEmail());
        updateStmt.setInt(4, a.getId());
        updateStmt.executeUpdate();
    }

    public void delete(Integer id) throws Exception{
        // conn.prepareStatement("DELETE FROM auditeurs WHERE id=?");
        deleteStmt.setInt(1, id.intValue());
        deleteStmt.executeUpdate();
    }

    public List<Auditeur> filter(Criteria<Auditeur> criteria) throws Exception{
        return DAO.DAOUtils.filter(this,criteria);
    }

}
