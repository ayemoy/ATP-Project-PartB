package algorithms.mazeGenerators;

public class EmptyMazeGenerator extends AMazeGenerator{


    public EmptyMazeGenerator() { }// constructor

    /**
     *
     * @param numOfRow
     * @param numOfCol
     * @return a new maze with 0 in every cell
     */
    @Override

    //create a new maze that initialize with zeros without walls, and a start ans end position
    public Maze generate(int numOfRow, int numOfCol) {
        if(numOfRow<2) //if the user entered impossible values for size
        {
            throw new IllegalArgumentException("Size of row maze must be at least 2");
        }
        if(numOfCol<2) //if the user entered impossible values for size
        {
            throw new IllegalArgumentException("Size of column maze must be at least 2");
        }

        Maze emptyMaze = new Maze(numOfRow , numOfCol); //open a new object maze

        for(int i=0; i<numOfRow; i++)
        {
            for(int j=0; j<numOfCol; j++)
            {
                emptyMaze.getIntMaze()[i][j] = 0; //get from object maze the 2D array, and put zeros in
            }
        }
        emptyMaze.setStartPosition(RandomStartGoalRowCol(0 ,numOfCol )); //set the start position
        emptyMaze.setGoalPosition(RandomStartGoalRowCol(numOfRow-1 ,numOfCol)); // set the end position
        return emptyMaze;
    }
}
