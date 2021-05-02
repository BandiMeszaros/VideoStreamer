package TCPClient;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPClient {

        public static void main(String[] args) {
            Thread t = new Thread(new ClientApp());
            t.start();
        }
    }

class ClientApp implements Runnable{

    @Override
    public void run() {
        //Socket létrehozása (default konstruktorral unbind állapotban -> nincs IP címhez/porthoz rendelve)
        Socket socket = new Socket();
        InetSocketAddress socketAddress;
        try {
            socketAddress = new InetSocketAddress(InetAddress.getLocalHost(),2500);
            socket.connect(socketAddress, 10000);
            BufferedReader bReader = new BufferedReader (new InputStreamReader(socket.getInputStream ()));
            PrintWriter printWriter = new PrintWriter (socket.getOutputStream ());
            String line;
            printWriter.println("haliho motherfucka");
            printWriter.flush();
            line = bReader.readLine();
            System.out.println(line);
        } catch (UnknownHostException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
        try {
                //Socket lezárása (mindkét irány lezárasa TCP FIN üzenettel)
                //socket.setSoLinger(true, 0); beállítás hatására, a close metódus TCP RST üzenettel zárja le a kapcsolatot
                socket.close();
                System.out.println("closed");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
