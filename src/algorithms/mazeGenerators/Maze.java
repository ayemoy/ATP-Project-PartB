package algorithms.mazeGenerators;

import java.util.ArrayList;
import java.util.List;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.ByteBuffer;

public class Maze { //all the parameters thay may be in the constructor. variable of class

    private int numOfRow; //hold the number(size) of rows we want build the maze
    private int numOfCol; //hold the number(size) of cols we want build the maze

    private Position startPosition; //hold the start point of the maze
    private Position goalPosition; //hold the end point of the maze

    protected int[][] intMaze; //this the 2D maze we create. every cell in the matrix build of int

    /**
     * define and print maze
     *
     * @param numOfRow
     * @param numOfCol
     */

    //_-_-_-_-_-_-_-_-_-constructor of new regular maze -_-_-_-_-_-_-_-_-_
    public Maze(int numOfRow, int numOfCol) {
        intMaze = new int[numOfRow][numOfCol];
        this.numOfRow = numOfRow;
        this.numOfCol = numOfCol;
        //this.startPosition = null;
        //this.goalPosition = null;
    }

    //_-_-_-_-_-_-_-_-_-constructor of new bytes maze -_-_-_-_-_-_-_-_-_
    public Maze(byte[] bytesMaze)
    {
        byte[] currentByte = new byte [4]; //hold 4 bytes everytime from the byte maze array and then we convert back to int
        int[] totalDetails = new  int[6]; //hold the aii details that we need to create int maze (like start/goal position' size of maze..)
        int indexCurrentByte = 0; //tell us if we took 4 bytes
        int indexTotalDetails = 0; //tell us where to put the int in the totalDetails

        for(int i=0 ; i<24 ; i++)
        {
            currentByte[indexCurrentByte] = bytesMaze[i];
            indexCurrentByte++;
            if(indexCurrentByte == 4)
            {
                totalDetails[indexTotalDetails] = convertByteToInt(currentByte); //convert to int
                indexTotalDetails++;
                indexCurrentByte=0;
            }
        }
        //set all maze details
        numOfRow = totalDetails[0];
        numOfCol = totalDetails[1];
        Position temp = new Position(totalDetails[2],totalDetails[3]);
        setStartPosition(temp);
        Position temp2 =new Position(totalDetails[4],totalDetails[5]);
        setGoalPosition(temp2);
        //_________________________
        intMaze = new int[numOfRow][numOfCol];
        int index = 24;
        for(int i=0 ; i<numOfRow ; i++)
        {
            for(int j=0 ; j<numOfCol ; j++)
            {
                intMaze[i][j] = bytesMaze[index];
                index++;
            }
        }
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


    //-_-_-_-_-_-_-_-_-_-_-a d d e d  f o r  p a r t B-_-_-_-_-_-_-_-_-_-_-_-_-_-_------
    public byte[] toByteArray() //convert all maze information to byte array so we can use it in MyDecompressorInputStream
    {
        byte[] mazeBytesArray = new byte[(numOfCol*numOfRow)+24]; //new byte array that hold all the maze in one line. 24 first index for rows,cols, start,goal,
        int indexToCopy = 0;

        convertIntToBytes(mazeBytesArray,numOfRow,indexToCopy); //convert the row size of the maze into bytes
        indexToCopy+=4; //raise the index in mazeBytesArray in 4 cus its the size of row int (1 int = 4 bytes)
        convertIntToBytes(mazeBytesArray,numOfCol,indexToCopy);//convert the col size of the maze into bytes
        indexToCopy+=4; //raise the index in mazeBytesArray in 4 cus its the size of col int (1 int = 4 bytes)

        convertIntToBytes(mazeBytesArray,startPosition.getRowIndex(),indexToCopy);//convert the row  of the start position into bytes
        indexToCopy+=4; //raise the index in mazeBytesArray in 4 cus its the size of col int (1 int = 4 bytes)
        convertIntToBytes(mazeBytesArray,startPosition.getColumnIndex(),indexToCopy);//convert the col  of the start position into bytes
        indexToCopy+=4; //raise the index in mazeBytesArray in 4 cus its the size of col int (1 int = 4 bytes)

        convertIntToBytes(mazeBytesArray,goalPosition.getRowIndex(),indexToCopy);//convert the row  of the goalPosition into bytes
        indexToCopy+=4; //raise the index in mazeBytesArray in 4 cus its the size of col int (1 int = 4 bytes)
        convertIntToBytes(mazeBytesArray,goalPosition.getColumnIndex(),indexToCopy);//convert the col  of the goalPosition into bytes

        //convert to bytes and put the intMaze in one big array start in index 24
        int startIndexForMaze = 24;
        for(int i=0 ; i<numOfRow ; i++)
        {
            for(int j=0 ; j<numOfCol ; j++)
            {
                mazeBytesArray[startIndexForMaze] = (byte)intMaze[i][j];
                startIndexForMaze++;
            }
        }
        return mazeBytesArray;
    }


    private void convertIntToBytes(byte[] mazeBytesArray,int numToConvert, int indexToCopy)
    {
        byte[] numInBytes =  ByteBuffer.allocate(4).putInt(numToConvert).array();// hold bytes array like array=[2354]
        System.arraycopy(numInBytes, 0, mazeBytesArray, indexToCopy,4);

      /* copy numInBytes from the beginning(ondex 0) to the mazeBytesArray  start in indexToCopy, all the 4 bytes
        public static void arraycopy(Object src, int srcPos, Object dest, int destPos, int length)
        src − This is the source array.
    srcPos − This is the starting position in the source array. dest − This is the destination array.
    destPos − This is the starting position in the destination data. length − This is the number of array elements to be copied.
    */
    }


    private int convertByteToInt(byte[] numToConvert) //convert byte to int
    {
        ByteBuffer byteBuffer = ByteBuffer.wrap(numToConvert);
        return byteBuffer.getInt();
    }


/*
    private byte[] convertIntToByte(int numToConvert) //
    {
        ByteBuffer bytes = ByteBuffer.allocate(4);
        bytes.putInt(numToConvert);
        return bytes.array();
    }
*/
}

