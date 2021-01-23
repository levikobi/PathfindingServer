package server_side.algorithms;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class Astar<T> extends CommonSearcher<T> {
    @Override
    public ShortestPath<T> search(Searchable<T> s) {
        initializeOpenList(s.getInitialState());
        Set<State<T>> closedSet = new HashSet<>();

        while (!isOpenListEmpty()) {
            State<T> n = popOpenList(); 
            closedSet.add(n);

            if (s.isGoalState(n)) return backTrace(n);

            List<State<T>> successors = s.getAllPossibleStates(n);
            for (State<T> state : successors) {
                if (!closedSet.contains(state) && !openListContains(state)) {
                    state.setStateWeCameFrom(n);
                    state.setCostToReachHere(n.getCostToReachHere() + 1);
                    addToOpenList(state);
                } else if (n.getCostToReachHere() < state.getStateWeCameFrom().getCostToReachHere()) {
                    state.setStateWeCameFrom(n);
                    state.setCostToReachHere(n.getCostToReachHere() + 1);
                    if (openListContains(state)) {
                        popOpenList(state);
                    }
                    addToOpenList(state);
                }
            }
        }
        return null;
    }
}

