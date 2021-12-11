package model;

import java.util.Random;

public class GhiseuSchedule implements Runnable {
	private Random random = new Random();
	private Birou _birou;
	private boolean condition=true;
	
	public GhiseuSchedule(Birou birou){
		
		_birou=birou;
	}

	@Override
	public void run() {
		int count = 0;
		while(condition){
			try {
				Thread.sleep(500);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			for(Ghiseu g : _birou.getGhisee()) {
				if(!g.getHadPause()) {
					float chance = random.nextFloat();
					try {
						if(g.getOnPause() == true) {
							Thread.sleep(3000);
							count++;
							g.clearOnPause();
							g.setHadPause(true); // Un ghiseu poate lua o singura pauza
						}
					}catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (chance <= 0.10f && !g.getHadPause()){
						g.setOnPause();
					}
				}
				else {
					if (count == _birou.getNrGhisee()) {
						condition = false;
					}
				}
			}
		}
	}
}
