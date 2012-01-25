import org.jfugue.*;
import java.io.*;
import java.util.*;

public class Wordsongs {
  public static void main(String[] argv) throws Exception {
    Player player = new Player();
    String book = "T[220] I[STRING_ENSEMBLE_2] ";
    Scanner sc = new Scanner(new File(argv[0]));
    while (sc.hasNext()) {
      String word = sc.next();
      int note = Math.abs(word.hashCode()%40+30);
      book = book + "[" + note + "]/";
      float duration = ((float)word.length()%32+1)/32;
      book = book + duration + " ";
      System.out.println(word);
    }
    System.out.println(book);
    Pattern pattern = new Pattern(book);
    player.play(pattern);
    player.saveMidi(pattern, new File(argv[0] + ".midi"));
    System.exit(0);
  }
}
