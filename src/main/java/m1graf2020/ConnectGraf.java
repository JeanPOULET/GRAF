package m1graf2020;

public class ConnectGraf extends Graf {

    /**
     * This constructor build a random connected graph with randomely 4 or 20 nodes which are automaticaly
     * linked to at least one other node. Like this all the nodes are connected
     */
    public ConnectGraf() {
        super();
        int rand = 4 + (int) (Math.random() * (20 - 4) + 1);

        for (int i = 0; i < rand; i++) {
            addNode(i + 1);
        }
        int j;
        for (j = 1; j < adjList.keySet().size(); j++) {
            addEdge(j, j + 1);
        }

        addEdge(j, 1);

        for (Node myNode : adjList.keySet()) {
            int randNbEdge = 0 + (int) (Math.random() * (3 - 0) + 1);
            for (int nbEdge = 0; nbEdge < randNbEdge; nbEdge++) {
                int randNode = 1 + (int) (Math.random() * (adjList.keySet().size() - 1) + 1);
                addEdge(myNode.getId(), randNode);
            }
        }


    }
}
