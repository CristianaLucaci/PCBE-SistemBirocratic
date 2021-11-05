package model;

import java.util.List;

public class Birou {
    private String name;
    private int nrGhisee;
    private List<Document> documents;

    public Birou(String name, int nrGhisee, List<Document> documents) {
        this.name = name;
        this.nrGhisee = nrGhisee;
        this.documents = documents;
    }

    public String getName() {
        return name;
    }

    public int getNrGhisee() {
        return nrGhisee;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNrGhisee(int nrGhisee) {
        this.nrGhisee = nrGhisee;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }
}
