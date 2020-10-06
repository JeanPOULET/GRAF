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

        Node toRemove = new Node();
        for (Node myNode : adjList.keySet()) {
            if (myNode.equals(n)) {
                toRemove = myNode;
            }
        }
        adjList.remove(toRemove);
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

        Node toRemove = new Node();
        for (Node myNode : adjList.keySet()) {
            if (myNode.getId() == id) {
               toRemove = myNode;
            }
        }
        adjList.remove(toRemove);
        poubelle.add(id);
    }

    public List<Node> getSuccessors(Node n){
        return adjList.get(n);
    }

    public Set<Node> getNodes() {
        return adjList.keySet();
    }

    public TreeSet<Integer> getPoubelle() {
        return poubelle;
    }

    public HashMap<Node, List<Node>> getMap(){
        return adjList;
    }


    public static int indexToUse() {
        if (poubelle.isEmpty()) {
            return adjList.keySet().size()+1;
        }
        int i = poubelle.first();
        poubelle.remove(i);
        return i;
    }

    public void addEdge(Node from, Node to) {
        for(Node key : adjList.keySet()) {
            if(key.equals(from)){
                adjList.get(key).add(to);
            }
        }
//        List<Node> nodeDestination = new ArrayList<>();
//        nodeDestination.add(to);
//        adjList.put(from, nodeDestination);
    }

}

