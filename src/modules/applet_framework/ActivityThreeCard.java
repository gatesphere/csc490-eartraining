import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import org.jfugue.*;

public class ActivityThreeCard extends ActivityCard implements MouseListener, ActionListener {
//variables
  ArrayList<Circle> circles;
  ArrayList<Circle> ccircles; //keep track of all circles that were played
  int level;
  int correctAnswers;
  int wrongAnswers;
  Circle right;
  int[] click = null;
  Random random = new Random();
  //instrument and tonic won't be hard coded in future
  int instrument;
  boolean gameOver;
  String tonic;
  Pattern pattern;
  IntervalNotation sequence;
  JButton startButton = new JButton("Start");
  JButton endButton = new JButton("End");
  JLabel label = new JLabel("<html><center><h1>Activity Three<br></h1><h2>Press start</h2></center></html>");
  JPanel topper = new JPanel(new BorderLayout());
  JPanel top = new JPanel(new FlowLayout(FlowLayout.CENTER));
  JPanel bottom = new JPanel(new FlowLayout(FlowLayout.CENTER));
  JLabel play = new JLabel("<html><center><h2>Your guess?</h2></center></html>");
  JPanel prompt = new JPanel(new FlowLayout(FlowLayout.CENTER));

  public void initializeThisCard() {
    System.out.println("Initializing pane...");
    level = 0;
    correctAnswers = 0;
    wrongAnswers = 0;
    instrument = ETApplet.selectedInstrument;
    tonic = ETApplet.selectedTonic;
    addMouseListener(this);
    assemble();
  }

  public ActivityThreeCard(){
    setLayout(new BorderLayout());
top.add(label);
    bottom.add(startButton);
    bottom.add(endButton);
    prompt.add(play);
    topper.add(top,BorderLayout.NORTH);
    topper.add(bottom,BorderLayout.CENTER);
    topper.add(prompt,BorderLayout.SOUTH);
    add(topper,BorderLayout.NORTH);
    play.setVisible(false);
    startButton.setEnabled(true);
    endButton.setEnabled(false);
    startButton.addActionListener(this);
    endButton.addActionListener(this);
  }

  public void actionPerformed(ActionEvent e) {
    String command = e.getActionCommand();
    if (command.equals("Start")) {
      label.setText("<html><center><h1>Activity Three<br></h1><h2>Level 1<br></center></h2></html>");
      startButton.setEnabled(false);
      test();
    } else if (command.equals("End")) {
        System.out.println("Ending...");
        ETApplet.switchToCard("menu");
    }
  }

  protected void test() {
    new Thread(new Runnable() {
      public void run() {
				playTonic();
				while (level < 5 && wrongAnswers < 1) {
          try {
            Thread.sleep(2000);
          } catch (InterruptedException ie) {}
          int i = random();
          right = circles.get(i);
          ccircles.add(right);
					System.out.println("top");
          int k = 0;
          while(k < ccircles.size()) { 
						playCircle(ccircles.get(k++));
						System.out.println("top" + k);
						try { Thread.sleep(250);
						} catch (InterruptedException ie) {}
					}
          play.setVisible(true);
          while(click == null) {
            try { Thread.sleep(500);
						} catch (InterruptedException ie) {}
					}
          play.setVisible(false);
					int totalClicks = 0;
          while(totalClicks < ccircles.size()){
          	while(click == null) {
            	try { Thread.sleep(250);
							} catch (InterruptedException ie) {}
						}
						right = ccircles.get(totalClicks);
          	if (isWithin(click)) playCorrect(ccircles.get(totalClicks++));
						else {
							while (k < ccircles.size()){ 
								playIncorrect(ccircles.get(k));
								try { Thread.sleep(250);
								} catch (InterruptedException ie) {}
							}
        		endButton.setEnabled(true);
        		gameOver = true;
						recordResults();
						return;
						}
						click = null;
					}
            correctAnswers++;
            if (correctAnswers >= level + 9) {
              level++;
              label.setText("<html><center><h1>Activity Three<br></h1><h2>Level " + (level + 1) + "<br></h2></center></html>");
              assemble();
              correctAnswers = 0;
              wrongAnswers = 0;
              try {
                Thread.sleep(1000);
              } catch (InterruptedException ie) {}
            }
					}
        endButton.setEnabled(true);
        gameOver = true;
				recordResults();
			}
    }).start();
  }

protected void recordResults() {
ETApplet.recordActivityResults(this,level);
}

  protected boolean isWithin(int[] click) {
    int[] position = right.getPosition();
    int radius = right.getRadius();
    return click[0] > position[0]-radius && click[0] < position[0]+radius &&
            click[1] > position[1]-radius && click[1] < position[1]+radius;
  }

  public void mouseClicked(MouseEvent event) {
    click = new int[2];
    click[0] = event.getX();
    click[1] = event.getY();
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
    ccircles = new ArrayList<Circle>();
    circles.add(new Circle(x,y,tonic));
    for (int i = 1; i <= (2 * (level+1)); i++) {
      if (i % 2 == 0) {
        if (i < 5) t = i;
        else t = i - 1;
        x += i * 70;
      } else {
        if (i < 8) t = -i;
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
    repaint();
    ETApplet.player.play(tonic);
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
