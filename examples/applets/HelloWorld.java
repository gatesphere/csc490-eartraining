// Hello world java applet

import java.applet.*;
import java.awt.*;

public class HelloWorld extends Applet {
  public void init() {
    // does nothing, called when the applet starts
  }
  
  public void stop() {
    // does nothing, called when the applet exits
  }
  
  public void paint(Graphics g) {
    // paint to the screen
    g.drawString("Hello world!", 20, 20);
  }
}

