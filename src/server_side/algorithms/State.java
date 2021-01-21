package server_side.algorithms;

public class State<T> {
    private final T state;
    private double costToReachHere;
    private State<T> stateWeCameFrom;

    public State(T state) {
        this.state = state;
    }

    public State(State<T> state) {
        this.state = state.state;
        this.costToReachHere = state.costToReachHere;
        this.stateWeCameFrom = state.stateWeCameFrom;
    }

    public State(T state, double costToReachHere, State<T> stateWeCameFrom) {
        this.state = state;
        this.costToReachHere = costToReachHere;
        this.stateWeCameFrom = stateWeCameFrom;
    }

    public boolean equals(State<T> s) {
        return state.equals(s.state);
    }

    @Override
    public boolean equals(Object obj) {
        return state.equals(((State)obj).state);
    }

    public T getState() {
        return state;
    }

    public double getCostToReachHere() {
        return costToReachHere;
    }

    public void setCostToReachHere(double costToReachHere) {
        this.costToReachHere = costToReachHere;
    }

    public State<T> getStateWeCameFrom() {
        return stateWeCameFrom;
    }

    public void setStateWeCameFrom(State<T> stateWeCameFrom) {
        this.stateWeCameFrom = stateWeCameFrom;
    }

    @Override
    public int hashCode() {
        return this.state.hashCode();
    }
}
