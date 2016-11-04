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
import net.IMoodWatch;
import net.MoodWatchFacade;
import controller.PageController;
import controller.UserClient;
import controller.Server;



@Startup
@ManagedBean (name="mainBean")
@ApplicationScoped
public class MainController {
public static Server server;


@ManagedProperty(value = "#{clients}")
public  ArrayList<UserClient> clients;


public static void main(String[] args) throws IOException, InterruptedException {

	
MainController mainController = new MainController();

//Start server		
		try {
			server = new Server (6066);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		new Thread(server).start();
	
		
/// Generate some clients and start desktop client.
mainController.createTestClients();
IMoodWatch facade = MoodWatchFacade.instance();
facade.runDesktop();
	
}	
	
	@PostConstruct
	public void createTestClients() throws InterruptedException, UnknownHostException, IOException
	{
		
			
        clients = new ArrayList<UserClient>();
	
	
		ExecutorService executor = Executors.newFixedThreadPool(5);
//		/// Create multiple clients
		for (int i = 0; i<3; i++)
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
	// Add call to saveData

	
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
		// Add call to saveData
		
		
		
	}
	


	
	}



