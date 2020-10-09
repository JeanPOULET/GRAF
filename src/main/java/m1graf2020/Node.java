package m1graf2020;

public class Node implements Comparable<Node> {

    private int id;

    public Node(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof Node)){
            return false;
        }
        Node i = (Node) o;
        return i.getId() == this.id;
    }



    public int getId() {
        return this.id;
    }

    @Override
    public String toString(){
        return id +" ";
    }

    @Override
    public int compareTo(Node o) {
        if (o.getId() == this.id) {
            return 0;
        }
        return o.getId() > this.id ? -1 : 1;
    }
}
