package algorithms.search;


//this interface will ensure that all searching algorithms use these methods
public interface ISearchingAlgorithm {


    String getName();
    Solution solve(ISearchable game);

    int getNumberOfNodesEvaluated();
}
