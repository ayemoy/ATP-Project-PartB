package algorithms.mazeGenerators;

public class Maze { //all the parameters thay may be in the constructor. variable of class

    private int numOfRow; //hold the number(size) of rows we want build the maze
    private int numOfCol; //hold the number(size) of cols we want build the maze

    private Position startPosition; //hold the start point of the maze
    private Position goalPosition; //hold the end point of the maze

    protected int[][] intMaze; //this the 2D maze we create. every cell in the matrix build of int

    /**
     * define and print maze
     * @param numOfRow
     * @param numOfCol
     */

    //_-_-_-_-_-_-_-_-_-constructor of new maze -_-_-_-_-_-_-_-_-_
    public Maze(int numOfRow , int numOfCol)
    {
        intMaze = new int[numOfRow][numOfCol];
        this.numOfRow = numOfRow;
        this.numOfCol = numOfCol;
        //this.startPosition = null;
        //this.goalPosition = null;
    }


//_-_-_-_-_-_-_-_-_-s e t t e r s   &   g e t t e r s -_-_-_-_-_-_-_-_-_
    public Position getStartPosition() { return startPosition;} //return the start position of the maze
    public Position getGoalPosition() { return goalPosition; }//return the end position of the maze
    public int getNumOfRow() { return numOfRow; } //return the size of rows of the maze bcus its private
    public int getNumOfCol() { return numOfCol; } //return the size of cols of the maze bcus its private
    public int[][] getIntMaze() { return intMaze; } //return the array of maze object
    public void setNumOfRow(int numOfRow) { this.numOfRow = numOfRow; } //set the number of rows
    public void setNumOfCol(int numOfCol) { this.numOfCol = numOfCol; } //set the number of cols
    public void setStartPosition(Position startPosition) { this.startPosition = startPosition; } //set the start position in the maze
    public void setGoalPosition(Position goalPosition) { this.goalPosition = goalPosition; } //set the end position in the maze
    public void setIntMaze(int[][] intMaze) { this.intMaze = intMaze; }


    // _-_-_-_-_-_-_-_-_-  f u n c t i o n  -_-_-_-_-_-_-_-_-_
    //print the 2D maze
    public void print()
    {
        for(int i=0 ; i<numOfRow ; i++)
        {
            String allTheRow = "{"; //new empty string to collect all the values in each index in one row
            for(int j=0 ; j<numOfCol ; j++)
            {
                if( startPosition.getRowIndex() == i && startPosition.getColumnIndex() == j) //if its start position put S
                {
                    allTheRow += " S";
                }
                else if(goalPosition.getRowIndex() == i && goalPosition.getColumnIndex() ==j) //if its the end position put E
                {
                    allTheRow += " E";
                }
                else {
                    allTheRow += " " + String.valueOf(intMaze[i][j]); //else put all the numbers in string
                }
            }
            System.out.println(allTheRow + " }");
        }
    }

}

