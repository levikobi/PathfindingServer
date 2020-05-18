package server_side;

import java.util.PriorityQueue;

public abstract class CommonSearcher<T> implements Searcher<T> {
    private PriorityQueue<State<T>> openList;
    private int evaluatedNodes;

    public CommonSearcher() {
        this.openList = new PriorityQueue<State<T>>();
        this.evaluatedNodes = 0;
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

    protected boolean isOpenListEmpty() {
        return openList.isEmpty();
    }

    protected boolean openListContains(State<T> state) {
        return openList.contains(state);
    }

    @Override
    public abstract Solution search(Searchable<T> s);

    @Override
    public int getNumberOfNodesEvaluated() {
        return evaluatedNodes;
    }
}
