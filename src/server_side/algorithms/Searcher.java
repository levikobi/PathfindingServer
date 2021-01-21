package server_side.algorithms;

public interface Searcher<T> {
    ShortestPath<T> search(Searchable<T> s);
    int getNumberOfNodesEvaluated();
    long measureTime(Searchable<T> s);
}
