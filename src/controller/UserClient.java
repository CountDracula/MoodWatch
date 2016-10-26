
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
        
            
            	
               System.out.println("Connecting to " + host + " on port " + portNumber);
               try {
				clientSocket = new Socket(host, portNumber);
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
               
               System.out.println("Just connected to " + clientSocket.getRemoteSocketAddress());
               
              //facade = MoodWatchFacade.instance();
              

              try {
				stopRunning();
				System.out.println("Closing client socket");
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            }
               
            
        public void stopRunning() throws IOException
        {
        	running = false;
        	clientSocket.close();
        }
        }
    