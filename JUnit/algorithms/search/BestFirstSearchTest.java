package algorithms.search;
import algorithms.maze3D.IMaze3DGenerator;
import algorithms.maze3D.Maze3D;
import algorithms.maze3D.MyMaze3DGenerator;
import algorithms.maze3D.SearchableMaze3D;
import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;

import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class BestFirstSearchTest {

    BestFirstSearch bestfsAlgo;


    @Test
    void searchGoalstate() {
        IMaze3DGenerator lolo = new MyMaze3DGenerator();
        bestfsAlgo = new BestFirstSearch();
        Maze3D mzmz = lolo.generate(20, 50,40);
        SearchableMaze3D game = new SearchableMaze3D(mzmz);
        assertNotNull(bestfsAlgo.solve(game));
    }

    @Test
    void getName() {
        bestfsAlgo=new BestFirstSearch();
        assertEquals("Best First Search", bestfsAlgo.getName());
    }

    @Test
    void getNumberOfNodesEvaluated() {
        bestfsAlgo =new BestFirstSearch();
        assertEquals(0, bestfsAlgo.getNumberOfNodesEvaluated());
    }
    @Test
    void solve3D() {
        bestfsAlgo =new BestFirstSearch();
        IMaze3DGenerator lolo = new MyMaze3DGenerator();
        Maze3D mzmz = lolo.generate(20, 50,40);
        SearchableMaze3D searchableMaze = new SearchableMaze3D(mzmz);
        assertNotNull(bestfsAlgo.solve(searchableMaze));

    }

    @Test
    void solveNull() {
        bestfsAlgo =new BestFirstSearch();
        assertThrows(NullPointerException.class,()-> bestfsAlgo.solve(null));
    }

    @Test
    void CheckPathLength(){
        IMazeGenerator lolo = new MyMazeGenerator();
        Maze mzmz = lolo.generate(1000, 1000);
        SearchableMaze searchableMaze = new SearchableMaze(mzmz);
        bestfsAlgo = new BestFirstSearch();
        BreadthFirstSearch breadth= new BreadthFirstSearch();
        Solution s1= bestfsAlgo.solve(searchableMaze);
        Solution s2=breadth.solve(searchableMaze);
        assertTrue(s2.getSolutionPath().size() <= s1.getSolutionPath().size());
    }


    @Test
    void Time(){
        IMazeGenerator lolo = new MyMazeGenerator();
        Maze mzmz = lolo.generate(1000, 1000);
        SearchableMaze searchableMaze = new SearchableMaze(mzmz);
        bestfsAlgo =new BestFirstSearch();
        assertTimeout(Duration.ofMinutes(1),()->bestfsAlgo.solve(searchableMaze));
    }
    @Test
    void Cost(){
        IMazeGenerator lolo = new MyMazeGenerator();
        Maze mzmz = lolo.generate(1000, 1000);
        SearchableMaze searchableMaze = new SearchableMaze(mzmz);
        bestfsAlgo = new BestFirstSearch();
        BreadthFirstSearch breadth= new BreadthFirstSearch();
        bestfsAlgo.solve(searchableMaze);
        breadth.solve(searchableMaze);
        assertTrue(bestfsAlgo.searchGoalstate(searchableMaze).getCost() <= breadth.searchGoalstate(searchableMaze).getCost());
    }

    @Test
    void Time3D(){
        IMaze3DGenerator mg = new MyMaze3DGenerator();
        Maze3D mzmz = mg.generate(100, 100,100);
        SearchableMaze3D searchableMaze = new SearchableMaze3D(mzmz);
        bestfsAlgo=new BestFirstSearch();
        assertTimeout(Duration.ofMinutes(1),()->bestfsAlgo.solve(searchableMaze));
    }


    @Test
    void getNumberOfNodes(){
        bestfsAlgo=new BestFirstSearch();
        IMazeGenerator mg = new MyMazeGenerator();
        Maze mum = mg.generate(100, 100);
        SearchableMaze searchableMaze = new SearchableMaze(mum);
        bestfsAlgo.solve(searchableMaze);
        assertNotEquals(1,bestfsAlgo.getNumberOfNodesEvaluated());
    }

}