package server_side.algorithms;

import java.util.List;

public class ShortestPath<T> {
    private final List<State<T>> path;

    public ShortestPath(List<State<T>> path) {
        this.path = path;
    }

    public List<State<T>> getPath() {
        return path;
    }
}
