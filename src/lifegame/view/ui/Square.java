package lifegame.view.ui;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

import lifegame.model.*;

public class Square extends JPanel {

	private static final long serialVersionUID = 710206767483697784L;
	
	private JLabel imageView = null;

	public Square() {
		
		initComponents();
	}
	
	public void setPoisson(Poisson poisson) {
		
		try {
			
			if(poisson instanceof Sardine) {

				setImage("drawable/sardine.png");
			} else {

				setImage("drawable/shark.png");
			}
			
		} catch(Exception exception) {
			
		}
	}
	
	public void removePoisson() {
		
		imageView.setIcon(null);
	}
	
	private void initComponents() {
		
		setBorder(BorderFactory.createLineBorder(Color.black));
		
		imageView = new JLabel();
		add(imageView);
	}
	
	private void setImage(String url) throws Exception {
		
		BufferedImage img = ImageIO.read(new File(url));
        ImageIcon rawIcon = new ImageIcon(img);
        
        Image rawImage = rawIcon.getImage();
        Image scaledImage = rawImage.getScaledInstance(30, 30,  java.awt.Image.SCALE_SMOOTH);
        
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        
        imageView.setIcon(scaledIcon);
	}
}
