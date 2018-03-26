package Controllers;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.util.Vector;

import static java.lang.Math.abs;


public class MainWindowController {
    private int dimensiuneMeniu = 200;
    private boolean meniuActiv;
    private boolean meniuFixat;
    @FXML public VBox meniu;
    @FXML public VBox menuHeader;
    @FXML public ImageView menuIconBack;
    @FXML public ImageView menuIconFront;
    @FXML public VBox verticalFullMenu;
    @FXML public HBox menuItem_Doneaza;
    @FXML public HBox menuItem_Istoric;
    @FXML public HBox menuItem_DatePersonale;
    private Vector<HBox> menuItems;
    private String culoareMeniuItemActiv;

    public MainWindowController() {
    }

    @FXML
    private void initialize() {
        //meniu.setStyle("-fx-background-color: rgb(155, 0, 0)");
        culoareMeniuItemActiv = "rgb(50, 50, 50)";
        meniuActiv = true;
        meniuFixat = false;
        hideMenu();

        menuItems = new Vector<>();
        menuItems.add(menuItem_Doneaza);
        menuItems.add(menuItem_Istoric);
        menuItems.add(menuItem_DatePersonale);
    }

    /**
     * S-a dat click pe iconuța de meniu.
     * Se afișează meniul dacă acesta era ascuns.
     * Se ascunde meniul dacă acesta era vizibil și SE DEFIXEAZĂ dacă era fixat.
     */
    @FXML
    public void fixMenu(MouseEvent mouseEvent) {
        //fixMenu();
    }

    public void fixMenu() {
        if (meniuActiv) {
            meniuFixat = true;
        }
    }

    @FXML
    public void showMenu(MouseEvent mouseEvent) {
        showMenu();
    }

    private void showMenu() {
        if (!meniuActiv) {
            moveHorizontal(meniu, dimensiuneMeniu, 200);
            rotate(menuIconBack, 90, 200);
            rotate(menuIconFront, 90, 400);
            meniuActiv = true;
        }
    }

    @FXML
    public void hideMenu(MouseEvent mouseEvent) {
        hideMenu();
    }

    private void hideMenu() {
        if (meniuActiv && !meniuFixat) {
            moveHorizontal(meniu, -dimensiuneMeniu, 200);
            rotate(menuIconFront, -270, 100);
            rotate(menuIconBack, -90, 400);
            meniuActiv = false;
        }
    }

    /**
     * Se afișează și fixează meniul doar la dublu click.
     */
    @FXML
    public void showAndFixMenu(MouseEvent mouseEvent) {
        if (mouseEvent.getButton().equals(MouseButton.PRIMARY))
            if (mouseEvent.getClickCount() == 2)
                showAndFixMenu();
    }

    private void showAndFixMenu() {
        if (!meniuActiv)
            showMenu();
        meniuFixat = true;
    }


    private void moveHorizontal(Node node, int distance, int duration) {
        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setDuration(Duration.millis(duration));
        translateTransition.setNode(node);
        // tratare bug pentru cazul în care distance == node.getWidth()
        int deviatie = (int) (abs(distance) - abs(node.getTranslateX()));    // getTranslateX() = poziția curentă relativ la poziția inițială
        if (deviatie != 0 && deviatie != abs(distance)) {
            node.setTranslateX(0);
        }
        translateTransition.setByX(distance);
        translateTransition.setAutoReverse(false);
        translateTransition.play();
    }

    private void rotate(Node node, int angle, int duration) {
        RotateTransition rotateTransition = new RotateTransition();
        rotateTransition.setDuration(Duration.millis(duration));
        rotateTransition.setNode(node);
        rotateTransition.setByAngle(angle);
        rotateTransition.setAutoReverse(false);
        rotateTransition.play();
    }


    public void goToDoneazaHandler(MouseEvent mouseEvent) {
        menuItems.forEach(menuItem -> menuItem.setStyle(""));
        menuItem_Doneaza.setStyle("-fx-background-color: " + culoareMeniuItemActiv +";");
    }

    public void goToIstoricHandler(MouseEvent mouseEvent) {
        menuItems.forEach(menuItem -> menuItem.setStyle(""));
        menuItem_Istoric.setStyle("-fx-background-color: " + culoareMeniuItemActiv +";");
    }

    public void goToDatePersonaleHandler(MouseEvent mouseEvent) {
        menuItems.forEach(menuItem -> menuItem.setStyle(""));
        menuItem_DatePersonale.setStyle("-fx-background-color: " + culoareMeniuItemActiv +";");
    }
}
