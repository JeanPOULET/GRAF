package m1graf2020;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UndirectedGrafTest {
    UndirectedGraf uGraf;
    Edge e1 = new Edge(1,2);
    Edge e2 = new Edge(3,2);
    Edge e3 = new Edge(1,3);
    Edge reverseE1 = new Edge(2,1);
    Edge reverseE2 = new Edge(2,3);
    Edge reverseE3 = new Edge(3,1);


    @Before
    public void setup(){
        uGraf = new UndirectedGraf();
        uGraf.addEdge(e1);
        uGraf.addEdge(e2);
        uGraf.addEdge(e3);
    }

    @Test
    public void addSomeEdges(){


        Assert.assertTrue(uGraf.existsEdge(e1));
        Assert.assertTrue(uGraf.existsEdge(e2));
        Assert.assertTrue(uGraf.existsEdge(e3));

        Assert.assertTrue(uGraf.existsEdge(reverseE1));
        Assert.assertTrue(uGraf.existsEdge(reverseE2));
        Assert.assertTrue(uGraf.existsEdge(reverseE3));

    }

    @Test
    public void getSuccesors(){
        List<Node> containedNodes = new ArrayList<>();
        containedNodes.add(new Node(1));
        containedNodes.add(new Node(3));

        Assert.assertEquals(uGraf.getSuccessors(1).size(),2);
        Assert.assertEquals(uGraf.getSuccessors(2).size(),2);
        Assert.assertEquals(uGraf.getSuccessors(3).size(),2);

        List<Node> succesorsNodes = uGraf.getSuccessors(2);
        containedNodes.forEach(n -> Assert.assertTrue(succesorsNodes.contains(n)));
    }

    @Test
    public void getIncidentEdges(){
        List<Edge>containedEdges = new ArrayList<>();
        containedEdges.add(e1);
        containedEdges.add(e2);

        containedEdges.add(reverseE1);
        containedEdges.add(reverseE2);

        List<Edge> incidentEdges = uGraf.getIncidentEdges(2);
        Assert.assertEquals(incidentEdges.size(),4);
        containedEdges.forEach(e -> Assert.assertTrue(incidentEdges.contains(e)));
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
        uGraf.toDotFile("exportUndirectedGrafTest.dot");

        File f = new File("exportUndirectedGrafTest.dot");
        Assert.assertNotNull(f);
        Assert.assertTrue(f.exists());

    }

    @Test
    public void testImportDotFileThenImport() throws IOException {
        Graf grafFromExample = new Graf(2, 4, 0, 0, 6, 0, 2, 3, 5, 8, 0, 0, 4, 7, 0, 3, 0, 7, 0);

        grafFromExample.toDotFile("UndirectedGrafFromExample.dot");

        Graf grafFromFile = new Graf("UndirectedGrafFromExample.dot");
        Assert.assertEquals(grafFromFile.getAllEdges().size(), 11);
        Assert.assertEquals(grafFromFile.getAllNodes().size(), 8);

        Assert.assertTrue(grafFromFile.existsNode(1));
        Assert.assertTrue(grafFromFile.existsNode(5));

        Assert.assertTrue(grafFromFile.existsEdge(1, 4));
        Assert.assertTrue(grafFromFile.existsEdge(6, 7));

    }

    @Test
    public void testDFS()  {
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
    public void testBFS()  {
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

}
