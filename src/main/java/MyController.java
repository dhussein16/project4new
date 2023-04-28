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

public class MyController implements Initializable {

	@FXML
	private VBox root;
	@FXML
	private BorderPane root2;
	@FXML
	private TextField textField;
	@FXML
	private TextField putText;
	@FXML
	private Button clientConfirm, serverConfirm;
	@FXML
	private Button sendStringToServer;

	@FXML
	ListView<String> listItems, listItems2;

	//static so each instance of controller can access to update
	private static String textEntered = "";

	//for listview
	private ObservableList<String> clients = FXCollections.observableArrayList();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
	}

//	public void loadData(){
//		listItems.setItems(clients);
//	}

	// this would handle the "server launch"
	public void serverLaunch(ActionEvent e) throws IOException {
		System.out.println("The server has launched!");

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/MyFXML3.fxml"));
		Parent root2 = loader.load(); //load view into parent
		root2.getStylesheets().add("/styles/style2.css");//set style
		root.getScene().setRoot(root2);//update scene graph
		// loadData();
		//TODO: connect this to your server controller and call the runlater THERE

		// took this from the GUI server example
//

		// server needs to look like how it does on the original, how to do that?
	}

	// sends over whatever the client has written to the client
	public void clientSendOver(ActionEvent e) throws IOException{
		System.out.println(textField.getText()); // just to check
		//sendStringToServer.setOnAction(x->{clientConnection.send(textField.getText()); textField.clear();});
	}

	public void clientLaunch(ActionEvent e) throws IOException{
		System.out.println("The Client has Launched!");

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/MyFXML2.fxml"));
		Parent root2 = loader.load(); //load view into parent
		root2.getStylesheets().add("/styles/style2.css");//set style
		root.getScene().setRoot(root2);//update scene graph
		//TODO: call the client controller and run the runLater THERE you already have it, you need to update it now

		// took this from the GUI server example
//		clientConnection = new Client(data->{
//			//Platform.runLater(()->{listItems2.getItems().add(data.toString());
//			});
//		});
//
//		clientConnection.start();
	}
}