package m1graf2020;

import m1graf2020.Exceptions.NodeAlreadyExist;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class main {
    public static void main(String args[]) throws NodeAlreadyExist {

        Graf g = new Graf(2, 4, 0, 0, 6, 0, 2, 3, 5, 8, 0, 0, 4, 7, 0, 3, 0, 7, 0);



        g.printMap();

        System.out.println(g.toDotString());



    }
}
