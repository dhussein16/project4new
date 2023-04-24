import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
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
    
    //static so each instance of controller can access to update 
    private static String textEntered = "";

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
	}

	// this would handle the "server launch"
	public void serverLaunch(ActionEvent e) throws IOException {
        System.out.println("The server has launched!");

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/MyFXML2.fxml"));
		Parent root2 = loader.load(); //load view into parent
		root2.getStylesheets().add("/styles/style2.css");//set style
		root.getScene().setRoot(root2);//update scene graph
	}
	
	public void clientLaunch(ActionEvent e) throws IOException{
        System.out.println("The Client has Launched!");

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/MyFXML2.fxml"));
		Parent root2 = loader.load(); //load view into parent
		root2.getStylesheets().add("/styles/style2.css");//set style
		root.getScene().setRoot(root2);//update scene graph
	}
}
