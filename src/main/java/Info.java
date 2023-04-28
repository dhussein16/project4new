import java.io.Serializable;
import java.util.ArrayList;

public class Info implements Serializable{
    private ArrayList<String> clients = new ArrayList<>();
    // private ArrayList<Integer> allClients = new ArrayList<>();
    private String textOne, clientUsername;
    private boolean flagClients, flagChats;


    public Info(String client){
        this.flagClients = false;
        this.flagChats = false;
        this.clientUsername = client;
        clients.add(client);
    }
}
