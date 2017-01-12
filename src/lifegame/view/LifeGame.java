package lifegame.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import lifegame.controller.*;
import lifegame.librairies.utils.*;
import lifegame.view.ui.*;

public class LifeGame extends JFrame implements CycleTaskListener {
	
	private static final long serialVersionUID = -1228926649796995669L;
	
	private Mer mer = null;
	private int cycles = -1;
	private java.util.Timer timer = null;
	
	private WindowAdapter windowAdapter = new WindowAdapter() {
		
		@Override
		public void windowClosing(WindowEvent e) {
			
			Resources resource = Resources.getInstance();
			
			final String title = resource.getString("close_app__title");
			final String message = resource.getString("close_app__message");
			
			if(DialogUtil.canAppClosed(title, message)) {
				
				AppUtil.closeApp();
			}
		}
	};

	@Override
	public boolean schedule(int currentCycle) {
		
		System.out.println(String.format("cycle %d scheduled", currentCycle));
		
		mer.update();
		
		return (cycles <= 0 || currentCycle < cycles);
	}
	
	@Override
	public void cancel() {
		
		System.out.println("stop cycles");
		
		if(timer != null) {
			
			timer.cancel();
			timer = null;
		}
	}
	
	public LifeGame(int sardinesCount, int requinsCount) {
		
		Resources.getInstance().initAppConfiguration();
		addWindowListener(windowAdapter);
		
		mer = new Mer(sardinesCount, requinsCount);
		
		initComponents();
	}
	
	public void start() {
		
		start(-1);
	}
	
	public void start(int cycles) {
		
		final String log;
		if(cycles <= 0) {
			
			log = String.format("start cycles boundlessly", cycles);
		} else {
			
			log = String.format("start %d cycles", cycles);
		}
		System.out.println(log);
		
		this.cycles = cycles;
		CycleTask task = new CycleTask(this);
		
		Resources resources = Resources.getInstance();
		
		final int period = resources.getInt("cycle__period");
		
		if(timer != null) {
			
			timer.cancel();
			timer = null;
		}

		timer = new java.util.Timer();
		timer.schedule(task, 1000, period * 1000);
	}

	private void initComponents() {
		
		setMinimumSize(new Dimension(400, 400));
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);
		
		final String title = System.getProperty("app_name");
		setTitle(title);
		
		Container container = getContentPane();
		container.setLayout(new BorderLayout());
		
		container.add(mer);
		
		pack();
	}
}
