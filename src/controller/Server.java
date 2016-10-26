package controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server extends Thread {
    
	
	private ServerSocket serverSocket = null;
	private Socket clientSocket = null;
    private ExecutorService threadPool; 
    private boolean running = false;
    int portNumber;

    

    public Server(int portNumber) throws IOException {
    	
        serverSocket = new ServerSocket(portNumber);
      
        
    }

  public void stopServer() throws IOException
  {
	  serverSocket.close();
	  clientSocket.close();
	  running = false;
	  System.out.println("Server shutting down.");
  }

  @Override
    public void run() {
       int counter = 0;


 	  threadPool = Executors.newFixedThreadPool(5);
   	running = true;
   	while (running)
   	{
       	System.out.println("Waiting for clients");
           // wait for a client to connect            
   		try {
 			clientSocket = serverSocket.accept();
 		} catch (IOException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
   		System.out.println("Accepted client");
   		UserClient connection = null;
		try {
			counter++;
			connection = new UserClient(clientSocket);
			connection.setId(counter);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   		threadPool.execute(connection);
   

  		
}
    	
}
  
}
 