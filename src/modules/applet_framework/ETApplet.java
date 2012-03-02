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
	static final Player player = new Player();
	private static JPanel cardpanel = new JPanel(new CardLayout());
	private static Map<String, ActivityCard> cards = new HashMap<String, ActivityCard>();
  
	static final String[] tonicsArray = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};
	static final Byte[] instrumentsArray = {0, 24, 40, 53, 73, 80};
  /* respectively: piano, guitar, violin, voice oohs, flute, square lead */
  
  /* Options for instruments and tonics.  All are enabled by default.  If this list contains
  the option, the option is enabled.  This list is accessed by the Options card. */
  static java.util.List<String> tonics = new ArrayList<String>(Arrays.asList(tonicsArray));
  static java.util.List<Byte> instruments = new ArrayList<Byte>(Arrays.asList(instrumentsArray));

	static String selectedTonic; 
	static int selectedInstrument;
  
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
    WelcomeCard welcomeCard = new WelcomeCard();
    
    addCard(welcomeCard, "welcome");
    
    // menu card
    
    // options card
    OptionsCard optionsCard = new OptionsCard();
    addCard(optionsCard, "options");
    
    // assessment card
    AssessmentCard assessmentCard = new AssessmentCard(); //to do: add params: selectedTonic, selectedInstrument
		addCard(assessmentCard, "assessment");
    
    // activity cards
    
    // don't touch this stuff
    this.getContentPane().add(cardpanel);
    this.validate();
    switchToCard("welcome");
  }
}
