import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    // Server socket that accepts client and handles data flow.
    private ServerSocket serverSocket;

    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void starServer() {
        try {
            while (!serverSocket.isClosed()) {
                // New connected client socket.
                Socket socket = serverSocket.accept();
                System.out.println("A new client has been connected!");
                ClientHandler clientHandler = new ClientHandler(socket);

                // Start a new thread for new connected client.
                Thread thread = new Thread(clientHandler);
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
            closeServerSocket();
        }
    }

    public void closeServerSocket() {
        try {
            if (serverSocket != null) {
                serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(5000);
        Server server = new Server(serverSocket);
        server.starServer();
    }

}
