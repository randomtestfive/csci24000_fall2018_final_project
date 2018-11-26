package tmhorne.fairychess;

import tmhorne.fairychess.util.ParlettParser;
import tmhorne.fairychess.util.Vector2;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The main class
 */
public class Main {
    public static void main(String[] args) {
        ParlettParser.getRules("oi2(1/2),n+")
            .forEach(System.out::println);
    }
}
