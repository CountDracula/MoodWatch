
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.faces.bean.ManagedBean;

import net.IMoodWatch;
import net.MoodWatchFacade;
import util.Facade;
import util.PageDao;


	@ManagedBean (name="client")
    public class UserClient implements Runnable {
        private Socket clientSocket;
        private IMoodWatch facade;
        private volatile boolean running = false;
        private int id;
        private String host;
        private int portNumber;
        private String ip;
        private PrintWriter out = null;
        private String status;
     
        public String getStatus() {
        	if (running)
        	{
        		status = "Running";
        	}
        	else
        	{
        		status = "Stopped";
        	}
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public int getId() {
			return id;
		}

		public boolean isRunning() {
			return running;
		}

		public void setRunning(boolean running) {
			this.running = running;
		}

		public int getPortNumber() {
			return portNumber;
		}

		public void setPortNumber(int portNumber) {
			this.portNumber = portNumber;
		}

		public String getIp() {
			return ip;
		}

		public void setIp(String ip) {
			this.ip = ip;
		}

		public void setId(int id) {
			this.id = id;
		}
		
        
        public UserClient() throws UnknownHostException, IOException {
            
        	
			this.facade = MoodWatchFacade.instance();
			this.host = "localhost";
			this.portNumber = 6066;
		
        }
            
       
        public void run() {
        	 running = true;
        		facade.startEngine();
        	  try {
        	clientSocket = new Socket(host, portNumber);
			ip =(((InetSocketAddress) clientSocket.getRemoteSocketAddress()).getAddress()).toString().replace("/","");
			System.out.println("I am client # " + this.id + " and I connected to : " + clientSocket.getRemoteSocketAddress());
        	  }	
        
		
    	catch (IOException e1) {
		e1.printStackTrace();
	}
  

      

        	/// Push all sites and threads to the client.
        	PageDao dao = new PageDao();
        	Collection<String> sites = dao.sitesToClient();
        	Map<String, List<String>> threads = dao.threadsToClient();
        	
/// Test "method" for trying to update pages. Doesn't work properly.
for (String s : sites)
{	
	
String k = facade.addSite(s);
System.out.println("Converting from " + s + "to " + k);
facade.addAllThreads(k, threads.get(s));
        		
   Map<String, Integer> moodOfSite = facade.getMood(k);
        		
        		for (Map.Entry<String, Integer> entry : moodOfSite.entrySet())
        		{
        			
        			try {
						dao.updatePage(s, entry.getValue(), entry.getKey());
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
        			
        			
        		}
        		
        		
        	}
        
        
        	
        	
        	
        	/// Debugging for retrieving moods
        	System.out.println("ARE THE THREADS EMPTY?::" + facade.getAllPages().isEmpty());
    		System.out.println("ARE THE SITES EMPTY?::" + facade.getAllSites().isEmpty());
                           	 Map<String, Map<String, Integer>> allMoods = new HashMap(); 
        	allMoods= facade.getAllMoods();
 	        System.out.println("ARE THE MOODS EMPTY?:: " + allMoods.isEmpty());  	
 	        
 	        String testSite = "Aamulehti";
 	        System.out.println("ARE THE MOODS FOR SITE + " + testSite + "EMPTY ?" + facade.getMood(testSite).isEmpty());
 	        
 	    
 	        
        	
        	
        	//TODO send updates over socket
			
       
        	

        	
    
        try {
			out = new PrintWriter(clientSocket.getOutputStream(),true);
		} catch (IOException e) {
			e.printStackTrace();
		}
        String msg = "Testing";
        while (running)
        {
		
		
		
//Send message (used to test socket communication, disabled for now)
          
			
	//	} catch (IOException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
	//	}
		
   //  out.println( msg );
	//	System.out.println(msg + " sent to server" + "from client" + getId());

		 //// DO stuff with Facade
         
        }
		// out.flush();
		//out.close();  //Commented out or Hibernate freaks out. Close later
		// try {
		//clientSocket.close();
	//	} catch (IOException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		//}
       
        
        	
        		
        	}
        		
        
            
        public Socket getClientSocket() {
			return clientSocket;
		}

		public IMoodWatch getFacade() {
			return facade;
		}

		

		public void stopRunning() throws IOException
        {
		this.running = false;
		System.out.println("Stopping client #: " + this.id);
		System.out.println("RUNNING ON: " + running);
		
		
		
				
			}
        	
        	
        }
        
    