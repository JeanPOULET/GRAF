package m1graf2020;

public class Edge implements Comparable<Edge> {
    private int from;
    private int to;

    public Edge(){

    }

    public Edge(int from, int to) {
        this.from = from;
        this.to = to;
    }

    public Edge(Node from, Node to) {
        this.from = from.getId();
        this.to = to.getId();
    }

    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }

    @Override
    public int compareTo(Edge o) {
        if (o.getFrom() == this.from) {
            if (o.getTo() == this.to) {
                return 0;
            }
            return o.getTo() > this.to ? -1 : 1;

        }
        return o.getFrom() > this.from ? -1 : 1;
    }

    @Override
    public boolean equals(Object o){
        Edge e = (Edge)o;
        return e.from == this.from && e.to == this.to;
    }
}
