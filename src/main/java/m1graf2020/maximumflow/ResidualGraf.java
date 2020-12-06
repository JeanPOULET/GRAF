package m1graf2020.maximumflow;

import m1graf2020.pw2.Edge;
import m1graf2020.pw2.Graf;
import m1graf2020.pw2.Node;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ResidualGraf extends Graf {

    public ResidualGraf() {
        /*addEdge(1, 2, 8);
        addEdge(1, 3, 6);
        addEdge(2, 4, 6);
        addEdge(3, 4, 10);
        addEdge(3, 5, 7);
        addEdge(4, 5, 3);
        addEdge(4, 6, 4);
        addEdge(5, 6, 6);*/
    }


    public List<Edge> getAugmentingPathBFS() {
        List<Node> nodes = getBFS();

        List<Edge> edges = new ArrayList<>();
        for (int indice = 0; indice < nodes.size() - 1; indice++) {

            Node next = nodes.get(indice + 1);
            edges.add(getEdge(nodes.get(indice), next));
        }
        return edges;
    }

    public List<Edge> getAugmentingPathDFS() {
        List<Node> nodes = getDFS();

        List<Edge> edges = new ArrayList<>();
        for (int indice = 0; indice < nodes.size() - 1; indice++) {
            Node next = nodes.get(indice + 1);
            edges.add(getEdge(nodes.get(indice), next));
        }
        return edges;
    }

    public int getMinimalWeight(List<Edge> edges) {
        int minimalWeight = -1;
        if(!edges.isEmpty()){
            minimalWeight= (int) edges.get(0).getWeight();
            for (Edge e : edges) {
                int actualWeight = (int) e.getWeight();
                if (actualWeight != 0) {
                    if (minimalWeight > actualWeight) {
                        minimalWeight = actualWeight;
                    }
                }
            }
        }
        return minimalWeight;
    }

    public int getWeight(Node from, Node to) {

        for (Edge edge : edges) {
            if (edge.getFrom() == from.getId() && edge.getTo() == to.getId()) {
                return (int) edge.getWeight();
            }
        }
        return 0;
    }

    public Edge getEdge(Node from, Node to) {

        for (Edge edge : edges) {
            if (edge.getFrom() == from.getId() && edge.getTo() == to.getId()) {
                return edge;
            }
        }
        return null;
    }

    /**
     * Will do the BFS traversal of the graph in a recursive way
     *
     * @return list of parcoured nodes in the BFS order
     */
    @Override
    public List<Node> getBFS() {
        List<Node> ls = new ArrayList<>();
        bfs(adjList.firstKey(), ls);
        return ls;
    }

    /**
     * Will do the bfs traversal of the graph
     *
     * @param actualNode actualNode to visit
     * @param ls         list of the parcoured nodes
     */
    @Override
    public void bfs(Node actualNode, List<Node> ls) {
        boolean[] visited = new boolean[256];
        Node[] parents = new Node[adjList.size() + 1];

        LinkedList<Node> queue = new LinkedList<>();

        visited[actualNode.getId()] = true;
        queue.add(actualNode);
        Node firstNode = actualNode;

        while (queue.size() != 0) {
            actualNode = queue.poll();

            for (Node n : adjList.get(actualNode)) {
                if (!visited[n.getId()]) {
                    visited[n.getId()] = true;
                    queue.add(n);
                    parents[n.getId()] = actualNode;
                }
            }
        }


        int index = parents.length - 1;
        ls.add(actualNode);
        while (true) {
            if (parents[index].equals(firstNode)) {
                ls.add(firstNode);
                break;
            }

            Node parent = parents[index];
            if (parent != null) {
                ls.add(parent);
                index = parent.getId();
            } else {
                break;
            }
        }

        Collections.reverse(ls);
    }

    /**
     * Will do the dfs traversal of the graph in a recursive way
     *
     * @return list of parcoured nodes in the DFS order
     */
    public List<Node> getDFS() {

        boolean[] visited = new boolean[256];

        List<Node> nodes = new ArrayList<>();

        Node[] parents = new Node[adjList.size() + 1];
        Node osef = new Node(-1);
        Arrays.fill(parents, osef);

        dfs(adjList.firstKey(), visited, parents);

        int index = parents.length - 1;

        nodes.add(adjList.lastKey());
        while(!parents[index].equals(adjList.firstKey())) {

            Node parent = parents[index];
            if (parent.getId() != -1) {
                nodes.add(parent);
                index = parent.getId();
            } else {
                return new ArrayList<>();
            }
        }

        nodes.add(adjList.firstKey());

        Collections.reverse(nodes);

        return nodes;
    }

    /**
     * Will do the dfs traversal of the graph in a recursive way
     *
     * @param actualNode actualNode to visit
     * @param visited    tab of visited nodes
     * @param parents    list of the parcoured nodes
     */
    public void dfs(Node actualNode, boolean[] visited, Node[] parents) {
        visited[actualNode.getId()] = true;

        List<Node> l = adjList.get(actualNode);

        Collections.sort(l);

        for (Node n : l) {
            if (!visited[n.getId()]) {
                parents[n.getId()] = actualNode;
                dfs(n, visited, parents);
            }
        }
    }

    @Override
    public String toDotString() {
        return toDotString(0);
    }

    public String computeLine(Node key, Node value) {

        String str = "";

        if (adjList.firstKey().equals(key)) {
            str += "s";
        } else if (adjList.lastKey().equals(key)) {
            str += "t";
        } else {
            str += (key.getId() - 1);
        }

        if (value != null) {
            str += " -> ";
        }

        if (value != null && adjList.firstKey().equals(value)) {
            str += "s";
        } else if (value != null && adjList.lastKey().equals(value)) {
            str += "t";
        } else if (value != null) {
            str += (value.getId() - 1);
        }


        return str;
    }

    public String toDotString(int index) {
        List<Edge> parcouredEdges = getAugmentingPathDFS();

        String dot = "# DOT Representation for the graph";
        dot += "\n\n digraph residualGraph" + index + "{ \n";
        dot += "rankdir=\"LR\"\n";
        dot += "label=\"(" + index + ") residual graph.\n";
        dot += "\n Augmenting path : " + (parcouredEdges.isEmpty() ? "none.\n" : "[");

        for (Edge e : parcouredEdges) {
            if (e.getFrom() - 1 == 0) {
                dot += "s";
            } else {
                dot += e.getFrom() - 1;
            }
            dot += ",";

        }
        int minimalWeight = getMinimalWeight(parcouredEdges);
        dot += (parcouredEdges.isEmpty() ? "" : "t]\n");
        dot += (parcouredEdges.isEmpty() ? "Previous flow was maximum.\n" : "Residual capacity: " + minimalWeight + "\";\n");


        for (Map.Entry<Node, List<Node>> entry : adjList.entrySet()) {
            for (Node edgeNode : entry.getValue()) {
                int edgeWeight = getWeight(entry.getKey(), edgeNode);
                dot += "\t" + computeLine(entry.getKey(), edgeNode);
                dot += " [label=\"" + edgeWeight + "\"";
                if (minimalWeight == edgeWeight) {
                    dot += ", fontcolor=\"red\"";
                }
                for (Edge e : parcouredEdges) {
                    if (edgeNode.getId() == e.getTo() && entry.getKey().getId() == e.getFrom()) {
                        dot += ", penwitdh=3, color=\"blue\"";

                    }
                }
                dot += "];\n";

            }
        }

        return dot += "}";
    }

    public void updateFlowFromResidual(Flow f){
        List<Edge> augmentingPath = getAugmentingPathDFS();
        for(Edge e : augmentingPath){
            double value = -(getMinimalWeight(augmentingPath) - f.getEdgeFlowValue(new Node(e.getTo()), new Node(e.getFrom())));

            f.setEdgeFlowValue(new Node(e.getFrom()), new Node(e.getTo()), (getMinimalWeight(augmentingPath) + f.getEdgeFlowValue(new Node(e.getFrom()), new Node(e.getTo()))));
            f.setEdgeFlowValue(new Node(e.getTo()), new Node(e.getFrom()), value);
        }
    }
}
