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
    protected TreeMap<Node, List<Node>> adjList = new TreeMap<>();
    protected TreeSet<Integer> trash = new TreeSet<>();
    protected List<Edge> edges = new ArrayList<>();

    /**
     * Will create a new empty Graf
     */
    public Graf() {

    }

    /**
     * Will create a new Graf based on the given successor array
     *
     * @param SA The successor array
     */
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

    /**
     * Will parse a dot file and create a graf based on it
     *
     * @param fileName The name of the file to parse
     * @throws IOException
     */
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

                    if (myLine.contains(",")) {

                        String[] line = myLine.split("->");
                        if (actualNode != Integer.parseInt(line[0].trim())) {
                            actualNode++;
                            addNode(actualNode);
                        }

                        String[] lineComa = Arrays.toString(line).trim().replace("[", "").replace("]", "").split(",");

                        for (int index = 1; index < lineComa.length; index++) {
                            addEdge(actualNode, Integer.parseInt(lineComa[index].replaceAll(";", "").trim()));
                        }

                    } else {
                        String[] line = myLine.trim().split("->");
                        if (actualNode != Integer.parseInt(line[0].trim())) {
                            actualNode++;
                            addNode(actualNode);
                        }
                        addEdge(actualNode, Integer.parseInt(line[1].replaceAll(";", "").trim()));
                    }

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

    /**
     * It will return a set containing all the nodes
     *
     * @return a set containing all the nodes
     */
    public Set<Node> getNodes() {
        return adjList.keySet();
    }

    /**
     * It will return a list containing all the edges
     *
     * @return a list containing all the edges
     */
    public List<Edge> getEdges() {
        return edges;
    }

    /**
     * Select the number to assigne for the creation of a node with the function addNode without parameter
     *
     * @return the first element of the array trash
     */
    public int indexToUse() {
        if (trash.isEmpty()) {
            return adjList.keySet().size() + 1;
        }
        int i = trash.first();
        trash.remove(i);
        return i;
    }

    /**
     * Print the whole map like : Node : Edges destination
     */
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

    /**
     * Print all edges like : (edge start, edge end)
     */
    public void printEdges() {
        for (Edge e : edges) {
            System.out.print("(" + e.getFrom() + "," + e.getTo() + ")  ");
        }
    }

    /**
     * Print all the number that can be assigne to a node
     */
    public void printTrash() {
        for (int i : trash) {
            System.out.println("elem : " + i);
        }
    }

    /************************************ FUNCTIONS NOT OF US ************************************/

    /****************************************************
     *                       NODES                      *
     ****************************************************/


    /**
     * Give the number of node contain in the graf
     *
     * @return the number of node contain in the graf
     */
    public int nbNodes() {
        return adjList.keySet().size();
    }

    /**
     * Check if a node already exist
     *
     * @param n the node
     * @return true if the node exist otherwise return false
     */
    public boolean existsNode(Node n) {
        return existsNode(n.getId());
    }

    /**
     * Check if a node already exist
     *
     * @param id of the node
     * @return true if the node exist otherwise return false
     */
    public boolean existsNode(int id) {
        for (Node adjNode : adjList.keySet()) {
            if (adjNode.getId() == id) {
                return true;
            }
        }
        return false;
    }

    /**
     * Give a node assiciate to an id
     *
     * @param id of the node
     * @return null if the note doesn't exist otherwise return the node
     */
    public Node getNode(int id) {
        for (Node adjNode : adjList.keySet()) {
            if (adjNode.getId() == id) {
                return adjNode;
            }
        }
        return null;
    }

    /**
     * Add a node with automatic id
     */
    public void addNode() {
        Node n = new Node(indexToUse());
        adjList.put(n, new ArrayList<>());
    }

    /**
     * Add a node to the map if the node doesn't already exist
     *
     * @param n node to add
     */
    public void addNode(Node n) {
        if (existsNode(n)) {
            return;
        }
        adjList.put(n, new ArrayList<>());

    }

    /**
     * Add a node with the id given to the map if the node doesn't already exist
     *
     * @param id of the node to add
     */
    public void addNode(int id) {
        addNode(new Node(id));
    }

    /**
     * Remove a node from the map and all the edges associate to him
     *
     * @param n node to remove
     */
    public void removeNode(Node n) {
        if (!existsNode(n)) {
            return;
        }
        for (Map.Entry<Node, List<Node>> entry : adjList.entrySet()) {
            entry.getValue().removeIf(node -> node.equals(n));
        }
        edges.removeIf(e -> (e.getFrom() == n.getId() || e.getTo() == n.getId()));

        adjList.keySet().removeIf(e -> (e.getId() == n.getId()));
        trash.add(n.getId());
    }

    /**
     * Remove a node associate to the id given from the map and all the edges associate to him
     *
     * @param id of the node to remove
     */
    public void removeNode(int id) {
        removeNode(new Node(id));
    }

    /**
     * Give the list of all the successor of a node n
     *
     * @param n node to get its successors
     * @return a list with all the successor of a given node
     */
    public List<Node> getSuccessors(Node n) {
        return adjList.get(n);
    }

    /**
     * Give the list of all the successor of a node associate to a given id
     *
     * @param id of the node to get its successors
     * @return a list with all the successor of a given node
     */
    public List<Node> getSuccessors(int id) {
        return getSuccessors(new Node(id));
    }

    /**
     * Check if both node u and v have an edge between them
     *
     * @param u first node to check if adjacent to v
     * @param v second node
     * @return true if there is existing an edge between u and v otherwise return false
     */
    public boolean adjacent(Node u, Node v) {
        return existsEdge(u, v);
    }

    /**
     * Check if both node u and v have an edge between them
     *
     * @param u id of the first node to check if adjacent to v
     * @param v id of the second node
     * @return true if there is existing an edge between u and v otherwise return false
     */
    public boolean adjacent(int u, int v) {
        return existsEdge(u, v);
    }

    /**
     * Give the list of all node in the map
     *
     * @return an arrayList with all the node
     */
    public List<Node> getAllNodes() {
        return new ArrayList<>(adjList.keySet());
    }

    /****************************************************
     *                       EDGES                      *
     ****************************************************/


    /**
     * Give the number of edge
     *
     * @return the size of the list edges
     */
    public int nbEdges() {
        return edges.size();
    }

    /**
     * Check if an edge between u and v exist
     *
     * @param u node from
     * @param v node to
     * @return true if an edge between u and v exist otherwise return false
     */
    public boolean existsEdge(Node u, Node v) {
        return existsEdge(new Edge(u, v));
    }

    /**
     * Check if an edge between the node with the id u and the node with the id v exist
     *
     * @param u id from
     * @param v id to
     * @return true if an edge between the node with the id u and the node with the id v exist otherwise return false
     */
    public boolean existsEdge(int u, int v) {
        return existsEdge(new Edge(u, v));
    }

    /**
     * Check if an edge exist
     *
     * @param e edge to check
     * @return true if the edge given exist otherwise return false
     */
    public boolean existsEdge(Edge e) {
        return edges.contains(e);
    }

    /**
     * Add an edge to the map and to the list of edges, if the accosiate nodes doesn't exist they are create
     *
     * @param from node where the edge come from
     * @param to   node where the edge go to
     */
    public void addEdge(Node from, Node to) {
        addEdge(new Edge(from.getId(), to.getId()));
    }

    /**
     * Add an edge to the map and to the list of edges the accosiate nodes doesn't exist they are create
     *
     * @param from id where the edge come from
     * @param to   id where the edge go to
     */
    public void addEdge(int from, int to) {
        addEdge(new Edge(from, to));
    }

    /**
     * Add an edge to the map and to the list of edges the accosiate nodes doesn't exist they are create
     *
     * @param ed edge to add
     */
    public void addEdge(Edge ed) {

        if (!existsNode(ed.getTo())) {
            addNode(ed.getTo());
        }
        if (!existsNode(ed.getFrom())) {
            addNode(ed.getFrom());
        }

        for (Node n : adjList.keySet()) {
            if (n.getId() == ed.getFrom()) {
                adjList.get(n).add(new Node(ed.getTo()));
                edges.add(ed);
                Collections.sort(edges);
                Collections.sort(adjList.get(n));
            }
        }
    }

    /**
     * Remove an edge from the map and the list edges
     *
     * @param from node where the edge come from
     * @param to   node where the edge go to
     */
    public void removeEdge(Node from, Node to) {
        removeEdge(new Edge(from.getId(), to.getId()));
    }

    /**
     * Remove an edge from the map and the list edges
     *
     * @param from id where the edge come from
     * @param to   id where the edge go to
     */
    public void removeEdge(int from, int to) {
        removeEdge(new Edge(from, to));
    }

    /**
     * Remove an edge from the map and the list edges
     *
     * @param e edge to remove
     */
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

    /**
     * Give a list of all edges that get out a node n
     *
     * @param n node to get its out edges
     * @return a list of all edges that get out a node n
     */
    public List<Edge> getOutEdges(Node n) {
        List<Edge> le = new ArrayList<>();

        for (Node myNode : adjList.get(getNode(n.getId()))) {
            le.add(new Edge(n.getId(), myNode.getId()));
        }
        return le;
    }

    /**
     * Give a list of all edges that get out a node with a given id
     *
     * @param id of a node to get its out edges
     * @return a list of all edges that get out a node with a given id
     */
    public List<Edge> getOutEdges(int id) {
        List<Edge> le = new ArrayList<>();

        for (Node myNode : adjList.get(getNode(id))) {
            le.add(new Edge(id, myNode.getId()));
        }
        return le;
    }

    /**
     * Give a list of all edges that get in a node n
     *
     * @param n node to get its in edges
     * @return Give a list of all edges that get in a node
     */
    public List<Edge> getInEdges(Node n) {
        List<Edge> le = new ArrayList<>();

        for (Edge e : edges) {
            if (e.getTo() == n.getId()) {
                le.add(e);
            }
        }
        return le;
    }

    /**
     * Give a list of all edges that get in a node with a given id
     *
     * @param id of a node to get its in edges
     * @return Give a list of all edges that get in a node with a given id
     */
    public List<Edge> getInEdges(int id) {
        List<Edge> le = new ArrayList<>();

        for (Edge e : edges) {
            if (e.getTo() == id) {
                le.add(e);
            }
        }
        return le;
    }

    /**
     * Give a list of all edges that get in and out a node n
     *
     * @param n node to get its incident edges
     * @return a list of all edges that get in and out a node n
     */
    public List<Edge> getIncidentEdges(Node n) {
        List<Edge> le = new ArrayList<>();

        le.addAll(getInEdges(n));
        le.addAll(getOutEdges(n));

        return le;
    }

    /**
     * Give a list of all edges that get in and out a node with a given id
     *
     * @param id of a node to get its incident edges
     * @return a list of all edges that get in and out a node with a given id
     */
    public List<Edge> getIncidentEdges(int id) {
        List<Edge> le = new ArrayList<>();

        le.addAll(getInEdges(id));
        le.addAll(getOutEdges(id));

        return le;
    }

    /**
     * Give a list with all edges contain in the map
     *
     * @return a list with all edges of the map
     */
    public List<Edge> getAllEdges() {
        return edges;
    }

    /****************************************************
     *                      DEGREES                     *
     ****************************************************/


    /**
     * Give the number of edge that have the node n in destination
     *
     * @param n node to get its in degree
     * @return the number of edge that have the node n in destination
     */
    public int inDegree(Node n) {
        return getInEdges(n).size();
    }

    /**
     * Give the number of edge that have the node with a given id in destination
     *
     * @param id of a node to get its in degree
     * @return the number of edge that have the node with a given id in destination
     */
    public int inDegree(int id) {
        return getInEdges(id).size();
    }

    /**
     * Give the number of edge that have the node n in source
     *
     * @param n node to get its out degree
     * @return the number of edge that have the node n in source
     */
    public int outDegree(Node n) {
        return getOutEdges(n).size();
    }

    /**
     * Give the number of edge that have the node with a given id in source
     *
     * @param id of a node to get its out degree
     * @return the number of edge that have the node with a given id in source
     */
    public int outDegree(int id) {
        return getOutEdges(id).size();
    }

    /**
     * Give the number of edge that have the node n in source or in destination
     *
     * @param n a node to get its degree
     * @return the number of edge that have the node n in source or in destination
     */
    public int degree(Node n) {
        return outDegree(n) + inDegree(n);
    }

    /**
     * Give the number of edge that have the node with a given id in source or in destination
     *
     * @param id of a node to get its degree
     * @return the number of edge that have the node with a given id in source or in destination
     */
    public int degree(int id) {
        return outDegree(id) + inDegree(id);
    }

    /****************************************************
     *               GRAPH REPRESENTATION               *
     ****************************************************/


    /**
     * Give an array representing the map
     *
     * @return an array representing the map
     */
    public int[] toSuccessorArray() {
        int SALength = edges.size() + getAllNodes().size(); //-1 because we add a 0 between each node 1-2-3-4-5
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

    /**
     * Give a matrix representing the map by using the successor array of the map
     *
     * @return a matrix representing the map
     */
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
     * Reverse the graf
     *
     * @return a reversed graf
     */
    public Graf getReverse() {
        Graf reversedGraf = new Graf();

        for (Edge e : this.edges) {
            reversedGraf.addEdge(e.getTo(), e.getFrom());
        }
        return reversedGraf;
    }

    /**
     * Add edge between all the node that are accessible by an other one
     *
     * @return a new graf with all the node added
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

        for (Node n : adjList.get(actualNode)) {
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

            for (Node n : adjList.get(actualNode)) {
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
        StringBuilder dot = new StringBuilder("# DOT Representation for the graph");
        dot.append("\n\n digraph graf {\n");

        for (Map.Entry<Node, List<Node>> entry : adjList.entrySet()) {
            if (entry.getValue().isEmpty()) {
                dot.append("\t").append(entry.getKey()).append(";\n");
            }
            for (Node edgeNode : entry.getValue()) {
                dot.append("\t").append(entry.getKey()).append(" -> ").append(edgeNode.getId()).append(";\n");
            }
        }

        dot.append("}");

        return dot.toString();
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

    /****************************************************
     *               GRAPH EXPORT                       *
     ****************************************************/
}

