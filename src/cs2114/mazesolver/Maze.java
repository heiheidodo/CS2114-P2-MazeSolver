package cs2114.mazesolver;

import java.util.Stack;

// -------------------------------------------------------------------------
/**
 * Write a one-sentence summary of your class here. Follow it with additional
 * details about its purpose, what abstraction it represents, and how to use it.
 *
 * @author heiheidodo
 * @version Feb 20, 2013
 */
public class Maze
    implements IMaze
{
    private int          size;
    private MazeCell[][] aMaze;
    private Location     startLoc;
    private Location     goalLoc;


    // ----------------------------------------------------------
    /**
     * Create a new Maze object.
     *
     * @param size
     *            maze size
     */
    public Maze(int size)
    {
        this.size = size;
        this.aMaze = createMaze();
        this.setStartLocation(new Location(0, 0));
        this.setGoalLocation(new Location(size - 1, size - 1));
    }


    /**
     * The method is to create a new Maze.
     *
     * @return unexplored new maze
     */
    public MazeCell[][] createMaze()
    {
        aMaze = new MazeCell[size][size];
        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {
                aMaze[i][j] = MazeCell.UNEXPLORED;
            }
        }
        return aMaze;
    }


    // ----------------------------------------------------------
    /**
     * The method is to get the start location.
     *
     * @return start location
     */
    public ILocation getStartLocation()
    {
        return startLoc;
    }


    /**
     * The method is to get the goal location.
     *
     * @return goal location
     */
    public ILocation getGoalLocation()
    {
        return goalLoc;
    }


    /**
     * The method is to get the current location.
     *
     * @param local
     *            the variable you want to check the location
     * @return aMaze location.
     */
    public MazeCell getCell(ILocation local)
    {
        if (local.x() < 0 || local.x() > (size - 1) || local.y() < 0
            || local.y() > (size - 1))
        {
            return MazeCell.INVALID_CELL;
        }
        return aMaze[local.x()][local.y()];
    }


    // ----------------------------------------------------------
    /**
     * The method is to set the start location.
     *
     * @param start
     *            start location
     */
    public void setStartLocation(ILocation start)
    {
        if (!(getCell(start).equals(MazeCell.INVALID_CELL)))
        {
            this.setCell(start, MazeCell.UNEXPLORED);
            startLoc = (Location)start;
        }
    }


    // ----------------------------------------------------------
    /**
     * The method is to set the goal location
     *
     * @param goal
     *            location of goal
     */
    public void setGoalLocation(ILocation goal)
    {
        if (!(getCell(goal).equals(MazeCell.INVALID_CELL)))
        {
            this.setCell(goal, MazeCell.UNEXPLORED);
            goalLoc = (Location)goal;
        }
    }


    /**
     * The method is to set cell.
     *
     * @param loc
     *            the cell's location
     * @param cel
     *            the cel
     */
    public void setCell(ILocation loc, MazeCell cel)
    {
        if (cel == MazeCell.WALL
            && (loc.equals(getStartLocation())
                || loc.equals(getGoalLocation())))
        {
            return;
        }
        else
        {
            aMaze[loc.x()][loc.y()] = cel;
        }
    }


    // ----------------------------------------------------------
    /**
     * The method is to get the maze size.
     *
     * @return the maze size
     */
    public int size()
    {
        return size;
    }


    // ----------------------------------------------------------
    /**
     * The method is to test if the cell is unexplored.
     *
     * @param loc
     *            the cell location
     * @return true if the cell is unexplored, otherwise, false;
     */
    public boolean canGo(ILocation loc)
    {
        if (loc.x() < 0 || loc.y() < 0 || loc.x() > size - 1
            || loc.y() > size - 1)
        {
            return false;
        }
        return this.aMaze[loc.x()][loc.y()].equals(MazeCell.UNEXPLORED);
    }


    /**
     * The method is to find the maze solution.
     *
     * @return the solution
     */
    public String solve()
    {
        String solution = "";
        Stack<ILocation> path = new Stack<ILocation>();
        ILocation current;
        boolean finish = false;
        path.push(startLoc);
        while (!path.empty() && !finish)
        {
            current = path.peek();
            aMaze[current.x()][current.y()] = MazeCell.CURRENT_PATH;
            if (current.equals(goalLoc))
            {
                break;
            }
            if (this.canGo(current.north()))
            {
                path.push(current.north());
                aMaze[current.x()][current.y()] = MazeCell.CURRENT_PATH;
            }
            else if (this.canGo(current.south()))
            {
                path.push(current.south());
                aMaze[current.x()][current.y()] = MazeCell.CURRENT_PATH;
            }
            else if (this.canGo(current.west()))
            {
                path.push(current.west());
                aMaze[current.x()][current.y()] = MazeCell.CURRENT_PATH;
            }
            else if (this.canGo(current.east()))
            {
                path.push(current.east());
                aMaze[current.x()][current.y()] = MazeCell.CURRENT_PATH;
            }
            else
            {
                aMaze[current.x()][current.y()] = MazeCell.FAILED_PATH;
                path.pop();
            }

        }
        if (path.empty())
        {
            return null;
        }
        else
        {
            Stack<ILocation> reverse = new Stack<ILocation>();
            while (!path.empty())
            {
                reverse.push(path.pop());
            }
            while (!reverse.empty())
            {
                solution = solution + reverse.pop() + " ";
            }
            return solution.substring(0, solution.length() - 1);
        }
    }
}
