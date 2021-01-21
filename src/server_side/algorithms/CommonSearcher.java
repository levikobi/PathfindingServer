package server_side.algorithms;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public abstract class CommonSearcher<T> implements Searcher<T> {
    private final PriorityQueue<State<T>> openList;
    private int evaluatedNodes;

    public CommonSearcher() {
        this.openList = new PriorityQueue<>((x, y) -> (int) (x.getCostToReachHere() - y.getCostToReachHere()));
        this.evaluatedNodes = 0;
    }

    final protected void initializeOpenList(State<T> state) {
        openList.clear();
        addToOpenList(state);
    }

    final protected void addToOpenList(State<T> state) {
        openList.add(new State<T>(state));
    }

    final protected State<T> popOpenList() {
        evaluatedNodes++;
        return openList.poll();
    }

    final protected State<T> popOpenList(State<T> state) {
        evaluatedNodes++;
        openList.remove(state);
        return state;
    }

    final protected boolean isOpenListEmpty() {
        return openList.isEmpty();
    }

    final protected boolean openListContains(State<T> state) {
        return openList.contains(state);
    }

    final protected ShortestPath<T> backTrace(State<T> goalState) {
        List<State<T>> track = new LinkedList<>();
        do {
            track.add(0, goalState);
        } while ((goalState = goalState.getStateWeCameFrom()) != null);
        return new ShortestPath<>(track);
    }

    @Override
    public abstract ShortestPath<T> search(Searchable<T> s);

    @Override
    public final int getNumberOfNodesEvaluated() {
        return evaluatedNodes;
    }

    @Override
    public final long measureTime(Searchable<T> s) {
        long time0 = System.nanoTime();
        search(s);
        return System.nanoTime() - time0;
    }
}
