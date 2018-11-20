package tmhorne.fairychess;

import tmhorne.fairychess.util.Vector2;

import java.util.HashSet;
import java.util.Set;

/**
 * The main class
 */
public class Main {
    public static void main(String[] args) {
        Vector2 a = new Vector2(0,0);
        Vector2 b = new Vector2(1,0);
        Vector2 c = new Vector2(1,0);
        System.out.println(a.equals(b));
        System.out.println(b.equals(c));

        Set<Vector2> s = new HashSet<>();
        s.add(a);
        s.add(b);
        s.add(c);
        s.forEach(System.out::println);
    }
}
