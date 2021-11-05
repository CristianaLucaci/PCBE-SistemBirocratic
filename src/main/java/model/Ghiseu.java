package model;

public class Ghiseu {
	
	private Birou birou; // biroul din care face parte ghiseul
	private int nr; // numarul de identificare al ghiseului, nu pot exista doua ghisee cu acelasi nr, apartinand aceluiasi birou 
	
	public Ghiseu(Birou birou, int nr) {
		this.birou = birou;
		this.nr = nr;
	}
	
	public int getNr() {
		return nr;
	}
	public void setNr(int nr) {
		this.nr = nr;
	}
	
	public Birou getBirou() {
		return birou;
	}
	public void setBirou(Birou birou) {
		this.birou = birou;
	}
	

}
