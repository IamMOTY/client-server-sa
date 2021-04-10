package clientserver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Provides implementation for server side, witch returns fibonacci number.
 */
public class Server {
    /**
     * Maximum length of the queue of incoming connections.
     */
    private static final int BACKLOG = 50;

    /**
     * Starts the server
     *
     * @param host the local InetAddress the server will bind to
     * @param port the port number.
     */
    public static void run(final InetAddress host, final int port)  {
        try {
            ServerSocket ss = new ServerSocket(port, BACKLOG, host);
            while (true) {
                Socket s = null;
                try {
                    s = ss.accept();
                    System.out.println("A new client is connected : " + s);
                    DataInputStream dis = new DataInputStream(s.getInputStream());
                    DataOutputStream dos = new DataOutputStream(s.getOutputStream());
                    System.out.println("Assigning new thread for this client");
                    Thread t = new ClientHandler(s, dis, dos);
                    t.start();
                } catch (Exception e) {
                    if (s != null) {
                        s.close();
                    }
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
  