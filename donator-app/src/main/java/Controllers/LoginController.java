package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class LoginController {

    @FXML
    private TextField passwordField;
    @FXML
    private TextField usernameField;


    @FXML
    private void submitInfo(MouseEvent mouseEvent) {
        final String username = usernameField.getText();
        final String password = passwordField.getText();
        try {
            Socket socket = new Socket("localhost", 4432);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            Map<String, String> createUserInfo = new HashMap<>();
            createUserInfo.put("api", "logincontroller/creazauserpentrudonator");
            createUserInfo.put("username", username);
            createUserInfo.put("parola", password);
            objectOutputStream.writeObject(createUserInfo);
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
