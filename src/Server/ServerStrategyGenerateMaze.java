package Server;

import IO.MyCompressorOutputStream;
import algorithms.mazeGenerators.AMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;

import java.io.*;

//this class implement IServerStrategy interface and must do the applyStrategy function like they want in the workPapers

public class ServerStrategyGenerateMaze implements IServerStrategy{


    @Override //this func get int arr=[sizeOfRowMaze,sizeFoColMaze] and create the maze. then we compressed the maze
    //with myCompressor and send it back to the client as compressed maze
    public void ServerStrategy(InputStream inputFromClient, OutputStream outputToClient) {

        try
        {
            ObjectInputStream readFromClient = new ObjectInputStream(inputFromClient); //read from here the array with the maze details
            ObjectOutputStream sendToClient = new ObjectOutputStream(outputToClient); //return to client the compressed maze object

            //reads from client
            int[] mazeArraySize = (int[]) readFromClient.readObject(); //in this array we will save the size of the maze that the client senf us

            //generates the maze
            AMazeGenerator mazeGenerator; //create a mazegenerator instance
            mazeGenerator = new MyMazeGenerator(); //open a new mazegenerator
            Maze maze = mazeGenerator.generate(mazeArraySize[0], mazeArraySize[1]); //open maze object in the right size

            //then we compresses the maze so we can send it back to client
            OutputStream out = new MyCompressorOutputStream(sendToClient);
            out.write(maze.toByteArray());

            out.flush();
            out.close();

        }
         catch (IOException | ClassNotFoundException e){e.printStackTrace();}

    }



}
