// Ear training Applet Framework -- Activity Cards
// Jacob Peck

import java.awt.*;
import javax.swing.*;

public class ActivityCard extends JPanel {
  public void initializeThisCard() {
    // card initialization code here
    System.out.println("Initializing pane...");
  }
  
  public ActivityCard() {
    super();
  }
  
  public ActivityCard(boolean isDoubleBuffered) {
    super(isDoubleBuffered);
  }
  
  public ActivityCard(LayoutManager layout) {
    super(layout);
  }
  
  public ActivityCard(LayoutManager layout, boolean isDoubleBuffered) {
    super(layout, isDoubleBuffered);
  }
}