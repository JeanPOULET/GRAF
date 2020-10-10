package m1graf2020;

import m1graf2020.Exceptions.NodeAlreadyExist;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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

    Graf g2Esport;


    @Before
    public void setup() throws NodeAlreadyExist {
        g = new Graf();
        n1 = new Node(1);
        n2 = new Node(2);
        n3 = new Node(3);

        g2Esport = new Graf();
        g2Esport.addNode();
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
    public void testGetNodeWithInt()throws NodeAlreadyExist {
        g.addNode(n1);
        Node newNode = g.getNode(1);
        Assert.assertTrue(newNode == n1);

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
    public void testAddNodeExceptionWithInt() throws NodeAlreadyExist {
        g.addNode(2);
        g.addNode(2);
        //g.printMap();
    }

    @Test(expected = NodeAlreadyExist.class)
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

        Edge ed12 = new Edge(1,2);
        g.addEdge(ed12);

        //g.printMap();

        Assert.assertTrue(g.getEdges().contains(ed12));

        g.removeEdge(ed12);

        //g.printMap();

        Assert.assertFalse(g.getEdges().contains(ed12));
    }

    @Test
    public void testGetOutEdgeWithNode() throws NodeAlreadyExist {
        g2Esport.printMap();
        List<Edge> le1 = g2Esport.getOutEdges(n2);
        for(Edge e : le1){
            System.out.print("{" + e.getFrom() + ", " + e.getTo() + "}");
        }
    }
}
