package m1graf2020;

public class GrafParameterized extends Graf {

    /**
     * This constructor build a random parameterized graph taking in parameter the number of nodes, the number of edges and the dentisy
     * @param nbNode the number of node for the graph
     * @param nbEdge the number of node for the graph
     * @param density the graph density, it represent the probability to have a link between 2 nodes
     */
    public GrafParameterized(int nbNode, int nbEdge, int density) {

        for (int i = 0; i < nbNode; i++) {
            addNode(i + 1);
        }

        int rand1;
        int rand2;

        while (nbEdge > 0) {
            rand1 = 1 + (int) (Math.random() * ((adjList.keySet().size()) - 1) + 1);
            rand2 = 1 + (int) (Math.random() * ((adjList.keySet().size()) - 1) + 1);

            int createOrNot = 0 + (int) (Math.random() * (100 - 0) + 1);

            if (createOrNot <= density) {
                addEdge(rand1, rand2);
                nbEdge--;
            }

        }
    }
}
