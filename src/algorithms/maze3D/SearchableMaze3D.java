package algorithms.maze3D;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;
import algorithms.search.AState;
import algorithms.search.ISearchable;
import algorithms.search.MazeState;

import java.util.ArrayList;
import java.util.HashMap;

public class SearchableMaze3D implements ISearchable {

    private Maze3D maze3D;
    private Maze3DState startState;
    private Maze3DState goalState;
    private int [][][] int3DMaze; //our 3D maze matrix
    private HashMap<String, Maze3DState> allStates;

    /**
     *
     * @param maze3D hold a 3D maze
     */
    public SearchableMaze3D(Maze3D maze3D)  //constructor
    {

        this.maze3D = maze3D;
        this.int3DMaze = maze3D.getIntMaze3D();
        this.allStates = new HashMap<>();

        for(int d=0 ; d<maze3D.getDepth() ; d++)
        {
            for (int i=0; i< maze3D.getNumOfRow(); i++)
            {
                for(int j=0; j< maze3D.getNumOfCol(); j++)
                {
                    if (int3DMaze[d][i][j] == 0){
                        Position3D pos = new Position3D(d,i,j); //create a new position so we can give it to new mazestate type as argument
                        Maze3DState tempMS = new Maze3DState(pos);
                        allStates.put(pos.toString(),tempMS);//insert every state to the hashmap when key is the string name of the state, and val is the object itself
                        tempMS.setCost(Integer.MAX_VALUE); //set the first cost as the biggest int that possible
                    }
                }
            }
        }

        this.startState = allStates.get(this.maze3D.getStartPosition().toString()); //initialize the start state . get for key and receive the value
        this.startState.setCost(0); //cost of start state from him to himself is 0
        this.goalState = allStates.get(this.maze3D.getGoalPosition().toString());

    }



    //hhhhhhhhhheeeeeeeeeeeelllllllppppppppp functionnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn

    public AState canGoUp(Maze3DState currentState) //check if we can move from a state to a new  up state
    {
        if(currentState == null){return null;}

        int depth = currentState.getStatePosition3D().getDepthIndex();
        int row = currentState.getStatePosition3D().getRowIndex(); //save the row index of our current state in the maze.
        int col =  currentState.getStatePosition3D().getColumnIndex();//save the col index of our current state in the maze.


        if( row-1>=0 && int3DMaze[depth][row-1][col] ==0 ) //check if we can go up.
        {
            Position3D pToAdd = new Position3D(depth, row-1, col); //if we can, we create a new position to give it to the new mazestate ocject
            Maze3DState ms = allStates.get(pToAdd.toString());//create a new mazestate object and give it the string of the possition
            if(currentState.getCost()+10 < ms.getCost()){ //check if the new mazestate is lower then the state before
                ms.setCost(currentState.getCost()+10); //add the new cost to the path
            }
            return ms;
        }
        return  null;

    }



    public AState canGoDown(Maze3DState currentState) //check if we can move from a state to a new  down state
    {
        if(currentState == null){return null;}

        int depth = currentState.getStatePosition3D().getDepthIndex();
        int row = currentState.getStatePosition3D().getRowIndex(); //save the row index of our current state in the maze.
        int col =  currentState.getStatePosition3D().getColumnIndex();//save the col index of our current state in the maze.

        if( row+1<maze3D.getNumOfRow() &&  int3DMaze[depth][row+1][col] ==0 ) //check if we can go down.
        {
            Position3D pToAdd = new Position3D(depth, row+1, col);
            Maze3DState ms = allStates.get(pToAdd.toString());
            if(currentState.getCost()+10 < ms.getCost()){
                ms.setCost(currentState.getCost()+10);
            }
            return ms;
        }

        return null;
    }



    public AState canGoRight(Maze3DState currentState) //check if we can move from a state to a new right state
    {
        if(currentState == null){return null;}

        int depth = currentState.getStatePosition3D().getDepthIndex();
        int row = currentState.getStatePosition3D().getRowIndex(); //save the row index of our current state in the maze.
        int col =  currentState.getStatePosition3D().getColumnIndex();//save the col index of our current state in the maze.

        if( col+1<maze3D.getNumOfCol() && int3DMaze[depth][row][col+1] ==0 ) //check if we can go right.
        {
            Position3D pToAdd = new Position3D(depth, row, col+1);
            Maze3DState ms = allStates.get(pToAdd.toString());
            if(currentState.getCost()+10 < ms.getCost()){
                ms.setCost(currentState.getCost()+10);
            }
            return ms;
        }
        return null;
    }



    public AState canGoLeft(Maze3DState currentState) //check if we can move from a state to a new  left state
    {
        if(currentState == null){return null;}

        int depth = currentState.getStatePosition3D().getDepthIndex();
        int row = currentState.getStatePosition3D().getRowIndex(); //save the row index of our current state in the maze.
        int col = currentState.getStatePosition3D().getColumnIndex();//save the col index of our current state in the maze.

        if( col-1>=0 && int3DMaze[depth][row][col-1] ==0 ) //check if we can go left.
        {
            Position3D pToAdd = new Position3D(depth, row, col-1); //create a new position
            Maze3DState ms = allStates.get(pToAdd.toString());
            if(currentState.getCost()+10 < ms.getCost()){
                ms.setCost(currentState.getCost()+10);
            }
            return ms;
        }
        return null;

    }

    public AState canGodepth(Maze3DState currentState) //check if we can move on the matrix array
    {
        if(currentState == null){return null;}

        int depth = currentState.getStatePosition3D().getDepthIndex();
        int row = currentState.getStatePosition3D().getRowIndex(); //save the row index of our current state in the maze.
        int col = currentState.getStatePosition3D().getColumnIndex();//save the col index of our current state in the maze.

        if( depth-1>=0 && int3DMaze[depth-1][row][col] ==0 ) //check if we can go left.
        {
            Position3D pToAdd = new Position3D(depth-1 ,row, col); //create a new position
            Maze3DState ms = allStates.get(pToAdd.toString());
            if(currentState.getCost()+10 < ms.getCost()){
                ms.setCost(currentState.getCost()+10);
            }
            return ms;
        }
        return null;

    }
    public AState canGodepth2(Maze3DState currentState) //check if we can move on the matrix array
    {
        if(currentState == null){return null;}

        int depth = currentState.getStatePosition3D().getDepthIndex();
        int row = currentState.getStatePosition3D().getRowIndex(); //save the row index of our current state in the maze.
        int col = currentState.getStatePosition3D().getColumnIndex();//save the col index of our current state in the maze.

        if( depth+1<maze3D.getDepth() && int3DMaze[depth+1][row][col] ==0 ) //check if we can go right.
        {
            Position3D pToAdd = new Position3D(depth+1 ,row, col); //create a new position
            Maze3DState ms = allStates.get(pToAdd.toString());
            if(currentState.getCost()+10 < ms.getCost()){
                ms.setCost(currentState.getCost()+10);
            }
            return ms;
        }
        return null;

    }


    //&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&

    @Override
    public AState getStartState() {return this.startState;}
    @Override
    public AState getGoalState() {return this.goalState;}


    @Override
    public ArrayList<AState> getAllSuccessors(AState currentState) //looking for all possible neighbors of some state in a maze
    {
        if(currentState == null){return null;}
        ArrayList <AState> possibleMoves = new ArrayList<>(); //this list save the possible move from some state in the maze
        Maze3DState castSomeState = (Maze3DState) currentState;
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
        if(canGodepth(castSomeState) !=null)
        {
            AState neighborToAdd = canGodepth(castSomeState);
            possibleMoves.add(neighborToAdd);
        }
        if(canGodepth2(castSomeState) !=null)
        {
            AState neighborToAdd = canGodepth2(castSomeState);
            possibleMoves.add(neighborToAdd);
        }
        return possibleMoves;
    }
}
