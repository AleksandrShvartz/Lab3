package ru.spbstu.telematics.java;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import main.ru.spbstu.telematics.java.Road;

/**
 * Unit test .
 */
public class RoadTest
        extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public RoadTest(String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( RoadTest.class );
    }


    @org.junit.Test
    public void testA()
    {
        Road road = new Road();
        road.getController().createCar('n', 's',  0);
        road.getController().createCar('e', 'w',  1);
        road.getController().createCar('e', 's',  2);
        road.getController().createCar('s', 'n',  3);
        road.getController().createCar('w', 'e',  4);

        road.getController().start();
        road.getController().runCars();
        while(road.getController().isAlive());
    }
}
