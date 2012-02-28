import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class OptionsCard extends ActivityCard implements ItemListener, ActionListener {
	java.util.List<JCheckBox> tonicBoxes = new ArrayList<JCheckBox>(12);
	java.util.List<JCheckBox> instrumentBoxes = new ArrayList<JCheckBox>(6);

	boolean[] tonicOptions = new boolean[12];
	boolean[] instrumentOptions = new boolean[6];

	public OptionsCard() {
		/* making and positioning the label */
		JLabel title = new JLabel("<html><h1>Options</h1></html>");
		title.setHorizontalTextPosition(JLabel.CENTER);
				
		JButton okay = new JButton("Okay");
		JButton cancel = new JButton("Cancel");
		
		/* panel of tonic checkboxes */
		JPanel tonicsPanel = new JPanel();
		tonicsPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Tonics"));
		tonicBoxes.add(new JCheckBox("C"));
		tonicBoxes.add(new JCheckBox("C#/Db"));
		tonicBoxes.add(new JCheckBox("D"));
		tonicBoxes.add(new JCheckBox("D#/Eb"));
		tonicBoxes.add(new JCheckBox("E"));
		tonicBoxes.add(new JCheckBox("F"));
		tonicBoxes.add(new JCheckBox("F#/Gb"));
		tonicBoxes.add(new JCheckBox("G"));
		tonicBoxes.add(new JCheckBox("G#/Ab"));
		tonicBoxes.add(new JCheckBox("A"));
		tonicBoxes.add(new JCheckBox("A#/Bb"));
		tonicBoxes.add(new JCheckBox("B"));
		
		Iterator it = tonicBoxes.iterator();
		JCheckBox cb;
		while (it.hasNext()) {
			cb = (JCheckBox)it.next();
			tonicsPanel.add(cb);
			cb.addItemListener(this);
		}
		
		/* panel of instrument checkboxes */
		JPanel instrumentsPanel = new JPanel();
		instrumentsPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Instruments"));
		instrumentBoxes.add(new JCheckBox("Piano"));
		instrumentBoxes.add(new JCheckBox("Guitar"));
		instrumentBoxes.add(new JCheckBox("Violin"));
		instrumentBoxes.add(new JCheckBox("Voice"));
		instrumentBoxes.add(new JCheckBox("Flute"));
		instrumentBoxes.add(new JCheckBox("Square wave"));
		
		it = instrumentBoxes.iterator();
		while (it.hasNext()) {
			cb = (JCheckBox)it.next();
			instrumentsPanel.add(cb);
			cb.addItemListener(this);
		}
		
		okay.addActionListener(this);
		okay.setActionCommand("okay");
		cancel.addActionListener(this);
		
		add(title);
		add(instrumentsPanel);
		add(tonicsPanel);
		add(okay);
		add(cancel);
		
		
	}
	
	public void initializeThisCard() {
		System.out.println("Initializing options card...");
		for ( int i = 0; i < 12; ++i ) {
			tonicBoxes.get(i).setSelected(ETApplet.tonics.contains(ETApplet.tonicsArray[i]));
		}
		for ( int i = 0; i < 6; ++i ) {
			instrumentBoxes.get(i).setSelected(ETApplet.instruments.contains(ETApplet.instrumentsArray[i]));
		}
	}
	
	/* checking and unchecking boxes alters the state of options in this card only,
		options are only finalized upon pressing "Okay" */
	public void itemStateChanged(ItemEvent e) {
		Object source = (Object)e.getItemSelectable();
		if ( e.getStateChange() == ItemEvent.DESELECTED ) {
			/* tonic checkboxes... */
			if ( source == tonicBoxes.get(0) )
				tonicOptions[0] = false;
			else if ( source == tonicBoxes.get(1) )
				tonicOptions[1] = false;
			else if ( source == tonicBoxes.get(2) )
				tonicOptions[2] = false;
			else if ( source == tonicBoxes.get(3) )
				tonicOptions[3] = false;
			else if ( source == tonicBoxes.get(4) )
				tonicOptions[4] = false;
			else if ( source == tonicBoxes.get(5) )
				tonicOptions[5] = false;
			else if ( source == tonicBoxes.get(6) )
				tonicOptions[6] = false;
			else if ( source == tonicBoxes.get(7) )
				tonicOptions[7] = false;
			else if ( source == tonicBoxes.get(8) )
				tonicOptions[8] = false;
			else if ( source == tonicBoxes.get(9) )
				tonicOptions[9] = false;
			else if ( source == tonicBoxes.get(10) )
				tonicOptions[10] = false;
			else if ( source == tonicBoxes.get(11) )
				tonicOptions[11] = false;
			/* instrument checkboxes... */
			else if ( source == instrumentBoxes.get(0) )
				instrumentOptions[0] = false;
			else if ( source == instrumentBoxes.get(1) )
				instrumentOptions[1] = false;
			else if ( source == instrumentBoxes.get(2) )
				instrumentOptions[2] = false;
			else if ( source == instrumentBoxes.get(3) )
				instrumentOptions[3] = false;
			else if ( source == instrumentBoxes.get(4) )
				instrumentOptions[4] = false;
			else if ( source == instrumentBoxes.get(5) )
				instrumentOptions[5] = false;
		/* tonic checkboxes... */
		} else if ( source == tonicBoxes.get(0) )
			tonicOptions[0] = true;
		else if ( source == tonicBoxes.get(1) )
			tonicOptions[1] = true;
		else if ( source == tonicBoxes.get(2) )
			tonicOptions[2] = true;
		else if ( source == tonicBoxes.get(3) )
			tonicOptions[3] = true;
		else if ( source == tonicBoxes.get(4) )
			tonicOptions[4] = true;
		else if ( source == tonicBoxes.get(5) )
			tonicOptions[5] = true;
		else if ( source == tonicBoxes.get(6) )
			tonicOptions[6] = true;
		else if ( source == tonicBoxes.get(7) )
			tonicOptions[7] = true;
		else if ( source == tonicBoxes.get(8) )
			tonicOptions[8] = true;
		else if ( source == tonicBoxes.get(9) )
			tonicOptions[9] = true;
		else if ( source == tonicBoxes.get(10) )
			tonicOptions[10] = true;
		else if ( source == tonicBoxes.get(11) )
			tonicOptions[11] = true;
		/* instrument checkboxes... */
		else if ( source == instrumentBoxes.get(0) )
			instrumentOptions[0] = true;
		else if ( source == instrumentBoxes.get(1) )
			instrumentOptions[1] = true;
		else if ( source == instrumentBoxes.get(2) )
			instrumentOptions[2] = true;
		else if ( source == instrumentBoxes.get(3) )
			instrumentOptions[3] = true;
		else if ( source == instrumentBoxes.get(4) )
			instrumentOptions[4] = true;
		else if ( source == instrumentBoxes.get(5) )
			instrumentOptions[5] = true;
	}
	
	/* pressing "Okay" commits options to ETApplet and switches back to the menu card,
		pressing "Cancel" switches to the menu card and discards options */
	public void actionPerformed(ActionEvent e) {
		Object source = (Object)e.getActionCommand();
		if ( source.equals("okay") ) {
			/* these for-loops check if the options in ETApplet are inconsistent with the options in OptionsCard, and if so, changes them */
			for ( int i = 0; i < 12; ++i ) {
				if ( tonicOptions[i] && !ETApplet.tonics.contains(ETApplet.tonicsArray[i]) ) ETApplet.tonics.add(ETApplet.tonicsArray[i]);
			}
			for ( int i = 0; i < 6; ++i ) {
				if ( instrumentOptions[i] && !ETApplet.instruments.contains(ETApplet.instrumentsArray[i]) ) ETApplet.instruments.add(ETApplet.instrumentsArray[i]);
			}
			for ( int i = 0; i < 12; ++i ) {
				if ( !tonicOptions[i] && ETApplet.tonics.contains(ETApplet.tonicsArray[i]) ) ETApplet.tonics.remove(ETApplet.tonicsArray[i]);
			}
			for ( int i = 0; i < 6; ++i ) {
				if ( !instrumentOptions[i] && ETApplet.instruments.contains(ETApplet.instrumentsArray[i]) ) ETApplet.instruments.remove(ETApplet.instrumentsArray[i]);
			}
		}
		ETApplet.switchToCard("test");
	}
}