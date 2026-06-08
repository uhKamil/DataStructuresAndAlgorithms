package dsaa.lab09;

public class DisjointSetLinkedList implements DisjointSetDataStructure {

    private class Element {
        int representant;
        int next;
        int length;
        int last;
    }

    private static final int NULL = -1;
    Element arr[];

    public DisjointSetLinkedList(int size) {
        arr = new Element[size];
    }

    @Override
    public void makeSet(int item) {
        Element el = new Element();
        arr[item] = el;
        arr[item].representant = item;
        arr[item].next = NULL;
        arr[item].length = 1;
        arr[item].last = item;
    }

    @Override
    public int findSet(int item) {
        return arr[item].representant;
    }

    @Override
    public boolean union(int itemA, int itemB) {
        int reprA = findSet(itemA);
        int reprB = findSet(itemB);
        if (reprA == reprB) return false;

        if (arr[reprA].length >= arr[reprB].length) {
            int tailA = arr[reprA].last;
            arr[tailA].next = reprB;
            arr[reprA].last = arr[reprB].last;
            arr[reprA].length += arr[reprB].length;
            int current = reprB;
            while (current != NULL) {
                arr[current].representant = reprA;
                current = arr[current].next;
            }
        } else {
            int tailB = arr[reprB].last;
            arr[tailB].next = reprA;
            arr[reprB].length += arr[reprA].length;
            arr[reprB].last = arr[reprA].last;
            int current = reprA;
            while (current != NULL) {
                arr[current].representant = reprB;
                current = arr[current].next;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Disjoint sets as linked list:\n");
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].representant == i) {
                int current = i;
                while (current != NULL) {
                    sb.append(current);
                    current = arr[current].next;
                    if (current != NULL) sb.append(", ");
                }
                sb.append("\n");
            }
        }
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }

}
