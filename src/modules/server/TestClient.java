// Test client

import java.net.*;
import java.util.*;
import java.io.*;

public class TestClient {
  public static void main(String[] args) {
    if(args.length != 1) {
      System.out.println("usage: java TestClient <port_number>");
      System.exit(-1);
    }
    
    int portnum = Integer.parseInt(args[0]);
    
    testAuth(portnum);
    testXMLDump(portnum);
    testIncorrectFormat(portnum);

    System.out.println("Tests complete.");
  }
  
  public static void testAuth(int portnum) {
    System.out.println("Testing authentication.");
    Socket socket;
    BufferedReader in;
    PrintStream out;
    String user, pass, output;
    for (int i = 0; i < 100; i++) {
      try {
        user = generateString(20);
        pass = generateString(20);
        // connect
        socket = new Socket("localhost",portnum);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintStream(socket.getOutputStream());
        // register
        output = "USERAUTH\n" + user + "\n" + pass + "\n" + "\n";
        out.print(output);
        // connect
        // auth
        // connect
        // auth with incorrect password
      } catch (Exception ex) {ex.printStackTrace();}
    }
  }
  
  public static void testXMLDump(int portnum) {
    System.out.println("Testing XML Dump.");
    for (int i = 0; i < 100; i++) {
      // connect
      // send textblock
    }
  }
  
  public static void testIncorrectFormat(int portnum) {
    System.out.println("Testing incorrect format.");
    for (int i = 0; i < 100; i++) {
      //.connect
      // send gibberish
    }
  }
  
  private static final char[] symbols = new char[36];
  static {
    for (int idx = 0; idx < 10; ++idx)
      symbols[idx] = (char) ('0' + idx);
    for (int idx = 10; idx < 36; ++idx)
      symbols[idx] = (char) ('a' + idx - 10);
  }

  private static char[] buf;

  public static String generateString(int length) {
    buf = new char[length];
    for (int idx = 0; idx < buf.length; ++idx) 
      buf[idx] = symbols[rng.nextInt(symbols.length)];
    return new String(buf);
  }
  
  private static final Random rng = new Random();
}
