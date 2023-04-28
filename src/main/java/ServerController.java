import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;


public class ServerController implements Initializable{

    Server serverConnection;

    @FXML
    ListView listItems;

    //TODO add all elements for the server fxml, make sure to reference it in your FXML file



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        //TODO: you have your runlater here, but now handle all the information parsing
        serverConnection = new Server(data -> {
			Platform.runLater(()->{
				listItems.getItems().add(data.toString());
			});
		});
    }
}
