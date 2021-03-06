package m1graf2020;

import maximumflow.AugmentingType;
import maximumflow.Flow;
import maximumflow.ResidualGraf;
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
    Graf g3PasEsport;
    Graf gTest;


    @Before
    public void setup() {
        gTest = new Graf(2, 3, 0, 4, 5, 0, 0, 3, 5, 0, 6, 7, 0, 0, 6, 0, 7, 0);
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
        g2Esport.addEdge(4, 5);
        g2Esport.addEdge(5, 5);

        g3PasEsport = new Graf();

        g3PasEsport.addNode(n1);
        g3PasEsport.addNode(n2);
        g3PasEsport.addNode(n3);
        g3PasEsport.addNode(n4);
        g3PasEsport.addNode(n5);
        g3PasEsport.addNode(6);

        g3PasEsport.addEdge(1, 2);
        g3PasEsport.addEdge(1, 3);
        g3PasEsport.addEdge(2, 4);
        g3PasEsport.addEdge(3, 4);
        g3PasEsport.addEdge(4, 5);
        g3PasEsport.addEdge(4, 6);

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
    public void testExistsNodeWithNode() {
        g.addNode(n1);
        for (Node myNode : g.getNodes()) {
            Assert.assertEquals((myNode.getId() == n1.getId()), g.existsNode(n1));
        }
    }

    @Test
    public void testExistsNodeWithInt() {
        g.addNode(1);

        for (Node myNode : g.getNodes()) {
            Assert.assertTrue(myNode.getId() == 1 == g.existsNode(1));
        }
    }

    @Test
    public void testGetNodeWithInt() {
        g.addNode(n1);
        Node newNode = g.getNode(1);
        Assert.assertSame(newNode, n1);

    }

    @Test
    public void testAddNodeWithInt() {
        g.addNode(1);
        Assert.assertEquals(1, g.nbNodes());
        Assert.assertTrue(g.existsNode(n1));
        Assert.assertTrue(g.existsNode(1));
        //g.printMap();
    }

    @Test
    public void testAddNodeWithNode() {
        g.addNode(n1);
        Assert.assertEquals(1, g.nbNodes());
        Assert.assertTrue(g.existsNode(n1));
        Assert.assertTrue(g.existsNode(1));
        //g.printMap();
    }

    @Test
    @Ignore
    public void testAddNodeExceptionWithInt() {
        g.addNode(2);
        g.addNode(2);
        //g.printMap();
    }

    @Test
    @Ignore
    public void testAddNodeExceptionWithNode() {
        g.addNode(n1);
        g.addNode(n1);
        //g.printMap();
    }

    @Test
    public void testAddNodeAuto() {
        g.addNode();
        g.addNode(2);
        g.addNode();
        g.addNode(10);

        Assert.assertTrue(g.existsNode(1));
        Assert.assertTrue(g.existsNode(3));
        Assert.assertTrue(g.existsNode(10));
        Assert.assertTrue(g.existsNode(n1));
        Assert.assertEquals(4, g.nbNodes());
    }

    @Test
    public void testRemoveNodeWithNode() {
        g.addNode(n1);
        Assert.assertTrue(g.existsNode(n1));
        g.removeNode(n1);
        Assert.assertFalse(g.existsNode(n1));
    }

    @Test
    public void testRemoveNodeWithNode2() {
        g.addNode(1);
        Assert.assertTrue(g.existsNode(1));
        g.removeNode(n1);
        Assert.assertFalse(g.existsNode(n1));
    }

    @Test
    public void testRemoveNodeWithInt() {
        g.addNode(1);
        Assert.assertTrue(g.existsNode(1));
        g.removeNode(1);
        Assert.assertFalse(g.existsNode(1));
    }

    @Test
    public void testRemoveNodeWithInt2() {
        g.addNode(n1);
        Assert.assertTrue(g.existsNode(n1));
        g.removeNode(1);
        Assert.assertFalse(g.existsNode(1));
    }

    @Test
    public void testRemoveNotExisting() {
        g.addNode(n1);
        //g.printMap();
        Assert.assertFalse(g.existsNode(n2));

        g.removeNode(n2);
        //g.printMap();
        Assert.assertFalse(g.existsNode(n2));
    }

    @Test
    public void testGetSuccessorWithNode() {
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
    public void testGetSuccessorWithInt() {
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
    public void testGetSuccessorInexistantWithNode() {
        g.addNode(n1);
        g.addNode(n2);
        g.addNode(n3);

        g.addEdge(n1, n2);
        g.addEdge(n1, n3);
        g.addEdge(n2, n3);

        List<Node> ln = g.getSuccessors(n3);
        Assert.assertTrue(ln.isEmpty());
    }

    @Test
    public void testGetSuccessorInexistantWithInt() {
        g.addNode(n1);
        g.addNode(n2);
        g.addNode(n3);

        g.addEdge(n1, n2);
        g.addEdge(n1, n3);
        g.addEdge(n2, n3);

        List<Node> ln = g.getSuccessors(3);
        Assert.assertTrue(ln.isEmpty());
    }


    @Test
    public void testGetSuccessorNoNodeExistingWithNode() {
        g.addNode(n1);
        g.addNode(n2);
        g.addNode(n3);

        g.addEdge(n1, n2);
        g.addEdge(n1, n3);
        g.addEdge(n2, n3);

        Assert.assertNull(g.getSuccessors(n4));
    }

    @Test
    public void testGetSuccessorNoNodeExistingWithInt() {
        g.addNode(n1);
        g.addNode(n2);
        g.addNode(n3);

        g.addEdge(n1, n2);
        g.addEdge(n1, n3);
        g.addEdge(n2, n3);

        Assert.assertNull(g.getSuccessors(4));
    }

    @Test
    public void testAdjacentWithNode() {
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
    public void testAdjacentWithInt() {
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
    public void testAdjacentWithNodeNonDirected() {
        g.addNode(n1);
        g.addNode(n2);
        g.addNode(n3);

        g.addEdge(n1, n2);
        g.addEdge(n1, n3);

        //g.printMap();

        Assert.assertTrue(g.adjacent(1, 3));
    }

    @Test
    public void testGetAllNodes() {
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
    public void testNbEdges() {
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
    public void testExistsEdgWitheNode() {
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
    public void testExistsEdgeWithInt() {
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
    public void testExistsEdgeWithEdge() {
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
    public void testExistsEdgeWithEdgeAlreadyCreated() {
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
    public void testAddEdgesWithNode() {
        g.addNode(n1);
        g.addNode(n2);
        g.addNode(n3);

        g.addEdge(n1, n2);

        //g.printMap();

        Assert.assertTrue(g.getEdges().contains(new Edge(n1, n2)));
    }

    @Test
    public void testAddEdgesWithInt() {
        g.addNode(n1);
        g.addNode(n2);
        g.addNode(n3);


        g.addEdge(1, 2);

        //g.printMap();

        Assert.assertTrue(g.getEdges().contains(new Edge(1, 2)));
    }

    @Test
    public void testAddEdgesWithEdges() {
        g.addNode(n1);
        g.addNode(n2);
        g.addNode(n3);

        Edge ed12 = new Edge(1, 2);
        g.addEdge(ed12);

        //g.printMap();

        Assert.assertTrue(g.getEdges().contains(ed12));
    }

    @Test
    public void testRemoveEdgeWithNode() {
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
    public void testRemoveEdgeWithInt() {
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
    public void testRemoveEdgeWithEdge() {
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
        /*for (Edge e : le3) {
            System.out.println("{" + e.getFrom() + ", " + e.getTo() + "}");
        }*/
        Assert.assertTrue(le3.contains(new Edge(1, 3)));
        Assert.assertTrue(le3.contains(new Edge(2, 3)));
        Assert.assertTrue(le3.contains(new Edge(3, 4)));
    }

    @Test
    public void testGetIndicentEdgesWithInt() {
        List<Edge> le3 = g2Esport.getIncidentEdges(5);

        Assert.assertTrue(le3.contains(new Edge(2, 5)));
        Assert.assertTrue(le3.contains(new Edge(4, 5)));
        Assert.assertTrue(le3.contains(new Edge(5, 5)));
    }

    @Test
    public void testGetAllEdges() {
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
    public void testInDegreeWithNode() {
        Assert.assertEquals(4, g2Esport.inDegree(n5));
    }

    @Test
    public void testInDegreeWithInt() {
        Assert.assertEquals(4, g2Esport.inDegree(5));
    }

    @Test
    public void testOutDegreeWithNode() {
        Assert.assertEquals(1, g2Esport.outDegree(n5));
    }

    @Test
    public void testOutDegreeWithInt() {
        Assert.assertEquals(1, g2Esport.outDegree(5));
    }

    @Test
    public void testDegreeWithNode() {
        Assert.assertEquals(5, g2Esport.degree(n5));
    }

    @Test
    public void testDegreeWithInt() {
        Assert.assertEquals(5, g2Esport.degree(5));
    }

    @Test
    public void testSuccessorArray() {
        int[] SA = g2Esport.toSuccessorArray();
        int[] SACheck = {2, 3, 0, 3, 5, 0, 4, 0, 1, 5, 5, 0, 5, 0};

//        for(int i = 0; i < SA.length; i++){
//            System.out.print("[" + SA[i] + "]");
//        }

        for (int i = 0; i < SA.length; i++) {
            Assert.assertEquals(SA[i], SACheck[i]);
        }
    }

    @Test
    public void testMatrix() {
        int[][] M = g2Esport.toAdjMatrix();
        int[][] MCheck = {{0, 1, 1, 0, 0}, {0, 0, 1, 0, 1}, {0, 0, 0, 1, 0}, {1, 0, 0, 0, 2}, {0, 0, 0, 0, 1}};
        for (int i = 0; i < MCheck.length; i++) {
            for (int j = 0; j < MCheck.length; j++) {
                Assert.assertTrue(M[i][j] == MCheck[i][j]);
            }
        }
    }

    @Test(expected = IOException.class)
    public void testImportDotFileDoesNotExist() throws IOException {
        Graf g = new Graf("jexistepas.dot");
        //g.printMap();
    }

    @Test
    public void testImportDotFile() throws IOException {
        Graf grafFromFile = new Graf("src/main/resources/exempleProf.dot");

        //grafFromFile.printMap();

        Assert.assertEquals(grafFromFile.getAllEdges().size(), 11);
        Assert.assertEquals(grafFromFile.getAllNodes().size(), 8);

        Assert.assertTrue(grafFromFile.existsNode(1));
        Assert.assertTrue(grafFromFile.existsNode(5));

        Assert.assertTrue(grafFromFile.existsEdge(1, 4));
        Assert.assertTrue(grafFromFile.existsEdge(6, 7));

    }

    @Test
    public void testExportDotFile() throws IOException {
        g2Esport.toDotFile("exportGrafTest.dot");

        File f = new File("exportGrafTest.dot");
        Assert.assertNotNull(f);
        Assert.assertTrue(f.exists());

    }

    @Test
    public void testImportDotFileThenImport() throws IOException {
        Graf grafFromExample = new Graf(2, 4, 0, 0, 6, 0, 2, 3, 5, 8, 0, 0, 4, 7, 0, 3, 0, 7, 0);

        grafFromExample.toDotFile("grafFromExample.dot");

        Graf grafFromFile = new Graf("grafFromExample.dot");
        Assert.assertEquals(grafFromFile.getAllEdges().size(), 11);
        Assert.assertEquals(grafFromFile.getAllNodes().size(), 8);

        Assert.assertTrue(grafFromFile.existsNode(1));
        Assert.assertTrue(grafFromFile.existsNode(5));

        Assert.assertTrue(grafFromFile.existsEdge(1, 4));
        Assert.assertTrue(grafFromFile.existsEdge(6, 7));

    }

    @Test
    public void testImportDotFileWithComa() throws IOException {
        Graf withComa = new Graf("src/main/resources/exempleProfWithComa.dot");
        withComa.printMap();

    }

    @Test
    public void testDFS() {
        Graf grafFromExample = new Graf(2, 4, 0, 0, 6, 0, 2, 3, 5, 8, 0, 0, 4, 7, 0, 3, 0, 7, 0);
        //grafFromExample.getDFS().forEach(System.out::println);
        List<Node> lsWaited = new ArrayList<>();
        lsWaited.add(new Node(1));
        lsWaited.add(new Node(2));
        lsWaited.add(new Node(4));
        lsWaited.add(new Node(3));
        lsWaited.add(new Node(6));
        lsWaited.add(new Node(7));
        lsWaited.add(new Node(5));
        lsWaited.add(new Node(8));

        List<Node> fromBfs = grafFromExample.getDFS();
        Assert.assertEquals(lsWaited.size(), fromBfs.size());
        for (int index = 0; index < fromBfs.size(); index++) {
            Assert.assertEquals(fromBfs.get(index), lsWaited.get(index));
        }
    }

    @Test
    public void testDFSfromTD() throws IOException {
        Graf grafFromExample = new Graf("src/main/resources/exempleTD.dot");
        //grafFromExample.getDFS().forEach(System.out::println);
        List<Node> lsWaited = new ArrayList<>();
        lsWaited.add(new Node(1));
        lsWaited.add(new Node(2));
        lsWaited.add(new Node(4));
        lsWaited.add(new Node(5));
        lsWaited.add(new Node(6));
        lsWaited.add(new Node(7));
        lsWaited.add(new Node(3));
        List<Node> fromBfs = grafFromExample.getDFS();
        Assert.assertEquals(lsWaited.size(), fromBfs.size());
        for (int index = 0; index < fromBfs.size(); index++) {
            Assert.assertEquals(fromBfs.get(index), lsWaited.get(index));
        }
    }

    @Test
    public void testBFS() {
        Graf grafFromExample = new Graf(2, 4, 0, 0, 6, 0, 2, 3, 5, 8, 0, 0, 4, 7, 0, 3, 0, 7, 0);
        List<Node> lsWaited = new ArrayList<>();
        lsWaited.add(new Node(1));
        lsWaited.add(new Node(2));
        lsWaited.add(new Node(4));
        lsWaited.add(new Node(3));
        lsWaited.add(new Node(5));
        lsWaited.add(new Node(8));
        lsWaited.add(new Node(6));
        lsWaited.add(new Node(7));
        //grafFromExample.getBFS().forEach(System.out::println);
        gTest.getBFS().forEach(System.out::println);
        List<Node> fromBfs = grafFromExample.getBFS();
        Assert.assertEquals(lsWaited.size(), fromBfs.size());
        for (int index = 0; index < fromBfs.size(); index++) {
            Assert.assertEquals(fromBfs.get(index), lsWaited.get(index));
        }
    }

    @Test
    public void testBFSfromTD() throws IOException {
        Graf grafFromExample = new Graf("src/main/resources/exempleTD.dot");
        //grafFromExample.getBFS().forEach(System.out::println);
        List<Node> lsWaited = new ArrayList<>();
        lsWaited.add(new Node(1));
        lsWaited.add(new Node(2));
        lsWaited.add(new Node(3));
        lsWaited.add(new Node(4));
        lsWaited.add(new Node(5));
        lsWaited.add(new Node(6));
        lsWaited.add(new Node(7));
        List<Node> fromBfs = grafFromExample.getBFS();
        Assert.assertEquals(lsWaited.size(), fromBfs.size());
        for (int index = 0; index < fromBfs.size(); index++) {
            Assert.assertEquals(fromBfs.get(index), lsWaited.get(index));
        }
    }

    @Test
    public void testReverseGraf() throws IOException {
        Graf grafFromExample = new Graf("src/main/resources/exempleTD.dot");
        Graf reversedGraf = grafFromExample.getReverse();
        reversedGraf.printMap();
        reversedGraf.toDotFile("oui.dot");
        Assert.assertTrue(reversedGraf.existsEdge(2, 1));
        Assert.assertTrue(reversedGraf.existsEdge(3, 1));
        Assert.assertTrue(reversedGraf.existsEdge(4, 2));
        Assert.assertTrue(reversedGraf.existsEdge(5, 2));
        Assert.assertTrue(reversedGraf.existsEdge(5, 4));
        Assert.assertTrue(reversedGraf.existsEdge(6, 5));
        Assert.assertTrue(reversedGraf.existsEdge(7, 5));
        Assert.assertTrue(reversedGraf.existsEdge(6, 7));
        Assert.assertTrue(reversedGraf.existsEdge(7, 8));

        Assert.assertFalse(reversedGraf.existsEdge(8, 7));

        Assert.assertTrue(reversedGraf.existsNode(1));
        Assert.assertTrue(reversedGraf.existsNode(2));
        Assert.assertTrue(reversedGraf.existsNode(3));
        Assert.assertTrue(reversedGraf.existsNode(4));
        Assert.assertTrue(reversedGraf.existsNode(5));
        Assert.assertTrue(reversedGraf.existsNode(6));
        Assert.assertTrue(reversedGraf.existsNode(7));
        Assert.assertTrue(reversedGraf.existsNode(8));
    }

    @Test
    public void testGetTransitiveClosur() {
        //g3PasEsport.printMap();
        Graf gTransi = g3PasEsport.getTransitiveClosure();

        gTransi.printMap();

        Assert.assertTrue(gTransi.existsNode(1));
        Assert.assertTrue(gTransi.existsNode(2));
        Assert.assertTrue(gTransi.existsNode(3));
        Assert.assertTrue(gTransi.existsNode(4));
        Assert.assertTrue(gTransi.existsNode(5));
        Assert.assertTrue(gTransi.existsNode(6));

        Assert.assertTrue(gTransi.existsEdge(1, 2));
        Assert.assertTrue(gTransi.existsEdge(1, 3));
        Assert.assertTrue(gTransi.existsEdge(1, 4));
        Assert.assertTrue(gTransi.existsEdge(1, 4));
        Assert.assertTrue(gTransi.existsEdge(1, 5));
        Assert.assertTrue(gTransi.existsEdge(1, 6));
        Assert.assertTrue(gTransi.existsEdge(2, 4));
        Assert.assertTrue(gTransi.existsEdge(2, 5));
        Assert.assertTrue(gTransi.existsEdge(2, 6));
        Assert.assertTrue(gTransi.existsEdge(3, 4));
        Assert.assertTrue(gTransi.existsEdge(3, 5));
        Assert.assertTrue(gTransi.existsEdge(3, 6));
        Assert.assertTrue(gTransi.existsEdge(4, 5));
        Assert.assertTrue(gTransi.existsEdge(4, 6));
    }

    @Test
    public void testRemoveMultipleEdge() {
        Graf graf = new Graf();
        graf.addEdge(1, 2);
        graf.addEdge(1, 2);
        graf.removeEdge(1, 2);
        Assert.assertTrue(graf.existsEdge(1, 2));
    }

    @Test
    public void testWeighted() throws IOException {
        Graf graf = new Graf("src/main/resources/exempleWeighted.dot", true);
    }

    @Test
    public void testFlowToResidual() {
        Flow f = new Flow();
        f.initExampleFlow();
        ResidualGraf rg = f.createResidualFromFlow();
        System.out.println(rg.toDotString());
    }

    @Test
    public void testResidualToFlow() {
        Flow f = new Flow();
        f.initExampleFlow();
        ResidualGraf rg = f.createResidualFromFlow();
        System.out.println(rg.toDotString());
        rg.updateFlowFromResidual(f, AugmentingType.BFS);
        System.out.println(f.toDotString());
        ResidualGraf rg2 = f.createResidualFromFlow();
        System.out.println(rg2.toDotString());
        rg2.updateFlowFromResidual(f, AugmentingType.BFS);
        System.out.println(f.toDotString(2));
        ResidualGraf rg3 = f.createResidualFromFlow();
        System.out.println(rg3.toDotString());
        rg3.updateFlowFromResidual(f, AugmentingType.BFS);
        System.out.println(f.toDotString(2));
        ResidualGraf rg4 = f.createResidualFromFlow();
        System.out.println(rg4.toDotString());
    }

    @Test
    public void testMaximumFlowLecture() throws IOException {
        Flow f = new Flow();
        f.initExampleFlow();
        f.FordFulkersonAlgorithm(AugmentingType.BFS);
        System.out.println(f.toDotString());
    }
}
