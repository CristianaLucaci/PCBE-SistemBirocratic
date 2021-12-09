import java.util.ArrayList;
import java.util.List;

import model.Birou;
import model.Client;
import model.Document;
import model.Dosar;

public class Main {
	public static void main(String[] args) {
		
		Document doc1 = new Document ("Poza",1000,false);
		Document doc2 = new Document ("Cerere",2000,false);
		Document doc3 = new Document ("Declaratie",3000,false);
		
		List<Document> docList = new ArrayList<>();
		List<Document> docListEmpty = new ArrayList<>();
		docList.add(doc1);
		docList.add(doc2);

		Dosar dos1 = new Dosar(docList, docListEmpty);
		
		List<Document> docList1 = new ArrayList<>();
		docList1.add(doc1);
		docList1.add(doc3);
		
		Dosar dos2 = new Dosar(docList1, docListEmpty);
		
		Birou bir1 = new Birou ("Buletin",2,dos1);
		Birou bir2 = new Birou ("Permis de conducere",3,dos1);
		Birou bir3 = new Birou ("Pasaport",1,dos1);
		Birou bir4 = new Birou ("Asigurare",2,dos2);
		Birou bir5 = new Birou ("Certificat de nastere",3,dos2);

		ArrayList<Client> clients = new ArrayList<>();

		Client client = new Client(1, bir1, bir1.getDosar());
		Client client1 = new Client(2, bir4, bir4.getDosar());
		Client client2 = new Client(3, bir3, bir3.getDosar());

		clients.add(client);
//		clients.add(client1);
//		clients.add(client2);

		for(Client cl : clients)
			cl.run();


		createClient(1,bir1);
		createClient(2,bir1);
	}

	public static void createClient(int id,Birou birou)
	{
		Thread thread = new Thread(new Client(id, birou, new Dosar(birou.getDosar().getAllRequiredDocuments(), new ArrayList<>())));
		System.out.println("Clientul " + id + " a fost creat");
		thread.start();
	}
}
