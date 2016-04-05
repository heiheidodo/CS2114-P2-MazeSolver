package cs2114.mazesolver;

import android.widget.TextView;
import android.widget.Button;
import sofia.graphics.ShapeView;

// -------------------------------------------------------------------------
/**
 * The class is a test class for MazeSloverScreen.
 *
 * @author Sheng Zhou
 * @version 2013.03.28
 */
public class MazeSolverScreenTests
    extends student.AndroidTestCase<MazeSolverScreen>
{
    // ~ Fields ................................................................

    // References to the widgets that you have in your layout. They
    // will be automatically filled in by the test class before your
    // tests run.
    private ShapeView shapeView;
    private Button    drawWalls;
    private Button    eraseWalls;
    private Button    setStart;
    private Button    setGoal;
    private Button    solve;
    private TextView  infoLabel;
    private IMaze     testMaze;

    // This field will store the pixel width/height of a cell in the maze.
    private int       cellSize;


    // ~ Constructors ..........................................................

    // ----------------------------------------------------------
    /**
     * Test cases that extend AndroidTestCase must have a parameterless
     * constructor that calls super() and passes it the screen/activity class
     * being tested.
     */
    public MazeSolverScreenTests()
    {
        super(MazeSolverScreen.class);
    }


    // ~ Public methods ........................................................

    // ----------------------------------------------------------
    /**
     * Initializes the text fixtures.
     */
    public void setUp()
    {
        float viewSize =
            Math.min(getScreen().getWidth(), getScreen().getHeight());
        cellSize = (int)viewSize / 7;
        testMaze = getScreen().getMaze();
    }


    // ----------------------------------------------------------
    /**
     * The method is to test if the draw walls button is clicked.
     */
    public void testDrawWallsClicked()
    {
        click(drawWalls);
        assertEquals("Draw Mode: (click to draw walls)", infoLabel.getText());
        touchDownCell(1, 3);
        touchMoveCell(1, 3);
        touchUp();
        assertEquals(testMaze.getCell(new Location(1, 3)), MazeCell.WALL);
        clickCell(6, 6);
        assertFalse(testMaze.getCell(new Location(6, 6)).equals(MazeCell.WALL));
    }


    // ----------------------------------------------------------
    /**
     * The method is to test if the test walls button is clicked.
     */
    public void testEraseWallsClicked()
    {
        click(eraseWalls);
        assertEquals("Erase Mode: (click to erase walls)", infoLabel.getText());

        testMaze.setCell(new Location(2, 4), MazeCell.WALL);
        touchDownCell(2, 4);
        touchMoveCell(2, 4);
        touchUp();
        assertEquals(testMaze.getCell(new Location(2, 4)), MazeCell.UNEXPLORED);

        clickCell(6, 6);
        assertEquals(testMaze.getCell(new Location(2, 4)), MazeCell.UNEXPLORED);
    }


    // ----------------------------------------------------------
    /**
     * The method is to test if setStart button is clicked.
     */
    public void testSetStartClicked()
    {
        click(setStart);
        assertEquals("Place a new start location", infoLabel.getText());

        clickCell(3, 4);
        assertTrue(testMaze.getStartLocation().equals((new Location(3, 4))));
    }


    // ----------------------------------------------------------
    /**
     * The method is to test setGoal button is clicked.
     */
    public void testSetGoalClicked()
    {
        click(setGoal);
        assertEquals("Place a new goal location", infoLabel.getText());

        clickCell(3, 4);
        assertTrue(testMaze.getGoalLocation().equals((new Location(3, 4))));
    }


    // ----------------------------------------------------------
    /**
     * The method is to test the solve;
     */
    public void testSolveClicked()
    {
        testMaze.setCell(new Location(3, 0), MazeCell.WALL);
        testMaze.setCell(new Location(3, 1), MazeCell.WALL);
        testMaze.setCell(new Location(3, 2), MazeCell.WALL);
        testMaze.setCell(new Location(3, 3), MazeCell.WALL);
        testMaze.setCell(new Location(2, 3), MazeCell.WALL);
        testMaze.setCell(new Location(1, 3), MazeCell.WALL);
        click(solve);
        assertEquals("(0, 0) (0, 1) (0, 2) (0, 3) (0, 4) (0, 5) (0, 6) (1, 6)"
            + " (1, 5) (1, 4) (2, 4) (2, 5) (2, 6) (3, 6) (3, 5) (3, 4) "
            + "(4, 4) (4, 3) (4, 2) (4, 1) (4, 0) (5, 0) (5, 1) (5, 2) "
            + "(5, 3) (5, 4) (5, 5) (5, 6) (6, 6)", infoLabel.getText());

        testMaze.setCell(new Location(0, 1), MazeCell.WALL);
        testMaze.setCell(new Location(1, 0), MazeCell.WALL);
        click(solve);
        assertEquals(infoLabel.getText(), "No solution was possible");
    }


    // ~ Private methods .......................................................

    // ----------------------------------------------------------
    /**
     * Simulates touching down on the middle of the specified cell in the maze.
     *
     * @param x
     *            x-coordinate
     * @param y
     *            y-coordinate
     */
    private void touchDownCell(int x, int y)
    {
        touchDown(shapeView, (x + 0.5f) * cellSize, (y + 0.5f) * cellSize);
    }


    // ----------------------------------------------------------
    /**
     * Simulates moving the finger instantaneously to the middle of the
     * specified cell in the maze.
     *
     * @param x
     *            x-coordinate
     * @param y
     *            y-coordinate
     */
    private void touchMoveCell(int x, int y)
    {
        touchMove((x + 0.5f) * cellSize, (y + 0.5f) * cellSize);
    }


    // ----------------------------------------------------------
    /**
     * Simulates clicking the middle of the specified cell in the maze. This is
     * equivalent to calling: touchDownCell(x, y); touchUp();
     *
     * @param x
     *            x-coordinate
     * @param y
     *            y-coordinate
     */
    private void clickCell(int x, int y)
    {
        touchDownCell(x, y);
        touchUp();
    }

}
