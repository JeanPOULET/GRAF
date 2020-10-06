package m1graf2020;

import m1graf2020.Exceptions.NodeAlreadyExist;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class main {
    public static void main(String args[]) throws NodeAlreadyExist {

        Node n3 = new Node(3);

        Graf g = new Graf();

        System.out.println("Nombre de noeud (expect 0) : " + g.nbNodes());
        g.addNode(1);
        g.addNode(new Node());
        System.out.println("Noeud numéro " + g.getNode(2).getId());
        g.addNode(n3);
        System.out.println("Nombre de noeud (expect 3) : " + g.nbNodes());
        System.out.println("3 existe (expect true): " + g.existsNode(n3));
        System.out.println("Graph :");

        //Affichage map
        for (Node n : g.getNodes()) {
            System.out.print("[" + n.getId() + "]");
        }
        System.out.println();

        g.removeNode(2);

        //Affichage map
        for (Node n : g.getNodes()) {
            System.out.print("[" + n.getId() + "]");
        }
        System.out.println();

        System.out.println("2 existe (expect false): " + g.existsNode(2));

        System.out.println("Poubelle :");
        for (int i : g.getPoubelle()) {
            System.out.println("elem : " + i);
        }

        g.addNode(new Node());

        //Affichage map
        for (Node n : g.getNodes()) {
            System.out.print("[" + n.getId() + "]");
        }
        System.out.println();

        System.out.println("Poubelle :");
        for (int i : g.getPoubelle()) {
            System.out.println("elem : " + i);
        }
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

        for (Node key : g.getMap().keySet()) {
            List<Node> value = g.getMap().get(key);
            System.out.print("[" + key.getId() + "] -> ");
            for (Node vNode : value) {
                System.out.print("{" + vNode.getId() + "}");
            }
            System.out.println();
        }
        Edge e = new Edge(4, 5);
        g.addEdge(e);

        System.out.println();

        System.out.println("graf a le edge : " + g.existsEdge(e));

    }
}
