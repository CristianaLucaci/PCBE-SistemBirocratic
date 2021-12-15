package model;

import java.util.ArrayList;
import java.util.List;

public class Client implements Runnable {

    private int id;     //numarul(id-ul) clientului
    private Birou birou;    //biroul de la care trebuie sa ia actul
    private Dosar dosar;    //dosarul clientului, gol la inceput
    private int orderNr=0;

    public Client(int id, Birou birou, Dosar dosar) {
        this.id = id;
        this.birou = birou;
        this.dosar = dosar;
    }

    public int getId() {
        return id;
    }

    @Override
    public void run() {
        List<Document> reqDocs = birou.getDosar().getAllRequiredDocuments();
        String docs = "";
        for(Document doc : reqDocs) {
            docs = docs + doc.getName() + " ";
        }

        System.out.println("Clientul " + id + ": are nevoie de actul: " + birou.getName());
        System.out.println("Clientul "+ id + ": are nevoie de urmatoarele documente: " + docs + "pentru a obtine actul: " + birou.getName());

        while(!dosar.checkIfHasAllDocuments(reqDocs)) {
            try {
            	dosar.getDocumente(id);
                if(dosar.checkIfHasAllDocuments(reqDocs)) {
                    goToGhiseu();
                    //System.out.println("Actul " + birou.getName() + " a fost obtinut\n");
                }
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }
        birou.getDosar().setDocumentsNotTaken();
    }

    public int getOrderNr() {
        return orderNr;
    }

    public void setOrderNr(int orderNr) {
        this.orderNr = orderNr;
    }

    public void goToGhiseu()
    {
        int minWaiting=999999;
        int minpos=0;
        int i = 0;
        Ghiseu g;
        int clientsWaiting;
        int count = 0;
        while(i<birou.getGhisee().size())
        {
        	g=birou.getGhisee().get(i);
        	
        	if(g.getOnPause() == true) {
        		count++;
        		if(count == birou.getGhisee().size()) {
        			i = -1;
        			count = 0;
        		}
        	}else {
        		clientsWaiting=g.getClientsWaiting();
                if(clientsWaiting<minWaiting)
                {
                    minWaiting=clientsWaiting;
                    minpos=i;
                }
        	}
        	i++;
        }
        g=birou.getGhisee().get(minpos);
        System.out.println("Clientul "+getId()+":Am mers la ghiseul "+(minpos+1));
        //synchronized (birou.getGhisee().get(minpos))
        {
            g.serveClient(this);
        }
    }
}
