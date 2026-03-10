package dsaa.lab01;

import java.util.Scanner;

public class Document {
    public static void loadDocument(String name, Scanner scan) {
//        boolean firstLine = false;
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
//            System.out.println(line);
            if (line.trim().equals("eod")) break;
            String[] words = line.trim().split("\\s+");
            // for word in words, check for link correctness, then if it's correct, print it
            for (String word : words) {
                if (word.toLowerCase().startsWith("link=")) {
                    String id = word.substring(5);
                    if (correctLink(id)) {
//                        if (firstLine) System.out.print("\n");
//                        firstLine = true;
//                        System.out.print(id.toLowerCase());
                        System.out.println(id.toLowerCase());
                    }
                }
            }
        }
    }

    // accepted only small letters, capitalic letter, digits and '_' (but not on the begin)
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
