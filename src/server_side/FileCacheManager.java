package server_side;

import java.util.HashMap;
import java.util.Map;

public class FileCacheManager<Problem, Solution> implements CacheManager<Problem, Solution> {
	Map<Problem, Solution> map = new HashMap<>();

	public FileCacheManager() {
		super();
		// TODO Auto-generated constructor stub
	}
	
    @Override
    public boolean isCached(Problem problem) {
        return map.containsKey(problem);
    }

    @Override
    public Solution loadSolution(Problem problem) {
		return map.get(problem);
    }

    @Override
    public void saveSolution(Problem problem, Solution solution) {
		map.put(problem, solution);
    }
}
