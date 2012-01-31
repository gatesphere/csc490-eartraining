// Ear Training Server
// Jacob Peck
// CSC 490 - Graci
// 20120130

// provides two functions:
//   * user/password authentication
//     -> if the username doesn't exist, user is created automatically
//   * data reporting
//     -> dump info to an XML file <- to do

import java.util.*;
import java.net.*;
import java.io.*;
import java.util.concurrent.*;

public class ETServer {
  public static void main(String[] args) {
    if(args.length < 2) {
      System.out.println("usage: java ETServer <port_number> <maximum_connections>");
      System.out.println("where <port_number> is a port number to run on, and");
      System.out.println("<maximum_connections> is the maximum amount of concurrent");
      System.out.println("connections.  Set this to 0 for infinite (not recommended)");
      System.exit(-1);
    }
    
    
    int portnum = Integer.parseInt(args[0]);
    int max_connections = Integer.parseInt(args[1]);
    int active_connections = 0;
    
    UserTable usertable;
    
    try{
      System.out.println("Initializing server...");
      ServerSocket ss = new ServerSocket(portnum);
      Socket sock;
      usertable = UserTable.getInstance();
      System.out.println("Server initialized on port " + portnum + " with " + max_connections + " maximum connections.");
      
      Runtime.getRuntime().addShutdownHook(
        new Thread(new Runnable() {
          public void run() {
            System.out.println("Server going down, hold on!");
            UserTable.writeFileNow();
          }
        })
      );
      
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




class UserTable { // singleton
  private static UserTable instance = null;
  private static File file = new File("users.dat");
  private static FileWriter fwrite;
  
  private UserTable() {
    System.out.println("Initializing User Table.");
    if(file.exists()) {
      System.out.println("User database already exists... populating.");
      Scanner fread = null;
      try {
        fread = new Scanner(file);
      } catch (Exception ex) {ex.printStackTrace();}
      String[] line;
      while(fread.hasNextLine()) {
        line = fread.nextLine().split(":");
        users.put(line[0], line[1]);
      }
    } else {
      System.out.println("No user database found.");
    }
    
    System.out.println("User Table initialized.");
    
    UserTable.startFileWriter(5); // update file every 5 minutes
  }
  
  public static UserTable getInstance() {
    if (instance == null)
      instance = new UserTable();
    return instance;
  }
  
  private static Map<String, String> users = new ConcurrentHashMap<String, String>();
  
  public boolean auth(String username, String pass) {
    if(!users.containsKey(username)) {
      users.put(username, pass);
      return true;
    } else {
      return users.get(username).equals(pass);
    }
  }
  
  private static void startFileWriter(final int minutes) {
    System.out.println("Writing user database every " + minutes + " minutes.");
    new Thread(new Runnable() {
      public void run() {
        for(;;) {
          try {
            Thread.sleep(minutes * 60 * 1000); // sleep for <minutes> minutes
            writeFileNow();
          } catch (Exception ex) {ex.printStackTrace();}
        }
      }
    }).start();
  }
  
  public static void writeFileNow() {
    try {  
      System.out.println("Writing user database...");
      fwrite = new FileWriter(file, false);
      for(String s : users.keySet()) {
        String line = s + ":" + users.get(s);
        fwrite.write(line + "\n");
      }
      fwrite.flush();
      fwrite.close();
    } catch (Exception ex) {ex.printStackTrace();}
  }
}




class CommAgent implements Runnable {
  private Socket server;
  private String line, input;

  public CommAgent(Socket server) {
    this.server=server;
  }

  public void run() {
    input = "";

    try {
      // create i/o streams
      BufferedReader in = new BufferedReader(new InputStreamReader(server.getInputStream()));
      PrintStream out = new PrintStream(server.getOutputStream());

      // read stream
      line = in.readLine();
      if(line == null) {server.close(); return;}; // keep-alive connection.  No point.
      if(line.equals("USERAUTH")) {
        // auth stuff
        System.out.println("User Auth.");
        while((line = in.readLine()) != null && !line.isEmpty()) {
          input = input + line;
          System.out.println("I got:" + line); // server-side confirmation, for testing
        }
      } else if(line.equals("XMLDUMP")) {
        // XML dump
        System.out.println("XML Dump.");
        while((line = in.readLine()) != null && !line.isEmpty()) {
          input = input + line;
          System.out.println("I got:" + line); // server-side confirmation, for testing
        }
      } else {
        // unknown
        System.out.println("Not sure what you're trying to do here...");
        System.out.println("I got:" + line);
        while((line = in.readLine()) != null && !line.isEmpty()) {
          input = input + line;
          System.out.println("I got:" + line); // server-side confirmation, for testing
        }
      }

      System.out.println("Overall message is:" + input);
      
      
      //out.println("Overall message is:" + input); // send ACK?

      server.close();
    }
    catch (Exception ex) {ex.printStackTrace();}
  }
}