import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.Math;
import java.util.HashMap;
import java.io.IOException;

class EsacTranslator {
	
	HashMap<Integer, Integer> durationMap = new HashMap<Integer, Integer>();
	int duration;
	int degree;
	int baseDuration;
	
	static String melody;
	static int limit;
	static int length; //number of notes in the melody
	
	EsacTranslator() {
		length = 0;
		durationMap.put(128, 0);
		durationMap.put(64, 1);
		durationMap.put(32, 2);
		durationMap.put(16, 3);
		durationMap.put(8, 4);
		durationMap.put(4, 5);
		durationMap.put(2, 6);
		durationMap.put(1, 7);
	}
	
	String getMelody() throws IOException {
		limit = (int)(3*Math.random()+4); //random length from 4 to 7
		System.out.println("Melody length: " + limit);
		int base; //smallest rhythmic unit
		char input;
		BufferedReader reader = new BufferedReader(new FileReader("esac.txt"));
		base = getBase(reader);
		baseDuration = durationMap.get(base);
		input = (char)reader.read();
		while ( input != '[' ) input = (char)reader.read();		//read until next line
		//TranslatorNote note; pretty sure this is unnecessary
		input = (char) reader.read();
		do input = (char) reader.read(); while ( !isDigit(input) );
		startNote(reader, input, baseDuration);
		return melody;
	}
	
	int getBase(BufferedReader reader) throws IOException {
		char input = (char) reader.read();
		String baseString;
		for ( ;; ) {
			if ( input == 'K' 
				&& (char)reader.read() == 'E' 
				&& (char)reader.read() == 'Y' 
				&& (char)reader.read() == '[' ) {
					break;
			}
			input = (char) reader.read();
		}
		reader.skip(7);
		char[] temp = new char[2];
		reader.read(temp, 0, 2);
		baseString =  new String(temp);
		System.out.println("Base: " + baseString);
		return Integer.parseInt(baseString);
	}
	
	void startNote(BufferedReader reader, char input, int baseDuration) throws IOException {
		TranslatorNote note;
		if ( input == '+' ) { //if it starts with a + modifier
			note = new TranslatorNote(true, baseDuration);
			input = (char)reader.read();
			while ( !isDigit(input) ) { //until it hits a digit
				if ( input == '+' ) note.octaveUp(); //increase the octave for each +
			} //when it does hit a digit
			note.setDegree(((int)input)-48); //set the note's degree
			while ( !isDigit(input) ) { //start parsing modifiers
				if ( input == '#' ) note.sharp();
				else if ( input == 'b' ) note.flat();
				else if ( input == '_' ) note.lengthen();
				else if ( input == '.' ) note.dot();
				input = (char)reader.read();
				if ( melody == null ) { //add the note to the melody
					System.out.println("Starting melody with " + note.toString());
					melody = note.toString();
				} else {
					melody += note.toString();
					System.out.println("Adding " + note.toString());
				}
				++length; //increment the length of the melody
				if ( length < limit ) startNote(reader, input, baseDuration); //start a new note if there's room
				return;
			}
		}
		else if ( input == '-' ) {
			note = new TranslatorNote(false, baseDuration);
			input = (char)reader.read();
			while ( !isDigit(input) ) {
				if ( input == '-' ) note.octaveDown();
			}
			note.setDegree(((int)input)-48);
			while ( !isDigit(input) ) {
				if ( input == '#' ) note.sharp();
				else if ( input == 'b' ) note.flat();
				else if ( input == '_' ) note.lengthen();
				else if ( input == '.' ) note.dot();
				input = (char)reader.read();
				if ( melody == null ) {
					System.out.println("Starting melody with " + note.toString());
					melody = note.toString();
				} else {
					melody += note.toString();
					System.out.println("Adding " + note.toString());
				}
				++length;
				if ( length < limit ) startNote(reader, input, baseDuration);
				return;
			}
		}
		else {
			System.out.println("This note has degree " + input + " and duration " + baseDuration);
			note = new TranslatorNote(((int)input)-48, baseDuration);
			input = (char)reader.read();
			while ( !isDigit(input) ) { //start parsing modifiers
				if ( input == '#' ) note.sharp();
				else if ( input == 'b' ) note.flat();
				else if ( input == '_' ) note.lengthen();
				else if ( input == '.' ) note.dot();
				input = (char)reader.read();
			}
			if ( melody == null ) {
				System.out.println("Starting melody with " + note.toString());
				melody = note.toString();
			} else {
				melody += note.toString();
				System.out.println("Adding " + note.toString());
			}
			++length;
			if ( length < limit ) startNote(reader, input, baseDuration); //start a new note
			return;
		}
	}
	
	boolean isDigit(char input) {
		return (input == '0' ||
				input == '1' ||
				input == '2' ||
				input == '3' ||
				input == '4' ||
				input == '5' ||
				input == '6' ||
				input == '7' ||
				input == '8' ||
				input == '9');
	}
	
	void addNote(String melody, TranslatorNote note) {
		if ( melody == null ) {
			System.out.println("Starting melody with " + note.toString());
			melody = note.toString();
		} else {
			melody += note.toString();
			System.out.println("Adding " + note.toString());
		}
		return;
	}
	
	//
}