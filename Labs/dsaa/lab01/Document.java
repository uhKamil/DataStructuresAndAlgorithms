package dsaa.lab01;

import java.util.Scanner;

public class Document {
    public static void loadDocument(String name, Scanner scan) {
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            if (line.trim().equalsIgnoreCase("eod")) break;
            String[] words = line.trim().split("\\s+");
            for (String word : words) {
                if (word.toLowerCase().startsWith("link=")) {
                    String id = word.substring(5);
                    if (correctLink(id)) {
                        System.out.println(id.toLowerCase());
                    }
                }
            }
        }
    }

    // accepted only small letters, capital letter, digits and '_' (but not on the begin)
    public static boolean correctLink(String link) {
        if (link.isEmpty()) return false;
        if (!Character.isLetter(link.charAt(0))) return false;
        for (int i = 1; i < link.length(); i++) {
            char c = link.charAt(i);
            if (!Character.isLetterOrDigit(c)) {
                if (c != '_') return false;
            }
        }
        return true;
    }

}
