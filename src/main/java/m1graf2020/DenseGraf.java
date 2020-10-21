package m1graf2020;

public class DenseGraf extends Graf {

    public DenseGraf() {
        super();
        int rand = 4 + (int) (Math.random() * (20 - 4) + 1);

        int density = 80 + (int) (Math.random() * (98 - 80) + 1);

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
