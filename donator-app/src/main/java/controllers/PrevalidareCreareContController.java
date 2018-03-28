package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Vector;

public class PrevalidareCreareContController {
    @FXML public CheckBox hepatita;
    @FXML public CheckBox bruceloza;
    @FXML public CheckBox diabetZaharat;
    @FXML public CheckBox sifilis;
    @FXML public CheckBox tbc;
    @FXML public CheckBox malarie;
    @FXML public CheckBox epilepsie;
    @FXML public CheckBox boliPsihice;
    @FXML public CheckBox ulcer;
    @FXML public CheckBox boliDeInima;
    @FXML public CheckBox boliDePiele;
    @FXML public CheckBox miopieForte;
    @FXML public CheckBox cancer;
    @FXML public CheckBox cetateanRoman;
    private Vector<CheckBox> boli;

    public PrevalidareCreareContController() {
        boli = new Vector<>();
    }

    @FXML
    private void initialize() {
        boli.add(hepatita);
        boli.add(bruceloza);
        boli.add(diabetZaharat);
        boli.add(sifilis);
        boli.add(tbc);
        boli.add(malarie);
        boli.add(epilepsie);
        boli.add(boliPsihice);
        boli.add(ulcer);
        boli.add(boliDeInima);
        boli.add(boliDePiele);
        boli.add(miopieForte);
        boli.add(cancer);
    }

    /**
     * Funcția verifică ce boli a selectat utilizatorul.
     * Dacă nu a selectat nimic, atunci utilizatorul e direcționat spre pagina de completare a datelor personale.
     * Dacă a selectat cel puțin o boală, utilizatorul e informat că nu poate continua crearea contului.
     * @param actionEvent - s-a dat click pe butonul Continuă
     */
    @FXML
    public void butonContinua(ActionEvent actionEvent) {
        for (CheckBox boala : boli) {
            if (boala.isSelected()) {
                anulareCreareCont("Ne pare rău, dar nu îți poți crea cont deoarece nu poți dona sânge dacă ai suferit de cel puțin una din bolile menționate.");
                return;
            }
        }
        if (!cetateanRoman.isSelected()) {
            anulareCreareCont("Ne pare rău, dar numai cetățenii români pot dona sânge.");
            return;
        }

        // utilizatorul e sănătos
        continuaCreareCont();
    }

    /**
     * Utilizatorul nu are nici o boală, deci își poate face cont.
     */
    private void continuaCreareCont() {
        try {
            FXMLLoader loaderUser = new FXMLLoader();
            loaderUser.setLocation(getClass().getClassLoader().getResource("xml/CreareCont.fxml"));
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

    /**
     * Utilizatorul e / a fost bolnav, deci nu își poate face cont.
     */
    private void anulareCreareCont(String mesaj) {
        Alert text;
        text = new Alert(Alert.AlertType.INFORMATION);
        text.setTitle("Nu ești eligibil(ă) pentru donare de sânge.");
        text.setContentText(mesaj);
        text.showAndWait();
    }


}
