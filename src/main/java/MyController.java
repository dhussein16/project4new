import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

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
	private ObservableList<String> clients = FXCollections.observableArrayList();
	private ListView<String>names = new ListView<String>(clients);
	private Server serverConnection;
	Client clientConnection;
	ListView<String> listItems, listItems2;

	//static so each instance of controller can access to update
	private static String textEntered = "";

	@Override
	public void initialize(URL location, ResourceBundle resources) {


		try{
			serverConnection = new Server(new ServerSocket(5555));

		}catch(IOException e){
			e.printStackTrace();
			System.out.println("Error Server not opening");
//		}
//		clients.heightProperty().addListener(new ChangeListener<Number>()){
//
//		}

	}
	//for listview


	serverConfirm.setOnAction(new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent actionEvent) {


			System.out.println("The server has launched!");

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/MyFXML2.fxml"));
			Parent root2 = null; //load view into parent
			try {
				root2 = loader.load();
			} catch (IOException e) {
				e.printStackTrace();
			}
			root2.getStylesheets().add("/styles/style2.css");//set style
			root.getScene().setRoot(root2);//update scene graph
		}
	});
	// this would handle the "server launch"
//	public void serverLaunch(ActionEvent e) throws IOException {
//		System.out.println("The server has launched!");
//
//		FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/MyFXML2.fxml"));
//		Parent root2 = loader.load(); //load view into parent
//		root2.getStylesheets().add("/styles/style2.css");//set style
//		root.getScene().setRoot(root2);//update scene graph
//
//		// took this from the GUI server example
////		serverConnection = new Server(data -> {
////			Platform.runLater(()->{
////				listItems.getItems().add(data.toString());
////			});
////		});
//
//		// server needs to look like how it does on the original, how to do that?
//	}
	sendStringToServer.setOnAction(new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent actionEvent) {
			System.out.println(textField.getText());
			String clientmessage = textField.getText();
			if(!clientmessage.isEmpty()){
				Text clienttext = new Text(clientmessage);
				root.getChildren().add(clienttext);
				//get the pinche punto shit to work
			}
		}
	});

	clientConfirm.setOnAction(new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent actionEvent) {
			System.out.println("The Client has Launched!");

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/MyFXML2.fxml"));
			Parent root2 = null; //load view into parent
			try {
				root2 = loader.load();
			} catch (IOException e) {
				e.printStackTrace();
			}
			root2.getStylesheets().add("/styles/style2.css");//set style
			root.getScene().setRoot(root2);//update scene graph
		}
	});
//	public void clientLaunch(ActionEvent e) throws IOException{
//		System.out.println("The Client has Launched!");
//
//		FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/MyFXML2.fxml"));
//			Parent root2 = null; //load view into parent
//			try {
//				root2 = loader.load();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			root2.getStylesheets().add("/styles/style2.css");//set style
//		root.getScene().setRoot(root2);//update scene graph

		// took this from the GUI server example
//		clientConnection = new Client(data->{
//			//Platform.runLater(()->{listItems2.getItems().add(data.toString());
//			});
//		});
//
//		clientConnection.start();


	}
}
