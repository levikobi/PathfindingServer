package server_side;

public interface Solver <Problem, Solution> {
    public Solution solver(Problem problem);
}
