package m1graf2020;

import m1graf2020.Exceptions.NodeAlreadyExist;

public class main {
    public static void main(String args[]) throws NodeAlreadyExist {

        Node n3 = new Node(3);

        Graf g = new Graf();
        System.out.println(g.nbNodes());
        g.addNode(1);
        g.addNode(new Node());
        System.out.println(g.getNode(2).getId());
        g.addNode(n3);
        System.out.println(g.nbNodes());
        System.out.println(g.existsNode(n3));
        g.removeNode(2);
        System.out.println("2 existe : " + g.existsNode(2));
        for (Node n : g.getNodes()) {
            if (n == null) {
                System.out.println("id : null ");
            } else {
                System.out.println("id : " + n.getId());
            }

        }
        for(int i : g.getPoubelle()){
            System.out.println("elem : "+i);
        }

        g.addNode(new Node());

        for(int i : g.getPoubelle()){
            System.out.println("elem : "+i);
        }


    }
}
