package server_side;

import java.net.SocketTimeoutException;

public interface Server {
    public void open(int port, ClientHandler c) throws SocketTimeoutException;
    public void stop();
}
