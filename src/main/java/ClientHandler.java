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

            this.clientUsername = readin.readLine();

            broadcastMessage("Server:" + clientUsername+" has entered!");

        } catch (IOException e) {
            closestuff(socket,readin,readout);
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
                closestuff(socket,readin,readout);
                break;
            }
        }

    }

    private void closestuff(Socket socket, BufferedReader readin, BufferedWriter readout) {
        removeClient();
        try{
            if(readin != null){
                readin.close();
            }
            if(readout != null){
                readout.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
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
                closestuff(socket,readin,readout);
            }
        }
    }
    public void removeClient(){
        clientHandlers.remove(this);
        broadcastMessage("Server: "+clientUsername+"has left the fucking chat!");
    }
}