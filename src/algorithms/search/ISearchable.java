package algorithms.search;

import java.util.*;

/**
 * this interface will take a problem and translate it to a searchable problem
 * we treat this like maze
 * the maze is kind of isearchable
 */

public interface ISearchable {
    AState getStartState();
    AState getGoalState();
    //this list hold all the possible states that we can go to from specific state
    ArrayList<AState> getAllSuccessors(AState currentState);
}
