package maximumflow;

import m1graf2020.Edge;
import m1graf2020.Graf;
import m1graf2020.Node;

import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Flow extends Graf {
    HashMap<PairFlow, Integer> flow = new HashMap<>();

    public HashMap<PairFlow, Integer> getFlow() {
        return flow;
    }

    public void printFlow() {
        for (Map.Entry<PairFlow, Integer> entry : flow.entrySet()) {
            System.out.println("From : " + entry.getKey().getFrom() + " to : " + entry.getKey().getTo() + " flowValue : " + entry.getValue());
        }
    }

    public Flow(String fileName) throws IOException {
        File file = new File(fileName);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufRead = new BufferedReader(fileReader);
        String myLine;
        int maxValue = 1;

        int edgeValue;
        int nodeValue;

        while ((myLine = bufRead.readLine()) != null) {
            if (myLine.isEmpty()) continue;
            if (!myLine.contains("{") && !myLine.contains("}") && !myLine.contains("#") && !myLine.contains("rankdir") && !myLine.contains("Flow")) {
                if (myLine.contains("->")) {

                    String[] line = myLine.trim().split("->");
                    String node = line[0].split("\\[")[0].replaceAll(" ", "");

                    if (node.trim().equals("s")) {
                        nodeValue = 1;

                    } else {
                        nodeValue = 1 + Integer.parseInt(node);
                        if (nodeValue > maxValue) maxValue = nodeValue + 1;
                    }


                }
            }
        }

        bufRead = new BufferedReader(new FileReader(file));

        while ((myLine = bufRead.readLine()) != null) {
            if (myLine.isEmpty()) continue;
            if (!myLine.contains("{") && !myLine.contains("}") && !myLine.contains("#") && !myLine.contains("rankdir") && !myLine.contains("Flow")) {
                if (myLine.contains("->")) {

                    String[] line = myLine.trim().split("->");
                    String edge = line[1].split("\\[")[0].replaceAll(" ", "");
                    String node = line[0].split("\\[")[0].replaceAll(" ", "");

                    System.out.println(edge);
                    System.out.println(node);

                    if (node.trim().equals("s")) {
                        nodeValue = 1;

                    } else {
                        nodeValue = 1 + Integer.parseInt(node);
                    }

                    if (edge.trim().equals("t")) {
                        edgeValue = maxValue + 1;
                    } else {
                        edgeValue = 1 + Integer.parseInt(edge);
                    }


                    if (myLine.contains("/")) {
                        String[] lineComa = Arrays.toString(line).replaceAll("\\[", "")
                                .replaceAll("]", "").replaceAll(", ", "")
                                .replaceAll(";", "").split("label=\"");

                        String[] values = lineComa[1].replaceAll("\"", "").split("/");

                        double weight = Integer.parseInt(values[1]);
                        int flowValue = Integer.parseInt(values[0]);
                        addEdge(nodeValue, edgeValue, weight);

                        flow.put(new PairFlow(nodeValue, edgeValue), flowValue);
                    } else {
                        String[] lineComa = Arrays.toString(line).replaceAll("\\[", "")
                                .replaceAll("]", "").replaceAll(", ", "")
                                .replaceAll(";", "").split("label=\"");
                        double weight = Integer.parseInt(lineComa[1].replaceAll("\"", ""));

                        addEdge(nodeValue, edgeValue, weight);
                        flow.put(new PairFlow(nodeValue, edgeValue), 0);
                    }


                }
            }
        }
        fileReader.close();
    }


    public Flow(int... SA) {
        super(SA);

        adjList.forEach((node, nodes) -> {
            nodes.forEach(edge -> {
                PairFlow pair = new PairFlow(node, edge);
                flow.put(pair, 0);
            });
        });
    }

    public Flow() {

    }

    public void initExampleFlow(){
        addEdge(new Edge(1, 2, 8));
        addEdge(new Edge(1, 3, 6));
        addEdge(new Edge(2, 4, 6));
        addEdge(new Edge(3, 4, 10));
        addEdge(new Edge(3, 5, 7));
        addEdge(new Edge(4, 5, 3));
        addEdge(new Edge(4, 6, 4));
        addEdge(new Edge(5, 6, 6));

        adjList.forEach((node, nodes) -> {
            nodes.forEach(edge -> {
                PairFlow pair = new PairFlow(node, edge);
                PairFlow pairReverse = new PairFlow(edge, node);

                flow.put(pair, 0);
                flow.put(pairReverse, 0);
            });
        });
    }

    @Override
    public String toDotString() {
        return toDotString(1);
    }

    public int getFinalValue(Node finalNode) {
        AtomicInteger value = new AtomicInteger();
        flow.forEach((pair, val) -> {
            if (pair.getTo().equals(finalNode))
                value.addAndGet(val);
        });
        return value.get();
    }

    public int getEdgeFlowValue(Node from, Node to) {
        AtomicInteger edgeFlowValue = new AtomicInteger();
        flow.forEach((pair, val) -> {
            if (pair.getFrom().equals(from) && pair.getTo().equals(to)) {
                edgeFlowValue.set(val);
            }
        });
        return edgeFlowValue.get();
    }

    public void setEdgeFlowValue(Node from, Node to, double value) {
        for (Map.Entry<PairFlow, Integer> entry : flow.entrySet()) {
            if (entry.getKey().equals(new PairFlow(from, to))) {
                entry.setValue((int) value);
            }
        }
    }

    public int getWeight(Node from, Node to) {

        for (Edge edge : edges) {
            if (edge.getFrom() == from.getId() && edge.getTo() == to.getId()) {
                return (int) edge.getWeight();
            }
        }
        return 0;
    }

    public String computeLine(Node key, Node value) {
        String str = "";
        str += (adjList.firstKey().equals(key) ? "s" : (key.getId() - 1)) + (" -> ");
        str += (adjList.lastKey().equals(value) ? "t" : (value.getId() - 1));

        return str;
    }

    public String toDotString(int index) {
        String dot = "# DOT Representation for the graph";
        dot += "\n\n digraph flow" + (index == 1 ? "_init " : index) + "{ \n";
        dot += "\n rankdir=\"LR\"";
        dot += "\n label=\"(" + (index == 1 ? "1) Flow initial. Value : 0" : index + ") Flow induced from residual graph" + (index - 1) + " Value: " + getFinalValue(adjList.lastKey())) + "\";\n";
        for (Map.Entry<Node, List<Node>> entry : adjList.entrySet()) {
            for (Node edgeNode : entry.getValue()) {
                dot += "\t" + computeLine(entry.getKey(), edgeNode);
                int val = getEdgeFlowValue(entry.getKey(), edgeNode);
                dot += " [label=\"" + (val == 0 ? "" : val + "/") + getWeight(entry.getKey(), edgeNode) + "\"];\n";

            }
        }

        dot += "}";

        return dot;
    }

    /**
     * Will write into the specified file the dot representation of the graf
     *
     * @param fileName file to write the dot representation
     * @throws IOException possible I/O exception with file
     */
    public void toDotFile(String fileName, int index) throws IOException {
        File file = new File(fileName);
        FileWriter fWriter = new FileWriter(file);
        fWriter.write(toDotString(index));
        fWriter.close();
    }

    public ResidualGraf createResidualFromFlow() {
        ResidualGraf rg = new ResidualGraf();
        //printFlow();
        for (Node n : adjList.keySet()) {
            rg.addNode(n);
        }

        flow.forEach((pair, flowValue) -> {
            int value = getWeight(pair.getFrom(), pair.getTo()) - flowValue;

            if (value > 0) {
                Edge e = new Edge(pair.getFrom(), pair.getTo(), value);
                rg.addEdge(e);
            }
        });

        return rg;
    }

    public void FordFulkersonAlgorithm(AugmentingType type) throws IOException {
        adjList.forEach((node, nodes) -> {
            nodes.forEach(edge -> {
                PairFlow pair = new PairFlow(node, edge);
                PairFlow pairReverse = new PairFlow(edge, node);

                flow.put(pair, 0);
                flow.put(pairReverse, 0);
            });
        });

        int index = 1;
        this.toDotFile("flow" + index + ".gv", index);
        ResidualGraf rg = createResidualFromFlow();
        rg.toDotFile("residual" + index + ".gv", index);
        index++;

        switch (type) {
            case BFS:

                while (!rg.getAugmentingPathBFS().isEmpty()) {
                    rg.updateFlowFromResidual(this, type);
                    this.toDotFile("flow" + index + ".gv", index);
                    rg = createResidualFromFlow();
                    rg.toDotFile("residual" + index + ".gv", index);
                    index++;
                }
                break;
            default:
                while (!rg.getAugmentingPathDFS().isEmpty()) {
                    rg.updateFlowFromResidual(this, type);
                    this.toDotFile("flow" + index + ".gv", index);
                    rg = createResidualFromFlow();
                    rg.toDotFile("residual" + index + ".gv", index);
                    index++;
                }
                break;
        }


    }

}
