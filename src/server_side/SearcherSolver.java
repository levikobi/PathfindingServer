package server_side;

import server_side.algorithms.Searchable;
import server_side.algorithms.Searcher;
import server_side.algorithms.ShortestPath;

public final class SearcherSolver<T> implements Solver<Searchable<T>, ShortestPath<T>> {
    private final Searcher<T> searcher;

    public SearcherSolver(Searcher<T> searcher) {
        this.searcher = searcher;
    }

    @Override
    public ShortestPath<T> solve(Searchable<T> searchable) {
        return searcher.search(searchable);
    }
}