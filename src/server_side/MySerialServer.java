package server_side;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class MySerialServer implements Server {
    @Override
    public void open(int port, ClientHandler c) throws SocketTimeoutException {
        ServerSocket server = new ServerSocket(port);
        server.setSoTimeout(1000);
        try {
            Socket aClient = server.accept();

            InputStream inFromClient = aClient.getInputStream();
            OutputStream outToClient = aClient.getOutputStream();

            //

            inFromClient.close();
            outToClient.close();
            aClient.close();
            server.close();
        } catch (SocketTimeoutException e) { e.printStackTrace(); }
    }

    @Override
    public void stop() {
        // TODO Auto-generated method stub
    }
}
