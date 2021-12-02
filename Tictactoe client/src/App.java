import java.net.*;
import java.util.*;
import java.io.*;

class networking{
    
    Socket server;
    DataInputStream dne;
    DataOutputStream dout;
    networking() throws Exception{
        this.server = new Socket("Gamer",8000);
        this.dne = new DataInputStream(server.getInputStream());
        this.dout = new DataOutputStream(server.getOutputStream());
        System.out.println("Connected");
    }
    networking(String hostname,int port) throws Exception{
        this.server = new Socket(hostname,port);
        this.dne = new DataInputStream(server.getInputStream());
        this.dout = new DataOutputStream(server.getOutputStream());
        System.out.println("Connected");
    }
}
class game{
    private static Scanner s = new Scanner(System.in);
    String p1;
    String p2;
    game(String player2) throws Exception{
        System.out.printf("Enter the hostname ");
        String host = s.nextLine();
        System.out.printf("Enter the port number ");
        int port = s.nextInt();
        networking net = new networking(host,port);
        this.p2 = player2;
        net.dout.writeUTF(this.p2);
        net.dout.flush();
        this.p1 = net.dne.readUTF();
    }
    public void run() throws Exception{
        System.out.println("Player 1: "+p1);
        System.out.println("Player 2: "+p2+"-->You");
        System.out.println("Press any button to continue");
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();  
    }
}
public class App {
    public static void main(String[] args) throws Exception {
        new game("Mizuhara").run(); 
    }
}
