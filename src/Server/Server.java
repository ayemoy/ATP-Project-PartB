package Server;

//import Server.Strategy.IServerStrategy;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//need to do it generic

public class Server {
    private int port; //the client use the port number to send info
    private int socketTimeOut; //The elapsed time until socket timeout = listen
    private IServerStrategy serverStrategy; //The strategy for handling clients
    private boolean stop; //when we want stop the server action
    private ExecutorService threadPool;
    //private final Logger LOG = LogManager.getLogger(); //Log4j2

    public Server(int port, int socketTimeOut, IServerStrategy serverStrategy) { //constructor
        this.port = port; //witch port the server listen
        this.socketTimeOut = socketTimeOut;
        this.serverStrategy = serverStrategy;
        this.stop = false;
        this.threadPool = Executors.newFixedThreadPool(3); //hold number of thread and cycle between them.
    }



    public void start(){ //every client that the server get it will open for him a new thread
        Thread newClientThread = new Thread(()-> {this.run();System.out.println("The Server End Now");});
        newClientThread.start();
    }


    public void run(){
        try
        { //init new socket that we listen to everytime we do accept to client
            ServerSocket serverSocket = new ServerSocket(port); //generic sever that get port address
            serverSocket.setSoTimeout(socketTimeOut); //open a new socket the client connect to, and set the time that client enter
            System.out.println("Starting server at port = " + port);

            while (!stop)  //stop thr while loop - stop waiting for clients
            {
                try
                {
                    Socket clientSocket = serverSocket.accept(); //waiting for next client
                    System.out.println("Client accepted: " + clientSocket.toString());
                    threadPool.submit(() -> handleClient(clientSocket)); //lambda expression - Insert the new task into the thread pool:
                }
                catch (IOException e){System.out.println("Socket timeout");}
            }
            serverSocket.close();
            threadPool.shutdown();
        }
        catch (IOException e){ e.printStackTrace();}//if we can listen to this port
    }


    //every client that the server get it will open for him a new thread
    private void handleClient(Socket clientSocket) { //manage server with a several clients
        try
        {
            InputStream inputFromClient = clientSocket.getInputStream();
            OutputStream outpuToClient = clientSocket.getOutputStream();
            this.serverStrategy.ServerStrategy(inputFromClient, outpuToClient);

            inputFromClient.close();
            outpuToClient.close();
            clientSocket.close(); //close the input and output stream
        }
            catch (IOException e){ e.printStackTrace(); }
    }

    public void stop(){ //stop thr while loop - stop waiting for clients
        stop = true;
    }

    //-----------------------getter and setters-----------------------------------------
    public int getPort() { return port; }
    public void setPort(int port) { this.port = port; }
    public int getSocketTimeOut() { return socketTimeOut; }
    public void setSocketTimeOut(int socketTimeOut) { this.socketTimeOut = socketTimeOut; }
    public IServerStrategy getServerStrategy() { return serverStrategy; }
    public void setServerStrategy(IServerStrategy serverStrategy) { this.serverStrategy = serverStrategy; }
    public ExecutorService getThreadPool() { return threadPool; }
    public void setThreadPool(ExecutorService threadPool) { this.threadPool = threadPool; }
    public boolean isStop() { return stop; }
    public void setStop(boolean stop) { this.stop = stop; }


}
