import java.util.ArrayList;
import java.util.List;

import model.Birou;
import model.Document;
import model.Dosar;

public class Main {
	public static void main(String[] args) {
		
		Document doc1 = new Document ("Poza",5,false);
		Document doc2 = new Document ("Cerere",7,false);
		Document doc3 = new Document ("Declaratie",10,false);
		
		List<Document> docList = new ArrayList<>();
		docList.add(doc1);
		docList.add(doc2);
		
		Dosar dos1 = new Dosar (docList);
		
		List<Document> docList1 = new ArrayList<>();
		docList1.add(doc1);
		docList1.add(doc3);
		
		Dosar dos2 = new Dosar (docList1);
		
		Birou bir1 = new Birou ("Buletin",2,dos1);
		Birou bir2 = new Birou ("Permis de conducere",3,dos1);
		Birou bir3 = new Birou ("Pasaport",1,dos1);
		Birou bir4 = new Birou ("Asigurare",2,dos2);
		Birou bir5 = new Birou ("Certificat de nastere",3,dos2);
	}
}
