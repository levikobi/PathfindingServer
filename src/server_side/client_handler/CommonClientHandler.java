package server_side.client_handler;

import java.io.*;
import java.util.Timer;
import java.util.TimerTask;

public abstract class CommonClientHandler implements ClientHandler {

    private boolean stop;

    @Override
    public void handleClient(InputStream inFromClient, OutputStream outToClient) {
        try {
            BufferedReader userInput = new BufferedReader(new InputStreamReader(inFromClient));
            PrintWriter outToScreen = new PrintWriter(outToClient);

            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    stop = true;
                }
            }, 5 * 60 * 1000);

            while (!stop) {
                readInputsAndSend(userInput, outToScreen);
            }

            userInput.close();
            outToScreen.close();
        } catch (IOException e) { e.printStackTrace(); }
    }

    protected abstract void readInputsAndSend(BufferedReader in, PrintWriter out);
}
