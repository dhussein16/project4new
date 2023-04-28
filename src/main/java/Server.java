import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.function.Consumer;

import javafx.application.Platform;
import javafx.scene.control.ListView;

public class Server{

    int count = 1;
    ArrayList<ClientThread> clients = new ArrayList<ClientThread>();
    TheServer server;
    private Consumer<Serializable> callback;


    Server(Consumer<Serializable> call){

        callback = call;
        server = new TheServer();
        server.start();
    }


    public class TheServer extends Thread{

        public void run() {

            try(ServerSocket mysocket = new ServerSocket(5555);){
                System.out.println("Server is waiting for a client!");


                while(true) {

                    ClientThread c = new ClientThread(mysocket.accept(), count);
                    callback.accept("client has connected to server: " + "client #" + count);
                    //TODO: update the Info class with all avaliable clients
                    clients.add(c);
                    c.start();

                    count++;

                }
            }//end of try
            catch(Exception e) {
                callback.accept("Server socket did not launch");
            }
        }//end of while
    }


    class ClientThread extends Thread{


        Socket connection;
        int count;
        ObjectInputStream in;
        ObjectOutputStream out;

        ClientThread(Socket s, int count){
            this.connection = s;
            this.count = count;
        }

        public void updateClients(String message) {
            for(int i = 0; i < clients.size(); i++) {
                ClientThread t = clients.get(i);
                try {
                    t.out.writeObject(message);
                }
                catch(Exception e) {}
            }
        }
        //TODO: Make function that takes an info class and will update clients recieved from the client, look at update clients
        //TODO: ^^ how to do it
        //TODO: Make function that send new list of avaliable of clients

        public void run(){

            try {
                in = new ObjectInputStream(connection.getInputStream());
                out = new ObjectOutputStream(connection.getOutputStream());
                connection.setTcpNoDelay(true);
            }
            catch(Exception e) {
                System.out.println("Streams not open");
            }

            updateClients("new client on server: client #"+count);

            while(true) {
                try {
                    //TODO: Change the below statement to accept an Info class
                    String data = in.readObject().toString();
                    callback.accept("client: " + count + " sent: " + data);
                    updateClients("client #"+count+" said: "+data);

                }
                catch(Exception e) {
                    callback.accept("OOOOPPs...Something wrong with the socket from client: " + count + "....closing down!");
                    updateClients("Client #"+count+" has left the server!");
                    clients.remove(this);
                    //TODO: Update avaliable client list
                    break;
                }
            }
        }//end of run


    }//end of client thread
}





