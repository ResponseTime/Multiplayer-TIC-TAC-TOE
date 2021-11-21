import java.io.*;
import java.net.*;
class newtworking{
    public static ServerSocket server;
    public static DataInputStream inputStream;
    public static DataOutputStream outputStream;
    

    public void start(int port) throws Exception {
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
}

class game{
    public String p1;
    game(String p1,String p2){
        this.p1 = p1;
    }
    public void run(){
        
    }
}
public class App {
   
   
    public static void main(String[] args) throws Exception {
        newtworking net = new newtworking();
        net.start(8008);
        game game = new game("p1","p2");
        game.run();

    }
}
