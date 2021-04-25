package algorithms.maze3D;

import algorithms.mazeGenerators.Position;



public class Maze3D {

    private int numOfRow; //hold the number(size) of rows we want build the maze
    private int numOfCol; //hold the number(size) of cols we want build the maze
    private int depth;

    private Position3D startPosition; //hold the start point of the maze
    private Position3D goalPosition; //hold the end point of the maze

    protected int[][][] intMaze3D; //this the 3D maze we create. every cell in the matrix build of int

    /**
     *
     * @param depth hold the size of depth of 3D maze
     * @param numOfRow hold the size of rows of 3D maze
     * @param numOfCol hold the size of cols of 3D maze
     */
    public Maze3D(int depth, int numOfRow , int numOfCol) { //constructor
        intMaze3D = new int[depth][numOfRow][numOfCol];
        this.numOfRow = numOfRow;
        this.numOfCol = numOfCol;
        this.depth = depth;
    }

    // _-_-_-_-_-_-_-_-_-  f u n c t i o n  -_-_-_-_-_-_-_-_-_

    public int[][][] getMap()
    {
        return intMaze3D;
    }


    public Position3D getStartPosition() { return startPosition; }


    public Position3D getGoalPosition()
    {
        return goalPosition;
    }



    //print the 3D maze
    public void print(){
        System.out.println("{");
        for(int depth = 0; depth < intMaze3D.length; depth++){
            for(int row = 0; row < intMaze3D[0].length; row++) {
                System.out.print("{ ");
                for (int col = 0; col < intMaze3D[0][0].length; col++) {
                    if (depth == startPosition.getDepthIndex() && row == startPosition.getRowIndex() && col == startPosition.getColumnIndex()) // if the position is the start - mark with S
                        System.out.print("S ");
                    else {
                        if (depth == goalPosition.getDepthIndex() && row == goalPosition.getRowIndex() && col == goalPosition.getColumnIndex()) // if the position is the goal - mark with E
                            System.out.print("E ");
                        else
                            System.out.print(intMaze3D[depth][row][col] + " ");
                    }
                }
                System.out.println("}");
            }
            if(depth < intMaze3D.length - 1) {
                System.out.print("---");
                for (int i = 0; i < intMaze3D[0][0].length; i++)
                    System.out.print("--");
                System.out.println();
            }
        }
        System.out.println("}");
    }

    //_-_-_-_-_-_-_-_-_-s e t t e r s   &   g e t t e r s -_-_-_-_-_-_-_-_-_

    public int getNumOfRow() { return numOfRow; }
    public void setNumOfRow(int numOfRow) { this.numOfRow = numOfRow; }
    public int getNumOfCol() { return numOfCol; }
    public void setNumOfCol(int numOfCol) { this.numOfCol = numOfCol; }
    public int getDepth() { return depth; }
    public void setDepth(int depth) { this.depth = depth; }
    public void setStartPosition(Position3D startPosition) { this.startPosition = startPosition; }
    public void setGoalPosition(Position3D goalPosition) { this.goalPosition = goalPosition; }
    public int[][][] getIntMaze3D() { return intMaze3D; }
    public void setIntMaze3D(int[][][] intMaze3D) { this.intMaze3D = intMaze3D; }


    //______________________________________________________________________________________
}
