package m1graf2020;


import m1graf2020.Exceptions.NodeAlreadyExist;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Node {

    private int id;

    public Node(int id) {
        this.id = id;
    }

    public Node() {
        this.id = Graf.indexToUse();
    }

    @Override
    public boolean equals(Object o){
        int i = (int) o;
        return i == this.id;
    }


    public int getId(){
        return this.id;
    }

}
