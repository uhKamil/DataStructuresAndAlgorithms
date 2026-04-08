package dsaa.lab03;

import java.util.Scanner;

public class Document {
    public String name;
    public TwoWayUnorderedListWithHeadAndTail<Link> link;

    public Document(String name, Scanner scan) {
        this.name = name;
        link = new TwoWayUnorderedListWithHeadAndTail<Link>();
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
                    String linkRef = word.substring(5);

                    if (correctLink(linkRef)) {
                        link.add(new Link(linkRef));
                    }
                }
            }
        }
    }

    // accepted only small letters, capitalic letter, digits nad '_' (but not on the begin)
    public static boolean correctLink(String link) {
        return link.matches("^[a-zA-Z][a-zA-Z0-9_]*$");
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Document: ").append(name);

        for (Link l : link) {
            sb.append("\n").append(l.ref);
        }

        return sb.toString();
    }

    public String toStringReverse() {
        String retStr = "Document: " + name;
        return retStr + link.toStringReverse();
    }

}
