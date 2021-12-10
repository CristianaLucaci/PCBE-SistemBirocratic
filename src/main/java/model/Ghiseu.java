package model;

public class Ghiseu {
	
	private Birou birou; // biroul din care face parte ghiseul
	private int nr; // numarul de identificare al ghiseului, nu pot exista doua ghisee cu acelasi nr, apartinand aceluiasi birou 
    private String act;
	//private Integer orderNr=new Integer(1);
	private int orderNr=1;
	//private Integer clientsWaiting=new Integer(0);
	private int clientsWaiting;
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

	public synchronized int getOrderNr() {
		return orderNr;
	}

	public synchronized int getClientsWaiting() {
		return clientsWaiting;
	}

	public synchronized boolean isServingClient() {
		return servingClient;
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

	public synchronized void incOrderNr()
	{
		orderNr++;
	}

	public synchronized void incClientsWaiting()
	{
		clientsWaiting++;
	}

	public synchronized void decClientsWaiting()
	{
		clientsWaiting--;
	}

	public synchronized void setServingClient()
	{
		servingClient=true;
	}

	public synchronized void clearServingClient()
	{
		servingClient=false;
	}

	public void clientWait(Client client)
	{
		//block
		System.out.println("Clientul "+client.getId()+": M-am blocat in asteptare cu numarul de ordine "+client.getOrderNr());
		while(client.getOrderNr()!=getOrderNr()){
			/*System.out.println("Clientul "+client.getId()+": In asteptare cu numarul de ordine "+client.getOrderNr()+" si ghiseul are nr ordine "+orderNr);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}*/
		}
		System.out.println("Clientul "+client.getId()+": M-am deblocat!");
		decClientsWaiting();
		serveClient(client);
	}

	public void serveClient(Client client) {
		if(client.getOrderNr()==0)
		{
			requestOrderNr(client);
		}
		else if(client.getOrderNr()!=getOrderNr())
		{
			clientWait(client);
		}
		else
		{
			//do serve
			setServingClient();
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Clientul "+client.getId()+":Actul " + birou.getName() + " a fost obtinut\n");
			clearServingClient();
			incOrderNr();
		}
	}

	public void requestOrderNr(Client client)
	{
		if(getClientsWaiting()==0)
		{
			if(!isServingClient())
			{
				client.setOrderNr(getOrderNr());
				serveClient(client);
			}
			else
			{
				client.setOrderNr(getOrderNr()+1);
				incClientsWaiting();
				clientWait(client);
			}
		}
		else
		{
			client.setOrderNr(getOrderNr()+getClientsWaiting()+1);
			incClientsWaiting();
			clientWait(client);
		}
	}
}
