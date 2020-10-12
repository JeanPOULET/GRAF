package m1graf2020;

import m1graf2020.Exceptions.NodeAlreadyExist;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class main {
    public static void main(String args[]) throws NodeAlreadyExist, IOException {

        System.out.println("c'est g");
        Graf g = new Graf(2, 4, 0, 0, 6, 0, 2, 3, 5, 8, 0, 0, 4, 7, 0, 3, 0, 7, 0);



        g.printMap();


        g.toDotFile("jambon.dot");

        Graf fromDotFile = new Graf("jambon.dot");
        System.out.println("c'est fromDot");

        fromDotFile.printMap();


    }
}
