package maximumflow;

import m1graf2020.Node;

public class PairFlow implements Comparable<PairFlow> {

    private Node from;
    private Node to;

    public PairFlow(Node from, Node to) {
        this.from = from;
        this.to = to;
    }

    public PairFlow(int from, int to) {
        this.from = new Node(from);
        this.to = new Node(to);
    }

    public Node getFrom() {
        return from;
    }

    public Node getTo() {
        return to;
    }

    public void setFrom(Node from) {
        this.from = from;
    }

    public void setTo(Node to) {
        this.to = to;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof PairFlow)) return false;

        PairFlow pf = (PairFlow) obj;

        return from.equals(pf.getFrom()) && to.equals(pf.getTo());
    }

    @Override
    public int compareTo(PairFlow o) {
        return from.compareTo(o.getFrom()) - to.compareTo(o.getTo());
    }
}
