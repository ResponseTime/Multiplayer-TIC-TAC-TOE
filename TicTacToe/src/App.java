import java.io.*;
import java.net.*;

public class App {
    public static ServerSocket server;
    public static DataInputStream inputStream;
    public static DataOutputStream outputStream;

    public static void start(int port) throws Exception {
        server = new ServerSocket(port);
        server.setSoTimeout(60000);
        try{
            Socket socket = server.accept();
            if(socket.isConnected()) {
                System.out.println("Connected");
                inputStream = new DataInputStream(socket.getInputStream());
                outputStream = new DataOutputStream(socket.getOutputStream());
            }
        }
        catch(Exception e){
            System.out.println("Failed to connect");
        }
       
    }

    public static void main(String[] args) throws Exception {
        start(8000);
    }
}
