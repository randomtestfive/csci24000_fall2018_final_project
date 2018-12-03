package tmhorne.fairychess.util;

import tmhorne.fairychess.moverule.MoveRule;
import tmhorne.fairychess.moverule.MultileapRule;
import tmhorne.fairychess.moverule.OrthogonalDiagonalRule;
import tmhorne.fairychess.moverule.diagonal.DiagonalBackwardsRule;
import tmhorne.fairychess.moverule.diagonal.DiagonalForwardsRule;
import tmhorne.fairychess.moverule.diagonal.DiagonalRule;
import tmhorne.fairychess.moverule.orthogonal.*;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * a parser for converting a string of parlett notation into a set of move rules
 * <br>
 * basic rundown:
 * <br>
 * a move is either capturing (c), noncapturing (o), or both (leave empty)
 * <br>
 * a move is either initial (i), or not (leave empty)
 * <br>
 * a move has a length, a number for a specific amount, or "n" for any amount
 * <br>
 * the actual move can either by a "normal move" (moving an amount in a direction),
 * or a hippogonal move, like a knight. look through the moverule package for examples
 * <br>
 * rules can be concatenated with a comma
 *
 * @see MoveRule
 */
public class ParlettParser {
    private static Map<String, MoveRule> cache = new HashMap<>();
    private static Pattern bitRegex = Pattern.compile(
            // modifiers  length             the normal ones                  hippogonal moves
            "^([oc]?)(i?)(\\d+|n)(?:(?:(\\*|\\+|<|>|<>|=|<=|>=|X|X>|X<))|(?:\\((\\d)\\/(\\d)\\)))$");

    private ParlettParser() {}

    /**
     * get a set of rules given a full parlett string
     * @param parlett parlett notation string
     * @return set of move rules
     */
    public static Set<MoveRule> getRules(String parlett) {
        // split concatenated bits
        String[] bits = parlett.split(",");

        // get the rules for the bits
        Set<MoveRule> rules = Arrays.stream(bits)
                .map(s -> getRule(s))
                .collect(Collectors.toSet());

        return rules;
    }

    private static MoveRule getRule(String bit) {
        MoveRule out = null;

        // check if we've done this already
        if(cache.containsKey(bit)) {
            return cache.get(bit);
        }

        // check if its valid parlett
        Matcher m = bitRegex.matcher(bit);
        if(m.find()) {
            // variables for storing the different things
            MoveModifier modifier;
            boolean initial;
            Integer length;

            // find move modifier
            switch (m.group(1)) {
                case "c":
                    modifier = MoveModifier.CAPTURE;
                    break;
                case "o":
                    modifier = MoveModifier.NONCAPTURE;
                    break;
                default:
                    modifier = MoveModifier.BOTH;
            }

            // find if initial
            initial = m.group(2).equals("i");

            // if the length is n
            if(m.group(3).equals("n")) {
                // set to null
                length = null;
            } else {
                // else parse it
                length = Integer.parseInt(m.group(3));
            }

            if(m.group(4) == null) { // hippogonal moves
                // get the two leaps
                int leap1 = Integer.parseInt(m.group(5));
                int leap2 = Integer.parseInt(m.group(6));

                // create leap vector
                Vector2 leap = new Vector2(leap1, leap2);

                // create the rule
                out = new MultileapRule(leap, modifier, initial, length);
            } else { // normal moves
                // WARNING: big ugly switch statement ahead
                switch(m.group(4)) {
                    case "*":
                        out = new OrthogonalDiagonalRule(modifier, initial, length);
                        break;
                    case "+":
                        out = new OrthogonalRule(modifier, initial, length);
                        break;
                    case "<":
                        out = new OrthogonalBackwardsRule(modifier, initial, length);
                        break;
                    case ">":
                        out = new OrthogonalForwardsRule(modifier, initial, length);
                        break;
                    case "<>":
                        out = new OrthogonalForwardsBackwardsRule(modifier, initial, length);
                        break;
                    case "=":
                        out = new OrthogonalSidewaysRule(modifier, initial, length);
                        break;
                    case "<=":
                        out = new OrthogonalBackwardsSidewaysRule(modifier, initial, length);
                        break;
                    case ">=":
                        out = new OrthogonalForwardsSidewaysRule(modifier, initial, length);
                        break;
                    case "X":
                        out = new DiagonalRule(modifier, initial, length);
                        break;
                    case "X>":
                        out = new DiagonalForwardsRule(modifier, initial, length);
                        break;
                    case "X<":
                        out = new DiagonalBackwardsRule(modifier, initial, length);
                        break;
                }
            }
        }

        return out;
    }
}
