package m1graf2020;


import java.io.*;
import java.util.*;

/****************************************************
 *                      QUESTIONS                   *
 ****************************************************/


/****************************************************
 *                   IMPLEMENTATION                 *
 ****************************************************/

public class Graf {
    private TreeMap<Node, List<Node>> adjList = new TreeMap<>();
    private TreeSet<Integer> poubelle = new TreeSet<>();
    private List<Edge> edges = new ArrayList<>();


    public Graf() {

    }

    public Graf(int... SA) {
        int actualNode = 1;
        addNode(1);
        for (int index = 0; index < SA.length; index++) {
            if (SA[index] != 0) {
                addEdge(actualNode, SA[index]);
            } else {
                if (index != SA.length - 1) {
                    addNode(actualNode + 1);
                    actualNode++;
                }
            }
        }

    }

    public Graf(String fileName) throws IOException {

        File file = new File(fileName);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufRead = new BufferedReader(fileReader);
        String myLine;
        int actualNode = 0;

        while ((myLine = bufRead.readLine()) != null) {
            if (myLine.isEmpty()) continue;
            if (!myLine.contains("{") && !myLine.contains("}") && !myLine.contains("#")) {
                if (myLine.contains("->")) {
                    String[] line = myLine.trim().split("->");
                    if (actualNode != Integer.parseInt(line[0].trim())) {
                        actualNode++;
                        addNode(actualNode);
                    }
                    addEdge(actualNode, Integer.parseInt(line[1].replaceAll(";", "").trim()));
                } else {
                    String line = myLine.replaceAll(";", "").trim();
                    if (actualNode != Integer.parseInt(line)) {
                        actualNode++;
                        addNode(actualNode);
                    }
                }
            }
        }
        fileReader.close();
    }

    /************************************ FUNCTIONS OF US ************************************/

    public Set<Node> getNodes() {
        return adjList.keySet();
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public TreeSet<Integer> getPoubelle() {
        return poubelle;
    }

    public TreeMap<Node, List<Node>> getMap() {
        return adjList;
    }

    public int indexToUse() {
        if (poubelle.isEmpty()) {
            return adjList.keySet().size() + 1;
        }
        int i = poubelle.first();
        poubelle.remove(i);
        return i;
    }

    public void printMap() {
        for (Node key : adjList.keySet()) {
            List<Node> value = adjList.get(key);
            System.out.print("[" + key.getId() + "] -> ");
            for (Node vNode : value) {
                System.out.print("{" + vNode.getId() + "}");
            }
            System.out.println();
        }

        for (Edge ed : edges) {
            System.out.print("(" + ed.getFrom() + "," + ed.getTo() + ")  ");
        }

        System.out.println();
    }

    public void printEdges() {
        for (Edge e : edges) {
            System.out.print("(" + e.getFrom() + "," + e.getTo() + ")  ");
        }
    }


    public void printPoubelle() {
        for (int i : poubelle) {
            System.out.println("elem : " + i);
        }
    }

    /************************************ FUNCTIONS NOT OF US ************************************/

    /****************************************************
     *                       NODES                      *
     ****************************************************/

    public int nbNodes() {
        return adjList.keySet().size();
    }

    public boolean existsNode(Node n) {
        return existsNode(n.getId());
    }

    public boolean existsNode(int id) {
        for (Node adjNode : adjList.keySet()) {
            if (adjNode.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public Node getNode(int id) {
        for (Node adjNode : adjList.keySet()) {
            if (adjNode.getId() == id) {
                return adjNode;
            }
        }
        return null;
    }

    public void addNode() {
        Node n = new Node(indexToUse());
        adjList.put(n, new ArrayList<>());
    }

    public void addNode(Node n) {
        if (existsNode(n)) {
            return;
        }
        adjList.put(n, new ArrayList<>());
    }

    public void addNode(int id) {
        addNode(new Node(id));
    }

    public void removeNode(Node n) {
        if (!existsNode(n)) {
            return;
        }
        for (Map.Entry<Node, List<Node>> entry : adjList.entrySet()) {
            entry.getValue().removeIf(node -> node.equals(n));
        }
        edges.removeIf(e -> (e.getFrom() == n.getId() || e.getTo() == n.getId()));

        adjList.keySet().removeIf(e -> (e.getId() == n.getId()));
        poubelle.add(n.getId());
    }

    public void removeNode(int id) {
        removeNode(new Node(id));
    }

    public List<Node> getSuccessors(Node n) {
        return adjList.get(n);
    }


    public List<Node> getSuccessors(int id) {
        return getSuccessors(new Node(id));
    }

    public boolean adjacent(Node u, Node v) {
        return existsEdge(u, v);
    }

    public boolean adjacent(int u, int v) {
        return existsEdge(u, v);
    }

    public List<Node> getAllNodes() {
        return new ArrayList<>(adjList.keySet());
    }

    /****************************************************
     *                       EDGES                      *
     ****************************************************/

    public int nbEdges() {
        return edges.size();
    }

    public boolean existsEdge(Node u, Node v) {
        return existsEdge(new Edge(u, v));
    }

    public boolean existsEdge(int u, int v) {
        return existsEdge(new Edge(u, v));
    }

    public boolean existsEdge(Edge e) {
        return edges.contains(e);
    }

    public void addEdge(Node from, Node to) {
        addEdge(new Edge(from.getId(), to.getId()));
    }

    public void addEdge(int from, int to) {
        addEdge(new Edge(from, to));
    }

    public void addEdge(Edge ed) {
        for (Node n : adjList.keySet()) {
            if (n.getId() == ed.getFrom()) {
                adjList.get(n).add(new Node(ed.getTo()));
                edges.add(ed);
                if (!existsNode(ed.getTo())) {
                    addNode(ed.getTo());
                }
                return;
            }
        }

        addNode(ed.getFrom());
        adjList.get(getNode(ed.getFrom())).add(new Node(ed.getTo()));
        edges.add(ed);
        if (!existsNode(ed.getTo())) {
            addNode(ed.getTo());
        }
    }

    public void removeEdge(Node from, Node to) {
        removeEdge(new Edge(from.getId(), to.getId()));
    }

    public void removeEdge(int from, int to) {
        removeEdge(new Edge(from, to));
    }

    public void removeEdge(Edge e) {
        edges.remove(e);

        Node nodeToDelete = new Node(e.getTo());

        for (Map.Entry<Node, List<Node>> entry : adjList.entrySet()) {
            for (Node nodeEdge : entry.getValue()) {
                if (nodeEdge.equals(nodeToDelete) && entry.getKey().getId() == e.getFrom()) {
                    entry.getValue().remove(nodeEdge);
                    Collections.sort(entry.getValue());
                    return;
                }
            }

        }
    }

    public List<Edge> getOutEdges(Node n) {
        List<Edge> le = new ArrayList<>();

        for (Node myNode : adjList.get(getNode(n.getId()))) {
            le.add(new Edge(n.getId(), myNode.getId()));
        }
        return le;
    }

    public List<Edge> getOutEdges(int id) {
        List<Edge> le = new ArrayList<>();

        for (Node myNode : adjList.get(getNode(id))) {
            le.add(new Edge(id, myNode.getId()));
        }
        return le;
    }

    public List<Edge> getInEdges(Node n) {
        List<Edge> le = new ArrayList<>();

        for (Edge e : edges) {
            if (e.getTo() == n.getId()) {
                le.add(e);
            }
        }
        return le;
    }

    public List<Edge> getInEdges(int id) {
        List<Edge> le = new ArrayList<>();

        for (Edge e : edges) {
            if (e.getTo() == id) {
                le.add(e);
            }
        }
        return le;
    }

    public List<Edge> getIncidentEdges(Node n) {
        List<Edge> le = new ArrayList<>();

        le.addAll(getInEdges(n));
        le.addAll(getOutEdges(n));

        return le;
    }

    public List<Edge> getIncidentEdges(int id) {
        List<Edge> le = new ArrayList<>();

        le.addAll(getInEdges(id));
        le.addAll(getOutEdges(id));

        return le;
    }

    public List<Edge> getAllEdges() {
        return edges;
    }

    /****************************************************
     *                      DEGREES                     *
     ****************************************************/

    public int inDegree(Node n) {
        return getInEdges(n).size();
    }

    public int inDegree(int id) {
        return getInEdges(id).size();
    }

    public int outDegree(Node n) {
        return getOutEdges(n).size();
    }

    public int outDegree(int id) {
        return getOutEdges(id).size();
    }

    public int degree(Node n) {
        return outDegree(n) + inDegree(n);
    }

    public int degree(int id) {
        return outDegree(id) + inDegree(id);
    }

    /****************************************************
     *               GRAPH REPRESENTATION               *
     ****************************************************/


    public int[] toSuccessorArray() {
        int SALength = edges.size() + getAllNodes().size() - 1; //-1 because we add a 0 between each node 1-2-3-4-5
        int[] SA = new int[SALength];
        int cptIndex = 0;

        for (Node key : adjList.keySet()) {
            List<Node> value = adjList.get(key);
            for (Node vNode : value) {
                SA[cptIndex] = vNode.getId();
                cptIndex++;
            }
            if (cptIndex < SALength) {
                SA[cptIndex] = 0;
                cptIndex++;
            }
        }
        return SA;
    }

    public int[][] toAdjMatrix() {
        int lnMatrix = getAllNodes().size();
        int[][] ADJM = new int[lnMatrix][lnMatrix];
        int ADJMHeight = 0;

        int[] SA = toSuccessorArray();

        for (int SAindex = 0; SAindex < SA.length; SAindex++) {
            for (int ADJMwidth = 0; ADJMwidth < ADJM[ADJMHeight].length; ADJMwidth++) {
                if (ADJMwidth + 1 == SA[SAindex]) { //+1 because we're starting at index 1
                    ADJM[ADJMHeight][ADJMwidth]++;
                }
            }
            if (SA[SAindex] == 0) {
                ADJMHeight++;
            }
        }

        return ADJM;
    }

    /****************************************************
     *               GRAPH TRANSFORMATION               *
     ****************************************************/
    /**
     * @return
     * @
     */
    public Graf getReverse() {
        Graf reversedGraf = new Graf();

        for (Edge e : this.edges) {
            reversedGraf.addEdge(e.getTo(), e.getFrom());
        }
        return reversedGraf;
    }

    /**
     * @return
     * @
     */
    public Graf getTransitiveClosure() {
        Graf transitiveClosureGraph = new Graf();

        for (Edge e : this.edges) {
            transitiveClosureGraph.addEdge(e.getFrom(), e.getTo());
        }

        for (Node u : transitiveClosureGraph.getNodes()) {
            for (Node p : transitiveClosureGraph.getNodes()) {
                if (transitiveClosureGraph.existsEdge(p, u)) {
                    for (Node s : transitiveClosureGraph.getNodes()) {
                        if (transitiveClosureGraph.existsEdge(u, s) && s != p) {
                            if (!transitiveClosureGraph.existsEdge(p, s)) {
                                transitiveClosureGraph.addEdge(p, s);
                            }
                        }
                    }
                }
            }
        }
        return transitiveClosureGraph;
    }


    /****************************************************
     *               GRAPH TRAVERSAL                    *
     ****************************************************/


    /**
     * Will do the dfs traversal of the graph in a recursive way
     *
     * @return list of parcoured nodes in the DFS order
     */
    public List<Node> getDFS() {
        boolean[] visited = new boolean[256];
        List<Node> ls = new ArrayList<>();

        ls.add(adjList.firstKey());
        dfs(adjList.firstKey(), visited, ls);
        return ls;
    }

    /**
     * Will do the dfs traversal of the graph in a recursive way
     *
     * @param actualNode actualNode to visit
     * @param visited    tab of visited nodes
     * @param ls         list of the parcoured nodes
     */
    public void dfs(Node actualNode, boolean[] visited, List<Node> ls) {
        visited[actualNode.getId()] = true;

        Iterator<Node> node = adjList.get(actualNode).listIterator();
        while (node.hasNext()) {
            Node n = node.next();
            if (!visited[n.getId()]) {
                ls.add(n);
                dfs(n, visited, ls);
            }
        }
    }

    /**
     * Will do the BFS traversal of the graph in a recursive way
     *
     * @return list of parcoured nodes in the BFS order
     */
    public List<Node> getBFS() {
        List<Node> ls = new ArrayList<>();
        ls.add(adjList.firstKey());
        bfs(adjList.firstKey(), ls);
        return ls;
    }

    /**
     * Will do the bfs traversal of the graph
     *
     * @param actualNode actualNode to visit
     * @param ls         list of the parcoured nodes
     */
    public void bfs(Node actualNode, List<Node> ls) {
        boolean[] visited = new boolean[256];

        LinkedList<Node> queue = new LinkedList<>();

        visited[actualNode.getId()] = true;
        queue.add(actualNode);

        while (queue.size() != 0) {
            actualNode = queue.poll();

            Iterator<Node> node = adjList.get(actualNode).listIterator();
            while (node.hasNext()) {
                Node n = node.next();
                if (!visited[n.getId()]) {
                    visited[n.getId()] = true;
                    queue.add(n);
                    ls.add(n);
                }
            }
        }
    }


    /****************************************************
     *               GRAPH EXPORT                       *
     ****************************************************/


    /**
     * the dot representation in a string of the graf
     *
     * @return the dot representation in a string of the graf
     */
    public String toDotString() {
        String dot = "# DOT Representation for the graph";
        dot += "\n\n digraph graf {\n";

        for (Map.Entry<Node, List<Node>> entry : adjList.entrySet()) {
            if (entry.getValue().isEmpty()) {
                dot += "\t" + entry.getKey() + ";\n";
            }
            for (Node edgeNode : entry.getValue()) {
                dot += "\t" + entry.getKey() + " -> " + edgeNode.getId() + ";\n";
            }
        }

        dot += "}";

        return dot;
    }

    /**
     * Will write into the specified file the dot representation of the graf
     *
     * @param fileName file to write the dot representation
     * @throws IOException possible I/O exception with file
     */
    public void toDotFile(String fileName) throws IOException {
        File file = new File(fileName);

        FileWriter fWriter = new FileWriter(file);
        fWriter.write(toDotString());
        fWriter.close();
    }
}

