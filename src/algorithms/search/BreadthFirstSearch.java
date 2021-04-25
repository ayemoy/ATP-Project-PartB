package algorithms.search;

import java.util.*;

public class BreadthFirstSearch extends  ASearchingAlgorithm{

    protected Queue<AState> notVisitedList; //save the not visited state of the game
    protected HashSet<AState> visitedList; //save the visited state of the game


    public BreadthFirstSearch() //constructor
    {
        this.notVisitedList = new LinkedList<AState>(); //use linkedlist of Astate to do the queue
        this.visitedList = new HashSet<AState>(); //use hash set of Astate to do the visited list
        this.name = "Breadth First Search";

    }



    @Override
    public AState searchGoalstate(ISearchable game) { //bfs run on all state in the maze and meadken sadot so we can later find the shortest path in solution function
        if(game == null){return null;} //if game empty, we cant do search on it, so return null

        AState startState =game.getStartState(); //tell us where the start state of the game
        AState goalState = game.getGoalState(); ////tell us where the end state of the game

        notVisitedList.add(startState); //add the first state to the list of visited states

        while (!notVisitedList.isEmpty())
        {
            AState currentState = notVisitedList.poll(); //hold the head of the queue. uts a new state
            visitedList.add(currentState); //add to visited the current state
            currentState.setVisited(true); //mark the new state we polled as visited
            ArrayList<AState> neighborsList = game.getAllSuccessors(currentState); //list of all possible state of the current state (neighbors)

            for(int i=0 ; i<neighborsList.size() ; i++)
            { //run on the neighbors list, and check if they not exist in visited and notvisited lists
                AState oneNeighbor = neighborsList.get(i);
                if(!(notVisitedList.contains(oneNeighbor))&& !(visitedList.contains(oneNeighbor)))
                { //if they not exist, we insert them to notvisited list
                    oneNeighbor.setPapa(currentState); //set the father of every neighbor
                    notVisitedList.add(neighborsList.get(i)); //add the neighbor to notvisited list
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
        return name;
    }

    @Override
    public int getNumberOfNodesEvaluated() {
        return visitedList.size();
    }

    ///??????????????????????????????????????????????????????????????????????///
    @Override
    public int hashCode() { return super.hashCode(); }

    @Override
    public boolean equals(Object obj) { return super.equals(obj); }
    //?????????????????????????????????????????????????????????//??????????///



}
