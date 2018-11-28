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

public class ParlettParser {
    private static Map<String, MoveRule> cache = new HashMap<>();
    private static Pattern bitRegex = Pattern.compile(
            // modifiers  length             the normal ones                  hippogonal moves
            "^([oc]?)(i?)(\\d+|n)(?:(?:(\\*|\\+|<|>|<>|=|<=|>=|X|X>|X<))|(?:\\((\\d)\\/(\\d)\\)))$");

    private ParlettParser() {}

    public static Set<MoveRule> getRules(String parlett) {
        String[] bits = parlett.split(",");

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

        Matcher m = bitRegex.matcher(bit);
        if(m.find()) {
            MoveModifier modifier;
            boolean initial;
            Integer length;

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

            initial = m.group(2) != null;

            if(m.group(3).equals("n")) {
                length = null;
            } else {
                length = Integer.parseInt(m.group(3));
            }

            if(m.group(4) == null) { // hippogonal moves
                int leap1 = Integer.parseInt(m.group(5));
                int leap2 = Integer.parseInt(m.group(6));
                Vector2 leap = new Vector2(leap1, leap2);
                out = new MultileapRule(leap, modifier, initial, length);
            } else { // normal moves
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
