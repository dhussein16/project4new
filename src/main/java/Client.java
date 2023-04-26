import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.function.Consumer;

public class Client {


    Socket socketClient;
    private BufferedReader readin;
    private BufferedWriter readout;
    private String username;
    public Client(Socket socket, String username){
        try{
            this.socketClient = socket;
            this.readin = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
            this.readout = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
            this.username = username;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void sendMessage(){
        try{
            readout.write(username);
            readout.newLine();
            readout.flush();
            Scanner scanner = new Scanner(System.in);
            while(socketClient.isConnected()){
                String messageToSend = scanner.nextLine();
                readout.write(username + ":" + messageToSend);
                readout.newLine();
                readout.flush();

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void listenForMessage(){
        new Thread(new Runnable() {
            @Override
            public void run() {
String groupchat;
try{
    groupchat = readin.readLine();
    System.out.println(groupchat);
} catch (IOException e) {
    throw new RuntimeException(e);
}
            }
        }).start();
    }
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter ur username whore");
        String username = scanner.nextLine();
        Socket socket = new Socket("localhost", 5555);
        Client client = new Client(socket,username);
        client.sendMessage();
    }



}