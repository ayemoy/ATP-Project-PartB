package algorithms.maze3D;

import algorithms.mazeGenerators.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MyMaze3DGenerator extends AMaze3DGenerator {


    /** create 3D maze
     * @param numOfDepth is the depth of the maze
     * @param numOfRow is the rows in the maze
     * @param numOfCol is the cols in the maze
     * @return 3D maze type
     */


    @Override
    public Maze3D generate(int numOfDepth, int numOfRow, int numOfCol) {
        if (numOfRow<2){throw new IllegalArgumentException("Size of row maze must be at least 2");} //cant init maze with less then depth 2
        if (numOfCol<2){throw new IllegalArgumentException("Size of column maze must be at least 2");} //cant init maze with less then depth 2
        if (numOfDepth<2){throw new IllegalArgumentException("Size of depth maze must be at least 2");} //cant init maze with less then depth 2

        Maze3D our3DMaze = new Maze3D(numOfDepth , numOfRow ,numOfCol ); //create Maze3D object
        int[][][] int3DMaze = new int[numOfDepth][numOfRow][numOfCol]; //open 3d int array

        for (int i = 0; i < numOfDepth; i++)  //set 1 in every cell in the array
        {
            for (int j = 0; j < numOfRow; j++)
            {
                for (int k = 0; k < numOfCol; k++)
                {
                    int3DMaze[i][j][k] = 1;
                }
            }
        }

        List<Position3D[]> neighborsList = new ArrayList<>(); //open list of position  for the neighbors index bekfitzot of 2

        Random random = new Random(); //choose randomly start position to start prims algo
        int randomRowIndex = random.nextInt(numOfRow); //choose random row index for function prim start point
        int randomColIndex = random.nextInt(numOfCol);//choose random col index for function prim start point
        int randomDepthIndex = random.nextInt(numOfDepth);//choose random depth index for function prim start point

        Position3D randomStartPoint = new Position3D(randomDepthIndex, randomRowIndex, randomColIndex); //return the random index to position

        Position3D[] firstNeighbor = new Position3D[2]; //open new list size 2
        firstNeighbor[0] = randomStartPoint; //add it to a list
        firstNeighbor[1] = randomStartPoint;//add it to a list
        neighborsList.add(firstNeighbor); //add them to the neighbors list


        while (!neighborsList.isEmpty()) {
            Position3D[] current = neighborsList.remove(random.nextInt(neighborsList.size())); //choose a random neighbor to move
            int currDepth = current[0].getDepthIndex(); //0 is the neighbor, [1] is the wall between them
            int currRow = current[0].getRowIndex();
            int currCol = current[0].getColumnIndex();

            if (int3DMaze[currDepth][currRow][currCol] == 1)
            {
                int3DMaze[currDepth][currRow][currCol] = 0; //break the neighbor
                int3DMaze[current[1].getDepthIndex()][current[1].getRowIndex()][current[1].getColumnIndex()] = 0; //break the wall between them

                //for (int d = 0; d < numOfDepth; d++) //run over the array of our matrix (maze), every d is a matrix
                //{
                //if we can go right
                if (currCol < numOfCol - 2 && int3DMaze[currDepth][currRow][currCol + 2] == 1)
                {
                    Position3D[] rightNeighbor = new Position3D[2]; //open list size 2 of position
                    Position3D pNeighborR = new Position3D(currDepth, currRow, currCol + 2); //convert the neighbor we can move to a position
                    Position3D wallBetweenR = new Position3D(currDepth, currRow, currCol + 1); // convert the wall between the neighbor and the current to a positon
                    rightNeighbor[0] = pNeighborR; //add the neighbor we want to walk to a list
                    rightNeighbor[1] = wallBetweenR;//add the wall between the old cell and the new neighbor to a list
                    neighborsList.add(rightNeighbor); //add them to the neighbors list
                }

                //if we can go left
                if (currCol >= 2 && int3DMaze[currDepth][currRow][currCol - 2] == 1)
                {
                    Position3D[] leftNeighbor = new Position3D[2]; //open list size 2 of position
                    Position3D pNeighborL = new Position3D(currDepth, currRow, currCol - 2); //convert the neighbor we can move to a position
                    Position3D wallBetweenL = new Position3D(currDepth, currRow, currCol - 1); // convert the wall between the neighbor and the current to a positon
                    leftNeighbor[0] = pNeighborL; //add it to a list
                    leftNeighbor[1] = wallBetweenL;//add it to a list
                    neighborsList.add(leftNeighbor); //add them to the neighbors list
                }
                    //if we can go up
                if (currRow >= 2 && int3DMaze[currDepth][currRow - 2][currCol] == 1)
                {
                    Position3D[] upNeighbor = new Position3D[2]; //open list size 2 of position
                    Position3D pNeighborU = new Position3D(currDepth, currRow - 2, currCol); //convert the neighbor we can move to a position
                    Position3D wallBetweenU = new Position3D(currDepth, currRow - 1, currCol); // convert the wall between the neighbor and the current to a positon
                    upNeighbor[0] = pNeighborU; //add it to a list
                    upNeighbor[1] = wallBetweenU;//add it to a list
                    neighborsList.add(upNeighbor); //add them to the neighbors list
                }
                //if we can go down
                if (currRow < numOfRow - 2 && int3DMaze[currDepth][currRow + 2][currCol] == 1)
                {
                    Position3D[] downNeighbor = new Position3D[2]; //open list size 2 of position
                    Position3D pNeighborD = new Position3D(currDepth, currRow + 2, currCol); //convert the neighbor we can move to a position
                    Position3D wallBetweenD = new Position3D(currDepth, currRow + 1, currCol); // convert the wall between the neighbor and the current to a positon
                    downNeighbor[0] = pNeighborD; //add it to a list
                    downNeighbor[1] = wallBetweenD;//add it to a list
                    neighborsList.add(downNeighbor); //add them to the neighbors list
                }

                if(currDepth+1 < numOfDepth && int3DMaze[currDepth+1][currRow][currCol] == 1 )
                {
                    Position3D[] depthNeighbor = new Position3D[2]; //open list size 2 of position
                    Position3D pNeighborD = new Position3D(currDepth+1, currRow, currCol); //convert the neighbor we can move to a position
                    depthNeighbor[0] = pNeighborD; //add it to a list
                    depthNeighbor[1] = pNeighborD; //add it to a list
                    neighborsList.add(depthNeighbor); //add them to the neighbors list
                }
                if(currDepth-1 >= 0 && int3DMaze[currDepth-1][currRow][currCol] == 1 )
                {
                    Position3D[] depthNeighbor = new Position3D[2]; //open list size 2 of position
                    Position3D pNeighborD = new Position3D(currDepth-1, currRow, currCol); //convert the neighbor we can move to a position
                    depthNeighbor[0] = pNeighborD; //add it to a list
                    depthNeighbor[1] = pNeighborD; //add it to a list
                    neighborsList.add(depthNeighbor); //add them to the neighbors list
                }

                //}
            }
        }

        Position3D sp = RandomStartGoalRowCol(0, 0, numOfCol);
        Position3D gp = RandomStartGoalRowCol(numOfDepth-1,numOfRow-1 ,numOfCol );

        our3DMaze.setIntMaze3D(int3DMaze); //set the int3D maze to a field in 3DMAze object
        our3DMaze.setStartPosition(sp); //choose random start position
        our3DMaze.setGoalPosition(gp); //choose random goal position


        int3DMaze[sp.getDepthIndex()][sp.getRowIndex()][sp.getColumnIndex()] = 0;
        int3DMaze[gp.getDepthIndex()][gp.getRowIndex()][gp.getColumnIndex()] = 0;
        int3DMaze[sp.getDepthIndex()][sp.getRowIndex()+1][sp.getColumnIndex()] = 0;
        int3DMaze[gp.getDepthIndex()][gp.getRowIndex()-1][gp.getColumnIndex()] = 0;



        return our3DMaze;

    }
}


