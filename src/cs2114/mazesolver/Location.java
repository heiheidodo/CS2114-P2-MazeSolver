package cs2114.mazesolver;

// -------------------------------------------------------------------------
/**
 * Write a one-sentence summary of your class here. Follow it with additional
 * details about its purpose, what abstraction it represents, and how to use it.
 *
 * @author Sheng Zhou
 * @version Feb 20, 2013
 */
public class Location
    implements ILocation
{
    private int xvalue;
    private int yvalue;


    // ----------------------------------------------------------
    /**
     * Create a new Location object.
     *
     * @param xvalue
     *            x-coordinate value
     * @param yvalue
     *            y-coordinate value
     */
    public Location(int xvalue, int yvalue)
    {
        this.xvalue = xvalue;
        this.yvalue = yvalue;
    }


    /**
     * The method is to return x value of the location.
     *
     * @return x value.
     */
    public int x()
    {
        return xvalue;
    }


    /**
     * The method is to return y value of the location.
     *
     * @return y value.
     */
    public int y()
    {
        return yvalue;
    }


    /**
     * The method is for check if alocation has the type location and compare
     * both x and y values with the current one.
     *
     * @param alocation
     *            the location to compare
     * @return true if both have same coordinates, otherwise false.
     */
    public boolean equals(Object alocation)
    {
        if (alocation instanceof Location
            && (((Location)alocation).x() == xvalue)
            && (((Location)alocation).y() == yvalue))
        {
            return true;
        }
        return false;
    }


    /**
     * The method is to go east.
     *
     * @return east location
     */
    public Location east()
    {
        return new Location(xvalue + 1, yvalue);
    }


    /**
     * The method is to go west.
     *
     * @return west location
     */
    public Location west()
    {
        return new Location(xvalue - 1, yvalue);
    }


    /**
     * The method is to go south.
     *
     * @return south location
     */
    public Location south()
    {
        return new Location(xvalue, yvalue + 1);
    }


    /**
     * The method is to go north.
     *
     * @return north location
     */
    public Location north()
    {
        return new Location(xvalue, yvalue - 1);
    }


    /**
     * The method is to return a message.
     *
     * @return message.
     */
    public String toString()
    {
        return "(" + xvalue + ", " + yvalue + ")";
    }

}
