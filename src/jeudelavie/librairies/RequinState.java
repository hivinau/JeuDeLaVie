package jeudelavie.librairies;

public interface RequinState {
	
	public static final int CHILD = 0x01;
	public static final int YOUNG = 0x02;
	public static final int ADULT = 0x03;
	
	public void stateChanged(int state);
}
