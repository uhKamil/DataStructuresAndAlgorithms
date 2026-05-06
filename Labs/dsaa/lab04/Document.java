package dsaa.lab04;

import java.util.ListIterator;
import java.util.Scanner;

public class Document {
    public String name;
    public TwoWayCycledOrderedListWithSentinel<Link> link;

    public Document(String name, Scanner scan) {
        this.name = name.toLowerCase();
        link = new TwoWayCycledOrderedListWithSentinel<>();
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

    // accepted only small letters, capital letter, digits nad '_' (but not on the beginning)
    public static boolean isCorrectId(String id) {
        return id.matches("^[a-zA-Z][a-zA-Z0-9_]*$");
    }

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
        StringBuilder sb = new StringBuilder();
        sb.append("Document: ").append(name).append("\n");
        int count = 0;

        for (Link l : link) {
            sb.append(l.toString());
            count++;
            if (count % 10 == 0) {
                sb.append("\n");
            } else {
                sb.append(" ");
            }
        }
        return sb.toString().trim();
    }

    public String toStringReverse() {
        StringBuilder sb = new StringBuilder();
        sb.append("Document: ").append(name).append("\n");

        ListIterator<Link> iter = link.listIterator();
        while (iter.hasNext()) {
            iter.next();
        }

        int count = 0;
        while (iter.hasPrevious()) {
            sb.append(iter.previous().toString());
            count++;
            if (count % 10 == 0) {
                sb.append("\n");
            } else {
                sb.append(" ");
            }
        }
        return sb.toString().trim();
    }
}

