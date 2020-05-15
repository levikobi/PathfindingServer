package server_side;

public interface CacheManager <Problem, Solution> {
    public boolean isCached(Problem problem);
    public Solution loadSolution(Problem problem);
    public void saveSolution(Problem problem, Solution solution);
}
