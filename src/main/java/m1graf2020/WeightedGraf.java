package m1graf2020;

public class WeightedGraf extends Graf {

    public void addEdge(int from, int to, int weight) {
        addEdge(new Edge(from, to, weight));
    }

    public void addEdge(Node from, Node to, int weight) {
        addEdge(new Edge(from.getId(), to.getId(), weight));
    }

    public void removeEdge(int from, int to, int weight) {
        removeEdge(new Edge(from, to, weight));
    }

    public void removeEdge(Node from, Node to, int weight) {
        removeEdge(new Edge(from.getId(), to.getId(), weight));
    }

    public boolean existsEdge(int u, int v, int weight) {
        return existsEdge(new Edge(u, v, weight));
    }

    public boolean existsEdge(Node u, Node v, int weight) {
        return existsEdge(new Edge(u, v, weight));
    }
}
