package algorithms.maze3D;

import algorithms.mazeGenerators.Position;
import algorithms.search.AState;

public class Maze3DState extends AState {
    private Position3D StatePosition3D;

    /**
     *
     * @param statePosition3D create a Position type for 3D
     */
    public Maze3DState(Position3D statePosition3D) { //constructor
        super(statePosition3D.toString());
        this.StatePosition3D = statePosition3D;
    }

    @Override
    public String toString() {return StatePosition3D.toString();}

    public Position3D getStatePosition3D() {return StatePosition3D;}
    public void setStatePosition3D(Position3D statePosition3D){this.StatePosition3D = statePosition3D; }

}
