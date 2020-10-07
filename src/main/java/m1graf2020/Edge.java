package m1graf2020;

public class Edge implements Comparable<Edge> {
    private int from;
    private int to;

    public Edge(int from, int to) {
        this.from = from;
        this.to = to;
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
            return o.getTo() > this.to ? 1 : -1;

        }
        return o.getFrom() > this.from ? 1 : -1;
    }
}
