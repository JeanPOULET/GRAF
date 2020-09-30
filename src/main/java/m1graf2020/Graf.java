package m1graf2020;

import m1graf2020.Exceptions.NodeAlreadyExist;

import java.util.*;

public class Graf {
    private static HashMap<Node, List<Node>> adjList = new HashMap<>();
    private static TreeSet<Integer> poubelle = new TreeSet<>();


    public Graf() {

    }

    public Graf(int... SA) {

    }


    public static int nbNodes() {
        return adjList.keySet().size();
    }

    public boolean existsNode(Node n) {
        return adjList.keySet().contains(n);
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

    public void addNode(Node n) throws NodeAlreadyExist {
        if (existsNode(n)) {
            throw new NodeAlreadyExist();
        }
        adjList.put(n, new ArrayList<Node>());
    }

    public void addNode(int id) throws NodeAlreadyExist {
        if (existsNode(id)) {
            throw new NodeAlreadyExist();
        }
        adjList.put(new Node(id), new ArrayList<Node>());
    }

    public void removeNode(Node n) {
        for (List<Node> adjLs : adjList.values()) {
            for (Node adjNode : adjLs) {
                if (adjNode.equals(n)) {
                    adjLs.remove(adjNode);
                }
            }
        }

        for (Node myNode : adjList.keySet()) {
            if (myNode.equals(n)) {
                adjList.remove(myNode);
            }
        }
        poubelle.add(n.getId());

    }

    public void removeNode(int id) {
       /* for (List<Node> adjLs : adjList.values()) {
            for (Node adjNode : adjLs) {
                if (adjNode.getId() == id) {
                    adjLs.remove(adjNode);
                }
            }
        }*/

        for (Node myNode : adjList.keySet()) {
            if (myNode.getId() == id) {
                adjList.remove(myNode);
            }
        }
        poubelle.add(id);
    }

    public Set<Node> getNodes() {
        return adjList.keySet();
    }

    public TreeSet<Integer> getPoubelle() {
        return poubelle;
    }

    public static int indexToUse() {
        if (poubelle.isEmpty()) {
            return adjList.keySet().size()+1;
        }
        int i = poubelle.first();
        poubelle.remove(i);
        return i;
    }


}

