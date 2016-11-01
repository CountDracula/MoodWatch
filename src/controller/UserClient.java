
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;

import net.IMoodWatch;
import net.MoodWatchFacade;
import util.Facade;
import util.PageDao;

 // this class handles each client connection

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
  

        try {
        	
        	///TODO, do this over the socket
        	PageDao dao = new PageDao();
        	Collection<String> sites = dao.sitesToClient();
        
        	
        	String testeroos = facade.addSite("www.nu.nl");
        	System.out.println("HARALDS SITE: " + testeroos);
        	
        	Map<String, List<String>> threads = dao.threadsToClient();
       Map<String, String> allSites = facade.addAllSites(sites);
       
       for (Map.Entry<String,String> site : allSites.entrySet())
       {
    	   System.out.println("URL:" + site.getKey());
    	   System.out.println("Site:" + site.getValue());
       }
       
       
       
       
       
        	System.out.println(threads.get("www.jatkoaika.com"));
        	Collection<String> threadsToAdd = threads.get("www.jatkoaika.com");
        
        Map<String, Boolean> checker = facade.addAllThreads("www.jatkoaika.com", threadsToAdd); 
        
        for (Map.Entry<String, Boolean> k : checker.entrySet())
        {
        	System.out.println("SITE: " + k.getKey());
        	System.out.println("VALUE: " + k.getValue());
        }
        
        	facade.startEngine();
        
			clientSocket = new Socket(host, portNumber);
			ip =(((InetSocketAddress) clientSocket.getRemoteSocketAddress()).getAddress()).toString().replace("/","");
			System.out.println("I am client # " + this.id + " and I connected to : " + clientSocket.getRemoteSocketAddress());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    
        try {
			out = new PrintWriter(clientSocket.getOutputStream(),true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        String msg = "Paskaa";
        while (running)
        {
		
		
		
		 //Send message
          
			
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
        
    