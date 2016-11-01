package testers;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.PostConstruct;
import javax.ejb.Startup;
import javax.el.ELContextEvent;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import util.PageDao;
import model.Page;
import model.PageController;
import net.IMoodWatch;
import net.MoodWatchFacade;
import controller.UserClient;
import controller.Server;

//TODO rename class to beancontroller or something...


@Startup
@ManagedBean (name="mainBean")
@ApplicationScoped
public class PoolTester {
public static Server server;

@ManagedProperty(value = "#{clients}")
public  ArrayList<UserClient> clients;


public static void main(String[] args) throws IOException, InterruptedException {
	// TODO Auto-generated method stub
	
PoolTester pooltester = new PoolTester();

//Start server		
		try {
			server = new Server (6066);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	


		new Thread(server).start();
		Thread.sleep(1000);
	PageDao dao = new PageDao();


pooltester.startServer();
	
}	
	@PostConstruct
	public void startServer() throws InterruptedException, UnknownHostException, IOException
	{
		
			
        clients = new ArrayList<UserClient>();
	
	
		ExecutorService executor = Executors.newFixedThreadPool(5);
//		/// Create multiple clients
		for (int i = 0; i<1; i++)
		{
			UserClient userClient = new UserClient();
			userClient.setId(i);
			executor.submit(userClient);
			userClient.setStatus("Running");
				
		
		clients.add(userClient);
		}
		
	
		

	}

	public ArrayList<UserClient> getClients() {
	
				return clients;
	}
	public void setClients(ArrayList<UserClient> clients) {
		this.clients = clients;
	}
	
	public void stopClient(String client) throws IOException
	{
		int retrieve = Integer.parseInt(client);
	clients.get(retrieve).stopRunning();

	
	}
	
	public void startClient(String client)
	{
		int retrieve = Integer.parseInt(client);
		clients.get(retrieve).setRunning(true);
	}
	
	public void deleteClient(String client) throws IOException
	{
		int retrieve = Integer.parseInt(client);
		
		// Check how to do this with the ThreadPooLExecutor
		
		clients.get(retrieve).stopRunning();
		clients.remove(retrieve);
		//System.out.println("DELETING CLIENT: " + clients.get(retrieve).getId());
	}
	


	
	}



