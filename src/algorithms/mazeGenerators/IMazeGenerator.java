package algorithms.mazeGenerators;


public interface IMazeGenerator {
    Maze generate(int numOfRow , int numOfCol);
    long measureAlgorithmTimeMillis(int numOfRow , int numOfCol);
}