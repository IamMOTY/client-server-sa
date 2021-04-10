package clientserver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * Provides implementation for client side, witch sends requests for server.
 */
public class Client {
    /**
     * Starts the client
     * @param host the IP address of server
     * @param port the port number
     */
    public static void run(final InetAddress host, final int port){
        try {
            Socket s = new Socket(host, port);
            try(Scanner scn = new Scanner(System.in);
                DataInputStream dis = new DataInputStream(s.getInputStream());
                DataOutputStream dos = new DataOutputStream(s.getOutputStream())) {
                while (true) {
                    System.out.println(dis.readUTF());
                    String toSend = scn.nextLine();
                    dos.writeUTF(toSend);

                    if (toSend.equals("")) {
                        System.out.println("Closing this connection : " + s);
                        s.close();
                        System.out.println("Connection closed");
                        break;
                    }
                    String received = dis.readUTF();
                    System.out.println(received);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}