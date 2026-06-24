package dsaa.lab12;

import java.util.LinkedList;

public class Automaton implements IStringMatcher {

    public int[] codeRange(String pattern, String text) {
        int[] range = new int[2];
        range[0] = Integer.MAX_VALUE;
        range[1] = Integer.MIN_VALUE;

        for (int i = 0; i < pattern.length(); i++) {
            range[0] = Math.min(range[0], pattern.charAt(i));
            range[1] = Math.max(range[1], pattern.charAt(i));
        }
        for (int i = 0; i < text.length(); i++) {
            range[0] = Math.min(range[0], text.charAt(i));
            range[1] = Math.max(range[1], text.charAt(i));
        }
        return range;
    }

    public int[] patternPi(String pattern) {
        int[] pi = new int[pattern.length()];
        pi[0] = 0;
        int k = 0;

        for (int q = 1; q < pattern.length(); q++) {
            while (k > 0 && pattern.charAt(k) != pattern.charAt(q)) {
                k = pi[k - 1];
            }
            if (pattern.charAt(k) == pattern.charAt(q)) {
                k++;
            }
            pi[q] = k;
        }
        return pi;
    }

    @Override
    public LinkedList<Integer> validShifts(String pattern, String text) {

        int[] range = codeRange(pattern, text);
        int lowerCode = range[0];
        int upperCode = range[1];

        int[][] delta = new int[pattern.length() + 1][upperCode - lowerCode + 1];
        int[] pi = patternPi(pattern);

        for (int q = 0; q <= pattern.length(); q++) {
            for (int c = lowerCode; c <= upperCode; c++) {
                if (q < pattern.length() && c == pattern.charAt(q)) {
                    delta[q][c - lowerCode] = q + 1;
                } else {
                    delta[q][c - lowerCode] = (q == 0) ? 0 : delta[pi[q-1]][c - lowerCode];
                }
            }
        }

        int q = 0;
        LinkedList<Integer> fit = new LinkedList<>();

        for (int i = 0; i < text.length(); i++) {
            q = delta[q][text.charAt(i) - lowerCode];
            if (q == pattern.length()) {
                fit.add(i - pattern.length() + 1);
            }
        }
        return fit;
    }
}
