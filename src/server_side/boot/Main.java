package server_side.boot;

import server_side.*;

public class Main {
    public static void main(String[] args) {
        Server server = new MySerialServer();
        CacheManager<String, String> cm = new FileCacheManager<>();
        ClientHandler ch = new MyTestClientHandler((String str) -> new StringBuilder(str).reverse().toString() ,cm);
        server.open(Integer.parseInt(args[0]), ch);
    }
}
