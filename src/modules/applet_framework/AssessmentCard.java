// Ear training Applet Framework -- Assessment Card
// Jacob Peck/ Jason Shaffner

import java.awt.*;
import java.util.*;
import javax.swing.*;
import org.jfugue.*;

public class AssessmentCard extends ActivityCard {
  
	//variables
	ArrayList<Circle> circles;
	int level;
	byte instrument;
	byte tonic;
							
	public void initializeThisCard() {
    System.out.println("Initializing pane...");
		ArrayList<Circle> circles = new ArrayList<>();
		level = 0;
		assemble();
  }
  
  public AssessmentCard(byte instrument, byte tonic) {
    super();
		this.instrument = instrument;
		this.tonic = tonic;
  }
  
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (Circle c : circles)
			c.draw(g);
	}

	public void assemble() {
		double x = getSize().getWidth();
		double y = getSize().getHeight();
		int t = tonic;
		circles.add(new Circle(x,y,35.0,5.0,new Note(tonic)));
		for (int i = 1; i <= 4 + 2 * level; i++) {
			if (i % 2 == 0) {
				t += i;
				x += i * 100;
			} else {
				t -= i;
				x -= i * 100;
			}
			circles.add(new Circle(x,y,new Note((byte)t)));
		}
		repaint();
	}
}
