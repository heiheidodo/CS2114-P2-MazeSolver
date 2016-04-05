package cs2114.mazesolver;

import sofia.graphics.RectangleShape;

/**
 * // -------------------------------------------------------------------------
/**
 *  This class is create a new Tile screen
 *
 * @author Sheng Zhou (zsheng2)
 * @version 2013.03.28
 */
public class Tile
    extends RectangleShape
{

    /**
     * Create a new Tile object.
     *
     * @param left
     *            the left coordinate
     * @param top
     *            the top coordinate
     * @param right
     *            the right coordinate
     * @param bottom
     *            the bottom coordinate
     */
    public Tile(float left, float top, float right, float bottom)
    {
        super(left, top, right, bottom);
        setFilled(true);
    }

}
