package m1graf2020.maximumflow;

import javafx.util.Pair;
import m1graf2020.pw2.Edge;
import m1graf2020.pw2.Graf;
import m1graf2020.pw2.Node;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Flow extends Graf {
    HashMap<PairFlow, Integer> flow = new HashMap<>();

    public HashMap<PairFlow, Integer> getFlow() {
        return flow;
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
        addEdge(new Edge(1, 2, 5));
        addEdge(new Edge(1, 3, 8));
        addEdge(new Edge(2, 3, 6));
        addEdge(new Edge(2, 2, 10));
        addEdge(new Edge(3, 4, 3));

        adjList.forEach((node, nodes) -> {
            nodes.forEach(edge -> {
                PairFlow pair = new PairFlow(node, edge);
                flow.put(pair, 0);
            });

        });
        setEdgeFlowValue(new Node(1), new Node(2), 5);
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
        flow.put(new PairFlow(from, to), (int) value);
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
        dot += "\n\n digraph flow" + (index == 0 ? "_init" : index) + " \n";
        dot += "\n rankdir=\"LR\"";
        dot += "\n label=\"(" + (index == 0 ? "1) Flow initial. Value : 0" : index + ") Flow induced from residual graph" + (index - 1) + " Value: " + getFinalValue(adjList.lastKey())) + "\n";
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


}
