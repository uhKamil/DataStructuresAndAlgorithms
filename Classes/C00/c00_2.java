import java.util.Arrays;
import java.util.Scanner;

public class c00_2 {
    private static int getSmallest(int[] arr) {
        int min = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < min) min = arr[i];
        }
        return min;
    }

    private static int getSecondSmallest(int[] arr) {
        int min = getSmallest(arr);
        int min2 = 0;
        int min3 = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > min) {

            }
//            if (Math.max(arr[i], min) == arr[i]) {
//                min2 = arr[i];
//                break;
//            }
//            if (arr[i] > min && arr[i] < min2) min2 = arr[i];
//            if (Math.max(arr[i], min) == arr[i] && Math.min(arr[i], min2) == min2) min2 = arr[i];
//            if (arr[i] > min && arr[i] < min2) min2 = arr[i];
        }
        return min2;
    }

    public static int valuesAmount(int value, int[] arr) {
        int sum = 0;
        for (int i = 1; i < arr.length; i++) {
            if (value == arr[i]) sum += 1;
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] arr = {2, 4, 8, 10};
        int result = getSmallest(arr);
        int result2 = getSecondSmallest(arr);
        System.out.println(result);
        System.out.println(result2);
        boolean active = true;
        while (active) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Give me an array and I'll retrieve the second smallest number");
            int[] array = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
            int result3 = getSecondSmallest(array);
            System.out.println(result3);
        }
    }
}
