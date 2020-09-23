package m1graf2020;


import m1graf2020.Exceptions.NodeAlreadyExist;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Node {

    private int id;
    static List<Integer> instances = new ArrayList<>();

    public Node(int id) throws NodeAlreadyExist {
        System.out.println(instances);
        if (Node.instances.contains(id)) {
            throw new NodeAlreadyExist();
        }
        this.id = id;
        Node.instances.add(id);
        System.out.println(String.format("id : %d & lgt : %d ", id, Node.instances.size()));
    }

    public Node() {
        this.id = Node.instances.size() + 1;
        Node.instances.add(this.id);
        System.out.println(String.format("id : %d & lgt : %d ", this.id, Node.instances.size()));
    }

    public static void removeNode(Node n) {
        Node.instances.remove((Integer) n.getId());
    }

    public static boolean existsNode(Node n) {
        return Node.instances.contains(n.getId());
    }

    public int getId() {
        return id;
    }

}
