package test;

import server_side.client_handler.SearchProblemHandler;
import server_side.server.MySerialServer;
import server_side.server.Server;

public class TestSetter {
	static Server s;
	
	public static void runServer(int port) {
		// put the code here that runs your server
		s = new MySerialServer(port); // initialize
		s.start(new SearchProblemHandler());
	}

	public static void stopServer() {
		s.stop();
	}
}
