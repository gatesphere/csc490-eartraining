// LCompose!
// The user inputs a system of rules and an initial seed, and a piece is
// generated for however many generations the user sees fit.  After each
// generation, the current value is played, and the user is given the option
// to continue generating or save the file and quit.
// Jacob Peck
// 20110402

// N.B.: No error checking is done on any input. This program will break easily
// if you don't ensure the input is correct. 

import java.io.*;
import java.util.*;
import org.jfugue.*;

public class LCompose {
  public static void main(String[] argv) throws Exception{
    int generation = 0;
    boolean debug = false;
    String temp = ""; // to take in input
    Scanner sc = new Scanner(System.in); // to tokenize input
    Scanner tempTokenizer = null;
    HashMap<String, String> rules = new HashMap<String, String>(); // dictionary of rules
    String current = "";
    Player player = new Player();
    
    // get scale
    System.out.println("Welcome to LCompose!  Please enter the alphabet as a space" +
                       " separated string of JFugue MusicString tokens.");
    System.out.print("> ");                   
    temp = sc.nextLine();
    tempTokenizer = new Scanner(temp);
    while (tempTokenizer.hasNext()) {
      rules.put(tempTokenizer.next().toUpperCase(), ""); // initialize the dictionary
    }
    
    if (debug == true) {
      System.out.print("  --scale: ");
      for (String s : rules.keySet()) 
        System.out.print(s + " ");
      System.out.println();
    }
    
    // get rules
    System.out.println("Please enter the rules in the following format: ");
    System.out.println("  <token to rewrite> --> <string to rewrite it to>");
    System.out.println("ex. A:B A B, with each newline denoting a new rule.");
    System.out.println("To indicate that you are finished, enter the word \"quit\".");
    System.out.println("> ");
    for (String s : rules.keySet()) {
      System.out.print(s + " --> ");
      temp = sc.nextLine().toUpperCase();
      rules.put(s, temp.trim());
    }
    
    if (debug == true) {
      System.out.println(" --rules: ");
      for (String s : rules.keySet()) 
        System.out.println(" ----" + s + ":" + rules.get(s));
    }
    
    // get seed
    System.out.println("Please enter seed:");
    System.out.print("> ");
    current = sc.next().toUpperCase(); // only the next token, not a whole line.
    
    if (debug == true) {
      System.out.println(" --seed: " + current);
    }
    
    temp = sc.nextLine(); // clear leftover newlines
    while (true) {
      // print current iteration
      if (generation == 0) 
        System.out.println ("Seed: " + current);
      else
        System.out.println ("Generation " + generation + ": " + current);
      
      // play it
      player.play(new Pattern(current));
      
      // decision point
      System.out.println("Press enter to iterate, or type \"quit\" to stop iterating.");
      System.out.print("> ");
      
      // break if quit
      temp = sc.nextLine();
      if (temp.equalsIgnoreCase("quit")) break;
      
      // increment generation
      generation++;
      
      // iterate
      tempTokenizer = new Scanner(current);
      String temp3 = "";
      while (tempTokenizer.hasNext()) {
        temp = tempTokenizer.next();
        temp3 = temp3 + " " + rules.get(temp);
        if (debug == true) {
          System.out.println(" --temp = " + temp);
          System.out.println(" --temp3 = " + temp3);
        }
      }
      current = temp3.trim();
    }
    
    System.out.println("If you would like to save the file, please type a filename below.  Otherwise, type \"quit\".");
    System.out.print("> ");
    temp = sc.next();
    if (!temp.equalsIgnoreCase("quit")) {
      player.saveMidi(new Pattern(current), new File(temp));
      System.out.println("File saved as " + temp);
    }
    
    System.out.println("Bye!");
    System.exit(0);
  }
}