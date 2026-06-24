package dsaa.lab12;

import java.util.Scanner;

public class LinesReader {
    String concatLines(int howMany, Scanner scanner) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < howMany; i++) {
            String line = scanner.nextLine();
            sb.append(line);
        }
        return sb.toString();
    }
}
