package question3;

import java.util.*;
import java.io.*;


public class FileAuditeurDAO implements DAO<Auditeur, Integer> {
	private String fileName;
// à compléter

	public FileAuditeurDAO(final String path, final String fileName) throws Exception {

		File dir = new File(path);
		if (!dir.exists()) { // si le répertoire n'existe pas (1 seul niveau)
			dir.mkdir();
		}
		this.fileName = path + fileName;
		File f = new File(this.fileName);
		if (!(f.isFile())) { // si le fichier n'existe pas
			f.createNewFile();
		}

	}

	public FileAuditeurDAO(final String fileName) throws Exception {
		this("." + File.separator, fileName);
	}

	public void create(Auditeur a) throws Exception {
		/*	
		List<Auditeur> auditeurs = this.findAll();
		
		for (Auditeur auditeur: auditeurs) {
			
			System.out.println(auditeur.toString());
		}
	
		int size = this.findAll().size();
		
		size++;
		
		a.setId(size);
		
		int id = a.getId();
		
		String idString =Integer.toString(id);
		
		System.out.println(size);
*/
		BufferedWriter csvWriter = new BufferedWriter(new FileWriter(fileName));
		csvWriter.append("1");
		csvWriter.append(";");
		csvWriter.append(a.getNom());
		csvWriter.append(";");
		csvWriter.append(a.getPrenom());
		csvWriter.append(";");
		csvWriter.append(a.getEmail());
		csvWriter.newLine();

		csvWriter.flush();
		csvWriter.close();

	}

	public Auditeur retrieve(Integer id) throws Exception {

		BufferedReader csvReader = new BufferedReader(new FileReader(fileName));

		String row = null;

		while ((row = csvReader.readLine()) != null) {

			Auditeur auditeur = Auditeur.parseAuditeur(row);

			if (auditeur.getId() == id) {

				return auditeur;
			}
		}
		csvReader.close();

		return null;
	}

	public List<Auditeur> findAll() throws Exception {

		BufferedReader csvReader = new BufferedReader(new FileReader(fileName));

		List<Auditeur> auditeurs = new ArrayList<>();

		String row = "";
		
		
		

		while ((row = csvReader.readLine()) != null) {
			
			Auditeur auditeur = Auditeur.parseAuditeur(row);
			
			System.out.println(auditeur.toString());

			auditeurs.add(auditeur);

		}

		csvReader.close();
		
		System.out.println(auditeurs.size());

		return auditeurs;
	}

	public void update(Auditeur a) throws Exception {
      
		int id = a.getId();
		
		delete(id);
		
		create(a);

	}

	public void delete(Integer id) throws Exception {
		
		List<Auditeur> auditeurs = findAll();
		
		File file = new File(fileName);
				
		file.delete();
		
		file.createNewFile();
				
		for (Auditeur a : auditeurs) {
			
			if(a.getId()!=id) {
			
			create(a);
			}
		}
	}

	public List<Auditeur> filter(Criteria<Auditeur> criteria) throws Exception {
		return DAO.DAOUtils.filter(this, criteria);
	}

}
