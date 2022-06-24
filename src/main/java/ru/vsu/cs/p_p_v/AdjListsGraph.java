package ru.vsu.cs.p_p_v;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.*;

/**
 * Реализация графа на основе списков смежности
 */
public class AdjListsGraph implements Graph {
    private List<List<Integer>> vEdjLists = new ArrayList<>();
    private int vCount = 0;
    private int eCount = 0;

    private static Iterable<Integer> nullIterable = new Iterable<Integer>() {
        @Override
        public Iterator<Integer> iterator() {
            return new Iterator<Integer>() {
                @Override
                public boolean hasNext() {
                    return false;
                }

                @Override
                public Integer next() {
                    return null;
                }
            };
        }
    };

    @Override
    public int vertexCount() {
        return vCount;
    }

    @Override
    public int edgeCount() {
        return eCount;
    }

    @Override
    public void addAdge(int v1, int v2) {
        int maxV = Math.max(v1, v2);
        // добавляем вершин в список списков связности
        for (; vCount <= maxV; vCount++) {
            vEdjLists.add(null);
        }
        if (!isAdj(v1, v2)) {
            if (vEdjLists.get(v1) == null) {
                vEdjLists.set(v1, new LinkedList<>());
            }
            vEdjLists.get(v1).add(v2);
            eCount++;
            // для наследников
                if (vEdjLists.get(v2) == null) {
                    vEdjLists.set(v2, new LinkedList<>());
                }
                vEdjLists.get(v2).add(v1);
        }
    }

    private int countingRemove(List<Integer> list, int v) {
        int count = 0;
        if (list != null) {
            for (Iterator<Integer> it = list.iterator(); it.hasNext(); ) {
                if (it.next().equals(v)) {
                    it.remove();
                    count++;
                }
            }
        }
        return count;
    }

    @Override
    public void removeAdge(int v1, int v2) {
        eCount -= countingRemove(vEdjLists.get(v1), v2);
        eCount -= countingRemove(vEdjLists.get(v2), v1);
    }

    @Override
    public Iterable<Integer> adjacencies(int v) {
        return vEdjLists.get(v) == null ? nullIterable : vEdjLists.get(v);
    }

    public boolean hasEulerCycle() {
        for (List<Integer> vEdjList : vEdjLists) {
            if (vEdjList == null)
                continue;

            if (vEdjList.size() % 2 != 0)
                return false;
        }

        return true;
    }

    public void fromStr(String graphStr) {
        Scanner scanner = new Scanner(graphStr);
        int vertexCount = scanner.nextInt();
        int edgeCount = scanner.nextInt();
        for (int i = 0; i < edgeCount; i++) {
            this.addAdge(scanner.nextInt(), scanner.nextInt());
        }
    }

    public String toGraphvizStr() {
        StringBuilder sb = new StringBuilder();
        sb.append("graph g {\r\n");
        HashSet<String> connections = new HashSet<>();

        for (int i = 0; i < vEdjLists.size(); i++) {
            if (vEdjLists.get(i) == null)
                continue;

            for (int to : vEdjLists.get(i)) {
                if (connections.contains(to + " -- " + i))
                    continue;

                sb.append(i);
                sb.append(" -- ");
                sb.append(to);
                sb.append("\r\n");
                connections.add(i + " -- " + to);
            }
        }
        sb.append("}");

        return sb.toString();
    }
}
