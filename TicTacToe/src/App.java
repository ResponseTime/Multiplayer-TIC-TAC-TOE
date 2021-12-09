import java.io.*;
import java.net.*;
import java.util.*;

class networking {
    public ServerSocket server;
    DataInputStream inputStream;
    DataOutputStream outputStream;

    public void start(int port) throws Exception {
        try {
            server = new ServerSocket(port);
            Socket socket = server.accept();
            if (socket.isConnected()) {
                System.out.println("Connected");
                inputStream = new DataInputStream(socket.getInputStream());
                outputStream = new DataOutputStream(socket.getOutputStream());
            }
        } catch (Exception e) {
            System.out.println("Failed to connect");
        }

    }
}

class game {
    private static Scanner s = new Scanner(System.in);
    public String p1;
    public String p2;
    networking net = new networking();

    game(String p1) throws Exception {
        net.start(8008);
        this.p1 = p1;
        this.p2 = net.inputStream.readUTF();
        net.outputStream.writeUTF(this.p1);
        net.outputStream.flush();
    }

    int gameBoard[][] = { { 0, 0, 0 },
            { 0, 0, 0 },
            { 0, 0, 0 } };

    public void displayBoard(int gameBoard[][]) throws Exception {
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard.length; j++) {
                net.inputStream.readUTF();
                System.out.printf(" %d ", gameBoard[i][j]);
                net.outputStream.writeInt(gameBoard[i][j]);
                net.outputStream.flush();
            }
            System.out.println();
        }
    }

    public void takeInput() throws Exception {
        int mark = s.nextInt();
        switch (mark) {
            case 1:
                net.inputStream.readUTF();
                net.outputStream.writeInt(1);
                net.outputStream.flush();
                if (gameBoard[0][0] != 0) {
                    System.out.println("Place already taken");
                    takeInput();
                } else {
                    gameBoard[0][0] = s.nextInt();
                }
                net.inputStream.readUTF();
                net.outputStream.writeInt(gameBoard[0][0]);
                net.outputStream.flush();
                break;
            case 2:
                net.inputStream.readUTF();
                net.outputStream.writeInt(2);
                net.outputStream.flush();
                if (gameBoard[0][1] != 0) {
                    System.out.println("Place already taken");
                    takeInput();
                } else {
                    gameBoard[0][1] = s.nextInt();
                }
                net.inputStream.readUTF();
                net.outputStream.writeInt(gameBoard[0][1]);
                net.outputStream.flush();
                break;
            case 3:
                net.inputStream.readUTF();
                net.outputStream.writeInt(3);
                net.outputStream.flush();
                if (gameBoard[0][2] != 0) {
                    System.out.println("Place already taken");
                    takeInput();
                } else {
                    gameBoard[0][2] = s.nextInt();
                }
                net.inputStream.readUTF();
                net.outputStream.writeInt(gameBoard[0][2]);
                net.outputStream.flush();
                break;
            case 4:
                net.inputStream.readUTF();
                net.outputStream.writeInt(4);
                net.outputStream.flush();
                if (gameBoard[1][0] != 0) {
                    System.out.println("Place already taken");
                    takeInput();
                } else {
                    gameBoard[1][0] = s.nextInt();
                }
                net.inputStream.readUTF();
                net.outputStream.writeInt(gameBoard[1][0]);
                net.outputStream.flush();
                break;
            case 5:
                net.inputStream.readUTF();
                net.outputStream.writeInt(5);
                net.outputStream.flush();
                if (gameBoard[1][1] != 0) {
                    System.out.println("Place already taken");
                    takeInput();
                } else {
                    gameBoard[1][1] = s.nextInt();
                }
                net.inputStream.readUTF();
                net.outputStream.writeInt(gameBoard[1][1]);
                net.outputStream.flush();
                break;
            case 6:
                net.inputStream.readUTF();
                net.outputStream.writeInt(6);
                net.outputStream.flush();
                if (gameBoard[1][2] != 0) {
                    System.out.println("Place already taken");
                    takeInput();
                } else {
                    gameBoard[1][2] = s.nextInt();
                }
                net.inputStream.readUTF();
                net.outputStream.writeInt(gameBoard[1][2]);
                net.outputStream.flush();
                break;
            case 7:
                net.inputStream.readUTF();
                net.outputStream.writeInt(7);
                net.outputStream.flush();
                if (gameBoard[2][0] != 0) {
                    System.out.println("Place already taken");
                    takeInput();
                } else {
                    gameBoard[2][0] = s.nextInt();
                }
                net.inputStream.readUTF();
                net.outputStream.writeInt(gameBoard[2][0]);
                net.outputStream.flush();
                break;
            case 8:
                net.inputStream.readUTF();
                net.outputStream.writeInt(8);
                net.outputStream.flush();
                if (gameBoard[2][1] != 0) {
                    System.out.println("Place already taken");
                    takeInput();
                } else {
                    gameBoard[2][1] = s.nextInt();
                }
                net.inputStream.readUTF();
                net.outputStream.writeInt(gameBoard[2][1]);
                net.outputStream.flush();
                break;
            case 9:
                net.inputStream.readUTF();
                net.outputStream.writeInt(9);
                net.outputStream.flush();
                if (gameBoard[2][2] != 0) {
                    System.out.println("Place already taken");
                    takeInput();
                } else {
                    gameBoard[2][2] = s.nextInt();
                }
                net.inputStream.readUTF();
                net.outputStream.writeInt(gameBoard[2][2]);
                net.outputStream.flush();
                break;
            default:
                System.out.println("Enter a valid place (1,9)");
                takeInput();
        }
    }

    public void run() throws Exception {
        System.out.println("Player 1: " + p1 + "-->You");
        System.out.println("Player 2: " + p2);
        displayBoard(gameBoard);
        takeInput();
        Thread.sleep(1000);
        takeInput();
        displayBoard(gameBoard);
    }
}

public class App {

    public static void main(String[] args) throws Exception {
        game game = new game("aayush");
        game.run();
    }
}
