import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Birou;
import model.Client;
import model.Document;
import model.Dosar;
import model.GhiseuSchedule;

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
		createGhiseuSchedule(bir1);
		Birou bir2 = new Birou ("Permis de conducere",3,dos1);
		createGhiseuSchedule(bir2);
		Birou bir3 = new Birou ("Pasaport",1,dos1);
		createGhiseuSchedule(bir3);
		Birou bir4 = new Birou ("Asigurare",2,dos2);
		createGhiseuSchedule(bir4);
		Birou bir5 = new Birou ("Certificat de nastere",3,dos2);
		createGhiseuSchedule(bir5);
		
		for(int i=0;i<50;i=i+5)
		{
			createClient(i, bir1);
			createClient(i+1, bir2);
			createClient(i+2, bir3);
			createClient(i+3, bir4);
			createClient(i+4, bir5);
		}
		
	}
	
	public static void createGhiseuSchedule(Birou birou) {
		Thread thread = new Thread(new GhiseuSchedule(birou));	
		thread.start();
	}

	public static void createClient(int id,Birou birou)
	{
		Thread thread = new Thread(new Client(id, birou, new Dosar(birou.getDosar().getAllRequiredDocuments(), new ArrayList<>())));
		System.out.println("Clientul " + id + ": a fost creat");
		thread.start();
	}

}
