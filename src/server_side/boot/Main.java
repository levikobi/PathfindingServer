package server_side.boot;

import server_side.client_handler.SearchProblemHandler;
import server_side.server.MySerialServer;
import server_side.server.Server;

public class Main {
    public static void main(String[] args) {
        Server server = new MySerialServer(5000);
        server.start(new SearchProblemHandler());

        System.out.println("Pathfinding server running on port 5000.");

    }
}
