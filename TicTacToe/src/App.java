import java.io.*;
import java.net.*;
import java.util.*;
class networking{
    public ServerSocket server;
    DataInputStream inputStream;
    DataOutputStream outputStream;
    public void start(int port) throws Exception {
        try{
            server = new ServerSocket(port);
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
}

class game{
    public String p1;
    public String p2;
    game(String p1) throws Exception{
        networking net = new networking();
        net.start(8008);
        this.p1 = p1;
        this.p2 = net.inputStream.readUTF();
        net.outputStream.writeUTF(this.p1);
        net.outputStream.flush();

    }
    public void run() throws Exception{
        System.out.println("Player 1: " + p1+"-->You");
        System.out.println("Player 2: " + p2);
        System.out.println("Press any button to continue");
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();  
    }
}
public class App {
   
   
    public static void main(String[] args) throws Exception {
        networking net = new networking();
        game game = new game("aayush");
        game.run();

    }
}
