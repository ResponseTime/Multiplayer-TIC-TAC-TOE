import java.net.*;
import java.util.*;
import java.io.*;

class networking {
    Socket server;
    public DataInputStream dne;
    public DataOutputStream dout;

    networking() throws Exception {
        this.server = new Socket("Gamer", 8000);
        this.dne = new DataInputStream(server.getInputStream());
        this.dout = new DataOutputStream(server.getOutputStream());
        System.out.println("Connected");
    }

    networking(String hostname, int port) throws Exception {
        this.server = new Socket(hostname, port);
        this.dne = new DataInputStream(server.getInputStream());
        this.dout = new DataOutputStream(server.getOutputStream());
        System.out.println("Connected");
    }
}

class game {
    public networking net;
    private static Scanner s = new Scanner(System.in);
    String p1;
    String p2;

    game(String player2) throws Exception {
        net = new networking("Gamer", 8008);
        this.p2 = player2;
        net.dout.writeUTF(this.p2);
        net.dout.flush();
        this.p1 = net.dne.readUTF();
    }

    int board[][] = new int[3][3];

    public void displayBoard() throws Exception {
        for (int i = 0; i <board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                net.dout.writeUTF("");
                net.dout.flush();
                int mega = net.dne.readInt();
                board[i][j] = mega;
                System.out.printf(" %d ", board[i][j]);
            }
            System.out.println();
        }
    }

    public void recieveInput() throws Exception {
        net.dout.writeUTF("");
        net.dout.flush();
        int mark = net.dne.readInt();
        net.dout.writeUTF("");
        net.dout.flush();
        int value = net.dne.readInt();
        switch (mark) {
            case 1:
                board[0][0] = value;
                break;
            case 2:
                board[0][1] = value;
                break;
            case 3:
                board[0][2] = value;
                break;
            case 4:
                board[1][0] = value;
                break;
            case 5:
                board[1][1] = value;
                break;
            case 6:
                board[1][2] = value;
                break;
            case 7:
                board[2][0] = value;
                break;
            case 8:
                board[2][1] = value;
                break;
            case 9:
                board[2][2] = value;
                break;
        }
    }

    public void run() throws Exception {
        System.out.println("Player 1: " + p1);
        System.out.println("Player 2: " + p2 + "-->You");
        displayBoard();
        recieveInput();
        Thread.sleep(1000);
        recieveInput();
        displayBoard();
    }

}

public class App {
    public static void main(String[] args) throws Exception {
        game g1 = new game("Mizu");
        g1.run();
    }
}
