package dsaa.lab10;

import java.util.Scanner;
import java.util.*;

public class Document implements IWithName {
    public String name;
    // TODO? You can change implementation of Link collection
    public SortedMap<String, Link> link;

    public Document(String name) {
        this.name = name.toLowerCase();
        link = new TreeMap<>();
    }

    public Document(String name, Scanner scan) {
        this.name = name.toLowerCase();
        link = new TreeMap<>();
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
                        link.put(newLink.ref, newLink);
                    }
                }
            }
        }
    }

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
        StringBuilder sb = new StringBuilder();
        sb.append("Document: ").append(name).append("\n");
        for (Link l : link.values()) {
            sb.append(l).append("\n");
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String getName() {
        return name;
    }
}
