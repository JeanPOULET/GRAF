package m1graf2020;

public class WeightedGraf extends Graf {

    @Override
    public void addEdge(int from, int to, double weight) {
        addEdge(new Edge(from, to, weight));
    }

    @Override
    public void addEdge(Node from, Node to, double weight) {
        addEdge(new Edge(from.getId(), to.getId(), weight));
    }

    @Override
    public void removeEdge(int from, int to, double weight) {
        removeEdge(new Edge(from, to, weight));
    }

    @Override
    public void removeEdge(Node from, Node to, double weight) {
        removeEdge(new Edge(from.getId(), to.getId(), weight));
    }

    @Override
    public boolean existsEdge(int u, int v, double weight) {
        return existsEdge(new Edge(u, v, weight));
    }

    @Override
    public boolean existsEdge(Node u, Node v, double weight) {
        return existsEdge(new Edge(u, v, weight));
    }
}
