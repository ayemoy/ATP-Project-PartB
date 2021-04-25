package algorithms.search;

import java.util.ArrayList;
/**
 * this class extend ASearchingAlgorithm class
 * this class will search the goal state by DFS algorithm
 */

public class Solution {

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
   // public void setSolutionPath(ArrayList<AState> solutionPath) {
        //this.solutionPath = solutionPath;
    //}


    //public void addStateToPathSolution(AState stateOfPath) //this function save the path of the solution
    //{
      //  if(stateOfPath != null){ solutionPath.add(stateOfPath);} //add a state to the solution path
   // }
}

