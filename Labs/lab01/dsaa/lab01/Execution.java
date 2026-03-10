package dsaa.lab01;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Execution {
    // Solely for testing how the subparts of my algorithms work
    public static void main(String[] args) throws FileNotFoundException {
//        Drawer.drawLine(3, '.');
//        Drawer.drawLine(1, 'X');
//        Drawer.drawLine(3, '.');
//        Drawer.drawPyramid(4);
//        Drawer.drawPyramid(5);
//        Drawer.drawChristmassTree(4);
        Document.loadDocument("testdoc", new Scanner(new File("./testdoc.txt")));
    }
}
