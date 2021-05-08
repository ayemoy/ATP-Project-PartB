package algorithms.mazeGenerators;

import java.io.Serializable;

public class Position implements Serializable {
    int rowIndex;
    int colIndex;

    public Position(int rowIndex, int colIndex)
    {
        this.colIndex = colIndex;
        this.rowIndex = rowIndex;

    }

    //_-_-_-_-_-_-_-_-_-s e t t e r s   &   g e t t e r s -_-_-_-_-_-_-_-_-_
    public int getRowIndex() {
        return rowIndex;
    }  //get the index of a row in the maze
    public int getColumnIndex() {
        return colIndex;
    } //get the index of a col in the maze

    public void setRowIndex(int rowIndex) { this.rowIndex = rowIndex; } //set the index of a row in the maze
    public void setColIndex(int colIndex) { this.colIndex = colIndex; }//set the index of a col in the maze



    //_-_-_-_-_-_-_-_-_-f  u  n  c  t  i  o  n -_-_-_-_-_-_-_-_-_
    @Override
    public String toString() {
        return "{" +rowIndex + "," + colIndex +"}" ;
    }

}
