import java.util.Arrays;

public class c00_3 {
    public static boolean nextPermutation(int[] arr) {
        int pivot = 0;
        int pivot_index = arr.length;
        for (int i = arr.length - 2; i >= 0; i--) {
            if (arr[i] < arr[i+1]) {
                pivot = arr[i];
                pivot_index = i;
                System.out.println(pivot);
                break;
            }
            if (i == 0) return false;
        }
        System.out.println(pivot);

        for (int i = arr.length - 1; i >= pivot_index; i--) {
            if (arr[i] > arr[pivot_index]) {
                int larger = arr[i];
                int smaller = arr[pivot_index];
                arr[pivot_index] = larger;
                arr[i] = smaller;
                break;
            }
        }

        int larger = arr[arr.length - 1];
        int smaller = arr[arr.length - 2];
        arr[arr.length - 2] = larger;
        arr[arr.length - 1] = smaller;
        System.out.println(Arrays.toString(arr));
        return true;
    }

    public static void main(String[] args) {
        int[] a1 = {1,2,3,4,5};
        int[] a2 = {5,4,3,2,1};
        int[] a3 = {3,4,2,5,1};
        int[][] test_cases = {a1, a2, a3};
        for (int i = 0; i < test_cases.length; i++) {
            nextPermutation(test_cases[i]);
        }
    }
}
