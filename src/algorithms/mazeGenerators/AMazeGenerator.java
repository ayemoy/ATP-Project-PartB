package algorithms.mazeGenerators;
//import Maze.IMazeGenerator;

import java.util.Random;

public abstract class AMazeGenerator implements IMazeGenerator{
    public abstract Maze generate(int numOfRow, int numOfCol);

    @Override
    public long measureAlgorithmTimeMillis(int numOfRow, int numOfCol) { //tell how much time it takes to create a maze
        long startTime = System.currentTimeMillis(); //tell us what time we start generate the maze
        this.generate(numOfRow, numOfCol); //generate and hold the new maze
        long endTime = System.currentTimeMillis();//tell us what time we end generate the maze
        long totalTime = endTime - startTime; //calculate the total time to create a new maze
        return totalTime;
    }

    //-_-_-_-_-_-_-_-_- help function -_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_
    //help function we add to choose a random position for start and end index of maze
    public Position RandomStartGoalRowCol(int row , int col)
    {
        Random random = new Random();
        Position chooseUpPosition = new Position(row, random.nextInt(col));// choose the start Position from the first row of the maze
        //Position chooseDownPosition = new Position(row - 1, random.nextInt(col));// choose the end Position from the last row of the maze
        return chooseUpPosition;
    }



    //_________________________________________________________________________________________________
}
