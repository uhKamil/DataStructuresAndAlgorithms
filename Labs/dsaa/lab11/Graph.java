package dsaa.lab11;

import java.util.*;
import java.util.Map.Entry;

public class Graph {
    int arr[][];
    HashMap<String, Integer> name2Int;
    Entry<String, Document>[] arrDoc;

    // The argument type depend on a selected collection in the Main class
    public Graph(SortedMap<String, Document> internet) {
        int size = internet.size();
        arr = new int[size][size];
        int i = 0;
        name2Int = new HashMap<>();
        arrDoc = (Map.Entry<String, Document>[]) new Map.Entry[size];

        for (Map.Entry<String, Document> entry : internet.entrySet()) {
            name2Int.put(entry.getKey(), i);
            arrDoc[i] = entry;
            i++;
        }
        for (Map.Entry<String, Document> entry : arrDoc) {
            Document doc = entry.getValue();
            for (Link l : doc.link.values()) {
                int a = name2Int.get(entry.getKey());
                if (name2Int.containsKey(l.ref)) {
                    int b = name2Int.get(l.ref);
                    arr[a][b] = l.weight;
                }
            }
        }
    }

    public String bfs(String start) {
        if (name2Int.containsKey(start)) {
            Queue<Integer> queue = new LinkedList<>();
            queue.add(name2Int.get(start));
            boolean[] visited = new boolean[arr.length];
            visited[name2Int.get(start)] = true;

            StringBuilder sb = new StringBuilder();

            while (!queue.isEmpty()) {
                Integer first = queue.poll();
                sb.append(arrDoc[first].getKey()).append(", ");
                for (int j = 0; j < arr.length; j++) {
                    if (arr[first][j] > 0 && !visited[j]) {
                        queue.add(j);
                        visited[j] = true;
                    }
                }
            }
            sb.setLength(sb.length() - 2);
            return sb.toString();
        } else {
            return null;
        }
    }

    public String dfs(String start) {
        if (name2Int.containsKey(start)) {
            ArrayDeque<Integer> stack = new ArrayDeque<>();
            stack.add(name2Int.get(start));
            boolean[] visited = new boolean[arr.length];

            StringBuilder sb = new StringBuilder();

            while (!stack.isEmpty()) {
                Integer first = stack.pop();
                if (visited[first]) continue;
                visited[first] = true;
                sb.append(arrDoc[first].getKey()).append(", ");
                for (int j = arr.length - 1; j >= 0; j--) {
                    if (arr[first][j] > 0 && !visited[j]) {
                        stack.push(j);
                    }
                }
            }
            sb.setLength(sb.length() - 2);
            return sb.toString();
        } else {
            return null;
        }
    }

    public int connectedComponents() {
        DisjointSetForest DFS = new DisjointSetForest(arr.length);
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (arr[i][j] > 0) {
                    DFS.union(i, j);
                }
            }
        }
        return DFS.countSets();
    }

    public String DijkstraSSSP(String startVertexStr) {
        if (!name2Int.containsKey(startVertexStr)) return null;

        int size = arr.length;
        int start = name2Int.get(startVertexStr);

        StringBuilder sb = new StringBuilder();

        int[] dist = new int[size];
        boolean[] visited = new boolean[size];
        int[] prev = new int[size];

        for (int i = 0; i < size; i++) {
            dist[i] = Integer.MAX_VALUE;
            visited[i] = false;
            prev[i] = -1;
        }

        dist[start] = 0;

		for (int i = 0; i < size; i++) {
            int u = -1;
            int minDist = Integer.MAX_VALUE;
            for (int j = 0; j < size; j++) {
                if (!visited[j] && dist[j] < minDist) {
                    minDist = dist[j];
                    u = j;
                }
            }
            if (u == -1) break;
            visited[u] = true;
            for (int v = 0; v < size; v++) {
                if (arr[u][v] > 0 && dist[u] + arr[u][v] < dist[v]) {
                    dist[v] = dist[u] + arr[u][v];
                    prev[v] = u;
                }
            }
        }

        for (int i = 0; i < size; i++) {
            // case 1
            if (i == start) {
                sb.append(arrDoc[i].getKey()).append("=").append(0);
                sb.append("\n");
            } else if (dist[i] == Integer.MAX_VALUE) {
                sb.append("no path to ").append(arrDoc[i].getKey());
                sb.append("\n");
            } else {
                // stos
                ArrayDeque<Integer> stack = new ArrayDeque<>();
                int current = i;
                while (current != -1) {
                    stack.push(current);
                    current = prev[current];
                }
                while (!stack.isEmpty()) {
                    int x = stack.pop();
                    sb.append(arrDoc[x].getKey());
                    if (!stack.isEmpty()) sb.append("->");
                }
                sb.append("=").append(dist[i]);
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}
