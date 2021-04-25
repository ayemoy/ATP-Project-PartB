package algorithms.search;
import algorithms.mazeGenerators.Position;

public class MazeState extends AState{
    private Position StatePosition;

    public MazeState(Position statePosition) { //constructor
        super(statePosition.toString());
        this.StatePosition = statePosition;
    }

    @Override
    public String toString(){return StatePosition.toString();}

    public Position getStatePosition() {
        return StatePosition;
    }

    public void setStatePosition(Position statePosition) {
        StatePosition = statePosition;
    }

}
