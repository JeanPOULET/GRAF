package m1graf2020;

import static org.junit.Assert.assertTrue;

import m1graf2020.Exceptions.NodeAlreadyExist;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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

    @Before
    public void setup(){
        g = new Graf();
        n1 = new Node(1);
    }


    @Test
    public void testNbNodesVide(){
        Set<Node> ln = g.getNodes();
        Assert.assertTrue(ln.size() == 0);
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
        g.addNode(n1);
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




}
