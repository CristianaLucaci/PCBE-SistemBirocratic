package model;

public class Ghiseu {
	
	//private Birou birou; // biroul din care face parte ghiseul
	private int nr; // numarul de identificare al ghiseului, nu pot exista doua ghisee cu acelasi nr, apartinand aceluiasi birou 
    private String act;
	
	public Ghiseu( int nr, String act) {
		this.nr = nr;
		this.act = act;
	}
	
	public int getNr() {
		return nr;
	}
	public void setNr(int nr) {
		this.nr = nr;
	}
	
	/*public Birou getBirou() {
		return birou;
	}
	
	public void setBirou(Birou birou) {
		this.birou = birou;
	}*/
	
	public String getAct() {
    	return act;
    }


    public void setAct(String act) {
    	this.act = act;
    }
	
	
	

}
