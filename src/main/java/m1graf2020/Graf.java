package m1graf2020;

import m1graf2020.Exceptions.NodeAlreadyExist;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

public class Graf {
    private TreeMap<Node, List<Node>> adjList = new TreeMap<>();
    private TreeSet<Integer> poubelle = new TreeSet<>();
    private List<Edge> edges = new ArrayList<>();


    public Graf() {

    }

    public Graf(int... SA) throws NodeAlreadyExist {
        int actualNode = 1;
        addNode(1);
        for (int indice : SA) {
            if (indice != 0) {
                addEdge(actualNode, indice);
            } else {
                addNode(actualNode + 1);
                actualNode++;
            }
        }

    }

    /************************************ FONCTIONS A NOUS ************************************/

    public Set<Node> getNodes() {
        return adjList.keySet();
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public TreeSet<Integer> getPoubelle() {
        return poubelle;
    }

    public TreeMap<Node, List<Node>> getMap() {
        return adjList;
    }

    public int indexToUse() {
        if (poubelle.isEmpty()) {
            return adjList.keySet().size() + 1;
        }
        int i = poubelle.first();
        poubelle.remove(i);
        return i;
    }

    public void printMap() {
        for (Node key : adjList.keySet()) {
            List<Node> value = adjList.get(key);
            System.out.print("[" + key.getId() + "] -> ");
            for (Node vNode : value) {
                System.out.print("{" + vNode.getId() + "}");
            }
            System.out.println();
        }

        for (Edge ed : edges) {
            System.out.print("(" + ed.getFrom() + "," + ed.getTo() + ")  ");
        }

        System.out.println();
    }


    public void printPoubelle() {
        for (int i : poubelle) {
            System.out.println("elem : " + i);
        }
    }

    /************************************ FONCTIONS A PAS NOUS ************************************/

    /****************************************************
     *                       NODES                      *
     ****************************************************/

    public int nbNodes() {
        return adjList.keySet().size();
    }

    public boolean existsNode(Node n) {
        return existsNode(n.getId());
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

    public void addNode() {
        Node n = new Node(indexToUse());
        adjList.put(n, new ArrayList<>());
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
        if (!existsNode(n)) {
            return;
        }
        for (Map.Entry<Node, List<Node>> entry : adjList.entrySet()) {
            entry.getValue().removeIf(node -> node.equals(n));
        }
        edges.removeIf(e -> (e.getFrom() == n.getId() || e.getTo() == n.getId()));

        adjList.keySet().removeIf(e -> (e.getId() == n.getId()));
        poubelle.add(n.getId());
    }

    public void removeNode(int id) {
        if (!existsNode(id)) {
            return;
        }
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
     */
    public List<Node> getSuccessors(int id) {
        for (Node myNode : adjList.keySet()) {
            if (myNode.getId() == id) {
                return adjList.get(myNode);
            }
        }
        return new ArrayList<>();
    }

    public boolean adjacent(Node u, Node v) {
        return existsEdge(u, v);
    }

    public boolean adjacent(int u, int v) {
        return existsEdge(u, v);
    }

    public List<Node> getAllNodes() {
        return new ArrayList<>(adjList.keySet());
    }

    /****************************************************
     *                       EDGES                      *
     ****************************************************/

    public int nbEdges() {
        return edges.size();
    }

    /**
     * Ordre définie entre u et v ?
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

        Node nodeToDelete = null;
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

    public List<Edge> getOutEdges(Node n) {
        List<Edge> le = new ArrayList<>();
        for (Node myNode : adjList.get(getNode(n.getId()))) {
            le.add(new Edge(n.getId(), myNode.getId()));
        }
        return le;
    }

    public List<Edge> getOutEdges(int id) {
        List<Edge> le = new ArrayList<>();
        for (Node myNode : adjList.get(getNode(id))) {
            le.add(new Edge(id, myNode.getId()));
        }
        return le;
    }

    public List<Edge> getInEdges(Node n) {
        List<Edge> le = new ArrayList<>();

        for (Edge e : edges) {
            if (e.getTo() == n.getId()) {
                le.add(e);
            }
        }

        return le;
    }

    public List<Edge> getInEdges(int id) {
        List<Edge> le = new ArrayList<>();

        for (Edge e : edges) {
            if (e.getTo() == id) {
                le.add(e);
            }
        }

        return le;
    }

    public List<Edge> getIncidentEdges(Node n) {
        List<Edge> le = new ArrayList<>();
        le.addAll(getInEdges(n));
        le.addAll(getOutEdges(n));
        return le;
    }

    public List<Edge> getIncidentEdges(int id) {
        List<Edge> le = new ArrayList<>();
        le.addAll(getInEdges(id));
        le.addAll(getOutEdges(id));
        return le;
    }

    public List<Edge> getAllEdges() {
        return edges;
    }

    /****************************************************
     *                      DEGREES                     *
     ****************************************************/

    public int inDegree(Node n){
        return getInEdges(n).size();
    }

    public int inDegree(int id){
        return getInEdges(id).size();
    }

    public int outDegree(Node n){
        return getOutEdges(n).size();
    }

    public int outDegree(int id){
        return getOutEdges(id).size();
    }

    public int degree(Node n){
        return outDegree(n) + inDegree(n);
    }

    public int degree(int id){
        return outDegree(id) + inDegree(id);
    }


}

