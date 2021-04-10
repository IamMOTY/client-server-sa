package clientserver;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Objects;

/**
 * Provides command line interface for client server application.
 * {@code client|server <host> <port>}
 */
public class CLI {
    /**
     * Insert point of application.
     * @param args input arguments
     */
    public static void main(String[] args) {
        if (args == null) {
            System.err.println("Arguments is null.");
            return;
        }
        if (args.length != 3) {
            System.err.println("Invalid count of arguments.");
            return;
        }
        if (Arrays.stream(args).anyMatch(Objects::isNull)) {
            System.err.println("Arguments contains null value.");
            return;
        }
        try {
            final InetAddress host = InetAddress.getByName(args[1]);
            final int port = Integer.parseInt(args[2]);
            if (port < 1 || port > 65535) {
                System.err.println("Port must be between 1 and 65535.");
                return;
            }
            if (args[0].equals("server")) {
                Server.run(host, port);
            } else if (args[0].equals("client")) {
                Client.run(host, port);
            } else {
                System.err.println("First argument must be <server|host>.");
            }
        } catch (final UnknownHostException e) {
            System.err.println("Unknown host.");
        } catch (final NumberFormatException e) {
            System.err.println("Port isn't number.");
        }
    }
}
