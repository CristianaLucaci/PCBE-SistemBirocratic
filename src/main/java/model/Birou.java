package model;

import java.util.ArrayList;
import java.util.List;

public class Birou {
    private String name;
    private int nrGhisee;
    private List<Ghiseu> ghisee;
    private Dosar dosar;

    public Birou(String name, int nrGhisee, Dosar dosar) {
        this.name = name;
        this.nrGhisee = nrGhisee;
        this.dosar = dosar;
        ghisee = new ArrayList();
        for(int i = 0 ; i < nrGhisee ; i++) {
        	ghisee.add(new Ghiseu(i,name));
            ghisee.get(i).setBirou(this);
        }
        
    }

    public String getName() {
        return name;
    }

    public int getNrGhisee() {
        return nrGhisee;
    }

    public List<Ghiseu> getGhisee(){
    	return ghisee;
    }
    
    public Dosar getDosar() {
        return dosar;
    }
    
}
