package m1graf2020;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class WeightedGraf extends Graf {

    public WeightedGraf() {

    }

    /**
     * Will parse a dot file and create a weighted graf based on it
     *
     * @param fileName file to parse
     * @throws IOException
     */
    public WeightedGraf(String fileName) throws IOException {
        File file = new File(fileName);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufRead = new BufferedReader(fileReader);
        String myLine;
        int actualNode = 0;

        while ((myLine = bufRead.readLine()) != null) {
            if (myLine.isEmpty()) continue;
            if (!myLine.contains("{") && !myLine.contains("}") && !myLine.contains("#")) {
                if (myLine.contains("->")) {

                    String[] line = myLine.trim().split("->");
                    String edge = line[1].split("\\[")[0];
                    if (actualNode != Integer.parseInt(line[0].trim())) {
                        actualNode++;
                        addNode(actualNode);
                    }

                    String[] lineComa = Arrays.toString(line).trim().replace("[", "").replace("]", "").split(",");
                    double weight = Double.parseDouble(lineComa[1]
                            .split("label=\"")[1].replace("\"", ""));
                    addEdge(actualNode, Integer.parseInt(edge.replaceAll(";", "").trim()), weight);

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
    public void addEdge(int from, int to, double weight) {
        addEdge(new Edge(from, to, weight));
    }


    @Override
    public void addEdge(Node from, Node to, double weight) {
        addEdge(new Edge(from.getId(), to.getId(), weight));
    }

    @Override
    public void addEdge(Edge ed) {
        if (!existsNode(ed.getTo())) {
            addNode(ed.getTo());
        }
        if (!existsNode(ed.getFrom())) {
            addNode(ed.getFrom());
        }

        for (Node n : adjList.keySet()) {
            if (n.getId() == ed.getFrom()) {
                adjList.get(n).add(new Node(ed.getTo()));
                edges.add(ed);
                Collections.sort(edges);
                Collections.sort(adjList.get(n));
            }
        }
    }

    /**
     * the dot representation in a string of the graf
     *
     * @return the dot representation in a string of the graf
     */
    @Override
    public String toDotString() {
        StringBuilder dot = new StringBuilder("# DOT Representation for the graph");
        dot.append("\n\n digraph graf {\n");

        for (Map.Entry<Node, List<Node>> entry : adjList.entrySet()) {
            if (entry.getValue().isEmpty()) {
                dot.append("\t").append(entry.getKey()).append(";\n");
            }
            List<Edge> edges = getOutEdges(entry.getKey());

            for (Edge edgeNode : edges) {
                System.out.println("f :" + edgeNode.getWeight());
                dot.append("\t").append(entry.getKey()).append(" -> ").append(edgeNode.getTo()).append("[label=")
                        .append(edgeNode.getWeight()).append(",weight=").append(edgeNode.getWeight()).append("];\n");
            }
        }

        dot.append("}");

        return dot.toString();
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

    /**
     * Give a list of all edges that get out a node n
     *
     * @param n node to get its out edges
     * @return a list of all edges that get out a node n
     */
    public List<Edge> getOutEdges(Node n) {
        return edges.stream().filter(e -> n.getId() == e.getFrom()).collect(Collectors.toList());
    }

    /**
     * Give a list of all edges that get out a node with a given id
     *
     * @param id of a node to get its out edges
     * @return a list of all edges that get out a node with a given id
     */
    public List<Edge> getOutEdges(int id) {
        return edges.stream().filter(e -> id == e.getFrom()).collect(Collectors.toList());
    }

    /**
     * Give a list of all edges that get in a node n
     *
     * @param n node to get its in edges
     * @return Give a list of all edges that get in a node
     */
    public List<Edge> getInEdges(Node n) {
        return edges.stream().filter(e -> n.getId() == e.getTo()).collect(Collectors.toList());
    }

    /**
     * Give a list of all edges that get in a node with a given id
     *
     * @param id of a node to get its in edges
     * @return Give a list of all edges that get in a node with a given id
     */
    public List<Edge> getInEdges(int id) {
        return edges.stream().filter(e -> id == e.getTo()).collect(Collectors.toList());
    }
}
