package cs2114.mazesolver;

import student.TestCase;

// -------------------------------------------------------------------------
/**
 * Test class for Maze class.
 *
 * @author Sheng Zhou (zsheng2)
 * @version Mar 2, 2013
 */
public class MazeTest
    extends TestCase
{
    private Maze maze1;
    private Maze maze2;
    private Maze maze3;
    private Maze maze4;

    // ----------------------------------------------------------
    /**
     * Sets up the test fixture. Called before every test case method.
     */
    public void setUp()
    {
        maze1 = new Maze(5);
        maze1.setCell(new Location(0, 1), MazeCell.WALL);
        maze1.setCell(new Location(1, 1), MazeCell.WALL);
        maze1.setCell(new Location(3, 0), MazeCell.WALL);
        maze1.setCell(new Location(3, 1), MazeCell.WALL);
        maze1.setCell(new Location(3, 2), MazeCell.WALL);
        maze1.setCell(new Location(3, 3), MazeCell.WALL);
        maze1.setCell(new Location(2, 3), MazeCell.WALL);
        maze1.setCell(new Location(1, 3), MazeCell.WALL);

        maze2 = new Maze(4);
        maze2.setCell(new Location(0, 1), MazeCell.WALL);
        maze2.setCell(new Location(1, 2), MazeCell.WALL);
        maze2.setCell(new Location(2, 3), MazeCell.WALL);
        maze2.setCell(new Location(3, 2), MazeCell.WALL);

        maze3 = new Maze(2);
        maze3.setGoalLocation(new Location(0, 0));

        maze4 = new Maze(5);
        maze4.setStartLocation(new Location(0, 4));
        maze4.setGoalLocation(new Location(4, 3));
        maze4.setCell(new Location(0, 1), MazeCell.WALL);
        maze4.setCell(new Location(1, 1), MazeCell.WALL);
        maze4.setCell(new Location(3, 0), MazeCell.WALL);
        maze4.setCell(new Location(3, 1), MazeCell.WALL);
        maze4.setCell(new Location(3, 2), MazeCell.WALL);
        maze4.setCell(new Location(3, 3), MazeCell.WALL);
        maze4.setCell(new Location(2, 3), MazeCell.WALL);
        maze4.setCell(new Location(1, 3), MazeCell.WALL);
    }


    /**
     * The method is to test getStartLocation.
     */
    public void testGetStartLocation()
    {
        Location start1 = new Location(0, 0);
        assertEquals(start1, maze1.getStartLocation());
    }


    /**
     * The method is to test getGoalLocation.
     */
    public void testGetGoalLocation()
    {
        Location goal2 = new Location(3, 3);
        assertEquals(goal2, maze2.getGoalLocation());
    }


    /**
     * The method is to test getCell function.
     */
    public void testGetCell()
    {
        Location cell1 = new Location(1, 3);
        assertEquals(maze1.getCell(cell1), MazeCell.WALL);
        assertEquals(maze1.getCell(new Location(0, 5)), MazeCell.INVALID_CELL);
    }


    /**
     * The method is to test setStartLocation.
     */
    public void testSetStartLocation()
    {
        Location start2 = new Location(1, 1);
        maze2.setCell(start2, MazeCell.WALL);
        maze2.setStartLocation(start2);
        assertEquals(maze2.getStartLocation(), start2);
        assertEquals(maze2.getCell(start2), MazeCell.UNEXPLORED);

        maze2.setCell(start2, MazeCell.INVALID_CELL);
        maze2.setStartLocation(start2);
        assertEquals(maze2.getStartLocation(), start2);
        assertEquals(maze2.getCell(start2), MazeCell.INVALID_CELL);
    }


    /**
     * The method is to test setGoalLocation.
     */
    public void testSetGoalLocation()
    {
        Location goal2 = new Location(2, 2);
        maze2.setCell(goal2, MazeCell.WALL);
        maze2.setGoalLocation(goal2);
        assertTrue(maze2.getGoalLocation().equals(goal2));
        assertEquals(maze2.getCell(goal2), MazeCell.UNEXPLORED);

        maze2.setCell(goal2, MazeCell.INVALID_CELL);
        maze2.setGoalLocation(goal2);
        assertEquals(maze2.getGoalLocation(), goal2);
        assertEquals(maze2.getCell(goal2), MazeCell.INVALID_CELL);
    }


    /**
     * The method is to test setCell.
     */
    public void testSetCell()
    {
        maze1.setCell(new Location(1, 1), MazeCell.FAILED_PATH);
        assertEquals(MazeCell.FAILED_PATH, maze1.getCell(new Location(1, 1)));

        maze1.setCell(maze1.getGoalLocation(), MazeCell.WALL);
        assertEquals(
            MazeCell.UNEXPLORED,
            maze1.getCell(maze1.getGoalLocation()));
    }


    /**
     * The method is to test size function.
     */
    public void testSize()
    {
        assertEquals(4, maze2.size());
    }


    /**
     * The metho is to test isValid.
     */
    public void testCanGo()
    {
        Location loc = new Location(1, 1);
        maze1.setCell(loc, MazeCell.UNEXPLORED);
        assertTrue(maze1.canGo(loc));
        Location loc1 = new Location(-1, 7);
        assertFalse(maze1.canGo(loc1));
    }


    /**
     * The method is to test solve.
     */
    public void testSolve()
    {
        String solution1 =
            "(0, 0) (1, 0) (2, 0) (2, 1) (2, 2) (1, 2) (0, 2) (0, 3) "
                + "(0, 4) (1, 4) (2, 4) (3, 4) (4, 4)";
        assertEquals(maze1.solve(), solution1);

        String solution2 = null;
        assertEquals(maze2.solve(), solution2);

        String solution3 = "(0, 0)";
        assertEquals(maze3.solve(), solution3);

        String solution4 = "(0, 4) (1, 4) (2, 4) (3, 4) (4, 4) (4, 3)";
        assertEquals(maze4.solve(), solution4);

    }
}
