package server_side;

import java.util.EnumMap;
import java.util.List;

public class MazeFactory {
    public enum Type { MAZE2D, }

    private interface Creator {
        MazeDomain create(List<String> data);
    }

    private static final EnumMap<Type, Creator> mazeCreators = new EnumMap<Type, Creator>(Type.class) {{
        put(Type.MAZE2D, Maze2D::new);
    }};

    public static MazeDomain createMaze(Type type, List<String> mazeData) {
        Creator c = mazeCreators.get(type);
        return (c == null) ? null : c.create(mazeData);
    }
}
