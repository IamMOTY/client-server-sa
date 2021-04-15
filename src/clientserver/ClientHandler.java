package clientserver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.net.Socket;

/**
 * Provides processing requests from the client
 */
class ClientHandler extends Thread {
    private final DataInputStream dis;
    private final DataOutputStream dos;
    private final Socket s;

    /**
     * Creates instance handler
     *
     * @param s   connection socket
     * @param dis data input stream
     * @param dos data output stream
     */
    public ClientHandler(final Socket s, final DataInputStream dis, final DataOutputStream dos) {
        this.s = s;
        this.dis = dis;
        this.dos = dos;
    }

    @Override
    public void run() {
        while (true) {
            try {
                dos.writeUTF("Input serial number of requested fibonacci number\n" +
                        "Type empty string to terminate connection");
                final String received = dis.readUTF();
                if (received.equals("")) {
                    System.out.println("clientserver.Client " + this.s + " sends exit...");
                    System.out.println("Closing this connection.");
                    this.s.close();
                    System.out.println("Connection closed");
                    break;
                }
                try {
                    final int n = Integer.parseInt(received);
                    final BigInteger fibonacci = Fibonacci.getNth(n);
                    dos.writeUTF(String.format("%dth fibonacci number is %d\n", n, fibonacci));
                } catch (final NumberFormatException e) {
                    dos.writeUTF("Invalid number format, input must be positive integral number.\n");
                } catch (final IllegalArgumentException e) {
                    dos.writeUTF(String.format("%s\n", e.getMessage()));
                }

            } catch (final IOException e) {
                e.printStackTrace();
            }
        }

        try {
            this.dis.close();
            this.dos.close();
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }
}