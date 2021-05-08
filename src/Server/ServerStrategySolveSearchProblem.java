package Server;

import java.io.InputStream;
import java.io.OutputStream;
import algorithms.mazeGenerators.Maze;
import algorithms.search.*;
import java.io.*;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;


//in this class the server will receive maze object from the client.
// server solve the maze and turn it back as Solution object that hold the maze solution
//every solution saved in separate file in temporary folder
public class ServerStrategySolveSearchProblem implements IServerStrategy{

    private volatile static AtomicInteger clientCounter; //save as volatile the number of client we have so we dont hold thid argument for too long so every client can use it
    private static ConcurrentHashMap<String, String> solveOfMazeMap; //here we save in key our maze as byte array string, the value is the solution in string
    private String tempDirectoryPath; //hold a temporary string path
    private ISearchingAlgorithm algorithmSearcher; //save the algorithm that we use with

    //constructor
    public ServerStrategySolveSearchProblem()
    {
        //tempDirPath = System.getProperty("java.io.tmpdir");
        this.solveOfMazeMap = new ConcurrentHashMap<>();
        this.clientCounter = new AtomicInteger(0);
        this.algorithmSearcher = new BreadthFirstSearch();
        this.tempDirectoryPath =System.getProperty("java.io.tmpdir"); //The server saves the solution to the mazes it receives on disk, each solution is saved in a separate file(3a)

    }

    @Override
    public void ServerStrategy(InputStream inputFromClient, OutputStream outputToClient)
    {
        try
        {
            ObjectInputStream fromClient = new ObjectInputStream(inputFromClient); //get from client the object maze
            ObjectOutputStream toClient = new ObjectOutputStream(outputToClient); //set to client solution object of maze

            Maze mazeFromClient = (Maze)fromClient.readObject(); //read the maze from client and hold it in Maze object
            byte[] mazeFromClientAsBytes = mazeFromClient.toByteArray(); //turn the maze to byte array so we can use it

            String newMazeKeyStr = Arrays.toString(mazeFromClientAsBytes); //return the bytes maze to string so we can use it for the key in solveOfMazeMap
            Solution solutionMaze; //save the solution that we need to return for client (Solution object)
            String solutionFileName; //save the solution

            //check if the maze maze already solved
            if (solveOfMazeMap.containsKey(newMazeKeyStr)) //if the hashmap of solved maze have this maze already
            {
                solutionFileName = solveOfMazeMap.get(newMazeKeyStr); //we save the name of this file that contain the solution
                solutionMaze = readSolutionFromFile(solutionFileName); //then we read this solution from this file and save it in solution objedct so we can return it to client
            //todo why we dont return the solution??
            }

            else //if solveOfMazeMap do not contain the solution of newMazeKeyStr maze so we need to solve and return it
            {
                SearchableMaze searchableMaze = new SearchableMaze(mazeFromClient); //search a path to solve the maze that we get from client
                solutionMaze = algorithmSearcher.solve(searchableMaze); //after we found the pate, we solve it and hold it in Solution Object
                solutionMaze.setNeighboursPathToNull(); //set all neighbors of the solotion path as null

                String mazeFileName = "Maze" + clientCounter.incrementAndGet();
                solutionFileName = "Solution" + clientCounter.get();

                //now we send to help function that will updates solveOfMazeMap and save the new maze and its solution so we can return it to the client
                writeAllDetailsToClient(solutionFileName,solutionMaze,mazeFileName,mazeFromClient,newMazeKeyStr);
            }
            toClient.writeObject(solutionMaze);
            toClient.flush();
        }

        catch (IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    //_______________help function__________________________

    //this help function is reading string solution from a specific file that contain the maze solution that we want, and make it a Solution object
    private Solution readSolutionFromFile(String solutionFileName) {
        Solution finalSolution = null; //here we save the final solution as object Solution and return it
        try
        {
            FileInputStream file = new FileInputStream(tempDirectoryPath + solutionFileName); //open a new file with this name
            ObjectInputStream input = new ObjectInputStream(file); //turn this file to a new ObjectInputStream
            finalSolution = (Solution) input.readObject(); //read the string solution and save it in Solution object
            file.close();
            return finalSolution;
        }
        catch (IOException|ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        return finalSolution;
    }




    //this help function get a new maze that we haven't his solution in map, and creates all his detaild ans write it to the client
    private synchronized void writeAllDetailsToClient(String solutionFileName, Solution solutionMaze, String mazeFileName, Maze outMazeClient, String newMazeKeyStr)
    {
        writeSolutionToFileValue(solutionFileName, solutionMaze); //we write the solution of the maze that client give us as string file and set it as a value in solveOfMaze
        writeMazeToFileKey(mazeFileName, outMazeClient); //we write the maze that client give us as string file and set it as a key in solveOfMazeMap
        solveOfMazeMap.put(newMazeKeyStr, solutionFileName); //update solveOfMazeMap
    }


    //this help function write the maze that client gave us to a file, so we can set this file as key in solveOfMazeMap and later save this maze solution
    private void writeMazeToFileKey(String mazeFileName, Maze outMazeClient)
    {
        try {
            FileOutputStream file = new FileOutputStream(tempDirectoryPath + mazeFileName);
            ObjectOutputStream mazeFile = new ObjectOutputStream(file);
            mazeFile.writeObject(outMazeClient);
            mazeFile.flush();
            mazeFile.close();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //this help function will write to file the solution of the new maze that we want to add to solveOfMazeMap
    private void writeSolutionToFileValue(String solutionFileName, Solution solutionMaze)
    {
        try {
            FileOutputStream file = new FileOutputStream(tempDirectoryPath + solutionFileName);
            ObjectOutputStream solutionFile = new ObjectOutputStream(file);
            solutionFile.writeObject(solutionMaze);
            solutionFile.flush();
            solutionFile.close();
            file.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }




}
