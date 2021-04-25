package algorithms.mazeGenerators;
import java.util.*;
import java.util.Random;

import java.util.List;


public class MyMazeGenerator extends AMazeGenerator {
    /**
     * in this class we did the prim algorithm
     * @param numOfRow
     * @param numOfCol
     * @return a Maze type of a new maze after prim built it
     */

    @Override
    public Maze generate(int numOfRow, int numOfCol) {

        EmptyMazeGenerator justMaze = new EmptyMazeGenerator(); // create new maze object type empty
        Maze zeroMaze = justMaze.generate(numOfRow, numOfCol); // create zeros maze to put walls later
        int[][] emptyIntMaze = zeroMaze.getIntMaze(); //hold the array so we dont need to call it everytime (save time)

        for (int i = 0; i < numOfRow; i++) //maybe need to do a path like in simple
        {
            for (int j = 0; j < numOfCol; j++) {
                emptyIntMaze[i][j] = 1;  //put 1 in every index of the maze. start prim algo with a big wall
            }
        }

        List<Position[]> neighborsList = new ArrayList<>(); //open list of position  for the neighbors index bekfitzot of 2

        Random random = new Random();
        int randomRowIndex = random.nextInt(numOfRow); //choose random row index for function prim start point
        int randomColIndex = random.nextInt(numOfCol);//choose random col index for function prim start point


        Position randomStartPoint = new Position(randomRowIndex, randomColIndex); //return the random index to position
        Position[] firstNeighbor = new Position[2]; //open new list size 2
        firstNeighbor[0] = randomStartPoint; //add it to a list
        firstNeighbor[1] = randomStartPoint;//add it to a list
        neighborsList.add(firstNeighbor); //add them to the neighbors list


        while (!neighborsList.isEmpty()) {
            Position[] current = neighborsList.remove(random.nextInt(neighborsList.size())); //choose a random neighbor to move
            int currRow = current[0].getRowIndex();
            int currCol = current[0].getColumnIndex();

            if (emptyIntMaze[currRow][currCol] == 1) {
                emptyIntMaze[currRow][currCol] = 0; //break the neighbor
                emptyIntMaze[current[1].getRowIndex()][current[1].getColumnIndex()] = 0; //break the wall


                if (currCol < numOfCol - 2 && emptyIntMaze[currRow][currCol + 2] == 1) //if we can go right
                {
                    Position[] rightNeighbor = new Position[2]; //open list size 2 of position
                    Position pNeighborR = new Position(currRow, currCol + 2); //convert the neighbor we can move to a position
                    Position wallBetweenR = new Position(currRow, currCol + 1); // convert the wall between the neighbor and the current to a positon
                    rightNeighbor[0] = pNeighborR; //add it to a list
                    rightNeighbor[1] = wallBetweenR;//add it to a list
                    neighborsList.add(rightNeighbor); //add them to the neighbors list
                }


                if (currCol >= 2 && emptyIntMaze[currRow][currCol - 2] == 1) //if we can go left
                {
                    Position[] leftNeighbor = new Position[2]; //open list size 2 of position
                    Position pNeighborL = new Position(currRow, currCol - 2); //convert the neighbor we can move to a position
                    Position wallBetweenL = new Position(currRow, currCol - 1); // convert the wall between the neighbor and the current to a positon
                    leftNeighbor[0] = pNeighborL; //add it to a list
                    leftNeighbor[1] = wallBetweenL;//add it to a list
                    neighborsList.add(leftNeighbor); //add them to the neighbors list
                }

                if (currRow >= 2 && emptyIntMaze[currRow - 2][currCol] == 1) //if we can go up
                {
                    Position[] upNeighbor = new Position[2]; //open list size 2 of position
                    Position pNeighborU = new Position(currRow - 2, currCol); //convert the neighbor we can move to a position
                    Position wallBetweenU = new Position(currRow - 1, currCol); // convert the wall between the neighbor and the current to a positon
                    upNeighbor[0] = pNeighborU; //add it to a list
                    upNeighbor[1] = wallBetweenU;//add it to a list
                    neighborsList.add(upNeighbor); //add them to the neighbors list
                }

                if (currRow < numOfRow - 2 && emptyIntMaze[currRow + 2][currCol] == 1) //if we can go down
                {
                    Position[] downNeighbor = new Position[2]; //open list size 2 of position
                    Position pNeighborD = new Position(currRow + 2, currCol); //convert the neighbor we can move to a position
                    Position wallBetweenD = new Position(currRow + 1, currCol); // convert the wall between the neighbor and the current to a positon
                    downNeighbor[0] = pNeighborD; //add it to a list
                    downNeighbor[1] = wallBetweenD;//add it to a list
                    neighborsList.add(downNeighbor); //add them to the neighbors list
                }

            }
        }
        Position startP = zeroMaze.getStartPosition();
        Position GoalP = zeroMaze.getGoalPosition();

        emptyIntMaze[startP.getRowIndex()][startP.getColumnIndex()] = 0;
        emptyIntMaze[GoalP.getRowIndex()][GoalP.getColumnIndex()] = 0;
        emptyIntMaze[startP.getRowIndex()+1][startP.getColumnIndex()] = 0;
        emptyIntMaze[GoalP.getRowIndex()-1][GoalP.getColumnIndex()] = 0;
        return zeroMaze;
    }

}
