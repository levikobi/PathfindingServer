package server_side;

public class State<T> {
    private T state;
    private double cost;       // cost to reach this state
    private State<T> cameFrom; // the state we came from to this state

    public State(T state) {
        this.state = state;
    }

    public boolean equals(State<T> s) {
        return state.equals(s.state);
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    /**
     ********** TODO! *********
     */
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    //    public static void main(String[] args) {
//        State<String> a, b, goal;
//        a = new State<>("A");
//        b = new State<>("B");
//        goal = new State<>("B");
//        System.out.println(b.equals(goal));
//    }
}
