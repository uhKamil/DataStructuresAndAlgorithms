package dsaa.lab08;

import java.util.Scanner;

public class Document implements IWithName {
    private static final int MOD_VALUE = 100000000;
    public String name;
    public BST<Link> link;

    public Document(String name) {
        this.name = name.toLowerCase();
        link = new BST<>();
    }

    public Document(String name, Scanner scan) {
        this.name = name.toLowerCase();
        link = new BST<>();
        load(scan);
    }

    public void load(Scanner scan) {
        while (scan.hasNextLine()) {
            String line = scan.nextLine();

            if (line.trim().equalsIgnoreCase("eod")) {
                break;
            }

            String[] words = line.trim().split("\\s+");

            for (String word : words) {
                if (word.toLowerCase().startsWith("link=")) {
                    String linkStr = word.substring(5);
                    Link newLink = createLink(linkStr);

                    if (newLink != null) {
                        link.add(newLink);
                    }
                }
            }
        }
    }

    // accepted only small letters, capital letter, digits nad '_' (but not at the beginning)
    public static boolean isCorrectId(String id) {
        return id.matches("^[a-zA-Z][a-zA-Z0-9_]*$");
    }

    // accepted only small letters, capital letter, digits nad '_' (but not at the beginning)
    public static Link createLink(String link) {
        int openBracket = link.indexOf('(');
        int closeBracket = link.indexOf(')');

        if (link.contains("(") && link.contains(")")) {

            String ref = link.substring(0, openBracket).toLowerCase();
            if (!isCorrectId(ref)) return null;

            String weightStr = link.substring(openBracket + 1, closeBracket);
            try {
                int weight = Integer.parseInt(weightStr);
                if (weight <= 0) return null;
                return new Link(ref, weight);
            } catch (NumberFormatException e) {
                return null;
            }
        } else {
            String ref = link.toLowerCase();
            if (isCorrectId(ref)) return new Link(ref);
            else return null;
        }
    }

    @Override
    public String toString() {
        String retStr = "Document: " + name + "\n";
        retStr += link.toStringInOrder();
        return retStr;
    }

    public String toStringPreOrder() {
        String retStr = "Document: " + name + "\n";
        retStr += link.toStringPreOrder();
        return retStr;
    }

    public String toStringPostOrder() {
        String retStr = "Document: " + name + "\n";
        retStr += link.toStringPostOrder();
        return retStr;
    }

    @Override
    public int hashCode() {
        if (name == null || name.isEmpty()) return 0;

        int[] sequence = {7, 11, 13, 17, 19};

        // take to a result the ASCII code of first letter
        int result = name.charAt(0);

        // "...and if there is a next letter, we multiply the first letter by the first number
        // in a sequence and add it to the code for a second letter"
        for (int i = 1; i < name.length(); i++) {
            int j = (i - 1) % sequence.length;
            int multiplier = sequence[j];
            result = (result * multiplier + name.charAt(i)) % MOD_VALUE;
        }
        return result;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null || getClass() != obj.getClass()) return false;

        Document other = (Document) obj;
        return getName().equals(other.getName());
    }
}
