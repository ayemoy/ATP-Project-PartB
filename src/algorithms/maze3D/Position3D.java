package algorithms.maze3D;

/**
 *
 */


public class Position3D {
    int depthIndex;
    int rowIndex;
    int colIndex;

    public Position3D(int depthIndex , int rowIndex , int colIndex) { //constructor
        this.depthIndex = depthIndex;
        this.rowIndex = rowIndex ;
        this.colIndex = colIndex;
    }

    //_-_-_-_-_-_-_-_-_-s e t t e r s   &   g e t t e r s -_-_-_-_-_-_-_-_-_

    public void setDepthIndex(int depthIndex) { this.depthIndex = depthIndex; }
    public void setRowIndex(int rowIndex) { this.rowIndex = rowIndex;}
    public void setColIndex(int colIndex) { this.colIndex = colIndex; }

    public int getDepthIndex()
    {
        return this.depthIndex;
    }
    public int getColumnIndex() { return this.colIndex; }
    public int getRowIndex()
    {
        return this.rowIndex;
    }


    //_-_-_-_-_-_-_-_-_-f  u  n  c  t  i  o  n -_-_-_-_-_-_-_-_-_


    @Override
    public String toString() {
        return "{" +depthIndex+"," +rowIndex + "," + colIndex + "}";
    }
}
