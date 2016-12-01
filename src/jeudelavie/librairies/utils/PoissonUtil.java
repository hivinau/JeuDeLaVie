package jeudelavie.librairies.utils;

import jeudelavie.librairies.*;

public final class PoissonUtil {
	
	public static double randomAge(double minAge, double maxAge) {
		
		return minAge + Math.random() * (maxAge - minAge);
	}

	public static Poisson beBornFrom(Poisson poisson) {
		
		return null;
	}
}
