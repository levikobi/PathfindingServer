package test;

import server_side.client_handler.CommonClientHandler;
import server_side.cache_manager.CacheManager;
import server_side.Solver;

import java.io.*;

public class MyTestClientHandler extends CommonClientHandler {
    private Solver<String, String> solver;
    private CacheManager<String, String> cm;

    public MyTestClientHandler(Solver<String, String> solver, CacheManager<String, String> cm) {
        this.solver = solver;
        this.cm = cm;
    }

    @Override
    protected void readInputsAndSend(BufferedReader in, PrintWriter out) {
        try {
            String line;
            while (!(line=in.readLine()).equals("end")) {
                if (!cm.isCached(line)) {
                    cm.saveSolution(line, solver.solve(line));
                }
                out.println(cm.loadSolution(line));
                out.flush();
            }
        } catch (IOException e) { e.printStackTrace(); }
    }
}
