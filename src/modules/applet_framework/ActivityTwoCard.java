import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import org.jfugue.*;

public class ActivityTwoCard extends ActivityCard implements MouseListener, ActionListener{
	
	//variables
	ArrayList<Circle> circles;
	int trial;
	int rightAnswers;
	int wrongAnswers;
	Circle right;
	//integer array for the number of clicks?
	int[] click = null;
	Random random = new Random();
	Random ran = new Random();
	int instrument;
	boolean gameOver;
	String tonic;
	Pattern pattern;
	IntervalNotation sequence;
	
	JButton startButton = new JButton("Start");
	JButton endButton = new JButton("End");
	JLabel header = new JLabel("<html><center><h1>Activity Two<br></h1></center></html>");
	JPanel topper = new JPanel(new BorderLayout());
	JPanel top = new JPanel(new FlowLayout(FlowLayout.CENTER));
	JPanel bottom = new JPanel(new FlowLayout(FlowLayout.CENTER));
	JLabel play = new JLabel("<html><center><h2>Your guess?</h2></center></html>");
	JPanel prompt = new JPanel(new FlowLayout(FlowLayout.CENTER));
	
	public void initializeThisCard(){
		System.out.println("Initializing pane...");
		trial = 1;
		rightAnswers = 0;
		wrongAnswers = 0;
		instrument = ETApplet.selectedInstrument;
		//switch tonic
		int i = RandomTonic();
		tonic = ETApplet.selectedTonic = ETApplet.tonics.get(i);
		System.out.println(tonic);
		addMouseListener(this);
		assemble();
	}
	public int RandomTonic(){
		return ran.nextInt(ETApplet.tonics.size());
	}
	public ActivityTwoCard(){
		setLayout(new BorderLayout());
		top.add(header);
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
	public void actionPerformed(ActionEvent e){
		String command = e.getActionCommand();
		if(command.equals("Start")){
			header.setText("<html><center<h1>Activity Two<br></h1><h2>Trial 1<br></center></h2></html>");
			startButton.setEnabled(false);
			test();
			endButton.setEnabled(false);
		}else if(command.equals("End")){
			System.out.println("Ending...");
			startButton.setEnabled(true);
			endButton.setEnabled(false);
			ETApplet.switchToCard("menu");
		}
	}
	public void test(){
		new Thread(new Runnable() {
			public void run(){
			playTonic();
		while(trial < 6){
			try{
				Thread.sleep(2000);
			}catch (InterruptedException ie){}
			int i = random();
			System.out.println(i);
			//this is the cirlce that gets picked to play
			right = circles.get(i);
			playCircle(circles.get(i));
			//this sets the phrase "your guess" to true so it is visible at this point
			play.setVisible(true);
			while(click == null){
				try{
					Thread.sleep(500);
				}catch (InterruptedException ie) {}
			}
			play.setVisible(false);
			if (isWithin(click)){
				playCorrect(right);
				rightAnswers++;
				
				//play tonic then up or down to the correct bubble
				try{
					Thread.sleep(500);
				}catch (InterruptedException ie) {}
				
				if(i % 2 == 0){
					playTonic();
					int k = 2;
					while(k < i){
						playSequence(circles.get(k));
						k += 2;
					}
					playCorrect(right);
				}else{
					playTonic();
					int k = 1;
					while(k < i){
						playSequence(circles.get(k));
						k+=2;
					}
					playCorrect(right);
				}
				try{
					Thread.sleep(1000);
				}catch (InterruptedException ie){}
			}else{
				playIncorrect(right);
				wrongAnswers++;
				
				try{
					Thread.sleep(500);
				}catch (InterruptedException ie) {}
				
				if(i % 2 == 0){
					playTonic();
					int k = 2;
					while(k < i){
						playSequence(circles.get(k));
						k += 2;
					}
					playCorrect(right);
				}else{
					playTonic();
					int k = 1;
					while(k < i){
						playSequence(circles.get(k));
						k+=2;
					}
					playCorrect(right);
				}
			}
				trial++;
				header.setText("<html><center><h1>Activity Two<br></h1><h2>Trial " +
				(trial) + "<br></h2></center></html>");
				click = null;
			}
			endButton.setEnabled(true);
			gameOver = true;
			assemble();
		}
		}).start();
	}
	
	protected void recordResults(){
		ETApplet.recordActivityResults(this,trial);
	}
	
	protected Boolean isWithin(int[] click){
		int[] position = right.getPosition();
		int radius = right.getRadius();
		return click[0] > (position[0] - radius) && 
				click[0] < (position[0]+radius) &&
				click[1] > (position[1] - radius) &&
				click[1] < (position[1]+radius);
	}
	
	public void mouseClicked(MouseEvent e){
		click = new int[2];
		click[0] = e.getX();
		click[1] = e.getY();
	}
	//not used
	public void mouseEntered (MouseEvent me) {} 
	public void mousePressed (MouseEvent me) {} 
	public void mouseReleased (MouseEvent me) {}  
	public void mouseExited (MouseEvent me) {}  
	
	protected int random(){
		return random.nextInt(circles.size());
	}
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		for(Circle c : circles)
			c.draw(g);
	}
	
	public void assemble(){
		//these move all the cirles left right or up down
		int x = (int)(getSize().getWidth()/2.0);
		int y = (int)(getSize().getHeight()/2.0);
		int t;
		//remowing this will cause a crash
		circles = new ArrayList<Circle>();
		//this is the middle cirlce
		circles.add(new Circle(x,y,tonic));
		//for loop adds the number of circles
		for(int i = 1; i <= (14); i++){
			if(i % 2 == 0){
				if(i < 5) t = i;
				else t = i - 1;
				x += i * 70;
			}else{
				if (i < 8) t = -i;
				else t = -i + 1;
				x -= i * 70;
			}
			String note = "<" + t + ">q";
			//this adds the new circles
			circles.add(new Circle(x,y,note));
		}
		repaint();
	}
				
	public void playCircle(Circle circle){
		sequence = new IntervalNotation(circle.getNote());
		pattern = sequence.getPatternForRootNote(tonic);
		repaint();
		ETApplet.player.play(pattern);
	}
	public void playSequence(Circle circle){
		circle.setColor(Color.YELLOW);
		sequence = new IntervalNotation(circle.getNote());
		pattern = sequence.getPatternForRootNote(tonic);
		repaint();
		ETApplet.player.play(pattern);
		circle.setColor(Color.BLACK);
		repaint();
	}
	public void playTonic(){
		circles.get(0).setColor(Color.BLUE);
		repaint();
		ETApplet.player.play(tonic);
		circles.get(0).setColor(Color.BLACK);
		repaint();
	}
	public void playCorrect(Circle circle){
		circle.setColor(Color.GREEN);
		sequence = new IntervalNotation(circle.getNote());
		pattern = sequence.getPatternForRootNote(tonic);
		repaint();
		ETApplet.player.play(pattern);
		circle.setColor(Color.BLACK);
		repaint();
	}
	public void playIncorrect(Circle circle){
		circle.setColor(Color.RED);
		repaint();
		sequence = new IntervalNotation(circle.getNote());
		pattern = sequence.getPatternForRootNote(tonic);
		ETApplet.player.play(pattern);
		circle.setColor(Color.BLACK);
		repaint();
	}
}