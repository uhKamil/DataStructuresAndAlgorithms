package dsaa.lab02;

import java.util.Scanner;

public class Document {
	public String name;
	public OneWayLinkedList<Link> links;

	public Document(String name, Scanner scan) {
		this.name = name;
		this.links = new OneWayLinkedList<>();
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
						links.add(new Link(linkRef.toLowerCase()));
					}
				}
			}
		}
	}

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

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Document: ").append(name).append("\n");

		for (Link link : links) {
			sb.append(link.toString()).append("\n");
		}

		return sb.toString().trim();
	}
}