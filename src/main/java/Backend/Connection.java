package src.main.java.Backend;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;

/**
 * Contains methods to communicate with another person over a socket
 */
public class Connection {
    
    private int mode; //0 = host, 1 = client
    private String hostName;
    private int serverPort;
    private ServerSocket server;
    private Socket client;
    private BufferedReader in;
    private PrintWriter out;
    private boolean connected = false;

    /**
     * Construct a new connection object, but it is not connected yet
     * @param mode - 0 = host, 1 = client
     * @param hostName A host name like localhost or an IP adress
     * @param serverPort The port number to connect on
     */
    public Connection(int mode, String hostName, int serverPort) {
        this.mode = mode;
        this.hostName = hostName;
        this.serverPort = serverPort;
    }

    /**
     * If set to host,this is blocking and it waits for a connection to be made. 
     * If set to client, this is not blocking and it just looks for a connection and throws an error if one isn't available. 
     * Then creates connection streams and sets connected to true.
     * @throws IOException If connection doesn't work
     * @throws IllegalArgumentException If port number is invalid
     */
    public void connect() throws IOException, IllegalArgumentException {
        if (mode == 0) { //host
            server = new ServerSocket(serverPort, 1);
            client = server.accept();
            server.close();
        } else if (mode == 1) { //join
            try {
                client = new Socket(hostName, serverPort);
                server = null;
            } catch (IOException e) {
                throw new IOException("Could not connect, make sure someone is hosting on port " + serverPort);
            }
            
        }
        out = new PrintWriter(client.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        connected = true;
    }

    /**
     * Blocking call to read in a line from connected source. Line is determined by newline, line end, or EOF
     * @return String sent from connected source
     * @throws IOException Connection fails
     */
    public String read() throws IOException {
        return in.readLine();
        
    }

    /**
     * Put the given output on the out stream with a newline character
     * @param output String to be sent on the connection
     * @throws IOException If output cannot be written
     */
    public void write(String output) throws IOException {
        out.println(output);
    }

    /**
     * Returns the mode the connection is set to
     * @return 0 = host, 1 = client
     */
    public int mode() {
        return mode;
    }

    /**
     * Returns whether this Connection object is currently connected
     * @return true if connected, false otherwise
     */
    public boolean connected() {
        return connected;
    }

    /**
     * Closes the connection and all data streams
     * @throws IOException If a stream cannot be closed
     */
    public void close() throws IOException {
        if (in != null) {
            in.close();
        }
        if (out != null) {
            out.close();
        }
        connected = false;
        if (server != null) {
            server.close();
        }
    }

    /**
     * Closes the connection and all data streams and sends a final message
     * @param output - The last message to send before closing the connection
     * @throws IOException If the message can't be send or the connection can't be closed
     */
    public void closingMessage(String output) throws IOException {
        connected = false;
        out.println(output);
        if (in != null) {
            in.close();
        }
        if (out != null) {
            out.close();
        }
        if (server != null) {
            server.close();
        }
    }
}
