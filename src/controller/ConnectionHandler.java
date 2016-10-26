package controller;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ConnectionHandler implements Runnable {
 
  private Socket socket;
 
  public ConnectionHandler(Socket socket) {
    this.socket = socket;
  }
 
  @Override
  public void run() {
    BufferedReader reader = null;
    PrintWriter writer = null;
    try {
      reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      writer = new PrintWriter(socket.getOutputStream(), true);
 
      // The read loop. Code only exits this loop if connection is lost / client disconnects
      while(true) {
        String line = reader.readLine();
        if(line == null) break;
        writer.println("Echo: " + line);
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    } finally {
      try {
        if(reader != null) reader.close();
        if(writer != null) writer.close();
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
  }
 
}