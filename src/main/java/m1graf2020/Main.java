package m1graf2020;


import java.io.IOException;


public class Main {
    public static void main(String args[]) throws IOException {

        System.out.println("c'est g");
        Graf g = new Graf(2, 4, 0, 0, 6, 0, 2, 3, 5, 8, 0, 0, 4, 7, 0, 3, 0, 7, 0);



        g.printMap();


        g.toDotFile("jambon.dot");

        Graf fromDotFile = new Graf("jambon.dot");
        System.out.println("c'est fromDot");

        fromDotFile.printMap();


    }
}
