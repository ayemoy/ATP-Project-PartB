package algorithms.search;

import java.io.Serializable;
import java.util.*;

public abstract class AState  implements Serializable {
    private double cost; //the cost of arrival to a specific state
    private String stateName;
    private AState papa;
    private ArrayList<AState> stateNeighborsList; //save all neighbors of specific state that we can go to
    private boolean isVisited;
    //maybe need to save the path to every state


    public AState(String stateName) //constructor
    {
        this.stateName = stateName;
    }


// -_-_-_-_-_-_-_-_-_-_--_-__ getters a n d setters -_-_--_-_-____--__--__

    public double getCost() {return cost;}
    public String getStateName() { return stateName; }
    public AState getPapa() { return papa; }
    public ArrayList<AState> getStateNeighborsList() { return stateNeighborsList; }
    public boolean isVisited() { return isVisited; }
    public void setCost(double cost) { this.cost = cost; }
    public void setStateName(String stateName) { this.stateName = stateName; }
    public void setPapa(AState papa) { this.papa = papa; }
    public void setStateNeighborsList(ArrayList<AState> stateNeighborsList) { this.stateNeighborsList = stateNeighborsList; }
    public void setVisited(boolean visited) { isVisited = visited; }


    //-_-_-_-_-_-_-_-_-_-_-_drisat operatorim -_-_-_-_-_-_-_-_--___--_

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AState aState = (AState) o;
        return stateName.equals(aState.stateName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stateName);
    }

    public void setNeighboursToNull() { stateNeighborsList = null;}
}