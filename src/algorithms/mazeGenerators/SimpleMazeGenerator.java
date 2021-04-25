package algorithms.mazeGenerators;
import java.util.Random; //import the random numbers to create random walls in the maze

public class SimpleMazeGenerator extends AMazeGenerator
{

    @Override
    //put randomly walls in the maze, able lots of possible solutions
    public Maze generate(int numOfRow, int numOfCol)
    {
        EmptyMazeGenerator emptyMaze = new EmptyMazeGenerator(); // create new maze object type empty
        Maze wallMaze=emptyMaze.generate(numOfRow, numOfCol); // create zeros maze to put walls later

        Position start = wallMaze.getStartPosition(); //hold the start position of maze
        Position end = wallMaze.getGoalPosition(); //hold the end position of maze

        Random random = new Random();
        int [][] emptyWallMaze = wallMaze.getIntMaze(); //hold the array so we dont need to call it everytime (save time)

        for(int i=1; i<numOfRow-1; i++)
        {
            for (int j = 1; j < numOfCol; j++)
            {
                emptyWallMaze[i][j] = random.nextInt(2); //choose randomly numbers between 0-1 and put them in the maze
            }
        }
        /*emptyWallMaze[start.getRowIndex()][start.getColumnIndex()]  = 0; //put 0 in the start position after create walls
        emptyWallMaze[end.getRowIndex()][end.getColumnIndex()] = 0; //put 0 in the end position after create walls
        zeroMaze.setIntMaze(emptyWallMaze);*/
        return wallMaze; //return maze with start and end points, with random walls
    }
}
