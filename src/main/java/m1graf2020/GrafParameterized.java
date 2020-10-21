package m1graf2020;

public class GrafParameterized extends Graf {

    public GrafParameterized(int nbNode, int nbEdge, int density) {

        for (int i = 1; i < nbNode; i++) {
            addNode(i);
        }

        int rand1;
        int rand2;

        System.out.println("vgvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv : " + (adjList.keySet().size()+1));
        while(nbEdge > 0){
            rand1 = 1 + (int) (Math.random() * ((adjList.keySet().size() + 1) - 1) + 1);
            System.out.println("rand 1 : " + rand1);

            rand2 = 1 + (int) (Math.random() * ((adjList.keySet().size() + 1) - 1) + 1);
            System.out.println("rand 2 : " + rand2);
            int createOrNot = 0 + (int) (Math.random() * (100 - 0) + 1);
            if (createOrNot <= density) {
                addEdge(rand1, rand2);
                nbEdge--;
            }

        }
    }
}
