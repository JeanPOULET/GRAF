package m1graf2020;

import m1graf2020.Exceptions.NodeAlreadyExist;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Unit test for simple App.
 */
public class GrafTest {
    /**
     * Rigorous Test :-)
     */

    Graf g;
    Node n1;
    Node n2;
    Node n3;
    Node n4;
    Node n5;

    Graf g2Esport;


    @Before
    public void setup() throws NodeAlreadyExist {
        g = new Graf();
        n1 = new Node(1);
        n2 = new Node(2);
        n3 = new Node(3);
        n4 = new Node(4);
        n5 = new Node(5);

        g2Esport = new Graf();
        g2Esport.addNode(1);
        g2Esport.addNode();
        g2Esport.addNode(n3);
        g2Esport.addNode();
        g2Esport.addNode();

        g2Esport.addEdge(1, 2);
        g2Esport.addEdge(1, 3);
        g2Esport.addEdge(2, 3);
        g2Esport.addEdge(2, 5);
        g2Esport.addEdge(3, 4);
        g2Esport.addEdge(4, 1);
        g2Esport.addEdge(4, 5);
        g2Esport.addEdge(5, 5);
    }


    @Test
    public void testNbNodesVide() {
        Set<Node> ln = g.getNodes();
        Assert.assertEquals(ln.size(), g.nbNodes());
    }

    @Test
    public void testNbNodesNonVide() {
        g.addNode();
        g.addNode();
        Set<Node> ln = g.getNodes();
        Assert.assertEquals(ln.size(), g.nbNodes());
    }

    @Test
    public void testExistsNodeWithNode() throws NodeAlreadyExist {
        g.addNode(n1);
        for (Node myNode : g.getNodes()) {
            Assert.assertEquals((myNode.getId() == n1.getId()), g.existsNode(n1));
        }
    }

    @Test
    public void testExistsNodeWithInt() throws NodeAlreadyExist {
        g.addNode(1);
        for (Node myNode : g.getNodes()) {
            Assert.assertTrue(myNode.getId() == 1 == g.existsNode(1));
        }
    }

    @Test
    public void testGetNodeWithInt() throws NodeAlreadyExist {
        g.addNode(n1);
        Node newNode = g.getNode(1);
        Assert.assertSame(newNode, n1);

    }

    @Test
    public void testAddNodeWithInt() throws NodeAlreadyExist {
        g.addNode(1);
        Assert.assertEquals(1, g.nbNodes());
        Assert.assertTrue(g.existsNode(n1));
        Assert.assertTrue(g.existsNode(1));
        //g.printMap();
    }

    @Test
    public void testAddNodeWithNode() throws NodeAlreadyExist {
        g.addNode(n1);
        Assert.assertEquals(1, g.nbNodes());
        Assert.assertTrue(g.existsNode(n1));
        Assert.assertTrue(g.existsNode(1));
        //g.printMap();
    }

    @Test(expected = NodeAlreadyExist.class)
    @Ignore
    public void testAddNodeExceptionWithInt() throws NodeAlreadyExist {
        g.addNode(2);
        g.addNode(2);
        //g.printMap();
    }

    @Test(expected = NodeAlreadyExist.class)
    @Ignore
    public void testAddNodeExceptionWithNode() throws NodeAlreadyExist {
        g.addNode(n1);
        g.addNode(n1);
        //g.printMap();
    }

    @Test
    public void testAddNodeAuto() {
        g.addNode();
        Assert.assertTrue(g.existsNode(1));
        Assert.assertTrue(g.existsNode(n1));
        Assert.assertEquals(1, g.nbNodes());
    }

    @Test
    public void testRemoveNodeWithNode() throws NodeAlreadyExist {
        g.addNode(n1);
        Assert.assertTrue(g.existsNode(n1));
        g.removeNode(n1);
        Assert.assertFalse(g.existsNode(n1));
    }

    @Test
    public void testRemoveNodeWithNode2() throws NodeAlreadyExist {
        g.addNode(1);
        Assert.assertTrue(g.existsNode(1));
        g.removeNode(n1);
        Assert.assertFalse(g.existsNode(n1));
    }

    @Test
    public void testRemoveNodeWithInt() throws NodeAlreadyExist {
        g.addNode(1);
        Assert.assertTrue(g.existsNode(1));
        g.removeNode(1);
        Assert.assertFalse(g.existsNode(1));
    }

    @Test
    public void testRemoveNodeWithInt2() throws NodeAlreadyExist {
        g.addNode(n1);
        Assert.assertTrue(g.existsNode(n1));
        g.removeNode(1);
        Assert.assertFalse(g.existsNode(1));
    }

    @Test
    public void testRemoveNotExisting() throws NodeAlreadyExist {
        g.addNode(n1);
        //g.printMap();
        Assert.assertFalse(g.existsNode(n2));

        g.removeNode(n2);
        //g.printMap();
        Assert.assertFalse(g.existsNode(n2));
    }

    @Test
    public void testGetSuccessorWithNode() throws NodeAlreadyExist {
        g.addNode(n1);
        g.addNode(n2);
        g.addNode(n3);

        g.addEdge(n1, n2);
        g.addEdge(n1, n3);
        g.addEdge(n2, n3);

        //g.printMap();

        List<Node> lnCheck = new ArrayList<>();
        lnCheck.add(n2);
        lnCheck.add(n3);
        List<Node> ln = g.getSuccessors(n1);
        for (int i = 0; i < ln.size(); i++) {
            Assert.assertEquals(ln.get(i).getId(), lnCheck.get(i).getId());
        }
    }

    @Test
    public void testGetSuccessorWithInt() throws NodeAlreadyExist {
        g.addNode(n1);
        g.addNode(n2);
        g.addNode(n3);

        g.addEdge(n1, n2);
        g.addEdge(n1, n3);
        g.addEdge(n2, n3);

        //g.printMap();

        List<Node> lnCheck = new ArrayList<>();
        lnCheck.add(n2);
        lnCheck.add(n3);
        List<Node> ln = g.getSuccessors(1);
        for (int i = 0; i < ln.size(); i++) {
            Assert.assertEquals(ln.get(i).getId(), lnCheck.get(i).getId());
        }
    }

    @Test
    public void testGetSuccessorInexistant() throws NodeAlreadyExist {
        g.addNode(n1);
        g.addNode(n2);
        g.addNode(n3);

        g.addEdge(n1, n2);
        g.addEdge(n1, n3);
        g.addEdge(n2, n3);

        List<Node> ln = g.getSuccessors(4);
        Assert.assertTrue(ln.isEmpty());
    }

    @Test
    public void testAdjacentWithNode() throws NodeAlreadyExist {
        g.addNode(n1);
        g.addNode(n2);
        g.addNode(n3);

        g.addEdge(n1, n2);
        g.addEdge(n1, n3);

        //g.printMap();

        Assert.assertTrue(g.adjacent(n1, n3));
        Assert.assertFalse(g.adjacent(n3, n2));
    }

    @Test
    public void testAdjacentWithInt() throws NodeAlreadyExist {
        g.addNode(n1);
        g.addNode(n2);
        g.addNode(n3);

        g.addEdge(n1, n2);
        g.addEdge(n1, n3);

        //g.printMap();

        Assert.assertTrue(g.adjacent(1, 3));
        Assert.assertFalse(g.adjacent(3, 2));
    }

    @Test
    public void testAdjacentWithNodeNonDirected() throws NodeAlreadyExist {
        g.addNode(n1);
        g.addNode(n2);
        g.addNode(n3);

        g.addEdge(n1, n2);
        g.addEdge(n1, n3);

        //g.printMap();

        Assert.assertTrue(g.adjacent(1, 3));
        Assert.assertTrue(g.adjacent(3, 1));
    }

    @Test
    public void testGetAllNodes() throws NodeAlreadyExist {
        g.addNode(n1);
        g.addNode(n2);
        g.addNode(n3);

        List<Node> lnCheck = new ArrayList<>();
        lnCheck.add(n1);
        lnCheck.add(n2);
        lnCheck.add(n3);
        List<Node> ln = g.getAllNodes();
        Assert.assertEquals(ln.size(), lnCheck.size());
        for (Node node : lnCheck) {
            Assert.assertTrue(g.existsNode(node));
        }
    }

    @Test
    public void testNbEdges() throws NodeAlreadyExist {
        g.addNode(n1);
        g.addNode(n2);
        g.addNode(n3);

        g.addEdge(n1, n2);
        g.addEdge(n1, n3);
        g.addEdge(n2, n3);

        //g.printMap();

        Assert.assertEquals(3, g.nbEdges());
    }

    @Test
    public void testNbEdgesEmpty() {
        Assert.assertEquals(0, g.nbEdges());
    }

    @Test
    public void testExistsEdgWitheNode() throws NodeAlreadyExist {
        g.addNode(n1);
        g.addNode(n2);
        g.addNode(n3);

        g.addEdge(n1, n2);
        g.addEdge(n1, n3);

        //g.printMap();

        Assert.assertTrue(g.existsEdge(n1, n2));
        Assert.assertFalse(g.existsEdge(n3, n2));
    }

    @Test
    public void testExistsEdgeWithInt() throws NodeAlreadyExist {
        g.addNode(n1);
        g.addNode(n2);
        g.addNode(n3);

        g.addEdge(n1, n2);
        g.addEdge(n1, n3);

        //g.printMap();

        Assert.assertTrue(g.existsEdge(1, 2));
        Assert.assertFalse(g.existsEdge(3, 2));
    }

    @Test
    public void testExistsEdgeWithEdge() throws NodeAlreadyExist {
        g.addNode(n1);
        g.addNode(n2);
        g.addNode(n3);

        g.addEdge(n1, n2);
        g.addEdge(n1, n3);

        //g.printMap();
        Edge ed12 = new Edge(1, 2);
        Edge ed32 = new Edge(3, 2);
        Assert.assertTrue(g.existsEdge(ed12));
        Assert.assertFalse(g.existsEdge(ed32));
    }

    @Test
    public void testExistsEdgeWithEdgeAlreadyCreated() throws NodeAlreadyExist {
        g.addNode(n1);
        g.addNode(n2);
        g.addNode(n3);

        Edge ed12 = new Edge(1, 2);

        g.addEdge(ed12);
        g.addEdge(n1, n3);

        //g.printMap();

        Edge ed32 = new Edge(3, 2);
        Assert.assertTrue(g.existsEdge(ed12));
        Assert.assertFalse(g.existsEdge(ed32));
    }

    @Test
    public void testAddEdgesWithNode() throws NodeAlreadyExist {
        g.addNode(n1);
        g.addNode(n2);
        g.addNode(n3);

        g.addEdge(n1, n2);

        //g.printMap();

        Assert.assertTrue(g.getEdges().contains(new Edge(n1, n2)));
    }

    @Test
    public void testAddEdgesWithInt() throws NodeAlreadyExist {
        g.addNode(n1);
        g.addNode(n2);
        g.addNode(n3);


        g.addEdge(1, 2);

        //g.printMap();

        Assert.assertTrue(g.getEdges().contains(new Edge(1, 2)));
    }

    @Test
    public void testAddEdgesWithEdges() throws NodeAlreadyExist {
        g.addNode(n1);
        g.addNode(n2);
        g.addNode(n3);

        Edge ed12 = new Edge(1, 2);
        g.addEdge(ed12);

        //g.printMap();

        Assert.assertTrue(g.getEdges().contains(ed12));
    }

    @Test
    public void testRemoveEdgeWithNode() throws NodeAlreadyExist {
        g.addNode(n1);
        g.addNode(n2);
        g.addNode(n3);

        g.addEdge(1, 2);

        //g.printMap();

        Assert.assertTrue(g.getEdges().contains(new Edge(1, 2)));

        g.removeEdge(n1, n2);

        //g.printMap();

        Assert.assertFalse(g.getEdges().contains(new Edge(1, 2)));
    }

    @Test
    public void testRemoveEdgeWithInt() throws NodeAlreadyExist {
        g.addNode(n1);
        g.addNode(n2);
        g.addNode(n3);

        g.addEdge(1, 2);

        //g.printMap();

        Assert.assertTrue(g.getEdges().contains(new Edge(1, 2)));

        g.removeEdge(1, 2);

        //g.printMap();

        Assert.assertFalse(g.getEdges().contains(new Edge(1, 2)));
    }

    @Test
    public void testRemoveEdgeWithEdge() throws NodeAlreadyExist {
        g.addNode(n1);
        g.addNode(n2);
        g.addNode(n3);

        Edge ed12 = new Edge(1, 2);
        g.addEdge(ed12);

        //g.printMap();

        Assert.assertTrue(g.getEdges().contains(ed12));

        g.removeEdge(ed12);

        //g.printMap();

        Assert.assertFalse(g.getEdges().contains(ed12));
    }

    @Test
    public void testGetOutEdgeWithNode() {
        //g2Esport.printMap();
        List<Edge> le2 = g2Esport.getOutEdges(n2);

        Assert.assertTrue(le2.contains(new Edge(2, 3)));
        Assert.assertTrue(le2.contains(new Edge(2, 5)));
    }

    @Test
    public void testGetOutEdgeWithInt() {
        //g2Esport.printMap();
        List<Edge> le2 = g2Esport.getOutEdges(2);

        Assert.assertTrue(le2.contains(new Edge(2, 3)));
        Assert.assertTrue(le2.contains(new Edge(2, 5)));
    }

    @Test
    public void testGetInEdgeWithNode() {
        //g2Esport.printMap();
        List<Edge> le3 = g2Esport.getInEdges(n3);

        Assert.assertTrue(le3.contains(new Edge(1, 3)));
        Assert.assertTrue(le3.contains(new Edge(2, 3)));
    }

    @Test
    public void testGetInEdgeWithInt() {
        //g2Esport.printMap();
        List<Edge> le3 = g2Esport.getInEdges(3);

        Assert.assertTrue(le3.contains(new Edge(1, 3)));
        Assert.assertTrue(le3.contains(new Edge(2, 3)));
    }

    @Test
    public void testGetIndicentEdgesWithNode() {
        List<Edge> le3 = g2Esport.getIncidentEdges(n3);
        for (Edge e : le3) {
            System.out.println("{" + e.getFrom() + ", " + e.getTo() + "}");
        }
        Assert.assertTrue(le3.contains(new Edge(1, 3)));
        Assert.assertTrue(le3.contains(new Edge(2, 3)));
        Assert.assertTrue(le3.contains(new Edge(3, 4)));
    }

    @Test
    public void testGetIndicentEdgesWithInt() {
        List<Edge> le3 = g2Esport.getIncidentEdges(5);

        Assert.assertTrue(le3.contains(new Edge(1, 3)));
        Assert.assertTrue(le3.contains(new Edge(2, 3)));
        Assert.assertTrue(le3.contains(new Edge(3, 4)));
    }

    @Test
    public void testGetAllEdges(){
        List<Edge> le = g2Esport.getAllEdges();

        Assert.assertTrue(le.contains(new Edge(1, 2)));
        Assert.assertTrue(le.contains(new Edge(1, 3)));
        Assert.assertTrue(le.contains(new Edge(2, 3)));
        Assert.assertTrue(le.contains(new Edge(2, 5)));
        Assert.assertTrue(le.contains(new Edge(3, 4)));
        Assert.assertTrue(le.contains(new Edge(4, 1)));
        Assert.assertTrue(le.contains(new Edge(4, 5)));
        Assert.assertTrue(le.contains(new Edge(5, 5)));
    }

    @Test
    public void testInDegreeWithNode(){
        Assert.assertEquals(3, g2Esport.inDegree(n5));
    }

    @Test
    public void testInDegreeWithInt(){
        Assert.assertEquals(3, g2Esport.inDegree(5));
    }

    @Test
    public void testOutDegreeWithNode(){
        Assert.assertEquals(1, g2Esport.outDegree(n5));
    }

    @Test
    public void testOutDegreeWithInt(){
        Assert.assertEquals(1, g2Esport.outDegree(5));
    }

    @Test
    public void testDegreeWithNode(){
        Assert.assertEquals(4, g2Esport.degree(n5));
    }

    @Test
    public void testDegreeWithInt(){
        Assert.assertEquals(4, g2Esport.degree(5));
    }

    @Test
    public void testSuccessorArray(){
        int[] SA = g2Esport.toSuccessorArray();
        int[] SACheck = {2,3,0,3,5,0,4,0,1,5,0,5};
//        for(int i = 0; i < SA.length; i++){
//            System.out.print("[" + SA[i] + "]");
//        }
        for(int i = 0; i < SA.length; i++){
            Assert.assertTrue(SA[i] == SACheck[i]);
        }
    }

    @Test(expected = IOException.class)
    public void testImportDotFileDoesNotExist() throws IOException, NodeAlreadyExist {
        Graf g = new Graf("jexistepas.dot");

    }

    @Test
    public void testImportDotFile() throws IOException, NodeAlreadyExist {
        Graf grafFromFile = new Graf("src/main/resources/exempleProf.dot");

        grafFromFile.printMap();

        Assert.assertEquals(grafFromFile.getAllEdges().size(),11);
        Assert.assertEquals(grafFromFile.getAllNodes().size(),8);

        Assert.assertTrue(grafFromFile.existsNode(1));
        Assert.assertTrue(grafFromFile.existsNode(5));

        Assert.assertTrue(grafFromFile.existsEdge(1,4));
        Assert.assertTrue(grafFromFile.existsEdge(6,7));

    }

    @Test
    public void testExportDotFile() throws IOException {
        g2Esport.toDotFile("exportGrafTest.dot");

        File f = new File("exportGrafTest.dot");
        Assert.assertNotNull(f);
        Assert.assertTrue(f.exists());

    }

    @Test
    public void testImportDotFileThenImport() throws IOException, NodeAlreadyExist {
        Graf grafFromExample = new Graf(2, 4, 0, 0, 6, 0, 2, 3, 5, 8, 0, 0, 4, 7, 0, 3, 0, 7, 0);

        grafFromExample.toDotFile("grafFromExample.dot");

        Graf grafFromFile = new Graf("grafFromExample.dot");
        Assert.assertEquals(grafFromFile.getAllEdges().size(),11);
        Assert.assertEquals(grafFromFile.getAllNodes().size(),8);

        Assert.assertTrue(grafFromFile.existsNode(1));
        Assert.assertTrue(grafFromFile.existsNode(5));

        Assert.assertTrue(grafFromFile.existsEdge(1,4));
        Assert.assertTrue(grafFromFile.existsEdge(6,7));

    }

}
