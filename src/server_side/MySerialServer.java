package server_side;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class MySerialServer implements Server {
    private volatile boolean stop = false;

    @Override
    public void open(int port, ClientHandler ch) {
        new Thread(() -> {
            try {
                runServer(port, ch);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void runServer(int port, ClientHandler ch) throws Exception {
        ServerSocket server = new ServerSocket(port);
        server.setSoTimeout(1000);
        while (!stop) {
            try {
                Socket aClient = server.accept();
                try {
                    ch.handleClient(aClient.getInputStream(), aClient.getOutputStream());

                    // Those closing calls should be delegated to the ClientHandler!
//                    aClient.getInputStream().close();
//                    aClient.getOutputStream().close();
                    aClient.close();
                } catch (IOException e) { e.printStackTrace(); }
            } catch (SocketTimeoutException e) { e.printStackTrace(); }
        }
        server.close();
    }

    @Override
    public void stop() {
        this.stop = true;
    }
}
