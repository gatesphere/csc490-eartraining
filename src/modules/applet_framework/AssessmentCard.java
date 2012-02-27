// Ear training Applet Framework -- Assessment Card
// Jacob Peck/ Jason Shaffner

import java.awt.*;
import java.util.*;
import javax.swing.*;
import org.jfugue.*;

public class AssessmentCard extends ActivityCard {
  
	//variables
	ArrayList<Circle> circles = new ArrayList<Circle>();
	int level;
	//instrument and tonic won't be hard coded in future
	byte instrument = 0;
	byte tonic = 60;
							
	public void initializeThisCard() {
    System.out.println("Initializing pane...");
		level = 0;
		assemble();
  }
  
  public AssessmentCard() {}
  
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (Circle c : circles)
			c.draw(g);
	}

	public void assemble() {
		double x = getSize().getWidth()/2;
		double y = getSize().getHeight()/2;
		int t = tonic;
		circles.add(new Circle(x,y,75.0,20.0,new Note(tonic)));
		for (int i = 1; i <= 4 + (2 * level); i++) {
			if (i % 2 == 0) {
				if (i < 5) t = tonic + i;
				else t = tonic + i - 1;
				x += i * 150;
			} else {
				if (i < 8)	t = tonic - i;
				else t = tonic - i + 1;
				x -= i * 150;
			}
			circles.add(new Circle(x,y,new Note((byte)t)));
		}
		repaint();
	}
}
