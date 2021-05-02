package TCPServer;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TCPServer {

    private static final int THREAD_COUNT = 5;
    private static final int PORT= 25000;

    public static void main(String[] args) {
        try{
            ExecutorService es = Executors.newFixedThreadPool(THREAD_COUNT);
            ServerSocket ss = new ServerSocket(PORT);
            while(true) {
                Socket s = ss.accept ();
                es.execute(new TCPServerThread (s));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
