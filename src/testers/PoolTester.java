package testers;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import model.PageController;
import net.MoodWatchFacade;
import controller.ConnectionHandler;
import controller.UserClient;
import controller.Server;

public class PoolTester {
public static Server server;
public static void main(String[] args) throws IOException, InterruptedException {
	// TODO Auto-generated method stub

PoolTester pooltester = new PoolTester();
pooltester.startServer();
}
	public void startServer() throws InterruptedException, UnknownHostException, IOException
	{
		// Start server		
		try {
			server = new Server (6066);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		new Thread(server).start();

		/// Just to ensure it's running before we create clients

		Thread.sleep(1000);
		ArrayList<UserClient> clients = new ArrayList<UserClient>();


		/// Create multiple clients
		for (int i = 0; i<=2; i++)
		{
			UserClient userClient = new UserClient();
			userClient.setId(i);
				clients.add(userClient);
		new Thread(userClient).start();

		}

		//server.clientShit();


	}
	
	




















	
	}



