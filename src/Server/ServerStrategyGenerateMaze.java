package Server;

import java.io.InputStream;
import java.io.OutputStream;

//this class implement IServerStrategy interface and must do the applyStrategy function like they want in the workPapers

public class ServerStrategyGenerateMaze implements IServerStrategy{


    @Override //this func get int arr=[sizeOfRowMaze,sizeFoColMaze] and create the maze. then we compressed the maze
    //with myCompressor and send it back to the client as compressed maze
    public void applyStrategy(InputStream inputFromClient, OutputStream outputToClient) {

        try
        {

        }

        catch ()
        {

        }

    }



}
