package test;

import server_side.algorithms.Searchable;
import server_side.algorithms.Searcher;
import server_side.algorithms.ShortestPath;

public class MyTestSearcher<T> {
    public void testSearcher(Searcher<T> searcher, Searchable<T> searchable) {
        ShortestPath<T> sol = searcher.search(searchable);
        int n = searcher.getNumberOfNodesEvaluated();
    }
}
