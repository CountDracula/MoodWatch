
package controller;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import net.IMoodWatch;
import net.MoodWatchFacade;

import org.infinispan.commons.util.Base64.OutputStream;

import util.Facade;

 // this class handles each client connection
    public class UserClient implements Runnable {
        private Socket clientSocket;
        private IMoodWatch facade;
        private volatile boolean running = false;
        private int id;

        
        public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}
		String host;
        int portNumber;
        
        public UserClient(Socket socket) throws UnknownHostException, IOException {
            
			
			this.clientSocket = socket;
        }
            
       
    
		
        public void run() {
        
            
            	
              
        	
        	 System.out.println("I am client # " + this.id + " and I connected to : " + clientSocket.getRemoteSocketAddress());
               
        }
               
				//clientSocket = new Socket(host, portNumber);
		
               
           
               
              //facade = MoodWatchFacade.instance();

            
               
            
        public void stopRunning() throws IOException
        {
        	running = false;
        	clientSocket.close();
        }
        }
    