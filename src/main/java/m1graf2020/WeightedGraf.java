package m1graf2020;

import java.util.Collections;

public class WeightedGraf extends Graf {

    public void addEdge(int from, int to, int weight) {
        addEdge(new Edge(from, to, weight));
    }

    public void addEdge(Node from, Node to, int weight) {
        addEdge(new Edge(from.getId(), to.getId(), weight));
    }

}
