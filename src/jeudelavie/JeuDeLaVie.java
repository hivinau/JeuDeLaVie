package jeudelavie;

import jeudelavie.librairies.Mer;

public class JeuDeLaVie {

	private final Mer mer;

	public JeuDeLaVie(int sardinesCount, int requinsCount) {

		mer = new Mer(sardinesCount, requinsCount);
	}

	public void lancerTemps(int cyclesCount) {

		mer.initializeSea();
		mer.displaySea();
	}

	public void lancerTemps() {

		lancerTemps(0);
	}


	public static void main(String[] args) {

		JeuDeLaVie gameLife = new JeuDeLaVie(2, 1);
		gameLife.lancerTemps(10);
	}
}
