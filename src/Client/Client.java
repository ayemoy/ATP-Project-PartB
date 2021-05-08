package Client;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Client implements IClientStrategy{

    private InetAddress serverIP; //save the server ip the client connect with
    private int serverPort; //save the server port that client connect with
    private IClientStrategy clientStrategy;

    public Client(InetAddress serverIP, int serverPort, IClientStrategy clientStrategy) //consructor
    {
        this.serverIP = serverIP;
        this.serverPort = serverPort;
        this.clientStrategy = clientStrategy;
    }

    @Override
    public void clientStrategy(InputStream inFromServer, OutputStream outToServer) {

    }

    public void communicateWithServer() {
        try {
            Socket socket = new Socket(serverIP, serverPort);
            clientStrategy.clientStrategy(socket.getInputStream(),socket.getOutputStream());
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
