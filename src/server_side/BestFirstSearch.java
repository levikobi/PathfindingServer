package server_side;

import java.util.HashSet;
import java.util.List;

public class BestFirstSearch<T> extends CommonSearcher<T> {
    @Override
    public Solution search(Searchable<T> s) {
        addToOpenList(s.getInitialState());
        HashSet<State<T>> closedSet = new HashSet<State<T>>();

        while (!isOpenListEmpty()) {
            State<T> n = popOpenList();
            closedSet.add(n);

            if (s.isGoalState(n)) {
//                return backTrace(n, s.getInitialState());
                // TODO: need to decide if I want to implement it in the BFS class itself, or at the CommonSearcher level
            }

            List<State<T>> successors = s.getAllPossibleStates(n);
            for (State<T> state : successors) {
                if (!closedSet.contains(state) && !openListContains(state)) {
                    state.setCameFrom(n);
                    state.setCost(n.getCost() + s.getWeight(n, state));
                    addToOpenList(state);
                } else if (n.getCost() + s.getWeight(n, state) < state.getCost()) {
                    state.setCameFrom(n);
                    state.setCost(n.getCost() + s.getWeight(n, state));
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
