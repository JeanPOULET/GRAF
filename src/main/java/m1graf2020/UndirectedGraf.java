package m1graf2020;

import java.io.IOException;
import java.util.Collections;

public class UndirectedGraf extends Graf {



    /**
     * Add an edge to the map and to the list of edges the accosiate nodes doesn't exist they are create
     * @param ed edge to add
     */
    public void addEdge(Edge ed) {
        Edge fromTo = new Edge(ed.getFrom(),ed.getTo());
        Edge toFrom = new Edge(ed.getTo(),ed.getFrom());

        if (!existsNode(ed.getTo())) {
            addNode(ed.getTo());
        }
        if (!existsNode(ed.getFrom())) {
            addNode(ed.getFrom());
        }

        for (Node n : this.adjList.keySet()) {
            if (n.getId() == ed.getFrom()) {
                adjList.get(n).add(new Node(ed.getTo()));
                edges.add(fromTo);
                Collections.sort(adjList.get(n));
            }
            if(n.getId() == ed.getTo()){
                adjList.get(n).add(new Node(ed.getFrom()));
                edges.add(toFrom);
                Collections.sort(adjList.get(n));
            }
        }
        Collections.sort(edges);

    }

}
