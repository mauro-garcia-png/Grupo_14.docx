package com.mycompany.proyectoestructuras.controller;

import com.mycompany.proyectoestructuras.Address;
import com.mycompany.proyectoestructuras.Company;
import com.mycompany.proyectoestructuras.Contact;
import com.mycompany.proyectoestructuras.Person;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;


public class AñadirCController implements Initializable {
    @FXML
    Label btListo;
    @FXML
    Label btCancelar;
    @FXML
    TextField tfnom;
    @FXML
    TextField tftel;
    @FXML
    TextField tfdir;
    @FXML
    TextField tfurl;
    @FXML
    TextField tfpais;
    @FXML
    TextField tfemail;
    @FXML
    TextField truc;
    @FXML
    TextField tweb;

    /**
     * Cierra la ventana actual (botón "Listo")
     */
    public void cerrarVentana() {
        Stage stage = (Stage) btListo.getScene().getWindow();
        stage.close(); 
    }

    /**
     * Regresa a la ventana principal "general.fxml"
     */
    public void volverInicio() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/mycompany/proyectoestructuras/general.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            Stage detallesStage = new Stage();
            detallesStage.setScene(scene);
            detallesStage.show();
        } catch (IOException e) {
        }
        Stage stage = (Stage) btCancelar.getScene().getWindow();
        stage.close();
    }

    /**
     * Abre la ventana "Eleccion.fxml" al cancelar la operación
     */
    public void volverVentana() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/mycompany/proyectoestructuras/Eleccion.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            Stage detallesStage = new Stage();
            detallesStage.setScene(scene);
            detallesStage.show();
        } catch (IOException e) {
        }
        Stage stage = (Stage) btCancelar.getScene().getWindow();
        stage.close();
    }

    /**
     * Guarda la información ingresada como un nuevo contacto de tipo "Company"
     */
    @FXML
    public void guardarContacto() {
        String linea = "compania" + "," + tfnom.getText() + "," + tftel.getText() + "," + truc.getText() + "," + tfdir.getText() + "," + tfurl.getText() + "," + tfemail.getText() + "," + tfpais.getText() + "," + tweb.getText();
        Address direccion = new Address(tfdir.getText());
        Company compa = new Company("compania", tfnom.getText(), tftel.getText(), truc.getText(), direccion, tfemail.getText(), tfpais.getText(), tweb.getText());
        Contact.guardarContactos(linea);
        GeneralController.contactos.add(compa);
        Contact.cargarContactos("Contactos.txt");
        InfoContactoController.contactList.add(compa);
        InfoContactoController.contactList = Contact.cargarContactosCircular("Contactos.txt");  
    }

    /**
     * Inicializa los eventos de los botones "Listo" y "Cancelar"
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btListo.setOnMouseClicked(event -> {
            guardarContacto();
            volverInicio();
            cerrarVentana();
        });

        btCancelar.setOnMouseClicked(event -> {
            volverVentana();
        });
    }
}