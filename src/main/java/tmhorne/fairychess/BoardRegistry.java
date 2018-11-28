package tmhorne.fairychess;

import tmhorne.fairychess.util.ChessBoard;
import tmhorne.fairychess.util.ChessBoardInfo;
import tmhorne.fairychess.util.Player;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class BoardRegistry {
    private static Map<String, ChessBoardInfo> registry = new HashMap<>();

    public static void init() {
        registry = new HashMap<>();

        loadFromFile("chess", BoardRegistry.class.getResourceAsStream("util/chess.board"));
    }

    public static void loadFromFile(String name, InputStream stream) {
        Scanner s = new Scanner(stream);

        String start = s.nextLine();
        Integer[] size = Arrays.stream(start.split("x"))
                .map(Integer::parseInt)
                .collect(Collectors.toList())
                .toArray(new Integer[]{});

        String[][] pieces = new String[size[1]][size[0]];

        for(int y = 0; y < size[1]; y++) {
            // if theres nothing to read, get out
            if(!s.hasNext()) {
                break;
            }

            String line = s.nextLine();
            String[] pieceLine = line.split(",");
            pieces[y] = pieceLine;
        }

        s.close();

        ChessBoardInfo info = new ChessBoardInfo(size[0], size[1], pieces);

        registry.put(name, info);
    }

    public static ChessBoardInfo getBoardInfo(String name) {
        return registry.get(name);
    }

    public static ChessBoard getBoardFromName(String name, Player p1, Player p2) {
        return new ChessBoard(getBoardInfo(name), p1, p2);
    }

}
