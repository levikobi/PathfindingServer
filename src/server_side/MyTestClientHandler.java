package server_side;

import java.io.*;

public class MyTestClientHandler implements ClientHandler {
    private Solver<String, String> solver;
    private CacheManager<String, String> cm;

    public MyTestClientHandler(Solver<String, String> solver, CacheManager<String, String> cm) {
        this.solver = solver;
        this.cm = cm;
    }

    @Override
    public void handleClient(InputStream inFromClient, OutputStream outToClient) {
        BufferedReader userInput = new BufferedReader(new InputStreamReader(inFromClient));


    }

    private void readInputsAndSend(BufferedReader in, PrintWriter out, String exitStr) {
        try {
            String line;
            while (!(line=in.readLine()).equals(exitStr)) {
                if (!cm.isCached(line)) {
                    cm.saveSolution(line, solver.solver(line));
                }
                out.println(cm.loadSolution(line));
                out.flush();
            }
        } catch (IOException e) { e.printStackTrace(); }
    }
}
