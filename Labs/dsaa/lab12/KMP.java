package dsaa.lab12;

import java.util.LinkedList;

public class KMP implements IStringMatcher {

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
        int[] pi = patternPi(pattern);
        int q = 0;

        LinkedList<Integer> fit = new LinkedList<>();

        for (int i = 0; i < text.length(); i++) {
            while (q > 0 && pattern.charAt(q) != text.charAt(i)) {
                q = pi[q - 1];
            }
            if (pattern.charAt(q) == text.charAt(i)) q++;
            if (q == pattern.length()) {
                fit.add(i - pattern.length() + 1);
                q = pi[pattern.length() - 1];
            }
        }
        return fit;
    }
}
