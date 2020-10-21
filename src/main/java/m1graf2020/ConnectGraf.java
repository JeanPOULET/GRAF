package m1graf2020;

public class ConnectGraf extends Graf {

    public ConnectGraf() {
        super();
        int rand = 4 + (int) (Math.random() * (20 - 4) + 1);

        for (int i = 1; i < rand; i++) {
            addNode(i);
        }

        int j;
        for (j = 1; j < adjList.keySet().size() - 1; j++) {
            addEdge(j, j + 1);
        }

        for (int k = 1; k < adjList.keySet().size(); k++) {
            int randNbEdge = 0 + (int) (Math.random() * (3 - 0) + 1);
            for (int nbEdge = 0; nbEdge < randNbEdge; nbEdge++) {
                int randNode = 1 + (int) (Math.random() * (adjList.keySet().size() - 1) + 1);
                addEdge(k, randNode);
            }
        }

        addEdge(j + 1, 1);
    }
}
