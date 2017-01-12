package lifegame.view.ui;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

import lifegame.model.*;
import lifegame.model.states.RequinState;

/**
 * <b>Square:</b><br>
 * <p>Représente une case de la mer.</p>
 * @author Jesus GARNICA OLARRA & Hivinau GRAFFE
 * @version 1.0
 */
public class Square extends JPanel implements RequinState {

	private static final long serialVersionUID = 710206767483697784L;
	
	private JLabel imageView = null;
	private Poisson poisson = null;

	@Override
	public void stateChanged(final int state) {
		
		String url = null;
		switch (state) {
		case RequinState.CHILD:
			
			url = "drawable/shark_child.png";
			break;
		case RequinState.YOUNG:
			
			url = "drawable/shark_young.png";
			break;
		case RequinState.ADULT:
			
			url = "drawable/shark_adult.png";
			break;
		}
		
		if(url != null) {
			
			try {

				setImage(url);
				
			} catch(Exception ignored) {

				ignored.printStackTrace();
			}
		}
	}

	/**
	 * <p>Crée une nouvelle instance de {@link lifegame.view.ui.Square}: <br>
	 * <ul>
	 * 	<li>Initialise les composants.</li>
	 * </ul>
	 * <p>
	 */
	public Square() {
		
		initComponents();
	}
	
	/**
	 * Modifie le poisson.
	 * @param poisson poisson qui occupera cet espace.
	 */
	public void setPoisson(Poisson poisson) {

		this.poisson = poisson;

		if(this.poisson instanceof Sardine) {

			try {

				setImage("drawable/sardine.png");
				
			} catch(Exception ignored) {

				ignored.printStackTrace();
			}
		} else {

			((Requin) this.poisson).setRequinState(this);
			stateChanged(RequinState.CHILD);
		}

	}
	
	/**
	 * Retire le poisson de cet espace.
	 */
	public void removePoisson() {
		
		imageView.setIcon(null);
	}
	
	/**
	 * Initialise les composants.
	 */
	private void initComponents() {
		
		setBorder(BorderFactory.createLineBorder(Color.black));
		
		imageView = new JLabel();
		add(imageView);
	}
	
	/**
	 * Met à jour l'image à partir d'un url.
	 * @param url url de l'image.
	 * @throws Exception l'usage de l'image spécifié a échoué.
	 */
	private void setImage(String url) throws Exception {
		
		BufferedImage img = ImageIO.read(new File(url));
        ImageIcon rawIcon = new ImageIcon(img);
        
        Image rawImage = rawIcon.getImage();
        Image scaledImage = rawImage.getScaledInstance(30, 30,  java.awt.Image.SCALE_SMOOTH);
        
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        
        imageView.setIcon(scaledIcon);
	}
}
