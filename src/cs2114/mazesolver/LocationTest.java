package cs2114.mazesolver;

import student.TestCase;

// -------------------------------------------------------------------------
/**
 * Test class for Location class.
 *
 * @author Sheng Zhou (zsheng2)
 * @version Feb 20, 2013
 */

public class LocationTest
    extends TestCase
{
    private Location test1;
    private Location test2;


    // ----------------------------------------------------------
    /**
     * Sets up the test fixture. Called before every test case method.
     */
    public void setUp()
    {
        test1 = new Location(1, 1);
        test2 = new Location(2, 2);
    }


    /**
     * The method is to test x class.
     */
    public void testX()
    {
        assertEquals(1, test1.x());
        assertEquals(2, test2.x());
    }


    /**
     * The method is to test y class.
     */
    public void testY()
    {
        assertEquals(1, test1.y());
        assertEquals(2, test2.y());
    }


    // ----------------------------------------------------------
    /**
     * The test case for equals() class.
     */
    public void testEquals()
    {
        assertFalse(test1.equals("ample, signal"));
        assertEquals(new Location(1, 1), test1);
    }


    /**
     * The test case for east class.
     */
    public void testEast()
    {
        Location test1e = new Location(2, 1);
        assertEquals(test1e, test1.east());
    }


    /**
     * The test case for east class.
     */
    public void testWest()
    {
        Location test1w = new Location(0, 1);
        assertEquals(test1w, test1.west());
    }


    /**
     * The test case for east class.
     */
    public void testSouth()
    {
        Location test1s = new Location(1, 2);
        assertEquals(test1s, test1.south());
    }


    /**
     * The test case for east class.
     */
    public void testNorth()
    {
        Location test1n = new Location(1, 0);
        assertEquals(test1n, test1.north());
    }


    // ----------------------------------------------------------
    /**
     * The test case for toString() class.
     */
    public void testToString()
    {
        assertEquals("(1, 1)", test1.toString());
        assertEquals("(2, 2)", test2.toString());
    }
}
