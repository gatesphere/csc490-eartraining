// Ear training Applet Framework -- Activity Cards
// Jacob Peck/Jason Shaffner

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;

public class WelcomeCard extends ActivityCard implements ActionListener, FocusListener {

	JLabel header = new JLabel("<html><center><h1>Welcome to the Ear Training Suite<br></h1><h3>Sign in to get started<br></h3></center></html>");
	JTextField userField = new JTextField("username",15);
	JPasswordField passwordField = new JPasswordField("password",15);
	JButton enterButton = new JButton("Enter");
	JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
	JPanel centerPanel = new JPanel(new BorderLayout());
	JPanel fieldPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
	JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
	char[] password;
	boolean userPrev;
	boolean passwordPrev;

	//layout not set in stone... i'll play with it if i have time
  public void initializeThisCard() {
    System.out.println("Initializing pane...");
		topPanel.add(header);
		add(topPanel,BorderLayout.NORTH);
		fieldPanel.add(userField);
		fieldPanel.add(passwordField);
		centerPanel.add(fieldPanel,BorderLayout.CENTER);
		add(centerPanel,BorderLayout.CENTER);
		buttonPanel.add(enterButton);
		add(buttonPanel,BorderLayout.SOUTH);
		enterButton.setEnabled(false);
		addActionListeners();
  }
  
  public WelcomeCard() {
    super();
		setLayout(new BorderLayout());
  }
  
	public void addActionListeners() {
		enterButton.addActionListener(this);
		enterButton.addFocusListener(this);
		enterButton.requestFocus();
		userField.addFocusListener(this);
		passwordField.addFocusListener(this);
		JButtonStateController enterButtonStateController = new JButtonStateController(userField.getDocument(),passwordField.getDocument());
		userField.getDocument().addDocumentListener(enterButtonStateController);
		passwordField.getDocument().addDocumentListener(enterButtonStateController);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == enterButton) {
				ETApplet.user = userField.getText();
				password = passwordField.getPassword();
				ETApplet.switchToCard("options");
		}
  }

	//clears preset text from username and password fields when cursor enters said field
	public void focusGained(FocusEvent e) {
		if (userField.isFocusOwner() && !userPrev) {
			userField.setText("");
			userPrev = true;
		} else if (passwordField.isFocusOwner() && !passwordPrev) {
			passwordField.setText("");
			passwordPrev = true;
		}
	}
	//not used
	public void focusLost(FocusEvent e) {}

	//controls state of enter button, enabling only when both username and password are filled out
	class JButtonStateController implements DocumentListener {
		boolean userFilled;
		boolean passwordFilled;
		Document userDoc;
		Document passwordDoc;
					   
		JButtonStateController(Document userDoc, Document passwordDoc) {
			this.userDoc = userDoc;
			this.passwordDoc = passwordDoc;
		}
	
		public void disableIfEmpty(DocumentEvent e) {
			if (e.getDocument() == userDoc) userFilled = e.getDocument().getLength() > 0;
			if (e.getDocument() == passwordDoc) passwordFilled = e.getDocument().getLength() > 0;
			enterButton.setEnabled(userFilled && passwordFilled);
		}

		public void changedUpdate(DocumentEvent e) { disableIfEmpty(e); }
		public void insertUpdate(DocumentEvent e) { disableIfEmpty(e); }
		public void removeUpdate(DocumentEvent e) { disableIfEmpty(e); }

	}
}
