package controllers;

import domain.GrupaSanguina;
import domain.Sex;
import domain.Utilizator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import service.UtilizatorService;
import validator.UtilizatorValidator;

import java.io.IOException;
import java.time.LocalDate;

public class CreareContController {
    @FXML public TextField inputCNP;
    @FXML public TextField inputNume;
    @FXML public TextField inputPrenume;
    @FXML public ComboBox<Sex> inputSex;
    @FXML public ComboBox<GrupaSanguina> inputGrupaSanguina;
    @FXML public DatePicker inputDataNasterii;
    @FXML public TextArea inputDomiciliu;
    @FXML public TextArea inputResedinta;
    @FXML public TextField inputRh;
    @FXML public TextField inputNumeUtilizator;
    @FXML public PasswordField inputParola;
    @FXML public PasswordField inputRepetareParola;
    private UtilizatorService utilizatorService;
    private ObservableList<Sex> sexe;
    private ObservableList<GrupaSanguina> grupeSanguine;

    public CreareContController() {
        utilizatorService = new UtilizatorService();
        sexe = FXCollections.observableArrayList(Sex.masculin, Sex.feminin);
        grupeSanguine = FXCollections.observableArrayList(GrupaSanguina.O, GrupaSanguina.A, GrupaSanguina.B, GrupaSanguina.AB);
    }

    @FXML
    private void initialize() {
        inputSex.setItems(sexe);
        inputGrupaSanguina.setItems(grupeSanguine);
    }

    public void creareCont(ActionEvent actionEvent) {
        try {
            // preluare date
            String cnp = inputCNP.getText();
            String nume = inputNume.getText();
            String prenume = inputPrenume.getText();
            Sex sex = inputSex.getSelectionModel().getSelectedItem();
            GrupaSanguina grupaSanguina = inputGrupaSanguina.getSelectionModel().getSelectedItem();
            LocalDate dataNasterii = inputDataNasterii.getValue();
            String domiciliu = inputDomiciliu.getText();
            String resedinta = inputResedinta.getText();
            String rh = inputRh.getText();
            String numeUtilizator = inputNumeUtilizator.getText();
            String parola = inputParola.getText();
            String repetareParola = inputRepetareParola.getText();

            // validare date
            UtilizatorValidator.validate(cnp, nume, prenume, sex, grupaSanguina, dataNasterii, domiciliu, resedinta, rh,
                    numeUtilizator, parola, repetareParola);

            // creare cont
            Utilizator utilizator = utilizatorService.creareCont(cnp, nume, prenume, sex, grupaSanguina, domiciliu, resedinta, rh,
                    numeUtilizator, parola, repetareParola);

            // deschidere interfață utilizator
            try {
                FXMLLoader loaderUser = new FXMLLoader();
                loaderUser.setLocation(getClass().getClassLoader().getResource("xml/MainWindow.fxml"));
                Parent rootUser = loaderUser.load();
                // pornirea ferestrei
                Stage stage = new Stage();
                stage.setTitle("Donează sânge ! - " + utilizator.toString());
                stage.setScene(new Scene(rootUser));
                stage.show();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        catch (Exception e) {
            Alert text;
            text = new Alert(Alert.AlertType.ERROR);
            text.setTitle("Nu s-a creat contul.");
            text.setContentText(e.getMessage());
            text.showAndWait();
        }
    }
}
