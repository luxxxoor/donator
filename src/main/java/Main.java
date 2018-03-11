import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.PrintWriter;

public class Main extends Application {
    private static PrintWriter printWriter;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("login.fxml"));
        primaryStage.setScene(new Scene(loader.load()));
        primaryStage.show();
    }
}
