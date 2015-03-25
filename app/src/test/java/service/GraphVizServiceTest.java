package service;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class GraphVizServiceTest {
    private GraphVizService service;

    @Before
    public void setUp() {
        service = new GraphVizService(new CacheService());
    }

    @Test
    public void testGraphVizPresent() {
        System.out.println(service.getGraphVizVersion());
        assertTrue(service.isGraphVizInstalled());
    }

    @Test
    public void testGenerateGraph() throws Exception {
        byte[] data = service.generateGraph("A--B--C");
        PlantUmlServiceTest.assertReadablePngImage(data);
    }

    @Test
    public void testGenerateDigraph() throws Exception {
        byte[] data = service.generateDigraph("A->B->C");
        PlantUmlServiceTest.assertReadablePngImage(data);
    }
}