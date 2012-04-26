// Ear training Applet Framework -- Activity Cards
// Jacob Peck

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MenuCard extends ActivityCard implements ActionListener {

JPanel top = new JPanel(new FlowLayout(FlowLayout.CENTER));
JPanel center = new JPanel(new FlowLayout(FlowLayout.CENTER));
JPanel centerLeft = new JPanel(new FlowLayout(FlowLayout.LEFT));
JPanel centerRight = new JPanel(new FlowLayout(FlowLayout.CENTER));
JPanel bottom = new JPanel(new FlowLayout(FlowLayout.CENTER));
JLabel header = new JLabel("<html><h1><center>Please select an activity</center></h1></html>");
JLabel description = new JLabel("<html><center><h2>Description of selected activity will appear here</h3></center></html>");
JButton nextButton = new JButton("Next");
JButton optionsButton = new JButton("Options");
String selectedActivity;

//we can add more as we complete more...
JRadioButton assessmentButton = new JRadioButton("Assessment");
JRadioButton activityOneButton = new JRadioButton("Activity One");
//New Here
JRadioButton activityTwoButton = new JRadioButton("Activity Two");
JRadioButton activityThreeButton = new JRadioButton("Activity Three");

  public void initializeThisCard() {
nextButton.addActionListener(this);
optionsButton.addActionListener(this);
assessmentButton.addActionListener(this);
activityOneButton.addActionListener(this);
//new Here
activityTwoButton.addActionListener(this);
//new Here
activityThreeButton.addActionListener(this);
}
  
  public MenuCard() {
setLayout(new BorderLayout());
top.add(header);
add(top,BorderLayout.NORTH);
centerLeft.add(assessmentButton);
centerLeft.add(activityOneButton);
//new here
centerLeft.add(activityTwoButton);
//new Here
centerLeft.add(activityThreeButton);
center.add(centerLeft);
centerRight.add(description);
center.add(centerRight);
add(center,BorderLayout.CENTER);
bottom.add(nextButton);
bottom.add(optionsButton);
add(bottom,BorderLayout.SOUTH);
  }

public void actionPerformed(ActionEvent e) {
if (e.getSource() == optionsButton) ETApplet.switchToCard("options");
else if (e.getSource() == nextButton)
if (selectedActivity != null) ETApplet.switchToCard(selectedActivity);
else description.setText("<html><h2><center>You must select an activity before moving on</center></h2</html>");
else selectedActivity = e.getActionCommand();
}

  
}