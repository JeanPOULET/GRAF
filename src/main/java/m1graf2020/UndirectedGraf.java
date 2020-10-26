package m1graf2020;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class UndirectedGraf extends Graf {

    public UndirectedGraf(int... SA) {
        super(SA);
    }

    /**
     * Will create an undirectedGraf from a dot file
     *
     * @param fileName File to parse
     * @throws IOException
     */
    public UndirectedGraf(String fileName) throws IOException {

        File file = new File(fileName);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufRead = new BufferedReader(fileReader);
        String myLine;
        int actualNode = 0;

        while ((myLine = bufRead.readLine()) != null) {
            if (myLine.isEmpty()) continue;
            if (!myLine.contains("{") && !myLine.contains("}") && !myLine.contains("#")) {
                if (myLine.contains("--")) {
                    String[] line = myLine.trim().split("--");
                    if (actualNode != Integer.parseInt(line[0].trim())) {
                        actualNode++;
                        addNode(actualNode);
                    }
                    addEdge(actualNode, Integer.parseInt(line[1].replaceAll(";", "").trim()));


                } else {
                    String line = myLine.replaceAll(";", "").trim();
                    if (actualNode != Integer.parseInt(line)) {
                        actualNode++;
                        addNode(actualNode);
                    }
                }
            }
        }
        fileReader.close();
    }

    @Override
    public boolean existsEdge(Edge e) {
        Edge reverse = new Edge(e.getTo(), e.getFrom());
        return edges.stream().anyMatch(edge -> edge.equals(e) || edge.equals(reverse));
    }

    /**
     * Give a list of all edges that get out a node n
     *
     * @param n node to get its out edges
     * @return a list of all edges that get out a node n
     */
    @Override
    public List<Edge> getOutEdges(Node n) {
        return getOutEdges(n.getId());
    }

    /**
     * Give a list of all edges that get out a node with a given id
     *
     * @param id of a node to get its out edges
     * @return a list of all edges that get out a node with a given id
     */
    @Override
    public List<Edge> getOutEdges(int id) {
        List<Edge> outEdges = new ArrayList<>();
        edges.forEach(edge -> {
            if (edge.getFrom() == id) {
                outEdges.add(edge);
            } else if (edge.getTo() == id) {
                outEdges.add(new Edge(edge.getTo(), edge.getFrom()));
            }
        });
        return outEdges;
    }

    /**
     * Give a list of all edges that get in a node n
     *
     * @param n node to get its in edges
     * @return Give a list of all edges that get in a node
     */
    @Override
    public List<Edge> getInEdges(Node n) {
        return getInEdges(n.getId());
    }

    /**
     * Give a list of all edges that get in a node with a given id
     *
     * @param id of a node to get its in edges
     * @return Give a list of all edges that get in a node with a given id
     */
    @Override
    public List<Edge> getInEdges(int id) {
        List<Edge> outEdges = new ArrayList<>();
        edges.forEach(edge -> {
            if (edge.getTo() == id) {
                outEdges.add(edge);
            } else if (edge.getFrom() == id) {
                outEdges.add(new Edge(edge.getTo(), edge.getFrom()));
            }
        });
        return outEdges;
    }

    /**
     * Give the list of all the successor of a node n
     *
     * @param n node to get its successors
     * @return a list with all the successor of a given node
     */
    @Override
    public List<Node> getSuccessors(Node n) {
        return getInEdges(n).stream().map(edge -> new Node(edge.getFrom())).collect(Collectors.toList());
    }

    /**
     * Give the list of all the successor of a node associate to a given id
     *
     * @param id of the node to get its successors
     * @return a list with all the successor of a given node
     */
    @Override
    public List<Node> getSuccessors(int id) {
        return getSuccessors(new Node(id));
    }

    /**
     * Will do the dfs traversal of the graph in a recursive way
     *
     * @return list of parcoured nodes in the DFS order
     */
    public List<Node> getDFS() {
        boolean[] visited = new boolean[256];
        List<Node> ls = new ArrayList<>();

        ls.add(adjList.firstKey());
        dfs(adjList.firstKey(), visited, ls);
        return ls;
    }

    /**
     * Will do the dfs traversal of the graph in a recursive way
     *
     * @param actualNode actualNode to visit
     * @param visited    tab of visited nodes
     * @param ls         list of the parcoured nodes
     */
    @Override
    public void dfs(Node actualNode, boolean[] visited, List<Node> ls) {
        visited[actualNode.getId()] = true;

        for (Node n : getSuccessors(actualNode)) {
            if (!visited[n.getId()]) {
                ls.add(n);
                dfs(n, visited, ls);
            }
        }
    }

    /**
     * Will do the BFS traversal of the graph in a recursive way
     *
     * @return list of parcoured nodes in the BFS order
     */
    @Override
    public List<Node> getBFS() {
        List<Node> ls = new ArrayList<>();
        ls.add(adjList.firstKey());
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

        LinkedList<Node> queue = new LinkedList<>();

        visited[actualNode.getId()] = true;
        queue.add(actualNode);

        while (queue.size() != 0) {
            actualNode = queue.poll();

            for (Node n : getSuccessors(actualNode)) {
                if (!visited[n.getId()]) {
                    visited[n.getId()] = true;
                    queue.add(n);
                    ls.add(n);
                }
            }
        }
    }

    @Override
    public String toDotString() {
        StringBuilder dot = new StringBuilder("# DOT Representation for the graph");
        dot.append("\n\n graph {\n");

        for (Map.Entry<Node, List<Node>> entry : adjList.entrySet()) {
            if (entry.getValue().isEmpty()) {
                dot.append("\t").append(entry.getKey()).append(";\n");
            }
            for (Node edgeNode : entry.getValue()) {
                dot.append("\t").append(entry.getKey()).append(" -- ").append(edgeNode.getId()).append(";\n");
            }
        }

        dot.append("}");

        return dot.toString();
    }

}
