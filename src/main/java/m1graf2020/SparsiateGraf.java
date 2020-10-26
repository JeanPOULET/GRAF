package m1graf2020;

public class SparsiateGraf extends Graf {

    /**
     * This constructor build a random sparse graph with randomely 4 or 20 nodes and a density between 2% and 20%.
     * It means that two nodes have between 2% and 20% chance to be linked
     */
    public SparsiateGraf() {
        super();
        int rand = 4 + (int) (Math.random() * (20 - 4) + 1);

        int density = 2 + (int) (Math.random() * (20 - 2) + 1);

        for (int i = 0; i < rand; i++) {
            addNode(i + 1);
        }

        for (int k = 1; k < adjList.keySet().size() + 1; k++) {
            for (int j = 1; j < adjList.keySet().size() + 1; j++) {
                int createOrNot = 1 + (int) (Math.random() * (100 - 1) + 1);
                if (createOrNot <= density) {
                    addEdge(k, j);
                }
            }
        }
    }
}
