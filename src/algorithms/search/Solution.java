package algorithms.search;

import java.io.Serializable;
import java.util.ArrayList;
/**
 * this class extend ASearchingAlgorithm class
 * this class will search the goal state by DFS algorithm
 */

public class Solution implements Serializable {

    private ArrayList<AState> solutionPath; //this list save the states of the solution path

    /**
     *
     * @param solutionPath return the right path of the algorithm do
     */

    public Solution(ArrayList<AState> solutionPath) {
        this.solutionPath = solutionPath;
    } //constructor

    public ArrayList<AState> getSolutionPath() {
        return solutionPath;
    }

    public void setNeighboursPathToNull()
    {
        for (AState state:solutionPath) {
            state.setNeighboursToNull();
        }
    }
}

