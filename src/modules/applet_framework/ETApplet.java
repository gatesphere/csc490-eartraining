// Ear training Applet Framework -- Main Applet Class
// Jacob Peck

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import java.applet.*;
import java.io.*;
import javax.imageio.*;
import java.util.*;
import org.jfugue.*;

public class ETApplet extends JApplet {
  private static JPanel cardpanel = new JPanel(new CardLayout());
  private static Map<String, ActivityCard> cards = new HashMap<String, ActivityCard>();
  
  static final String[] tonicsArray = {"C4", "C#4", "D4", "D#4", "E4", "F4", "F#4", "G4", "G#4", "A4", "A#4", "B4"};
	static final Byte[] instrumentsArray = {0, 24, 40, 53, 73, 80};
  /* respectively: piano, guitar, violin, voice oohs, flute, square lead */
  
  /* Options for instruments and tonics.  All are enabled by default.  If this list contains
  the option, the option is enabled.  This list is accessed by the Options card. */
  static java.util.List<String> tonics = new ArrayList<String>(Arrays.asList(tonicsArray));
  static java.util.List<Byte> instruments = new ArrayList<Byte>(Arrays.asList(instrumentsArray));

	/* Eventually we're likely going to want tonic to be in bytes, 
	   in JFugue Note takes byte as parameter ie "new Note((byte)60);" creates new note at C5
		 we could probably use a map for readability, "put("C4",48);" etc...*/
	static Byte selectedTonic; 
	static Byte selectedInstrument;
  
  public void init() {
    System.out.println("Initializing...");
     try {
      javax.swing.SwingUtilities.invokeAndWait(new Runnable() {
        public void run() {
          createGUI();
        }
      });
    } catch (Exception e) {
      System.err.println("createGUI didn't successfully complete");
    }
    System.out.println("Initialized.");
  }
 
  public void stop() {
    // stub
  }
	

  
  public static void switchToCard(String cardname) {
    CardLayout cl = (CardLayout)(cardpanel.getLayout());
    cl.show(cardpanel, cardname);
    initializeCard(cardname);
  }
  
  public static void addCard(ActivityCard card, String cardname) {
    cardpanel.add(card, cardname);
    cards.put(cardname, card);
  }
  
  public static void initializeCard(String cardname) {
    cards.get(cardname).initializeThisCard();
  }
  
  
  public void createGUI() {
    
    // add cards here
    ActivityCard welcomeCard = new ActivityCard(new FlowLayout(FlowLayout.CENTER));
    welcomeCard.add(new JLabel("<html><h1>Welcome to ETApplet</h1></html>"));
    
    addCard(welcomeCard, "welcome");
    
    // menu card
    
    // options card
		// for the sake of testing, if lines make it so code won't compile, just comment them out, please and thankyou
    //OptionsCard optionsCard = new OptionsCard();
    //addCard(optionsCard, "options");
    
    // assessment card
    AssessmentCard assessmentCard = new AssessmentCard();
		addCard(assessmentCard, "assessment");
    
    // activity cards
    
    // don't touch this stuff
    this.getContentPane().add(cardpanel);
    this.validate();
    switchToCard("assessment");
    
  }
}
