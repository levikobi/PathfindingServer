package server_side.client_handler;

import server_side.*;
import server_side.algorithms.*;
import server_side.cache_manager.CacheManager;
import server_side.cache_manager.FileCacheManager;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public final class SearchProblemHandler extends CommonClientHandler {
    private final CacheManager<List<String>, String> cacheManager;
    private final Solver<Searchable<Position>, ShortestPath<Position>> solver;
    private List<String> clientInput;

    public SearchProblemHandler() {
        cacheManager = new FileCacheManager<>();
//        solver = new SearcherSolver<>(new BestFirstSearch<>());
        solver = new SearcherSolver<>(new Astar<>());
    }

    @Override
    protected void readInputsAndSend(BufferedReader in, PrintWriter out) {
        try {
            readClientInput(in);
            sendClientInput(out);
        } catch (IOException e) { e.printStackTrace(); }
    }

    private void readClientInput(BufferedReader in) throws IOException {
        clientInput = new ArrayList<>();
        Scanner scanner = new Scanner(in);
        String line;
        do {
            clientInput.add(line=scanner.nextLine());
        } while (!line.equals("end"));
        clientInput.add(scanner.nextLine()); // entry point
        clientInput.add(scanner.nextLine()); // target point
        clientInput.add(scanner.nextLine()); // weight limit
    }

    private void sendClientInput(PrintWriter out) {
        if (!cacheManager.isCached(clientInput)) {
            MazeDomain maze = MazeFactory.createMaze(MazeFactory.Type.MAZE2D, clientInput);
            ShortestPath<Position> sol= solver.solve(maze);
            cacheManager.saveSolution(clientInput, translateSolution(sol.getPath()));
        }
        out.println(cacheManager.loadSolution(clientInput));
        out.flush();
    }

    private String translateSolution(List<State<Position>> path) {
        StringBuilder s = new StringBuilder();
        for (int i = 1; i < path.size(); i++) {
            s.append(Position.getDirection(path.get(i-1).getState(), path.get(i).getState())).append(",");
        }
        return s.subSequence(0, s.length()-1).toString();
    }
}
