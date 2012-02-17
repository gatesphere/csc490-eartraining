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

public class ETApplet extends JApplet {
  private static JPanel cardpanel = new JPanel(new CardLayout());
  private static Map<String, ActivityCard> cards = new HashMap<String, ActivityCard>();
  
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
    
    // assessment card
    ActivityCard assessmentCard = new ActivityCard(new FlowLayout(FlowLayout.CENTER));
    assessmentCard.add(new JLabel("<html><h1>Assessment</h1></html>"));
    
    // activity cards
    
    // don't touch this stuff
    this.getContentPane().add(cardpanel);
    this.validate();
    switchToCard("welcome");
    
  }
}