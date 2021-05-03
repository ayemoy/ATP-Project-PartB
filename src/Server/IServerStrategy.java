package Server;

import java.io.InputStream;
import java.io.OutputStream;

//this interface use the strategy ans hold type of generic IServerStrategy
//this strategy use for create mazes and solve mazes.
//in run time this strategy choose witch strategy we wanna use

public interface IServerStrategy {

    public void applyStrategy(InputStream inputFromClient, OutputStream outpuToClient);

}