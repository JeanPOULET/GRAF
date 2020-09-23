package m1graf2020;

import m1graf2020.Exceptions.NodeAlreadyExist;

public class main {
    public static void main(String args[]) throws NodeAlreadyExist {
        Node n = new Node();
        Node n2 = new Node();
        Node n3 = new Node(3);
        Node n4 = new Node(5);
        Node.removeNode(n2);
        System.out.println(" "+ Node.existsNode(n4));
        System.out.println(" "+ Node.existsNode(n2));

    }
}
