// Ear training Applet Framework -- Assessment Card
// Jacob Peck/ Jason Shaffner

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import org.jfugue.*;

public class AssessmentCard extends ActivityCard implements MouseListener, ActionListener {
  
	//variables
	ArrayList<Circle> circles = new ArrayList<Circle>();
	int level;
	int rightAnswers;
	int wrongAnswers;
	Circle right;
	int[] click = null;
	Random random = new Random();
	//instrument and tonic won't be hard coded in future
	int instrument = 0;
	boolean gameOver = false;
	String tonic = "C4";
	Pattern pattern;
	IntervalNotation sequence;
	JButton startButton = new JButton("Start");
	JButton endButton = new JButton("End");
							
	public void initializeThisCard() {
    System.out.println("Initializing pane...");
		level = 0;
		rightAnswers = 0;
		wrongAnswers = 0;
		addMouseListener(this);
		assemble();
		add(startButton);
		add(endButton);
		startButton.setVisible(true);
		endButton.setVisible(false);
		startButton.addActionListener(this);
  }
  
  public AssessmentCard() {
		setLayout(new FlowLayout(FlowLayout.CENTER));
	}
	
	public AssessmentCard(String selectedTonic, int selectedInstrument) {
		instrument = selectedInstrument;
		selectedTonic = selectedTonic;
		setLayout(new FlowLayout(FlowLayout.CENTER));
	}

	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if (command.equals("Start")) {
			startButton.setVisible(false);
			test();
		} else ETApplet.switchToCard("welcome");
	}
  
	protected void test() {
		new Thread(new Runnable() {
			public void run() {
				playTonic();
				System.out.println("Tonic played");
				while (level < 8 && wrongAnswers < 8) {
					try {
						Thread.sleep(2000);
					} catch (InterruptedException ie) {}
					int i = random();
					right = circles.get(i);
					System.out.println("Right = " + i);
					playCircle(circles.get(i));
					System.out.println("Right played");
					while(click == null) {
						try {
							Thread.sleep(500);
						} catch (InterruptedException ie) {}
					}
					if (isWithin(click)) {
						playCorrect(right);
						rightAnswers++;
						if (rightAnswers > 10) {
							level++;
							rightAnswers = 0;
							wrongAnswers = 0;
							assemble();
						}
					} else {
						playIncorrect(right);
						wrongAnswers++;
					}
					click = null;
				}
				endButton.setVisible(true);
				gameOver = true;
			}
		}).start();

	}

	protected boolean isWithin(int[] click) {
		int[] position = right.getPosition();
		System.out.println("Right x = " + position[0] + " Right y = " + position[1]);
		int radius = right.getRadius();
		return click[0] > position[0]-radius && click[0] < position[0]+radius && 
						click[1] > position[1]-radius &&  click[1] < position[1]+radius;
	}

	public void mouseClicked(MouseEvent event) { 
		click = new int[2];
		click[0] = event.getX();
		click[1] = event.getY();
		System.out.println("X = " + click[0] + " Y = " + click[1]);
	}   

	//unused...
	public void mouseEntered (MouseEvent me) {} 
	public void mousePressed (MouseEvent me) {} 
	public void mouseReleased (MouseEvent me) {}  
	public void mouseExited (MouseEvent me) {}  


	protected int random() {
		return random.nextInt(circles.size());
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (Circle c : circles)
			c.draw(g);
	}

	public void assemble() {
		int x = (int)(getSize().getWidth()/2.0);
		int y = (int)(getSize().getHeight()/2.0);
		int t;
		circles.add(new Circle(x,y,50,20,tonic));
		for (int i = 1; i <= 4 + (2 * level); i++) {
			if (i % 2 == 0) {
				if (i < 5) t = i;
				else t = i - 1;
				x += i * 100;
			} else {
				if (i < 8)	t = -i;
				else t = -i + 1;
				x -= i * 100;
			}
			String note = "<" + t + ">q"; 
			circles.add(new Circle(x,y,note));
		}
		repaint();
	}

	public void playCircle(Circle circle) {
		sequence = new IntervalNotation(circle.getNote());
		pattern = sequence.getPatternForRootNote(tonic);
		ETApplet.player.play(pattern);
	}

	public void playTonic() {
		circles.get(0).setColor(Color.YELLOW);
		repaint();
		System.out.println("Color = yellow");
		ETApplet.player.play(tonic);
		System.out.println("Pattern played");
		circles.get(0).setColor(Color.BLACK);
		repaint();
	}

	public void playCorrect(Circle circle) {
		circle.setColor(Color.GREEN);
		repaint();
		sequence = new IntervalNotation(circle.getNote());
		pattern = sequence.getPatternForRootNote(tonic);
		ETApplet.player.play(pattern);
		circle.setColor(Color.BLACK);
		repaint();
	}
	
	public void playIncorrect(Circle circle) {
		circle.setColor(Color.RED);
		repaint();
		playTonic();
		sequence = new IntervalNotation(circle.getNote());
		pattern = sequence.getPatternForRootNote(tonic);
		ETApplet.player.play(pattern);
		circle.setColor(Color.BLACK);
		repaint();
	}
}
