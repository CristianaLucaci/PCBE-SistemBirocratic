package model;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;

public class Dosar {
    private List<Document> requiredDocs;
    private List<Document> existingDocs;

    public Dosar(List<Document> requiredDocs, List<Document> existingDocs) {
        this.requiredDocs = requiredDocs;
        this.existingDocs = existingDocs;
    }

    public void addDocument(Document d)
    {
        requiredDocs.add(d);
    }

    public List<Document> getAllRequiredDocuments() {
        return requiredDocs;
    }

    public List<Document> getAllExistingDocuments() { return existingDocs; }

    public void getDocumente(int idClient) throws InterruptedException {
        if(!checkIfHasAllDocuments(requiredDocs)) {
            for(Document doc : requiredDocs) {
                if(!doc.isLuat()) {
                    existingDocs.add(doc.getDocument());
                    System.out.println("Clientul " + idClient+": Documentul " + doc.getName() + " a fost luat");
                    Thread.sleep(doc.getDurata());
                    doc.setLuat(true);
                }
            }
        }
    }

    public boolean checkIfHasAllDocuments(List<Document> requiredDocs) {
        if(existingDocs.size() == requiredDocs.size()) {
            //System.out.println("Toate documentele au fost luate");
            return true;
        }
        return false;
    }

    public void setDocumentsNotTaken() {
        for(Document doc : requiredDocs)
            doc.setLuat(false);
        existingDocs.removeAll(existingDocs);
    }

    public void setRequiredDocs(List<Document> requiredDocs) {
        this.requiredDocs = requiredDocs;
    }
}
