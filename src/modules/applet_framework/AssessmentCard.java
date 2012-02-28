// Ear training Applet Framework -- Assessment Card
// Jacob Peck/ Jason Shaffner

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import org.jfugue.*;

public class AssessmentCard extends ActivityCard implements MouseListener, ActionListener {
  
	//variables
	ArrayList<Circle> circles;
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
	JLabel label = new JLabel("<html><center><h1>Assessment<br></h1><h2>Press start</h2></center></html>");
	JPanel topper = new JPanel(new BorderLayout());
	JPanel top = new JPanel(new FlowLayout(FlowLayout.CENTER));
	JPanel bottom = new JPanel(new FlowLayout(FlowLayout.CENTER));
							
	public void initializeThisCard() {
    System.out.println("Initializing pane...");
		level = 0;
		rightAnswers = 0;
		wrongAnswers = 0;
		addMouseListener(this);
		assemble();

		top.add(label);
		bottom.add(startButton);
		bottom.add(endButton);
		topper.add(top,BorderLayout.NORTH);
		topper.add(bottom,BorderLayout.SOUTH);
		add(topper,BorderLayout.NORTH);
		startButton.setEnabled(true);
		endButton.setEnabled(false);
		startButton.addActionListener(this);
  }
  
  public AssessmentCard() {
		setLayout(new BorderLayout());
	}
	
	public AssessmentCard(String selectedTonic, byte selectedInstrument) {
		instrument = (int)selectedInstrument;
		selectedTonic = selectedTonic;
		setLayout(new BorderLayout());
	}

	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if (command.equals("Start")) {
			label.setText("<html><center><h1>Assessment<br></h1><h2>Level 2<br></center></h2></html>");
			startButton.setEnabled(false);
			test();
		} else if (command.equals("End"))
				ETApplet.switchToCard("welcome");
	}
  
	protected void test() {
		new Thread(new Runnable() {
			public void run() {
				playTonic();
				System.out.println("Tonic played");
				while (level < 5 && wrongAnswers < 8) {
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
							label.setText("<html><center><h1>Assessment<br></h1><h2>Level " + (level+1) + "<br></h2></center></html>");
							assemble();
							rightAnswers = 0;
							wrongAnswers = 0;
							try {
								Thread.sleep(1000);
							} catch (InterruptedException ie) {}
						}
					} else {
						playIncorrect(right);
						wrongAnswers++;
					}
					click = null;
				}
				endButton.setEnabled(true);
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
		circles = new ArrayList<Circle>();
		circles.add(new Circle(x,y,tonic));
		for (int i = 1; i <= 4 + (2 * level); i++) {
			if (i % 2 == 0) {
				if (i < 5) t = i;
				else t = i - 1;
				x += i * 70;
			} else {
				if (i < 8)	t = -i;
				else t = -i + 1;
				x -= i * 70;
			}
			String note = "<" + t + ">q"; 
			circles.add(new Circle(x,y,note));
		}
		repaint();
	}

	public void playCircle(Circle circle) {
		sequence = new IntervalNotation(circle.getNote());
		pattern = sequence.getPatternForRootNote(tonic);
		repaint();
		ETApplet.player.play(pattern);
	}

	public void playTonic() {
		circles.get(0).setColor(Color.YELLOW);
		System.out.println("Color = yellow");
		repaint();
		ETApplet.player.play(tonic);
		System.out.println("Pattern played");
		circles.get(0).setColor(Color.BLACK);
		repaint();
	}

	public void playCorrect(Circle circle) {
		circle.setColor(Color.GREEN);
		sequence = new IntervalNotation(circle.getNote());
		pattern = sequence.getPatternForRootNote(tonic);
		repaint();
		ETApplet.player.play(pattern);
		circle.setColor(Color.BLACK);
		repaint();
	}
	
	public void playIncorrect(Circle circle) {
		circle.setColor(Color.RED);
		playTonic();
		sequence = new IntervalNotation(circle.getNote());
		pattern = sequence.getPatternForRootNote(tonic);
		ETApplet.player.play(pattern);
		circle.setColor(Color.BLACK);
		repaint();
	}
}
