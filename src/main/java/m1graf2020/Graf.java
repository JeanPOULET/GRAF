package m1graf2020;

import m1graf2020.Exceptions.NodeAlreadyExist;

import java.lang.reflect.Array;
import java.util.*;

public class Graf {
    private static HashMap<Node, List<Node>> adjList = new HashMap<>();
    private static TreeSet<Integer> poubelle = new TreeSet<>();
    private static List<Edge> edges = new ArrayList<>();


    public Graf() {

    }

    public Graf(int... SA) {

    }

    /************************************ FONCTIONS A NOUS ************************************/

    public Set<Node> getNodes() {
        return adjList.keySet();
    }

    public TreeSet<Integer> getPoubelle() {
        return poubelle;
    }

    public HashMap<Node, List<Node>> getMap() {
        return adjList;
    }

    public static int indexToUse() {
        if (poubelle.isEmpty()) {
            return adjList.keySet().size() + 1;
        }
        int i = poubelle.first();
        poubelle.remove(i);
        return i;
    }

    /************************************ FONCTIONS A PAS NOUS ************************************/

    public static int nbNodes() {
        return adjList.keySet().size();
    }

    public boolean existsNode(Node n) {
        return adjList.containsKey(n);
    }

    public boolean existsNode(int id) {
        for (Node adjNode : adjList.keySet()) {
            if (adjNode.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public Node getNode(int id) {
        for (Node adjNode : adjList.keySet()) {
            if (adjNode.getId() == id) {
                return adjNode;
            }
        }
        return null;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void addNode(Node n) throws NodeAlreadyExist {
        if (existsNode(n)) {
            throw new NodeAlreadyExist();
        }
        adjList.put(n, new ArrayList<>());
    }

    public void addNode(int id) throws NodeAlreadyExist {
        if (existsNode(id)) {
            throw new NodeAlreadyExist();
        }
        adjList.put(new Node(id), new ArrayList<>());
    }

    public void removeNode(Node n) {
        for (Map.Entry<Node, List<Node>> entry : adjList.entrySet()) {
            entry.getValue().removeIf(node -> node.equals(n));
        }
        edges.removeIf(e -> (e.getFrom() == n.getId() || e.getTo() == n.getId()));

        adjList.keySet().removeIf(e -> (e.getId() == n.getId()));
        poubelle.add(n.getId());
    }

    public void removeNode(int id) {
        Node toRemove = new Node(id);
        edges.removeIf(e -> (e.getFrom() == id || e.getTo() == id));

        for (Map.Entry<Node, List<Node>> entry : adjList.entrySet()) {
            entry.getValue().removeIf(node -> node.equals(toRemove));
        }

        adjList.keySet().removeIf(e -> (e.getId() == id));
        poubelle.add(id);
    }

    public List<Node> getSuccessors(Node n) {
        return adjList.get(n);
    }

    /**
     * Si inexistant ???
     *
     * @param id
     * @return
     */
    public List<Node> getSuccessors(int id) {
        for (Node myNode : adjList.keySet()) {
            if (myNode.getId() == id) {
                return adjList.get(myNode);
            }
        }
        return adjList.get(new Node(id));
    }

    public boolean adjacent(Node u, Node v) {
        return existsEdge(u, v);
    }

    public boolean adjacent(int u, int v) {
        return existsEdge(u, v);
    }

    public List<Node> getAllNodes() {
        List<Node> ln = new ArrayList<>(adjList.keySet());
        return ln;
    }

    public int nbEdges() {
        return edges.size();
    }

    /**
     * Ordre définie entre u et v ?
     *
     * @param u
     * @param v
     * @return
     */
    public boolean existsEdge(Node u, Node v) {
        for (Edge e : edges) {
            if (u.getId() == e.getFrom() && v.getId() == e.getTo()
                    || u.getId() == e.getTo() && v.getId() == e.getFrom()) {
                return true;
            }
        }
        return false;
    }

    public boolean existsEdge(int u, int v) {
        for (Edge e : edges) {
            if (u == e.getFrom() && v == e.getTo()
                    || u == e.getTo() && v == e.getFrom()) {
                return true;
            }
        }
        return false;
    }

    public boolean existsEdge(Edge e) {
        return edges.contains(e);
    }

    public void addEdge(Node from, Node to) {
        Edge ed = new Edge(from.getId(), to.getId());
        if (!edges.contains(ed)) {
            adjList.get(from).add(to);
            edges.add(new Edge(from.getId(), to.getId()));
        }
    }

    public void addEdge(int from, int to) {
        Edge ed = new Edge(from, to);
        if (!edges.contains(ed)) {
            for (Node n : adjList.keySet()) {
                if (n.getId() == ed.getFrom()) {
                    adjList.get(n).add(new Node(ed.getTo()));
                    edges.add(ed);
                }
            }
        }
    }

    public void addEdge(Edge ed) {
        if (!edges.contains(ed)) {
            for (Node n : adjList.keySet()) {
                if (n.getId() == ed.getFrom()) {
                    adjList.get(n).add(new Node(ed.getTo()));
                    edges.add(ed);
                }
            }
        }
    }

    public void removeEdge(Node from, Node to) {
        edges.removeIf(e -> (e.getFrom() == from.getId() || e.getTo() == to.getId()));

        adjList.get(from).remove(to);
    }

    public void removeEdge(int from, int to) {
        edges.removeIf(e -> (e.getFrom() == from || e.getTo() == to));

        Node nodeToDelete = new Node();
        for (Node myNode : adjList.keySet()) {
            if (myNode.getId() == from) {
                nodeToDelete = myNode;
            }
        }

        adjList.get(nodeToDelete).remove(new Node(to));
    }

    public void removeEdge(Edge e) {
        edges.remove(e);

        Node nodeToDelete = new Node(e.getTo());

        for (Map.Entry<Node, List<Node>> entry : adjList.entrySet()) {
            entry.getValue().removeIf(node -> node.equals(nodeToDelete));
        }
    }


}

