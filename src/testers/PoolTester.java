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

Thread.sleep(1000);

for (int i = 0; i<10; i++)
{
UserClient userClient = new UserClient(new Socket("localhost", 6066));
System.out.println("New client");
}





















	
	}
}


