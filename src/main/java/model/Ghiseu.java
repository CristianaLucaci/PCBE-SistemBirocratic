package model;

public class Ghiseu {
	
	private Birou birou; // biroul din care face parte ghiseul
	private int nr; // numarul de identificare al ghiseului, nu pot exista doua ghisee cu acelasi nr, apartinand aceluiasi birou 
    private String act;
	private int orderNr=1;
	private int clientsWaiting;
	
	public Ghiseu(int nr, String act) {
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

	public int getOrderNr() {
		return orderNr;
	}

	public int getClientsWaiting() {
		return clientsWaiting;
	}

	public Birou getBirou() {
		return birou;
	}

	public void setBirou(Birou birou) {
		this.birou = birou;
	}

	public void clientLeave()
	{
		clientsWaiting--;
		orderNr++;
	}

	public void clientWait(Client client)
	{
		//block
		System.out.println("Clientul "+client.getId()+": M-am blocat in asteptare");
		while(client.getOrderNr()!=orderNr);
		System.out.println("Clientul "+client.getId()+": M-am deblocat!");
		clientsWaiting--;
		serveClient(client);
	}

	public void serveClient(Client client)
	{
		if(client.getOrderNr()==0)
		{
			requestOrderNr(client);
		}
		else if(client.getOrderNr()!=orderNr)
		{
			clientWait(client);
		}
		else
		{
			//do serve
			System.out.println("Actul " + birou.getName() + " a fost obtinut\n");
			orderNr++;
		}
	}

	public void requestOrderNr(Client client)
	{
		if(clientsWaiting==0)
		{
			client.setOrderNr(orderNr);
			serveClient(client);
		}
		else
		{
			client.setOrderNr(orderNr+clientsWaiting+1);
			clientsWaiting++;
			clientWait(client);
		}
	}
}
