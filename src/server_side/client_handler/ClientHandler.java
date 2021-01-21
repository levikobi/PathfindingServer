package server_side.client_handler;

import java.io.InputStream;
import java.io.OutputStream;

public interface ClientHandler {
    void handleClient(InputStream inFromClient, OutputStream outToClient);
}
