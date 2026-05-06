package dsaa.lab07;

import java.util.LinkedList;

@SuppressWarnings({"rawtypes", "unchecked"})
public class HashTable {
    LinkedList[] arr; // use pure array
    private final static int defaultInitSize = 8;
    private final static double defaultMaxLoadFactor = 0.7;
    private int size;
    private final double maxLoadFactor;

    public HashTable() {
        this(defaultInitSize);
    }

    public HashTable(int size) {
        this(size, defaultMaxLoadFactor);
    }

    public HashTable(int initCapacity, double maxLF) {
        arr = new LinkedList[initCapacity];
        this.maxLoadFactor = maxLF;
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new LinkedList();
        }
    }

    public boolean add(Object elem) {
        int index = Math.abs(elem.hashCode()) % arr.length;
        if (arr[index].contains(elem)) return false;
        arr[index].add(elem);
        size++;
        if ((double) size / arr.length > maxLoadFactor) doubleArray();
        return true;
    }

    private void doubleArray() {
        LinkedList[] oldArr = arr;
        arr = new LinkedList[oldArr.length * 2];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = new LinkedList();
        }

        for (LinkedList list : oldArr) {
            for (Object elem : list) {
                int newIndex = Math.abs(elem.hashCode()) % arr.length;
                arr[newIndex].add(elem);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < arr.length; i++) {
            sb.append(i).append(":");

            if (!arr[i].isEmpty()) {
                sb.append(" ");
                boolean first = true;

                for (Object elem : arr[i]) {
                    if (!first) {
                        sb.append(", ");
                    }

                    IWithName doc = (IWithName) elem;
                    sb.append(doc.getName());

                    first = false;
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public Object get(Object toFind) {
        int index = Math.abs(toFind.hashCode()) % arr.length;
        int listIndex = arr[index].indexOf(toFind);
        if (listIndex != -1) return arr[index].get(listIndex);
        return null;
    }
}

