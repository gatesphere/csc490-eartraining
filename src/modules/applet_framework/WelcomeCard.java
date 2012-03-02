// Ear training Applet Framework -- Activity Cards
// Jacob Peck

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class WelcomeCard extends ActivityCard implements ActionListener {

	JLabel header = new JLabel("<html><center><h1>Welcome to the Ear Training Suite</h1></center></html>");
	JLabel prompter = new JLabel("<html><center><h3>Sign in to get started</h3></center></html>");
	JTextField userField = new JTextField("Enter your username",15);
	JPasswordField passwordField = new JPasswordField("Enter your password",15);
	JButton enterButton = new JButton("Enter");
	JPanel centerPanel = new JPanel(new BorderLayout());
	JPanel fieldPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
	JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
	String user;
	char[] password;

	//layout not set in stone... i'll play with it if i have time
  public void initializeThisCard() {
    System.out.println("Initializing pane...");
		add(header,BorderLayout.NORTH);
		fieldPanel.add(prompter);
		fieldPanel.add(userField);
		fieldPanel.add(passwordField);
		centerPanel.add(fieldPanel,BorderLayout.CENTER);
		add(centerPanel,BorderLayout.CENTER);
		buttonPanel.add(enterButton);
		add(buttonPanel,BorderLayout.SOUTH);
	//	enterButton.setEnabled(false);
		addActionListeners();
  }
  
  public WelcomeCard() {
    super();
		setLayout(new BorderLayout());
  }
  
	public void addActionListeners() {
	//	userField.addActionListener(this);
	//	passwordField.addActionListener(this);
		enterButton.addActionListener(this);
	}

	//will need to implement this guard correctly at some point...
	//right now only works if you press enter while in each field
	//so i just commented it out until i figure it out
	public void actionPerformed(ActionEvent e) {
	/*	if (e.getSource() == userField) {
			user = userField.getText();
			if (user != null && password != null) enterButton.setEnabled(true);
		} else if (e.getSource() == passwordField) {
			password = passwordField.getPassword();
			if (user != null && password != null) enterButton.setEnabled(true);
		} else if (e.getSource() == enterButton) {
			if (user == null || password == null) prompter.setText("<html><h3><center>You must enter a username and password</center></h3></html>");			
			else {
		*/		user = userField.getText();
				password = passwordField.getPassword();
				ETApplet.switchToCard("options");
	//		}
	//	}
  }
}
