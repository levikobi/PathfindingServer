package server_side.cache_manager;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public final class FileCacheManager<Problem, Solution> implements CacheManager<Problem, Solution> {
    HashMap<Problem, Solution> map;

    public FileCacheManager() {
        super();
        // TODO Auto-generated constructor stub
        // Keep trying to format the hashmap inside the XML file.

        try {
            File cacheFile = new File("out.txt");
            if (!cacheFile.exists()) {
                cacheFile.createNewFile();
                map = new HashMap<>();
            } else {
                XMLDecoder in = new XMLDecoder(new BufferedInputStream(new FileInputStream(cacheFile)));
                map = (HashMap) in.readObject();
                in.close();
            }
        } catch (IOException e) { e.printStackTrace(); }
    }

    @Override
    public boolean isCached(Problem problem) {
        return false;
    }

    @Override
    public Solution loadSolution(Problem problem) {
        return map.get(problem);
    }

    @Override
    public void saveSolution(Problem problem, Solution solution) {
        map.put(problem, solution);
        try (XMLEncoder out = new XMLEncoder(new BufferedOutputStream(new FileOutputStream("out.txt")))) {
            out.writeObject(map);
        } catch (IOException e) { e.printStackTrace(); }
    }
}

//package server_side.cache_manager;
//
//import java.io.*;
//import java.util.HashMap;
//import java.util.Map;
//
//public final class FileCacheManager<Problem, Solution> implements CacheManager<Problem, Solution> {
//	HashMap<Problem, Solution> map;
//
//	public FileCacheManager() {
//		super();
//		// TODO Auto-generated constructor stub
//        // Serialize the HashMap to a file
//        // Try to use a buffer to improve efficiency.
//        // Remember: HashMap<List<String>, String> - everything is Serialized already!
//
//        try {
//            File cacheFile = new File("out.txt");
//            if (!cacheFile.exists()) {
//                cacheFile.createNewFile();
//                map = new HashMap<>();
//            } else {
//                ObjectInputStream in = new ObjectInputStream(new FileInputStream(cacheFile));
//                map = (HashMap) in.readObject();
//                in.close();
//            }
//        } catch (IOException | ClassNotFoundException e) { e.printStackTrace(); }
//    }
//
//    @Override
//    public boolean isCached(Problem problem) {
//        return map.containsKey(problem);
//    }
//
//    @Override
//    public Solution loadSolution(Problem problem) {
//		return map.get(problem);
//    }
//
//    @Override
//    public void saveSolution(Problem problem, Solution solution) {
//		map.put(problem, solution);
//
//        try {
//            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("out.txt"));
//            out.writeObject(map);
//            out.close();
//        } catch (IOException e) { e.printStackTrace(); }
//    }
//}
