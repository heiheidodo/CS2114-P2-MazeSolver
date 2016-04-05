package cs2114.mazesolver;

import sofia.graphics.Color;
import android.widget.TextView;
import sofia.app.ShapeScreen;

// -------------------------------------------------------------------------
/**
 * The class is to design a screen for maze solver app.
 *
 * @author Sheng Zhou(zsheng2)
 * @version 2013.03.18
 */
public class MazeSolverScreen
    extends ShapeScreen
{
    // ~ Fields ................................................................
    private TextView infoLabel;
    private Maze     myMaze;
    private Tile[][] coverTiles;
    private boolean  erase = true;
    private boolean  start = false;
    private boolean  goal  = false;


    // ~ Public methods ........................................................

    // ----------------------------------------------------------
    /**
     * The method is to initialize the whole screen.
     */
    public void initialize()
    {
        myMaze = new Maze(7);
        float size = Math.min(getHeight(), getWidth());
        float cell = size / 7;
        coverTiles = new Tile[7][7];

        for (int i = 0; i < 7; i++)
        {
            for (int j = 0; j < 7; j++)
            {
                Tile atile =
                    new Tile(cell * i, cell * j,
                        cell * (i + 1), cell * (j + 1));

                if ((i + j) == 0)
                {
                    atile.setColor(Color.green);
                }
                else if ((i + j) == 12)
                {
                    atile.setColor(Color.red);
                }
                else
                {
                    atile.setColor(Color.white);
                }
                coverTiles[i][j] = atile;
                add(atile);

            }
        }
    }


    // ----------------------------------------------------------
    // ----------------------------------------------------------
    /**
     * The method is to return the final state of maze.
     *
     * @return maze the maze
     */
    public IMaze getMaze()
    {
        return myMaze;
    }


    // ----------------------------------------------------------
    /**
     * The method is for single click.
     *
     * @param x
     *            x-coordinate
     * @param y
     *            y-coordinate
     */
    public void onTouchDown(float x, float y)
    {
        touch(x, y);
    }


    // ----------------------------------------------------------
    /**
     * The method is for dragging.
     *
     * @param x
     *            x-coordinate
     * @param y
     *            y-coordinate
     */
    public void onTouchMove(float x, float y)
    {
        touch(x, y);
    }


    // ----------------------------------------------------------
    /**
     * Get the location of the mouse click in shapeView.
     *
     * @param x
     *            x-coordinate
     * @param y
     *            y-coordinate
     * @return the location of the click
     */
    public ILocation cellRange(float x, float y)
    {
        float size = Math.min(getHeight(), getWidth());
        float cell = size / 7;
        Location loc = new Location((int)(x / cell), (int)(y / cell));
        infoLabel.setText("cell: (" + x / cell + ", " + y / cell + ") intLoc: "
            + loc);

        return new Location((int)(x / cell), (int)(y / cell));
    }


    // ----------------------------------------------------------
    /**
     * Place walls on the screen if not under erase mode, otherwise, erase walls
     * on the screen.
     *
     * @param x
     *            x-coordinate
     * @param y
     *            y-coordinate
     */
    public void touch(float x, float y)
    {
        ILocation loc = cellRange(x, y);
        ILocation preGoal = myMaze.getGoalLocation();
        ILocation preStart = myMaze.getStartLocation();

        if (!erase)
        {
            myMaze.setCell(loc, MazeCell.WALL);

            if (myMaze.getCell(loc) == MazeCell.WALL)
            {
                coverTiles[loc.x()][loc.y()].setColor(Color.black);
            }

        }
        else if (erase && myMaze.getCell(loc) == MazeCell.WALL)
        {
            myMaze.setCell(loc, MazeCell.UNEXPLORED);
            coverTiles[loc.x()][loc.y()].setColor(Color.white);
        }
        else if (start)
        {
            preStart = myMaze.getStartLocation();
            coverTiles[preStart.x()][preStart.y()].setColor(Color.white);

            myMaze.setStartLocation(loc);
            coverTiles[loc.x()][loc.y()].setColor(Color.green);
        }
        else if (goal)
        {
            preGoal = myMaze.getGoalLocation();
            coverTiles[preGoal.x()][preGoal.y()].setColor(Color.white);

            myMaze.setGoalLocation(loc);
            coverTiles[loc.x()][loc.y()].setColor(Color.red);
        }
    }


    // ----------------------------------------------------------
    /**
     * The method is to check if drawWalls button is clicked.
     */
    public void drawWallsClicked()
    {
        erase = false;
        goal = false;
        start = false;
        infoLabel.setText("Draw Mode: (click to draw walls)");
    }


    // ----------------------------------------------------------
    /**
     * The method is to check if eraseWalls button is clicked.
     */
    public void eraseWallsClicked()
    {
        erase = true;
        start = false;
        goal = false;
        infoLabel.setText("Erase Mode: (click to erase walls)");
    }


    // ----------------------------------------------------------
    /**
     * The method is to check if start button is clicked.
     */
    public void setStartClicked()
    {
        infoLabel.setText("Place a new start location");
        start = true;
        erase = true;
    }


    // ----------------------------------------------------------
    /**
     * The method is to check if goal button is clicked.
     */
    public void setGoalClicked()
    {
        infoLabel.setText("Place a new goal location");
        goal = true;
        erase = true;
    }


    // ----------------------------------------------------------
    /**
     * The method is to solve the maze while solve button is clicked.
     */
    public void solveClicked()
    {
        String sol = myMaze.solve();
        erase = true;
        goal = false;
        start = false;

        for (int i = 0; i < 7; i++)
        {
            for (int j = 0; j < 7; j++)
            {
                if (myMaze.getCell(new Location(i, j)) == MazeCell.CURRENT_PATH)
                {
                    coverTiles[i][j].setImage("current");
                }
                else if (myMaze.getCell(new Location(i, j))
                    == MazeCell.FAILED_PATH)
                {
                    coverTiles[i][j].setImage("fail");
                }

            }
        }

        if (sol != null)
        {
            infoLabel.setText(sol);

        }
        else
        {
            infoLabel.setText("No solution was possible");
        }
    }
}
