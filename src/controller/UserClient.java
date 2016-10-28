
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import net.IMoodWatch;
import net.MoodWatchFacade;

import util.Facade;

 // this class handles each client connection
    public class UserClient implements Runnable {
        private Socket clientSocket;
        private IMoodWatch facade;
        private volatile boolean running = false;
        private int id;
        private String host;
        private int portNumber;
     
      
        private PrintWriter out = null;
     
        public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}
		
        
        public UserClient() throws UnknownHostException, IOException {
            
        	
			this.facade = MoodWatchFacade.instance();
			this.clientSocket = new Socket();
			this.host = "localhost";
			this.portNumber = 6066;
		
        }
            
       
        public void run() {
        running = true;
        try {
			clientSocket = new Socket(host, portNumber);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    
		try {
 	      
        	 System.out.println("I am client # " + this.id + " and I connected to : " + clientSocket.getRemoteSocketAddress());
            
        	 /// Send message
              out = new PrintWriter(clientSocket.getOutputStream(),true);
              String msg = "Paskaa";
             out.println( msg );
             System.out.println(msg + " sent to server" + "from client" + getId());
             
             out.flush();
            // out.close(); //Commented out or Hibernate freaks out. Close later
             
             //// DO stuff with Facade
                     

        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    
              
        }
            
               
            
        public Socket getClientSocket() {
			return clientSocket;
		}

		public IMoodWatch getFacade() {
			return facade;
		}

		

		public void stopRunning() throws IOException
        {
        	running = false;
        	clientSocket.close();
        }
        }
    