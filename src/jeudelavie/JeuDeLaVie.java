package jeudelavie;

import jeudelavie.librairies.Mer;

public class JeuDeLaVie {

	private final Mer mer;
	
	public JeuDeLaVie(int sardinesCount, int requinsCount) {
		
		mer = new Mer(sardinesCount, requinsCount);
	}
	
	public void lancerTemps(int cyclesCount) {
		
		mer.setCyclesCount(cyclesCount);
		mer.start();
	}
	
	public void lancerTemps() {
		
		lancerTemps(0);
	}

}
