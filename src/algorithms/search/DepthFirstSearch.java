package algorithms.search;
import java.util.*;



public class DepthFirstSearch extends  ASearchingAlgorithm {

    protected Stack<AState> notVisitedList; //save the not visited state of the game
    protected HashSet<AState> visitedList; //save the visited state of the game



    public DepthFirstSearch() { //constructor
        this.name = "Depth First Search";
        this.notVisitedList = new Stack<>(); //use linkedlist of Astate to do the queue
        this.visitedList = new HashSet<AState>(); //use hash set of Astate to do the visited list
    }


    @Override
    public AState searchGoalstate(ISearchable game)
    {
        if(game == null){return null;} //if game empty, we cant do search on it, so return null

        AState startState =game.getStartState(); //tell us where the start state of the game
        AState goalState = game.getGoalState(); ////tell us where the end state of the game

        notVisitedList.push(startState);//add the first state to the list of visited states

        while (!notVisitedList.isEmpty())
        {
            AState currentState = notVisitedList.pop(); //hold the head of the queue. uts a new state
            visitedList.add(currentState); //add to visited the current state
            currentState.setVisited(true); //mark the new state we polled as visited
            ArrayList<AState> neighborsList = game.getAllSuccessors(currentState); //list of all possible state of the current state (neighbors)

            for(int i=0 ; i<neighborsList.size() ; i++)
            { //run on the neighbors list, and check if they not exist in visited and notvisited lists
                AState oneNeighbor = neighborsList.get(i);
//                if(!(notVisitedList.contains(oneNeighbor))&& !(visitedList.contains(oneNeighbor)))
                if(!(visitedList.contains(oneNeighbor)))
                { //if they not exist, we insert them to notvisited list
                    oneNeighbor.setPapa(currentState); //set the father of every neighbor
                    notVisitedList.add(oneNeighbor); //add the neighbor to notvisited list
                }

                if(currentState.equals(goalState)) //check if we already at the end of the game.(if the current state is the goal state)
                {//if true we enter to the if
                    goalState = currentState;
                    visitedList.add(goalState);
                    break;
                }
            }
            if (visitedList.contains(goalState)){
                break;
            }
        }
        return  goalState;
    }


    @Override
    public String getName() {
        return this.name;
    }


    @Override
    public int getNumberOfNodesEvaluated() {
        return visitedList.size();
    }
}
