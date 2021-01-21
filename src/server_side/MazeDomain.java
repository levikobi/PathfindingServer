package server_side;

import server_side.algorithms.Searchable;
import server_side.algorithms.State;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class MazeDomain implements Searchable<Position> {
    private Position entry;
    private Position exit;
    private int[][] board;
    private double weightLimit;
//    private boolean isSolved;
//    private Character[] characters;

    public MazeDomain(List<String> data) {
        formatMazeData(data);
    }

    private void formatMazeData(List<String> data) {
        formatBoard(data.subList(0, data.size()-4));
        formatPositions(data.subList(data.size()-3, data.size()-1));
        weightLimit = Double.parseDouble(data.get(data.size()-1));
    }

    private void formatBoard(List<String> data) {
        int row = 0, col = 0;
        int m = data.size(), n = data.get(0).split(",").length;
        board = new int[m][n];
        for (String currLine : data) {
            for (String currNum : currLine.split(",")) {
                board[col][row++] = Integer.parseInt(currNum);
            }
            row=0;
            col++;
        }
    }

    private void formatPositions(List<String> data) {
        entry = new Position(data.get(0));
        exit = new Position(data.get(1));
    }

    @Override
    public State<Position> getInitialState() {
        return new State<>(entry, 0, null);
    }

    @Override
    public boolean isGoalState(State<Position> s) {
        return s.getState().equals(exit);
    }

    @Override
    public List<State<Position>> getAllPossibleStates(State<Position> s) {
        List<State<Position>> possibilities = new ArrayList<>(4);
        int x = s.getState().getX(), y = s.getState().getY();
        checkPossibilityAndAddToList(possibilities, x, y-1, s);
        checkPossibilityAndAddToList(possibilities, x-1, y, s);
        checkPossibilityAndAddToList(possibilities, x, y+1, s);
        checkPossibilityAndAddToList(possibilities, x+1, y, s);
        return possibilities;
    }

    @Override
    public double getWeightLimit() {
        return weightLimit;
    }

    private void checkPossibilityAndAddToList(List<State<Position>> possibilities, int x, int y, State<Position> s) {
        if (0 <= x && x < board[0].length && 0 <= y && y < board.length) {
            possibilities.add(new State<>(new Position(x, y), board[y][x], s));
        }
    }

    @Override
    public String toString() {
        return "MazeDomain{" +
                "entry=" + entry +
                ", exit=" + exit +
                ", board=" + Arrays.toString(board) +
                '}';
    }
}
