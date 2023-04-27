import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.function.Consumer;

public class Client {


    private Socket socket;
    private BufferedReader readin;
    private BufferedWriter readout;
    private String username;
    public Client(Socket socket, String username){
        try{
            this.socket = socket;
            this.readin = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.readout = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.username = username;
        } catch (IOException e) {

            closeeverything(socket,readin,readout);
        }
    }

    private void closeeverything(Socket socket, BufferedReader readin, BufferedWriter readout) {
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

    public void sendMessage(){
        try{
            readout.write(username);
            readout.newLine();
            readout.flush();
            Scanner scanner = new Scanner(System.in);
            while(socket.isConnected()){
                String messageToSend = scanner.nextLine();
                readout.write(username + ":" + messageToSend);
                readout.newLine();
                readout.flush();

            }
        } catch (IOException e) {
            closeeverything(socket,readin,readout);

        }
    }
    public void listenForMessage(){
        new Thread(() -> {
            String groupchat;
            try{
                groupchat = readin.readLine();
                System.out.println(groupchat);
            } catch (IOException e) {
                closeeverything(socket,readin,readout);
            }
        }).start();
    }
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter ur username whore");
        String username = scanner.nextLine();
        Socket socket = new Socket("localhost",5555);
        Client client = new Client(socket,username);
        client.listenForMessage();
        client.sendMessage();
    }



}