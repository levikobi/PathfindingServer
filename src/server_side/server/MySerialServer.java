package server_side.server;

import server_side.client_handler.ClientHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeUnit;

public final class MySerialServer implements Server {
    private volatile boolean stop = false;
    private final int port;

    public MySerialServer(int port) {
        this.port = port;
    }

    @Override
    public void start(ClientHandler ch) {
        new Thread(() -> {
            try {
                runServer(ch);
            } catch (Exception e) { e.printStackTrace(); }
        }).start();
    }

    private void runServer(ClientHandler ch) throws Exception {
        ServerSocket server = new ServerSocket(port);
        server.setSoTimeout(3 * 60 * 1000);
        while (!stop) {
            try {
                acceptNewClient(server, ch);
            } catch (SocketTimeoutException e) { stop(); }
        }
        server.close();
    }

    private void acceptNewClient(ServerSocket server, ClientHandler ch) throws Exception {
        Socket aClient = server.accept();
        try {
            System.out.println("New client");
            ch.handleClient(aClient.getInputStream(), aClient.getOutputStream());
            aClient.close();
        } catch (IOException e) { e.printStackTrace(); }
    }

    @Override
    public void stop() {
        this.stop = true;
    }
}
