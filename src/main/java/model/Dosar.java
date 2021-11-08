package model;

import java.util.ArrayList;
import java.util.List;

public class Dosar {
    private int nrDocumente;
    private List<Document> documente;

    public Dosar() {
        nrDocumente=0;
        documente=new ArrayList<>();
    }

    public Dosar(int nrDocumente, List<Document> documente) {
        this.nrDocumente = nrDocumente;
        this.documente = documente;
    }

    public void addDocument(Document d)
    {
        nrDocumente++;
        documente.add(d);
    }

    public int getNrDocumente() {
        return nrDocumente;
    }

    public void setNrDocumente(int nrDocumente) {
        this.nrDocumente = nrDocumente;
    }

    public List<Document> getDocumente() {
        return documente;
    }

    public void setDocumente(List<Document> documente) {
        this.documente = documente;
    }
}
