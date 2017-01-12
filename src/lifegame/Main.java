package lifegame;

import javax.swing.*; 
import lifegame.view.*;
import lifegame.librairies.utils.*;

public class Main {
	
	public static void main(String[] args) {
		
		try {
			
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			
        } catch (Exception exception) {
        	
        	System.err.print(exception.getMessage());
        	exception.printStackTrace();
        }
		
		AppUtil.runOnUIThread(new Runnable() {
			
			@Override
			public void run() {
				
				LifeGame game = new LifeGame(5, 1);
				game.setVisible(true);
				
				game.start();
			}
		});
	}
}
