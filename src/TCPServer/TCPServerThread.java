package TCPServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TCPServerThread implements Runnable{

    private Socket socket;

    public TCPServerThread(Socket s) {
        this.socket = s;
    }

    public void run(){
     try
     {
         BufferedReader bReader = new BufferedReader (new InputStreamReader(socket.getInputStream ()));
         PrintWriter printWriter = new PrintWriter (socket.getOutputStream ());
         String line;
         while ((line = bReader.readLine ()) != null) {
             System.out.println(line);
             printWriter.println (line.toUpperCase ());
             printWriter.flush ();
         }
     }
     catch (Exception e) {
         e.printStackTrace ();
     }
     finally {
         try { socket.close ();
         }
         catch (IOException e2)
         {
             e2.printStackTrace ();
         }
     }

     }
    }
