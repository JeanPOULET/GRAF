package m1graf2020;

import static org.junit.Assert.assertTrue;

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
public class GrafTest
{
    /**
     * Rigorous Test :-)
     */

    Graf g;
    Node n1;
    Node n2;
    Node n3;

    @Before
    public void setup(){
        g = new Graf();
        n1 = new Node(1);
        n2 = new Node(2);
        n3 = new Node(3);
    }


    @Test
    public void testNbNodesVide(){
        Set<Node> ln = g.getNodes();
        Assert.assertTrue(ln.size() == g.nbNodes());
    }

    @Test
    public void testNbNodesNonVide(){
        g.addNode();
        g.addNode();
        Set<Node> ln = g.getNodes();
        Assert.assertTrue(ln.size() == g.nbNodes());
    }

    @Test
    public void testExistsNodeWithNode() throws NodeAlreadyExist {
        g.addNode(n1);
        for (Node myNode : g.getNodes()){
            Assert.assertTrue((myNode.getId() == n1.getId()) == g.existsNode(n1));
        }
    }

    @Test
    public void testExistsNodeWithInt() throws NodeAlreadyExist {
        g.addNode(1);
        for (Node myNode : g.getNodes()){
            Assert.assertTrue((myNode.getId() == n1.getId()) == g.existsNode(1));
        }
    }

    @Test
    public void testAddNodeWithInt() throws NodeAlreadyExist {
        g.addNode(1);
        Assert.assertTrue(g.nbNodes() == 1);
        Assert.assertTrue(g.existsNode(n1));
        Assert.assertTrue(g.existsNode(1));
        //g.printMap();
    }

    @Test
    public void testAddNodeWithNode() throws NodeAlreadyExist {
        g.addNode(n1);
        Assert.assertTrue(g.nbNodes() == 1);
        Assert.assertTrue(g.existsNode(n1));
        Assert.assertTrue(g.existsNode(1));
        //g.printMap();
    }

    @Test (expected = NodeAlreadyExist.class)
    public void testAddNodeExceptionWithInt() throws NodeAlreadyExist {
        g.addNode(2);
        g.addNode(2);
        //g.printMap();
    }

    @Test (expected = NodeAlreadyExist.class)
    public void testAddNodeExceptionWithNode() throws NodeAlreadyExist {
        g.addNode(n1);
        g.addNode(n1);
        //g.printMap();
    }

    @Test
    public void testAddNodeAuto(){
        g.addNode();
        Assert.assertTrue(g.existsNode(1));
        Assert.assertTrue(g.existsNode(n1));
        Assert.assertTrue(g.nbNodes() == 1);
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

        g.addEdge(n1,n2);
        g.addEdge(n1,n3);
        g.addEdge(n2,n3);

        //g.printMap();

        List<Node> lnCheck = new ArrayList<>();
        lnCheck.add(n2);
        lnCheck.add(n3);
        List<Node> ln = g.getSuccessors(n1);
        for(int i = 0; i < ln.size(); i++){
            Assert.assertTrue(ln.get(i).getId() == lnCheck.get(i).getId());
        }
    }

    @Test
    public void testGetSuccessorWithInt()throws NodeAlreadyExist {
        g.addNode(n1);
        g.addNode(n2);
        g.addNode(n3);

        g.addEdge(n1,n2);
        g.addEdge(n1,n3);
        g.addEdge(n2,n3);

        //g.printMap();

        List<Node> lnCheck = new ArrayList<>();
        lnCheck.add(n2);
        lnCheck.add(n3);
        List<Node> ln = g.getSuccessors(1);
        for(int i = 0; i < ln.size(); i++){
            Assert.assertTrue(ln.get(i).getId() == lnCheck.get(i).getId());
        }
    }

    @Test
    public void testGetSuccessorInexistant()throws NodeAlreadyExist {
        g.addNode(n1);
        g.addNode(n2);
        g.addNode(n3);

        g.addEdge(n1,n2);
        g.addEdge(n1,n3);
        g.addEdge(n2,n3);

        //g.printMap();

        List<Node> lnCheck = new ArrayList<>();
        lnCheck.add(n2);
        lnCheck.add(n3);
        List<Node> ln = g.getSuccessors(4);
        for(int i = 0; i < ln.size(); i++){
            Assert.assertTrue(ln.get(i).getId() == lnCheck.get(i).getId());
        }
    }

    @Test
    public void testAdjacentWithNode()throws NodeAlreadyExist {
        g.addNode(n1);
        g.addNode(n2);
        g.addNode(n3);

        g.addEdge(n1,n2);
        g.addEdge(n1,n3);

        //g.printMap();

        Assert.assertTrue(g.adjacent(n1,n3));
        Assert.assertFalse(g.adjacent(n3,n2));
    }

    @Test
    public void testAdjacentWithInt()throws NodeAlreadyExist {
        g.addNode(n1);
        g.addNode(n2);
        g.addNode(n3);

        g.addEdge(n1,n2);
        g.addEdge(n1,n3);

        //g.printMap();

        Assert.assertTrue(g.adjacent(1,3));
        Assert.assertFalse(g.adjacent(3,2));
    }

    @Test
    public void testAdjacentWithNodeNonDirected()throws NodeAlreadyExist {
        g.addNode(n1);
        g.addNode(n2);
        g.addNode(n3);

        g.addEdge(n1,n2);
        g.addEdge(n1,n3);

        //g.printMap();

        Assert.assertTrue(g.adjacent(1,3));
        Assert.assertTrue(g.adjacent(3,1));
    }

    @Test
    public void testGetAllNodes()throws NodeAlreadyExist {
        g.addNode(n1);
        g.addNode(n2);
        g.addNode(n3);

        List<Node> lnCheck = new ArrayList<>();
        lnCheck.add(n1);
        lnCheck.add(n2);
        lnCheck.add(n3);
        List<Node> ln = g.getAllNodes();
        Assert.assertTrue(ln.size() == lnCheck.size());
        for(int i = 0; i < ln.size(); i++){
            Assert.assertTrue(ln.get(i).getId() == lnCheck.get(i).getId());
        }
    }

    @Test
    public void testNbEdges()throws NodeAlreadyExist {
        g.addNode(n1);
        g.addNode(n2);
        g.addNode(n3);

        g.addEdge(n1,n2);
        g.addEdge(n1,n3);
        g.addEdge(n2,n3);

        //g.printMap();

        Assert.assertTrue(g.nbEdges() == 3);
    }

    @Test
    public void testNbEdgesEmpty(){
        Assert.assertTrue(g.nbEdges() == 0);
    }

    @Test
    public void testExistsEdgWitheNode()throws NodeAlreadyExist {
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
    public void testExistsEdgeWithInt()throws NodeAlreadyExist {
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
    public void testExistsEdgeWithEdge()throws NodeAlreadyExist {
        g.addNode(n1);
        g.addNode(n2);
        g.addNode(n3);

        g.addEdge(n1, n2);
        g.addEdge(n1, n3);

        //g.printMap();
        Edge ed12 = new Edge(1,2);
        Edge ed32 = new Edge(3,2);
        Assert.assertTrue(g.existsEdge(ed12));
        Assert.assertFalse(g.existsEdge(ed32));
    }

    @Test
    public void testExistsEdgeWithEdgeAlreadyCreated()throws NodeAlreadyExist {
        g.addNode(n1);
        g.addNode(n2);
        g.addNode(n3);

        Edge ed12 = new Edge(1,2);

        g.addEdge(ed12);
        g.addEdge(n1, n3);

        //g.printMap();

        Edge ed32 = new Edge(3,2);
        Assert.assertTrue(g.existsEdge(ed12));
        Assert.assertFalse(g.existsEdge(ed32));
    }
}
