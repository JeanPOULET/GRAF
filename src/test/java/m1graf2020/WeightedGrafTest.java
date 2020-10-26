package m1graf2020;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class WeightedGrafTest {
    WeightedGraf graf;

    @Before
    public void setup(){

    }

    @Test
    public void importDotFile() throws IOException {
        graf = new WeightedGraf("src/main/resources/exempleWeightedGraf.dot");
        System.out.print(graf.toDotString());
    }
}
