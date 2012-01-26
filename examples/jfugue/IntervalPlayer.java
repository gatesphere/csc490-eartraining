// Interval notation example -- useful for generating a major key...
// major keys all use the same intervals

import org.jfugue.*;

public class IntervalPlayer {
  public static void main(String[] args) {
    // major scales are 2-2-1-2-2-2-1
    // indexing at 1, that gives 1-3-5-6-8-10-12-13
    String intervals = "<1>q <3>q <5>q <6>q <8>q <10>q <12>q <13>q"; // play an entire scale, tonic to tonic, ascending
    IntervalNotation notation = new IntervalNotation(intervals);
    
    String[] tonics = {"C4","C#4", "D4", "D#4", "E4", "F4", "F#4", "G4", "G#4", "A4", "A#4", "B4"};
    
    Player player = new Player();
    for(int i = 0; i < 12; i++) {
      System.out.println("Playing " + tonics[i] + " major scale.");
      player.play(notation.getPatternForRootNote(tonics[i])); // play the 21 major scales
    }
    
    player.close();
    System.exit(0);
  }
}
