import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable {
    public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
    private Socket socket;
    private BufferedReader readin;
    private BufferedWriter readout;
    private String clientUsername;
    public ClientHandler(Socket socket){
        try{
            this.socket = socket;
            this.readin = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.readout = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            clientHandlers.add(this);
            for(int i = 0;i>0;i++){
                this.clientUsername = ("Client"+ i);
            }
            broadcastMessage("Server:" + clientUsername+" has entered!");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void run() {
        String messageFromClient;
        while(socket.isConnected()){
            try{
                messageFromClient = readin.readLine();
                broadcastMessage(messageFromClient);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
    public void broadcastMessage(String message){
        for(ClientHandler clienthandler : clientHandlers){
            try{
                if(!clienthandler.clientUsername.equals(clientUsername)){
                    clienthandler.readout.write(message);
                    clienthandler.readout.newLine();
                    clienthandler.readout.flush();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void removeClient(){
        clientHandlers.remove(this);
        broadcastMessage("Server: "+clientUsername+"has left the fucking chat!");
    }
}
