package m1graf2020.maximumflow;

import m1graf2020.pw2.Edge;
import m1graf2020.pw2.Graf;
import m1graf2020.pw2.Node;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Flow extends Graf {
    HashMap<PairFlow, Integer> flow = new HashMap<>();

    public HashMap<PairFlow, Integer> getFlow() {
        return flow;
    }

    public void printFlow(){
        for(Map.Entry<PairFlow, Integer> entry : flow.entrySet()){
            System.out.println("From : " + entry.getKey().getFrom() + " to : " + entry.getKey().getTo() + " flowValue : " + entry.getValue());
        }
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
        addEdge(new Edge(1, 2, 8));
        addEdge(new Edge(1, 3, 6));
        addEdge(new Edge(2, 4, 6));
        addEdge(new Edge(3, 4, 10));
        addEdge(new Edge(3, 5, 7));
        addEdge(new Edge(4, 5, 3));
        addEdge(new Edge(4, 6, 4));
        addEdge(new Edge(5, 6, 6));
        /*addEdge(1, 2, 16);
        addEdge(1, 3, 13);
        addEdge(2, 3, 10);
        addEdge(2, 4, 12);
        addEdge(3, 2, 4);
        addEdge(3, 5, 14);
        addEdge(4, 3, 9);
        addEdge(4, 6, 20);
        addEdge(5, 4, 7);
        addEdge(5, 6, 4);*/


        adjList.forEach((node, nodes) -> {
            nodes.forEach(edge -> {
                PairFlow pair = new PairFlow(node, edge);
                PairFlow pairReverse = new PairFlow(edge, node);

                flow.put(pair, 0);
                flow.put(pairReverse, 0);
            });
        });
       /*setEdgeFlowValue(new Node(1), new Node(2), 3);
       setEdgeFlowValue(new Node(2), new Node(1), -3);
       setEdgeFlowValue(new Node(2), new Node(4), 3);
       setEdgeFlowValue(new Node(4), new Node(2), -3);
       setEdgeFlowValue(new Node(4), new Node(5), 3);
       setEdgeFlowValue(new Node(5), new Node(4), -3);
       setEdgeFlowValue(new Node(5), new Node(6), 3);
       setEdgeFlowValue(new Node(6), new Node(5), -3);*/

    }

    @Override
    public String toDotString() {
        return toDotString(0);
    }

    public int getFinalValue(Node finalNode) {
        AtomicInteger value = new AtomicInteger();
        flow.forEach((pair, val) -> {
            if (pair.getFrom().equals(finalNode))
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
        for(Map.Entry<PairFlow, Integer> entry : flow.entrySet()){
            if(entry.getKey().equals(new PairFlow(from, to))){
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
        dot += "\n\n digraph flow" + (index == 0 ? "_init {" : index) + " \n";
        dot += "\n rankdir=\"LR\"";
        dot += "\n label=\"(" + (index == 0 ? "1) Flow initial. Value : 0" : index + ") Flow induced from residual graph" + (index - 1) + " Value: " + getFinalValue(adjList.lastKey())) + "\";\n";
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

    public ResidualGraf createResidualFromFlow(){
        ResidualGraf rg = new ResidualGraf();
        //printFlow();
        for(Node n : adjList.keySet()){
            rg.addNode(n);
        }

        flow.forEach((pair, flowValue) -> {
            int value = getWeight(pair.getFrom(), pair.getTo()) - flowValue;

            if(value > 0) {
                Edge e = new Edge(pair.getFrom(), pair.getTo(), value);
                rg.addEdge(e);
            }
        });

        return rg;
    }

    public void FordFulkersonAlgorithm(){
        ResidualGraf rg = createResidualFromFlow();
        while(!rg.getAugmentingPathDFS().isEmpty()){
            rg.updateFlowFromResidual(this);
            rg = createResidualFromFlow();
            System.out.println(rg.toDotString());
        }
    }

}
