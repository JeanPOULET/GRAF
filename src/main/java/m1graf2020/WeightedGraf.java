package m1graf2020;

import java.io.*;
import java.util.*;

public class WeightedGraf extends Graf {

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
                    System.out.println(Double.parseDouble(lineComa[1]
                            .split("label=\"")[1].replace("\"","")));
                    addEdge(actualNode, Integer.parseInt(edge.replaceAll(";", "").trim()), Double.parseDouble(lineComa[1]
                            .split("label=\"")[1].replace("\"","")));

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


    public void addEdge(int from, int to, double weight) {
        addEdge(new Edge(from, to, weight));
    }

    public void addEdge(Node from, Node to, double weight) {
        addEdge(new Edge(from.getId(), to.getId(), weight));
    }

    /**
     * the dot representation in a string of the graf
     *
     * @return the dot representation in a string of the graf
     */
    public String toDotString() {
        StringBuilder dot = new StringBuilder("# DOT Representation for the graph");
        dot.append("\n\n digraph graf {\n");

        for (Map.Entry<Node, List<Node>> entry : adjList.entrySet()) {
            if (entry.getValue().isEmpty()) {
                dot.append("\t").append(entry.getKey()).append(";\n");
            }
            List<Edge> edges = getOutEdges(entry.getKey());

            for (Edge edgeNode : edges) {
                dot.append("\t").append(entry.getKey()).append(" -> ").append(edgeNode.getTo()).append("[label=")
                        .append(edgeNode.getWeight()).append(",weight=").append(edgeNode.getWeight()).append("];\n");
            }
        }

        dot.append("}");

        return dot.toString();
    }

}
