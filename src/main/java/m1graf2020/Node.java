package m1graf2020;

public class Node implements Comparable<Node> {

    private int id;

    public Node(int id) {
        this.id = id;
    }

    public Node() {
        this.id = Graf.indexToUse();
    }

    @Override
    public boolean equals(Object o) {
        Node i = (Node) o;
        return i.getId() == this.id;
    }



    public int getId() {
        return this.id;
    }

    @Override
    public int compareTo(Node o) {
        if (o.getId() == this.id) {
            return 0;
        }
        return o.getId() > this.id ? 1 : -1;
    }
}
