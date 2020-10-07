package m1graf2020;

import m1graf2020.Exceptions.NodeAlreadyExist;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class main {
    public static void main(String args[]) throws NodeAlreadyExist {

        Graf g = new Graf(2, 4, 0, 0, 6, 0, 2, 3, 5, 8, 0, 0, 4, 7, 0, 3, 0, 7, 0);

        g.printMap();


       /* Node n3 = new Node(3);

        Graf g = new Graf();

        System.out.println("Nombre de noeud (expect 0) : " + g.nbNodes());
        g.addNode(1);
        g.addNode();
        g.addEdge(2,1);
        g.addEdge(1,2);

        g.printMap();

        System.out.println("Noeud numéro " + g.getNode(2).getId());
        g.addNode(n3);
        System.out.println("Nombre de noeud (expect 3) : " + g.nbNodes());
        System.out.println("3 existe (expect true): " + g.existsNode(n3));

        g.printMap();

        g.removeNode(2);

        System.out.println("edge de 2 à 1 exists ? expect : false  => " +g.existsEdge(2,1));

        g.printMap();


        System.out.println("2 existe (expect false): " + g.existsNode(2));

        g.addNode();

        g.printMap();

        System.out.println("Poubelle :");

        Graf.printPoubelle();

        Node n4 = new Node(4);
        g.addNode(n4);
        Node n5 = new Node(5);
        g.addNode(n5);
        Node n6 = new Node(6);
        g.addNode(n6);
        Node n7 = new Node(7);
        g.addNode(n7);

        g.addEdge(n3, n4);
        g.addEdge(n3, n5);
        g.addEdge(n3, n6);
        g.addEdge(n3, n7);

        g.printMap();

        Edge e = new Edge(4, 2);
        g.addEdge(e);

        System.out.println("graf a le edge : " + g.existsEdge(e));

        g.printMap();

        List<Node> ln = g.getSuccessors(n3);
        System.out.print("Successors (expect 4, 5, 6, 7): ");
        for (Node key : ln){
            System.out.print("{" + key.getId() + "}");
        }
        System.out.println();

        List<Node> ln2 = g.getSuccessors(3);
        System.out.print("Successors2 (expect 4, 5, 6, 7): ");
        for (Node key : ln2){
            System.out.print("{" + key.getId() + "}");
        }
        System.out.println();

        System.out.println("Adjacent ? (expect true, false true, false): " + g.adjacent(n3, n4) + " / " + g.adjacent(n5, n4) + " / " + g.adjacent(4, 2) + " / " + g.adjacent(1, 5));

        System.out.print("Liste de tous les noeuds (expect 1, 2, ..., 7): ");
        List<Node> allNode = g.getAllNodes();
        for (Node key : allNode){
            System.out.print(key.getId() + ", ");
        }
        System.out.println();

        System.out.println("Nombre de lien (expect 5): " + g.nbEdges());


        for (Node key : g.getMap().keySet()) {
            List<Node> value = g.getMap().get(key);
            System.out.print("[" + key.getId() + "] -> ");
            for (Node vNode : value) {
                System.out.print("{" + vNode.getId() + "}");
            }
            System.out.println();
        }
        System.out.println();

        g.removeEdge(n3, n4);

        for (Node key : g.getMap().keySet()) {
            List<Node> value = g.getMap().get(key);
            System.out.print("[" + key.getId() + "] -> ");
            for (Node vNode : value) {
                System.out.print("{" + vNode.getId() + "}");
            }
            System.out.println();
        }

        for (Edge ed : g.getEdges()){
            System.out.print("(" + ed.getFrom() + "," + ed.getTo() + ")  " );
        }

        System.out.println();

        g.removeEdge(3, 5);

        for (Node key : g.getMap().keySet()) {
            List<Node> value = g.getMap().get(key);
            System.out.print("[" + key.getId() + "] -> ");
            for (Node vNode : value) {
                System.out.print("{" + vNode.getId() + "}");
            }
            System.out.println();
        }

        for (Edge ed : g.getEdges()){
            System.out.print("(" + ed.getFrom() + "," + ed.getTo() + ")  " );
        }

        System.out.println();

        g.removeEdge(e);

        System.out.println("edge exits ? expect false : " + g.existsEdge(e) );

        for (Node key : g.getMap().keySet()) {
            List<Node> value = g.getMap().get(key);
            System.out.print("[" + key.getId() + "] -> ");
            for (Node vNode : value) {
                System.out.print("{" + vNode.getId() + "}");
            }
            System.out.println();
        }

        for (Edge ed : g.getEdges()){
            System.out.print("(" + ed.getFrom() + "," + ed.getTo() + ")  " );
        }

        System.out.println();*/


    }
}
