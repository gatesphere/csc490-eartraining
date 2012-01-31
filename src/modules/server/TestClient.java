// Test client

import java.net.*;
import java.util.*;
import java.io.*;

public class TestClient {  
  public static void main(String[] args) {
    if(args.length != 2) {
      System.out.println("usage: java TestClient <port_number> <test_num>");
      System.exit(-1);
    }
    
    int portnum = Integer.parseInt(args[0]);
    int num_tests = Integer.parseInt(args[1]);
    
    testAuth(portnum, num_tests);
    testXMLDump(portnum, num_tests);
    testIncorrectFormat(portnum, num_tests);

    System.out.println("Tests complete.");
  }
  
  public static void testAuth(int portnum, int num_tests) {
    System.out.println("Testing authentication.");
    Socket socket;
    BufferedReader in;
    PrintStream out;
    String user, pass, output, ack;
    for (int i = 0; i < num_tests; i++) {
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
        ack = in.readLine();
        socket.close();
        if(!ack.equals("SUCCESS")) {
          System.out.println("FAILURE ON AUTH!");
          break;
        }
        // connect
        socket = new Socket("localhost",portnum);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintStream(socket.getOutputStream());
        // auth
        output = "USERAUTH\n" + user + "\n" + pass + "\n" + "\n";
        out.print(output);
        ack = in.readLine();
        socket.close();
        if(!ack.equals("SUCCESS")) {
          System.out.println("FAILURE ON AUTH!");
          break;
        }
        // connect
        socket = new Socket("localhost",portnum);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintStream(socket.getOutputStream());
        // auth with incorrect password
        pass = generateString(20);
        output = "USERAUTH\n" + user + "\n" + pass + "\n" + "\n";
        out.print(output);
        ack = in.readLine();
        socket.close();
        if(!ack.equals("FAILURE")) {
          System.out.println("FAILURE ON AUTH!");
          break;
        }
      } catch (Exception ex) {ex.printStackTrace();}
    }
  }
  
  public static void testXMLDump(int portnum, int num_tests) {
    System.out.println("Testing XML Dump.");
    for (int i = 0; i < num_tests; i++) {
      try {
      String text;
      Socket socket;
      PrintStream out;
        text = "XMLDUMP\n<xml>" + generateString(15) + "</xml>\n\n";
        // connect
        socket = new Socket("localhost",portnum);
        out = new PrintStream(socket.getOutputStream());
        // send text block
        out.println(text);
        socket.close();
      } catch (Exception ex) {ex.printStackTrace();}
    }
  }
  
  public static void testIncorrectFormat(int portnum, int num_tests) {
    System.out.println("Testing incorrect format.");
    String gibberish;
    Socket socket;
    PrintStream out;
    for (int i = 0; i < num_tests; i++) {
      try {
        gibberish = generateString(50);
        // connect
        socket = new Socket("localhost",portnum);
        out = new PrintStream(socket.getOutputStream());
        // send gibberish
        out.println(gibberish);
        socket.close();
      } catch (Exception ex) {ex.printStackTrace();}
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
