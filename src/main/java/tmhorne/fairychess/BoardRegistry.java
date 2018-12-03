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

/**
 * a registry of boards
 * <br>
 * comes with chess
 */
public class BoardRegistry {
    private static Map<String, ChessBoardInfo> registry = new HashMap<>();

    private BoardRegistry() {}

    /**
     * initialize with boards
     */
    public static void init() {
        // reset registry because why not
        registry = new HashMap<>();

        // load chess from resource file
        loadFromFile("chess", BoardRegistry.class.getResourceAsStream("util/chess.board"));

        // boards directory
        File boardDirectory = new File("boards");

        // make it
        boardDirectory.mkdir();

        // get all of the files
        File[] boards = boardDirectory.listFiles(File::isFile);

        // for each board
        for(File board : boards) {
            try {
                // just load it
                loadFromFile(board.getName().split("\\.")[0], new FileInputStream(board));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * loads a board from a file (technically an input stream)
     * @param name name for the board
     * @param stream stream to read from
     */
    public static void loadFromFile(String name, InputStream stream) {
        // create scanner
        Scanner s = new Scanner(stream);

        // first line
        String start = s.nextLine();
        // complicated thing to convert first line into an array with the size
        Integer[] size = Arrays.stream(start.split("x")) // stream split by comma
                .map(Integer::parseInt) // parse ints
                .collect(Collectors.toList()) // turn it into a list
                .toArray(new Integer[]{}); // its an integer list

        // intialize for pieces
        String[][] pieces = new String[size[1]][size[0]];

        // for each line
        for(int y = 0; y < size[1]; y++) {
            // if theres nothing to read, get out
            if(!s.hasNext()) {
                break;
            }

            // get the line
            String line = s.nextLine();
            // split it
            String[] pieceLine = line.split(",");
            // get the pieces
            pieces[y] = pieceLine;
        }

        // close the file
        s.close();

        // create the info object
        ChessBoardInfo info = new ChessBoardInfo(size[0], size[1], pieces);

        // put it in the registry
        registry.put(name, info);
    }

    public static ChessBoardInfo getBoardInfo(String name) {
        return registry.get(name);
    }

    public static ChessBoard getBoardFromName(String name, Player p1, Player p2) {
        return new ChessBoard(getBoardInfo(name), p1, p2);
    }

    public static String[] listBoards() {
        String[] out = new String[registry.size()];
        registry.keySet().toArray(out);
        return out;
    }

}
