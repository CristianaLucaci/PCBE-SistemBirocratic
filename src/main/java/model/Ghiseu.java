package model;

import java.util.concurrent.TimeUnit;

public class Ghiseu {
	
	private Birou birou; // biroul din care face parte ghiseul
	private int nr; // numarul de identificare al ghiseului, nu pot exista doua ghisee cu acelasi nr, apartinand aceluiasi birou 
    private String act;
	//private Integer orderNr=new Integer(1);
	private int orderNr=1;
	//private Integer clientsWaiting=new Integer(0);
	private int clientsWaiting;
	private boolean servingClient=false;
	private boolean onPause = false;
	private boolean hadPause = false;
	
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
    
    public void setHadPause(boolean state) {
    	hadPause = state;
    }
    
    public boolean getHadPause() {
    	return hadPause;
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

	public synchronized void clientLeave()
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
	
	public synchronized boolean getOnPause() {
		return onPause;
	}
	
	public synchronized void setOnPause() {
		onPause = true;
		System.out.println("Birou " + birou.getName() +": Ghiseul " + (nr+1) + " este in pauza");
		
		//clearOnPause();
	}
	
	public synchronized void clearOnPause() {
		System.out.println("Birou " + birou.getName() +": Ghiseul " + (nr+1) + " a revenit din pauza");
		onPause = false;
	}
	
	public void goToOtherGhiseu(Client client) {
		System.out.println("Clientul "+client.getId()+": Ghiseul "+(nr+1)+" este in pauza merg la alt ghiseu!");
		client.setOrderNr(0);
		clientLeave();
		client.goToGhiseu();
	}

	public void clientWait(Client client)
	{
			//block
			
			System.out.println("Clientul "+client.getId()+": M-am blocat la "+ birou.getName() +", la ghiseul "+(nr+1)+" in asteptare cu numarul de ordine "+client.getOrderNr());
			
			while(client.getOrderNr()!=getOrderNr() && !this.getOnPause()){
				/*System.out.println("Clientul "+client.getId()+": In asteptare cu numarul de ordine "+client.getOrderNr()+" si ghiseul are nr ordine "+orderNr);
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}*/
			}
			if(!this.getOnPause()) {
				System.out.println("Clientul "+client.getId()+": M-am deblocat!");
				decClientsWaiting();
				serveClient(client);
			}else {
				goToOtherGhiseu(client);
			}
		
		
	}

	public void serveClient(Client client) {
		if(client.getOrderNr()==0)
		{
			requestOrderNr(client);
		}
		else if(client.getOrderNr()==getOrderNr())		
		{
			//do serve
			/*if(this.getOnPause() == true) {
				System.out.println("Clientul "+client.getId()+" asteapta ca ghiseul "+(nr+1)+" sa se deschida");
				while(this.getOnPause()) {
					// ghiseul este in pauza
					//System.out.println("Clientul "+client.getId()+" asteapta ca ghiseul "+nr+" sa se deschida");
				}
			}*/
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
		else {
			clientWait(client);
		}
	}

	public void requestOrderNr(Client client)
	{
		//int clientsWaiting=getClientsWaiting();
		//int orderNr=getOrderNr();
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
