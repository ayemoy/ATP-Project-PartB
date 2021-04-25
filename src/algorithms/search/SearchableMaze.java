package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.ArrayList;
import java.util.HashMap;

public class SearchableMaze implements ISearchable {
    private Maze maze;
    private AState startState;
    private AState goalState;
    private int [][] intMazeArray; //our 2D maze matrix
    private HashMap<String, MazeState> allStates;




    public SearchableMaze(Maze maze) { // constructor
        this.maze = maze;
        this.intMazeArray = maze.getIntMaze();
        this.allStates = new HashMap<>();

        for (int i=0; i< maze.getNumOfRow(); i++){
            for(int j=0; j< maze.getNumOfCol(); j++){
                if (intMazeArray[i][j] == 0){
                    Position pos = new Position(i,j); //create a new position so we can give it to new mazestate type as argument
                    MazeState tempMS = new MazeState(pos);
                    allStates.put(pos.toString(),tempMS);//insert every state to the hashmap when key is the string name of the state, and val is the object itself
                    tempMS.setCost(Integer.MAX_VALUE); //set the first cost as the biggest int that possible
                }
            }
        }
        this.startState = allStates.get(this.maze.getStartPosition().toString()); //initialize the start state . get for key and receive the value
        this.startState.setCost(0); //cost of start state from him to himself is 0
        this.goalState = allStates.get(this.maze.getGoalPosition().toString());

    }
//-_-_-_-_-_-_-_-_-_-_-_-_-help functions -_-_-_-_-_-_-_-_-_-_-_-_-_-
    @Override
    public AState getStartState() {
        return startState;
    } //return the start state of the maze
    @Override
    public AState getGoalState() {
        return goalState;
    } //return the goal state of the maze



    public AState canGoUp(MazeState currentState) //check if we can move from a state to a new  up state
    {
        int row = currentState.getStatePosition().getRowIndex(); //save the row index of our current state in the maze.
        int col =  currentState.getStatePosition().getColumnIndex();//save the col index of our current state in the maze.


        if( row-1>=0 && intMazeArray[row-1][col] ==0 ) //check if we can go up.
        {
            Position pToAdd = new Position(row-1, col); //if we can, we create a new position to give it to the new mazestate ocject
            MazeState ms = allStates.get(pToAdd.toString());//create a new mazestate object and give it the string of the possition
            if(currentState.getCost()+10 < ms.getCost()){ //check if the new mazestate is lower then the state before
                ms.setCost(currentState.getCost()+10); //add the new cost to the path
            }
            return ms;
        }
        return  null;

    }



    public AState canGoDown(MazeState currentState) //check if we can move from a state to a new  down state
    {
        int row = currentState.getStatePosition().getRowIndex(); //save the row index of our current state in the maze.
        int col =  currentState.getStatePosition().getColumnIndex();//save the col index of our current state in the maze.

        if( row+1<maze.getNumOfRow() &&  intMazeArray[row+1][col] ==0 ) //check if we can go down.
        {
            Position pToAdd = new Position(row+1, col);
            MazeState ms = allStates.get(pToAdd.toString());
            if(currentState.getCost()+10 < ms.getCost()){
                ms.setCost(currentState.getCost()+10);
            }
            return ms;
        }

        return null;
    }



    public AState canGoRight(MazeState currentState) //check if we can move from a state to a new right state
    {
        int row = currentState.getStatePosition().getRowIndex(); //save the row index of our current state in the maze.
        int col =  currentState.getStatePosition().getColumnIndex();//save the col index of our current state in the maze.

        if( col+1<maze.getNumOfCol() && intMazeArray[row][col+1] ==0 ) //check if we can go right.
        {
            Position pToAdd = new Position(row, col+1);
            MazeState ms = allStates.get(pToAdd.toString());
            if(currentState.getCost()+10 < ms.getCost()){
                ms.setCost(currentState.getCost()+10);
            }
            return ms;
        }
        return null;
    }



    public AState canGoLeft(MazeState currentState) //check if we can move from a state to a new  left state
    {
        int row = currentState.getStatePosition().getRowIndex(); //save the row index of our current state in the maze.
        int col = currentState.getStatePosition().getColumnIndex();//save the col index of our current state in the maze.

        if( col-1>=0 && intMazeArray[row][col-1] ==0 ) //check if we can go left.
        {
            Position pToAdd = new Position(row, col-1); //create a new position
            MazeState ms = allStates.get(pToAdd.toString());
            if(currentState.getCost()+10 < ms.getCost()){
                ms.setCost(currentState.getCost()+10);
            }
            return ms;
        }
        return null;

    }



    public AState alahsonUpRight(MazeState currentState) {
        int row = currentState.getStatePosition().getRowIndex(); //save the row index of our current state in the maze.
        int col = currentState.getStatePosition().getColumnIndex();//save the col index of our current state in the maze.

        if (row-1>=0 && col+1<maze.getNumOfCol())
        {
            if((intMazeArray[row-1][col]==0 && intMazeArray[row-1][col+1]==0) || (intMazeArray[row][col+1]==0 && intMazeArray[row-1][col+1]==0))
            {
                Position pToAdd = new Position(row-1, col+1); //create a new position
                MazeState ms = allStates.get(pToAdd.toString());
                if(currentState.getCost()+15 < ms.getCost()){
                    ms.setCost(currentState.getCost()+15);
                }
                return ms;
            }

        }
        return null;

    }


    public AState alahsonUpLeft(MazeState currentState)
    {
        int row = currentState.getStatePosition().getRowIndex(); //save the row index of our current state in the maze.
        int col =  currentState.getStatePosition().getColumnIndex();//save the col index of our current state in the maze.

        if (row-1>=0 && col-1>=0)
        {
            if((intMazeArray[row-1][col]==0 && intMazeArray[row-1][col-1]==0) || (intMazeArray[row][col-1]==0 && intMazeArray[row-1][col-1]==0))
            {
                Position pToAdd = new Position(row-1, col-1); //create a new position
                MazeState ms = allStates.get(pToAdd.toString());
                if(currentState.getCost()+15 < ms.getCost()){
                    ms.setCost(currentState.getCost()+15);
                }
                return ms;
            }
        }
        return null;
    }
    public AState alahsonDownRight(MazeState currentState)
    {
        int row = currentState.getStatePosition().getRowIndex(); //save the row index of our current state in the maze.
        int col =  currentState.getStatePosition().getColumnIndex();//save the col index of our current state in the maze.

        if (row+1<maze.getNumOfRow() && col+1<maze.getNumOfCol())
        {
            if((intMazeArray[row][col+1]==0 && intMazeArray[row+1][col+1]==0) || (intMazeArray[row+1][col]==0 && intMazeArray[row+1][col+1]==0))
            {
                Position pToAdd = new Position(row+1, col+1); //create a new position
                MazeState ms = allStates.get(pToAdd.toString());
                if(currentState.getCost()+15 < ms.getCost()){
                    ms.setCost(currentState.getCost()+15);
                }
                return ms;
            }
        }
        return null;
    }
    public AState alahsonDownLeft(MazeState currentState)
    {
        int row = currentState.getStatePosition().getRowIndex(); //save the row index of our current state in the maze.
        int col =  currentState.getStatePosition().getColumnIndex();//save the col index of our current state in the maze.

        if (row+1<maze.getNumOfRow() && col-1>=0)
        {
            if((intMazeArray[row+1][col]==0 && intMazeArray[row+1][col-1]==0) || (intMazeArray[row][col-1]==0 && intMazeArray[row+1][col-1]==0))
            {
                Position pToAdd = new Position(row+1, col-1); //create a new position
                MazeState ms = allStates.get(pToAdd.toString());
                if(currentState.getCost()+15 < ms.getCost()){
                    ms.setCost(currentState.getCost()+15);
                }
                return ms;
            }
        }
        return null;
    }


    //-_-_-_-_-_-_-_-_-_-_-_-_-function -_-_-_-_-_-_-_-_-_-_-_-_-_-
    public ArrayList<AState> getAllSuccessors(AState someState)  //looking for all possible neighbors of some state in a maze
    {
        if(someState == null){return null;}
        ArrayList <AState> possibleMoves = new ArrayList<>(); //this list save the possible move from some state in the maze
        MazeState castSomeState = (MazeState) someState;
//insert all possible states to the array
        if(canGoUp(castSomeState) != null)
        {
            AState neighborToAdd = canGoUp(castSomeState);
            possibleMoves.add(neighborToAdd);
        }
        if(canGoDown(castSomeState)!= null)
        {
            AState neighborToAdd = canGoDown(castSomeState);
            possibleMoves.add(neighborToAdd);
        }
        if(canGoRight(castSomeState)!= null)
        {
            AState neighborToAdd = canGoRight(castSomeState);
            possibleMoves.add(neighborToAdd);
        }
        if(canGoLeft(castSomeState)!= null)
        {
            AState neighborToAdd = canGoLeft(castSomeState);
            possibleMoves.add(neighborToAdd);
        }
        if(alahsonUpRight(castSomeState) !=null)
        {
            AState neighborToAdd = alahsonUpRight(castSomeState);
            possibleMoves.add(neighborToAdd);
        }
        if(alahsonUpLeft(castSomeState) !=null)
        {
            AState neighborToAdd = alahsonUpLeft(castSomeState);
            possibleMoves.add(neighborToAdd);
        }
        if(alahsonDownRight(castSomeState) !=null)
        {
            AState neighborToAdd = alahsonDownRight(castSomeState);
            possibleMoves.add(neighborToAdd);
        }
        if(alahsonDownLeft(castSomeState) !=null)
        {
            AState neighborToAdd = alahsonDownLeft(castSomeState);
            possibleMoves.add(neighborToAdd);
        }
        return possibleMoves;
    }
}
