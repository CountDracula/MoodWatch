package controller;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import util.DBManager;
import util.LoginDAO;

/// TODO: rename UserClient to something better + rename connection to user or something
@ManagedBean (name ="server")
@ApplicationScoped
public class Server extends Thread {
    
	
	private ServerSocket serverSocket = null;
	private Socket clientSocket = null;
    private ExecutorService threadPool; 
    private boolean running = false;
    private ArrayList<ClientHandler> connections;
    private int portNumber;
    public static DBManager dbConn;

   private BufferedReader in = null;


   
   

    public Server(int portNumber) throws IOException {
    	
    this.portNumber = portNumber;
       this.serverSocket = new ServerSocket(this.portNumber);
       initDB();
       
        
    }

    public void initDB()
    {
    	// Init DB
    	DBManager.buildSessionFactory();
    	
    	///Create admin user in db for login validation page
    	LoginDAO logindao = new LoginDAO();
    	logindao.addUser("administrator", "admin");
    	
    }
    
  public void stopServer() throws IOException
  {
	  
	  /// Add method-call to save data before shutting down
	  serverSocket.close();
	  clientSocket.close();
	  running = false;
	  System.out.println("Server shutting down..");
  }

  @Override
    public void run() {
	 running = true;
	 

      // TODO: use non-fixed pool? && make acceptClients() method so this shit doesn't get unreadable..
	  // Can be moved to acceptClients()?
 	  threadPool = Executors.newFixedThreadPool(5);

 	  
 	 // Save data every X minutes  
 	//ScheduledExecutorService exec = Executors.newScheduledThreadPool(1);
 	//exec.scheduleAtFixedRate(periodicSaveData , 0, 15, TimeUnit.MINUTES);
 	
 	/// Method that listens to port 6066 and handles client connection(s)
   	try {
		acceptClients();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
   
  }
   	
 
   		
public void acceptClients() throws IOException
   	{
	connections = new ArrayList<ClientHandler>();
   		while (running)
   	   	{
   		   // wait for a client to connect  
   	       	System.out.println("Waiting for clients");
   	                  
   	   		try {
   	 			clientSocket = serverSocket.accept();
   	 		} catch (IOException e) {
   	 			// TODO Auto-generated catch block
   	 			e.printStackTrace();
   	 		}
   	   	
   	   		
   			ClientHandler connection = new ClientHandler(clientSocket);
   			connections.add(connection);
   			System.out.println("Accepted client");
   			
			threadPool.execute(connection);

			
   			
			//TODO threadpool shutdown? or clientSOcket.close

   	   
   	}
   		
   		
   	
  		
}
    	
Runnable periodicSaveData = new Runnable() {
   	    public void run() {
   	        /// Add method call (e.g. saveData() to save data to DB)
   	    }
   	};
  
  public void clientShit(ArrayList<UserClient> clients) throws IOException
  {
	  	
	  /// Site == main page, thread = subpage. You have to check the output from addSite to use it for addThread, i.e. it's not just "NU" but the whole shebang as pointed below.
 	
 		
 
 		System.out.println(clients.get(0).getFacade().addSite("www.dutchnews.nl"));
 		System.out.println(clients.get(0).getFacade().addThread("DutchNews.nl brings daily news from The Netherlands in English", "/features"));
 		
 		System.out.println(clients.get(0).getFacade().getAllSites().toString());
 		System.out.println(clients.get(0).getFacade().getAllPages().toString());
 		
 		
	
  }
  
  class ClientHandler implements Runnable 
  {
      private Socket connection;
       
      ClientHandler(Socket connection) 
      {
          this.connection = connection;
      }
      public void run() 
      {
          String line;
           
          try
          {
              //Reader & writer
              BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
              PrintStream out = new PrintStream(connection.getOutputStream());
   
              //Send welcome message to client (test only)
              out.println("Welcome to the Server");
              System.out.println("Welcome ");
   
              //Read client's input
              while((line = br.readLine()) != null) 
            {
                  System.out.println("Server received: " + line + " from client");
              }
               
              //Client disconnects,close socket
              out.flush();
              out.close();
              br.close();
              connection.close();
          } 
         
          catch (IOException e) 
          {
              System.out.println("IOException on socket : " + e);
              e.printStackTrace();
          }
      }
  }
  
}
 