package m1graf2020;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;


public class Main {
    static String loggedKey;
    static boolean run = true;
    static BufferedReader bufferReader;
    static Graf myGraf = null;

    public static void main(String[] args) throws IOException {
        bufferReader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Welcome to our Graf library ! ");

        while (run) {
            printMenu();
            loggedKey = bufferReader.readLine().replaceAll("[^0-9|q]", "").trim();
            compute(loggedKey);

        }
        System.out.println("Auf Wiedersehen !");

    }

    public static void printMenu() {
        System.out.println();
        System.out.println("Press 1 : Create a new Graf ");
        System.out.println("Press 2 : To add a node ");
        System.out.println("Press 3 : To remove a node ");
        System.out.println("Press 4 : To add an edge ");
        System.out.println("Press 5 : Remove an edge ");
        System.out.println("Press 6 : show the graph in the DOT format ");
        System.out.println("Press 7 : read the graph from a DOT file ");
        System.out.println("Press 8 : export the graph to a DOT file ");
        System.out.println("Press 9 : reverse the actual graf ");
        System.out.println("Press 10 : compute the transitive closure of the graf ");
        System.out.println("Press 11 : traverse the graf in DSF ");
        System.out.println("Press 12 : traverse the graf in BSF ");
        System.out.println("Press q to quit !");

    }

    public static void compute(String key) throws IOException {
        switch (key) {
            case "q":
                run = false;
                break;
            case "1":
                createNewGraf();
                break;
            case "2":
                addNode();
                break;
            case "3":
                removeNode();
                break;
            case "4":
                addEdge();
                break;
            case "5":
                removeEdge();
                break;
            case "6":
                showGrafInDot();
                break;
            case "7":
                readGrafFromDotFile();
                break;
            case "8":
                exportGrafToDotFile();
                break;
            case "9":
                reverseGraf();
                break;
            case "10":
                computeTransitive();
                break;
            case "11":
                traverseDFS();
                break;
            default:
                traverseBFS();
                break;
        }
    }

    public static void createNewGraf() throws IOException {
        System.out.println("Do you want a directed graf or a undirected graf or a random connected graf ? (press 1 or 2 or 3)");
        char key = bufferReader.readLine().replaceAll("[^0-9]", "").trim().charAt(0);
        switch (key) {
            case '1':
                myGraf = new Graf();
                break;
            case '2':
                myGraf = new UndirectedGraf();
                break;
            case '3':
                myGraf = new ConnectGraf();
                break;
            case '4':
                myGraf = new DenseGraf();
                break;
            case '5':
                myGraf = new SparsiateGraf();
                break;
            case '6':
                parameterizedGraf();
                break;
        }
        System.out.println("Your empty " + (key == '1' ? "directed " : "undirected ") + "graf have been created !");
    }

    public static void parameterizedGraf() throws IOException {
        int nbNode = 0;
        int nbedge = 0;
        int density = 0;

        System.out.println("Please enter the number of node  : ");
        String nodeNumber = bufferReader.readLine().replaceAll("[^0-9]", "").trim();
        nbNode = (Integer.parseInt(nodeNumber));
        System.out.println("Please enter the number of edge : ");
        String edgeNumber = bufferReader.readLine().replaceAll("[^0-9]", "").trim();
        nbedge = (Integer.parseInt(edgeNumber));
        System.out.println("Please enter the edge probability distribution (between 0 and 100) : ");
        String densityNumber = bufferReader.readLine().replaceAll("[^0-9]", "").trim();
        density = (Integer.parseInt(densityNumber));

        myGraf = new GrafParameterized(nbNode, nbedge, density);
    }

    public static void addNode() throws IOException {
        System.out.println("Please enter the node number or press enter to add a node automatically");
        String nodeNumber = bufferReader.readLine().replaceAll("[^0-9]", "").trim();
        if (myGraf == null) {
            myGraf = new Graf();
        }
        if (!nodeNumber.isEmpty()) {
            myGraf.addNode(Integer.parseInt(nodeNumber.trim()));

        } else {
            myGraf.addNode();
        }
        System.out.println("The node (" + (!nodeNumber.isEmpty() ? Integer.parseInt(nodeNumber) : "auto") + ") have been added !");

    }

    public static void removeNode() throws IOException {
        if (myGraf == null) {
            System.out.println("It appears that your graf is not created !");
            return;
        }
        System.out.println("Please enter the node number that you want to remove");
        String nodeNumber = bufferReader.readLine().replaceAll("[^0-9]", "").trim();
        myGraf.removeNode(Integer.parseInt(nodeNumber));
        System.out.println("The node (" + Integer.parseInt(nodeNumber) + ") have been removed ! ");
    }

    public static void addEdge() throws IOException {
        if (myGraf == null) {
            myGraf = new Graf();
        }
        System.out.println("Please enter where your edge come from");
        String edgeFrom = bufferReader.readLine().replaceAll("[^0-9]", "").trim();
        System.out.println("Now please enter where your edge go to");
        String edgeTo = bufferReader.readLine().replaceAll("[^0-9]", "").trim();
        myGraf.addEdge(Integer.parseInt(edgeFrom), Integer.parseInt(edgeTo));
        System.out.println("The edge (" + Integer.parseInt(edgeFrom) + "," + Integer.parseInt(edgeTo) + ") have been added !");
    }

    public static void removeEdge() throws IOException {
        if (myGraf == null) {
            System.out.println("It appears that your graf is not created !");
            return;
        }
        System.out.println("Please enter where your edge come from");
        String edgeFrom = bufferReader.readLine().replaceAll("[^0-9]", "").trim();
        System.out.println("Now please enter where your edge go to");
        String edgeTo = bufferReader.readLine().replaceAll("[^0-9]", "").trim();

        myGraf.removeEdge(Integer.parseInt(edgeFrom), Integer.parseInt(edgeTo));
        System.out.println("The edge (" + Integer.parseInt(edgeFrom) + "," + Integer.parseInt(edgeTo) + ") have been removed !");
    }

    public static void showGrafInDot() {
        if (myGraf == null) {
            System.out.println("It appears that your graf is not created !");
            return;
        }
        System.out.println(myGraf.toDotString());
    }

    public static void readGrafFromDotFile() throws IOException {
        System.out.println("Please enter your filename (and the path before your file if it not next to the executable) ! It will erase your actual graph !");
        String fileName = bufferReader.readLine().trim();
        myGraf = new Graf(fileName);
        System.out.println("Your graph is now the graph from file !");
    }

    public static void exportGrafToDotFile() throws IOException {
        if (myGraf == null) {
            System.out.println("It appears that your graf is not created !");
            return;
        }
        System.out.println("Please enter the filename for the export of your actual graf !");
        String fileName = bufferReader.readLine().trim();
        myGraf.toDotFile(fileName);
        System.out.println("Your file " + fileName + " have been created, please check it !");
    }

    public static void reverseGraf() {
        if (myGraf == null) {
            System.out.println("It appears that your graf is not created !");
            return;
        }
        myGraf = myGraf.getReverse();
        System.out.println("Your graf have been reversed, check it with the dot representation !");
    }

    public static void traverseDFS() {
        if (myGraf == null) {
            System.out.println("It appears that your graf is not created !");
            return;
        }
        List<Node> parcouredNodes = myGraf.getDFS();
        System.out.println("Here is the list of the parcoured nodes (in the order) -- DFS ");
        parcouredNodes.forEach(System.out::println);
    }

    public static void traverseBFS() {
        if (myGraf == null) {
            System.out.println("It appears that your graf is not created !");
            return;
        }
        List<Node> parcouredNodes = myGraf.getBFS();
        System.out.println("Here is the list of the parcoured nodes (in the order) -- BFS ");
        parcouredNodes.forEach(System.out::println);
    }

    public static void computeTransitive() {
        if (myGraf == null) {
            System.out.println("It appears that your graf is not created !");
            return;
        }
        myGraf = myGraf.getTransitiveClosure();
        System.out.println("Your graph is now the transitive closure of himself");
    }


}
