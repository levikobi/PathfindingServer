package server_side;

import java.util.PriorityQueue;

public abstract class CommonSearcher<T> implements Searcher<T> {
    private PriorityQueue<State<T>> openList;
    private int evaluatedNodes;

    public CommonSearcher() {
        this.openList = new PriorityQueue<State<T>>();
        this.evaluatedNodes = 0;
    }

    final protected State<T> popOpenList() {
        evaluatedNodes++;
        return openList.poll();
    }

    @Override
    public abstract Solution search(Searchable<T> s);

    @Override
    public int getNumberOfNodesEvaluated() {
        return evaluatedNodes;
    }
}
