package server_side;

import java.util.HashMap;
import java.util.Map;

public class Position {
    private int x;
    private int y;
    private static final Map<String, String> map = new HashMap<String, String>() {{
        put("0,-1", "Up");
        put("1,0", "Right");
        put("0,1", "Down");
        put("-1,0", "Left");
    }};

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position(String data) {
        String[] arr = data.split(",");
        this.y = Integer.parseInt(arr[0]);
        this.x = Integer.parseInt(arr[1]);
    }

    public boolean equals(Position p) {
        return x == p.x && y == p.y;
    }

    public boolean equals(int x, int y) {
        return this.x == x && this.y == y;
    }

    @Override
    public boolean equals(Object obj) {
        return this.x == ((Position)obj).getX() && this.y == ((Position)obj).getY();
    }

    public static String getDirection(Position from, Position to) {
        return map.get((to.getX()-from.getX()) + "," + (to.getY()-from.getY()));
    }

    public int getX() {
        return x;
    }

    private void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    private void setY(int y) {
        this.y = y;
    }

    @Override
    public int hashCode() {
        return (this.x + "," + this.y).hashCode();
    }
}
