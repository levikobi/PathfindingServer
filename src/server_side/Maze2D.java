package server_side;

import server_side.algorithms.State;

import java.util.List;

public final class Maze2D extends MazeDomain {

    public Maze2D(List<String> data) {
        super(data);
    }

    @Override
    public State<Position> getInitialState() {
        return super.getInitialState();
    }

    @Override
    public boolean isGoalState(State<Position> s) {
        return super.isGoalState(s);
    }

    @Override
    public List<State<Position>> getAllPossibleStates(State<Position> s) {
        return super.getAllPossibleStates(s);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
