package algorithms.maze3D;

import algorithms.mazeGenerators.Position;


import java.util.Random;

public abstract class AMaze3DGenerator  implements IMaze3DGenerator {

    public AMaze3DGenerator() {} // constructor

    public abstract Maze3D generate(int depth, int row, int column);

    @Override
    public long measureAlgorithmTimeMillis(int depth, int row, int column) {
        long startTime = System.currentTimeMillis(); //tell us what time we start generate the maze
        this.generate(depth, row , column); //generate and hold the new maze
        long endTime = System.currentTimeMillis();//tell us what time we end generate the maze
        long totalTime = endTime - startTime; //calculate the total time to create a new maze
        return totalTime;
    }

    //-_-_-_-_-_-_-_-_- help function -_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_
    //help function we add to choose a random position for start and end index of maze
    public Position3D RandomStartGoalRowCol(int depth , int row , int col)
    {
        Random random = new Random();
        Position3D chooseSomePosition = new Position3D(depth, row, random.nextInt(col));// choose the start Position from the first row of the maze
        return chooseSomePosition;
    }

}
