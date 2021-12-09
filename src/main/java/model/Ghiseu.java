package model;

public class Ghiseu {
	
	private Birou birou; // biroul din care face parte ghiseul
	private int nr; // numarul de identificare al ghiseului, nu pot exista doua ghisee cu acelasi nr, apartinand aceluiasi birou 
    private String act;
	private Integer orderNr=new Integer(1);
	//private int orderNr=1;
	private Integer clientsWaiting=new Integer(0);
	//private int clientsWaiting;
	private boolean servingClient=false;
	
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
		System.out.println("Clientul "+client.getId()+": M-am blocat in asteptare cu numarul de ordine "+client.getOrderNr());
		while(client.getOrderNr()!=orderNr){
			System.out.println("Clientul "+client.getId()+": In asteptare cu numarul de ordine "+client.getOrderNr()+" si ghiseul are nr ordine "+orderNr);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Clientul "+client.getId()+": M-am deblocat!");
		clientsWaiting--;
		serveClient(client);
	}

	public void serveClient(Client client) {
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
			servingClient=true;
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Clientul "+client.getId()+":Actul " + birou.getName() + " a fost obtinut\n");
			servingClient=false;
			orderNr++;
		}
	}

	public void requestOrderNr(Client client)
	{
		if(clientsWaiting==0)
		{
			if(!servingClient)
			{
				client.setOrderNr(orderNr);
				serveClient(client);
			}
			else
			{
				client.setOrderNr(orderNr+1);
				clientsWaiting++;
				clientWait(client);
			}
		}
		else
		{
			client.setOrderNr(orderNr+clientsWaiting+1);
			clientsWaiting++;
			clientWait(client);
		}
	}
}
