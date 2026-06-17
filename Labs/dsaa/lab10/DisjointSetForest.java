package dsaa.lab10;

public class DisjointSetForest implements DisjointSetDataStructure {

    static class Element {
        int rank;
        int parent;
    }

    Element[] arr;

    public DisjointSetForest(int size) {
        arr = new Element[size];
        for(int i = 0; i < arr.length; i++){
            makeSet(i);
        }
    }

    @Override
    public void makeSet(int item) {
        Element el = new Element();
        arr[item] = el;
        arr[item].parent = item;
        arr[item].rank = 0;
    }

    @Override
    public int findSet(int item) {
        if (arr[item].parent == item) {
            return item;
        }
        arr[item].parent = findSet(arr[item].parent);
        return arr[item].parent;
    }

    @Override
    public boolean union(int itemA, int itemB) {
        if (findSet(itemA) == findSet(itemB)) return false;

        int reprA = findSet(itemA);
        int reprB = findSet(itemB);

        if (arr[reprA].rank > arr[reprB].rank) {
            arr[reprB].parent = reprA;
        } else if (arr[reprA].rank < arr[reprB].rank) {
            arr[reprA].parent = reprB;
        }
        else {
            arr[reprA].parent = reprB;
            arr[reprB].rank += 1;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Disjoint sets as forest:\n");
        for (int i = 0; i < arr.length; i++) {
            sb.append(i);
            sb.append(" -> ");
            sb.append(arr[i].parent);
            sb.append("\n");
        }
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }

    @Override
    public int countSets() {
        int setsAmount = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].parent == i) {
                setsAmount++;
            }
        }
        return setsAmount;
    }
}
