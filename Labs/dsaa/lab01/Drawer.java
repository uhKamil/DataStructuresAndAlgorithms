package dsaa.lab01;

public class Drawer {
	public static void drawLine(int n, char ch) {
		for (int i = 1; i <= n; i++) System.out.print(ch);
	}

	public static void drawPyramid(int n) {
        drawPartPyramid(n, n);
	}

	static void drawPartPyramid(int n, int size) {
		int d = n - 1; // dot segment initial length
		int x = 1;     // X segment initial length

		for (int i = 1; i <= size; i++) {
			drawLine(d, '.');
			drawLine(x, 'X');
			drawLine(d, '.');
			d -= 1;
			x += 2;
			System.out.println();
		}
	}

	public static void drawChristmassTree(int n) {
		for (int i = 1; i <= n; i++) {
			drawPartPyramid(n, i);
		}
	}

}
