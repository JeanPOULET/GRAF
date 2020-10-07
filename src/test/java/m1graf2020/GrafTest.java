package m1graf2020;

import static org.junit.Assert.assertTrue;

import m1graf2020.Exceptions.NodeAlreadyExist;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
    public void testAddNodeWithInt() throws NodeAlreadyExist {
        g.addNode(1);
        Assert.assertTrue(g.nbNodes() == 1);
        Assert.assertTrue(g.existsNode(1));
        g.printMap();
    }

    @Test
    public void testAddNodeWithNode() throws NodeAlreadyExist {
        g.addNode(n1);
        Assert.assertTrue(g.nbNodes() == 1);
        Assert.assertTrue(g.existsNode(n1));
        Assert.assertTrue(g.existsNode(1));
        g.printMap();
    }

//    @Test (expected = NodeAlreadyExist.class)
//    public void testAddNodeExceptionWithInt() throws NodeAlreadyExist {
//        g.addNode(2);
//    }
//
//    @Test (expected = NodeAlreadyExist.class)
//    public void testAddNodeExceptionWithNode() throws NodeAlreadyExist {
//        g.addNode(n1);
//    }


}
