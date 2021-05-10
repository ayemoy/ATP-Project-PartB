package algorithms.search;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.io.Serializable;

public class MazeState extends AState implements Serializable {
    private Position StatePosition;

    public MazeState(Position statePosition) { //constructor
        super(statePosition.toString());
        this.StatePosition = statePosition;
    }

   // public MazeState (String name){
     //   super(name);
   // }

    @Override
    public String toString(){return StatePosition.toString();}

    public Position getStatePosition() {
        return StatePosition;
    }

    public void setStatePosition(Position statePosition) {
        StatePosition = statePosition;
    }

}
