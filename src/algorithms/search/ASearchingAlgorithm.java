package algorithms.search;

import java.util.ArrayList;


/**
 * an abstract class that all the searching algorithms need to extend
 *
 */

public abstract class  ASearchingAlgorithm implements ISearchingAlgorithm {


    protected String name;
    protected int numOfEvaluatedNodes; //save the number of steps (visited node that the search algo walk on them to find a solution)


    public ASearchingAlgorithm() {
        this.numOfEvaluatedNodes = 0;
    }//constructor

    public abstract AState searchGoalstate(ISearchable game);

    @Override
    public Solution solve(ISearchable game)
    {
        if(game == null){throw new NullPointerException();}

        ArrayList<AState> totalPath = new ArrayList<>(); //hold the final path of the game the we need to print
        AState algoGoalState = searchGoalstate(game); //we run one of BFS/DFS/BestFS algo and save the goal state

        while(algoGoalState!=null)
        {
            totalPath.add(algoGoalState); //insert to the path list the goal state
            algoGoalState = algoGoalState.getPapa(); //go to the father of every state
        }

        ArrayList<AState> finalRes = new ArrayList<>(); //new list to revers the total path
        for(int i=totalPath.size()-1; i>=0; i--)
        {
            finalRes.add(totalPath.get(i));
        }

        Solution solution = new Solution(finalRes); //make the path a solution object

        return solution;
    }

    @Override
    public String getName() { return name; }
    @Override
    public int getNumberOfNodesEvaluated() { return numOfEvaluatedNodes; }
}
