package testers;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import net.MoodWatchFacade;
import controller.ConnectionHandler;
import controller.UserClient;
import controller.Server;

public class PoolTester {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub


	

		

// Start server		
Server server = new Server (6066);	
new Thread(server).start();

/// Just to ensure it's running before we create clients
Thread.sleep(1000);


/// Create multiple clients
for (int i = 0; i<5; i++)
{
UserClient userClient = new UserClient(new Socket("localhost", 6066));


}





















	
	}
}


