package algorithms.search;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * this class is a searching algorithm that return the lightest path from start to goal
 * this class inherit BFS class and have the same searching algorithm with different data structure
 *//**
 * this class is a comparator for the priority queue that we use in Best First Search algorithm
 * it will compare between 2 AStates by their cost
 */
public class BestFirstSearch extends BreadthFirstSearch{

    protected PriorityQueue<AState> notVisitedList;

    public BestFirstSearch() {
        super();
        this.name = "Best First Search";
        this.numOfEvaluatedNodes = 0;
        this.notVisitedList = new PriorityQueue<AState>();
    }


    private class compareAstates implements Comparator<AState>{
        @Override
        public int compare(AState state1, AState state2) { //compare the cost betweet 2 state, and take the lowerdouble state1Cost = state1.getCost();
            double state1Cost = state1.getCost();
            double state2Cost = state2.getCost();
            if (state1Cost == state2Cost) { return 0;}//if the cost equal
            else if (state1Cost > state2Cost) { return 1; }
            else { return -1; }
        }
    }

    /**
     *
     * @param game the searchable problem
     * @return Astate of the goal
     */

    @Override
    public AState searchGoalstate(ISearchable game) {
        return super.searchGoalstate(game);
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public int getNumberOfNodesEvaluated() {
        return super.getNumberOfNodesEvaluated();
    }
}
