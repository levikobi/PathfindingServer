package server_side.server;

import server_side.client_handler.ClientHandler;

public interface Server {
    public void start(ClientHandler c);
    public void stop();
}
