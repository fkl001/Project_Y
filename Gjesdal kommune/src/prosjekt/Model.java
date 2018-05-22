package prosjekt;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.update.UpdateAction;

public class Model {
	static OntModel model = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
	
	//Dette er stien til ontologien vi lagde i Protegé
	static String base = "../Gjesdalkommune/src/prosjekt/Ontologi.owl";

	public static void main(String[] args) {
		
		getOntology();
		upDateModelWithDBpediaAbstract();
		model.write(System.out, "TURTLE");
		
		writeOntToFile();
	
	}
	/*
	 *  Denne funksjonen henter ontologien ved å bruke stringen base, somm er en sti til ontologien
	 * og lagrer den i OntModellen model. 
	 */
	private static void getOntology() {
		try {
			FileInputStream input = new FileInputStream(base);
			model.read(input, "turtle");
			input.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	/*
	 * Denne funksjonen bruker klassen DBpedia og metoden getAbstract for å 
	 * hente DBpedias Abstract-verdi til de forskjellige stedene som vi fant i DBpedia
	 * før vi putter de inn i modellen vår. 
	*/
	private static void upDateModelWithDBpediaAbstract() {
		
		String gjesdalAbstract = DBpedia.getAbstract("<http://dbpedia.org/resource/Gjesdal>");
		String oltKirkAbstract = DBpedia.getAbstract("<http://dbpedia.org/resource/Oltedal_Church>");
		String gjesKrikAbstract =DBpedia.getAbstract("<http://dbpedia.org/resource/Gjesdal_Church>");
		String dirKirkAbst = DBpedia.getAbstract("<http://dbpedia.org/resource/Dirdal_Church>");
		String aalgKirkAbs = DBpedia.getAbstract("<http://dbpedia.org/resource/Ålgård_Church>");
		String aalgGamKirkAbs = DBpedia.getAbstract("<http://dbpedia.org/resource/Old_Ålgård_Church>");
		String limaVannetAbs = DBpedia.getAbstract("<http://dbpedia.org/resource/Limavatnet>");	
		String kongeparkenAbs=  DBpedia.getAbstract("<http://dbpedia.org/resource/Kongeparken>");
		String oltVannAbs = DBpedia.getAbstract("<http://dbpedia.org/resource/Oltedalsvatnet>");
		String edlaVannAbs = DBpedia.getAbstract("<http://dbpedia.org/resource/Edlandsvatnet>");
		String flassVannAbs= DBpedia.getAbstract("<http://dbpedia.org/resource/Flassavatnet>");
		String gjesdalontology = "<http://www.semanticweb.org/marte/ontologies/2018/2/gjesdalontology.owl#>";
		
	    String prefixes = ""
	          + "PREFIX mr: " + gjesdalontology + ""
	          + "PREFIX dbo: <http://dbpedia.org/ontology/#>";
	    
	    String insertdata = "INSERT DATA{"
							+"    mr:Gjesdal dbo:Abstract \"" + gjesdalAbstract + "\"."
	    						+"    mr:OltedalKyrkje dbo:Abstract \"" + oltKirkAbstract + "\"."
	    						+"    mr:GjesdalKyrkje dbo:Abstract \"" + gjesKrikAbstract + "\"."
	    						+"    mr:DirdalKyrkje dbo:Abstract \"" + dirKirkAbst + "\"."
	    						+"    mr:ÅlgårdKirke dbo:Abstract \"" + aalgKirkAbs + "\"."
	    						+"    mr:ÅlgårdGamleKirke dbo:Abstract \"" + aalgGamKirkAbs + "\"."
	    						+"    mr:Limavatnet dbo:Abstract \"" + limaVannetAbs + "\"."
	    						+"    mr:Kongeparken dbo:Abstract \"" + kongeparkenAbs + "\"."
	    						+"    mr:Oltedalsvatnet dbo:Abstract \"" + oltVannAbs + "\"."
	    						+"    mr:Edlandsvatnet dbo:Abstract \"" + edlaVannAbs + "\"."
	    						+"    mr:Flassavatnet dbo:Abstract \"" + flassVannAbs + "\"."
	    						+"    mr:Gjesdal mr:hasName \"Gjesdal kommune\" "
	    						+"}";
	    
	    UpdateAction.parseExecute(prefixes+insertdata, model);
	    

	     model.getWriter("TURTLE").write(model, System.out, "base");
		
	}
	
	/*
	 * Denne funksjonen skriver modellen til en ny fil med navn
	 *  Gjesdal.ttl i TURTLE format
	 */
	
	private static void writeOntToFile() {
		try {
			model.write(new FileOutputStream("Gjesdal.ttl"), "TURTLE");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
