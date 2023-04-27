import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.function.Consumer;
import java.io.*;
import java.net.*;
import java.util.*;

import javafx.application.Platform;
import javafx.scene.control.ListView;

public class Server{
    private ServerSocket serverSocket;
    public Server(ServerSocket serverSocket){
        this.serverSocket = serverSocket;
    }

        public void run() {

            try{
                System.out.println("Server is waiting for a client!");


                while(!serverSocket.isClosed()) {


                    Socket s = serverSocket.accept();
                    System.out.println("Client connected: " + s);
                    ClientHandler c = new ClientHandler(s);
                    // callback.accept("client has connected to server: " + "client #" + count);

                    Thread thread = new Thread(c);

                    thread.start();

                    //count++;

                }
            }//end of try
            catch(IOException e) {
               closeServer();
            }
        }//end of while

    public void closeServer(){
        try{
            if(serverSocket != null){
                serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(5555);
        Server server = new Server(serverSocket);
        server.run();
    }




//    class ClientThread extends Thread{
//
//
//        Socket connection;
//        int count;
//        ObjectInputStream in;
//        ObjectOutputStream out;
//
//        ClientThread(Socket s, int count){
//            this.connection = s;
//            this.count = count;
//        }
//
//        public void updateClients1(String message) {
//            for(int i = 0; i < clients.size(); i++) {
//                ClientThread t = clients.get(i);
//                try {
//                    t.out.writeObject(message);
//                    //
//                    t.out.reset();
//                }
//                catch(Exception e) {}
//            }
//        }
//
//
//
//        public void run(){
//
//            try {
//                in = new ObjectInputStream(connection.getInputStream());
//                out = new ObjectOutputStream(connection.getOutputStream());
//                connection.setTcpNoDelay(true);
//            }
//            catch(Exception e) {
//                System.out.println("Streams not open");
//            }
//
//            updateClients1("new client on server: client #"+count);
//
//            while(true) {
//                try {
//                    String data = in.readObject().toString();
//                    callback.accept("client: " + count + " sent: " + data);
//                    updateClients1("client #"+count+" said: "+data);
//
//                }
//                catch(Exception e) {
//                    callback.accept("OOOOPPs...Something wrong with the socket from client: " + count + "....closing down!");
//                    updateClients1("Client #"+count+" has left the server!");
//                    clients.remove(this);
//                    break;
//                }
//            }
//        }//end of run
//
//
//    }//end of client thread
}