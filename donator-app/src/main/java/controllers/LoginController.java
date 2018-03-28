package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

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

    public void goToCreareCont(MouseEvent mouseEvent) {
        try {
            FXMLLoader loaderUser = new FXMLLoader();
            loaderUser.setLocation(getClass().getClassLoader().getResource("xml/PrevalidareCreareCont.fxml"));
            Parent rootUser = loaderUser.load();
            // pornirea ferestrei
            Stage stage = new Stage();
            stage.setTitle("Creare cont");
            stage.setScene(new Scene(rootUser));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
