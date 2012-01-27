// Barebones server implementation: receives strings and writes to file

import java.util.*;
import java.net.*;
import java.io.*;
import java.util.concurrent.*;

public class DataStorageServer {
  public static void main(String[] args) {
    if(args.length < 2) {
      System.out.println("usage: java DataStorageServer <port_number> <maximum_connections>");
      System.exit(-1);
    }
    
    int portnum = Integer.parseInt(args[0]);
    int max_connections = Integer.parseInt(args[1]);
    int active_connections = 0;
    
    try{
      ServerSocket ss = new ServerSocket(portnum);
      Socket sock;
      
      while((active_connections++ < max_connections) 
             || (max_connections == 0)) {
        
        sock = ss.accept();
        CommAgent connection = new CommAgent(sock);
        new Thread(connection).start();
      }
    }
    catch (Exception ex) {ex.printStackTrace();}
    
  }

}

class CommAgent implements Runnable {
  private Socket server;
  private String line,input;

  public CommAgent(Socket server) {
    this.server=server;
  }

  public void run () {
    input = "";

    try {
      // create i/o streams
      BufferedReader in = new BufferedReader(new InputStreamReader(server.getInputStream()));
      PrintStream out = new PrintStream(server.getOutputStream());

      // read stream
      while((line = in.readLine()) != null && !line.equals(".")) {
        input = input + line;
        out.println("I got:" + line); // server-side confirmation, for testing
      }

      System.out.println("Overall message is:" + input);
      // write message to file
      // ...
      
      
      //out.println("Overall message is:" + input); // send ACK?

      server.close();
    }
    catch (Exception ex) {ex.printStackTrace();}
  }
}
