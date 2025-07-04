package com.mycompany.proyectoestructuras.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class EleccionController implements Initializable {

    @FXML
    private Button personaB;
    @FXML
    private Button compaB;

    /**
     * Cancela la selección actual y vuelve a la pantalla principal.
     */
    @FXML
    public void cancelar(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/mycompany/proyectoestructuras/general.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            Stage detallesStage = new Stage();
            detallesStage.setScene(scene);
            detallesStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = (Stage) personaB.getScene().getWindow();
        stage.close(); 
    }

    /**
     * Abre la pestaña para añadir una nueva compañía.
     */
    @FXML
    public void pestañaC(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/mycompany/proyectoestructuras/añadirC.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            Stage detallesStage = new Stage();
            detallesStage.setScene(scene);
            detallesStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = (Stage) personaB.getScene().getWindow();
        stage.close(); 
    }

    /**
     * Abre la pestaña para añadir una nueva persona.
     */
    @FXML
    public void pestañaP(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/mycompany/proyectoestructuras/añadirVentana.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            Stage detallesStage = new Stage();
            detallesStage.setScene(scene);
            detallesStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = (Stage) personaB.getScene().getWindow();
        stage.close(); 
    }

    /**
     * Inicializa el controlador (actualmente vacío).
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
}
