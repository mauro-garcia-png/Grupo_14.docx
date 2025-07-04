package com.mycompany.proyectoestructuras.controller;

import com.mycompany.proyectoestructuras.Address;
import com.mycompany.proyectoestructuras.App;
import com.mycompany.proyectoestructuras.Company;
import com.mycompany.proyectoestructuras.Contact;
import com.mycompany.proyectoestructuras.Person;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;


public class AñadirVentanaController implements Initializable {

    @FXML
    Label btListo;
    @FXML
    Label btCancelar;
    @FXML
    TextField tfnom;
    @FXML
    TextField tfape;
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

    private static AñadirVentanaController instance;

    /**
     * Establece el texto del campo tfemail.
     */
    public void setEmail(String texto) {
        tfemail.setText(texto);
    }

    /**
     * Establece el texto del campo tfpais.
     */
    public void setPais(String texto) {
        tfpais.setText(texto);
    }

    /**
     * Establece el texto del campo tfdir.
     */
    public void setDir(String texto) {
        tfdir.setText(texto);
    }

    /**
     * Establece el texto del campo tftel.
     */
    public void setTel(String texto) {
        tftel.setText(texto);
    }

    /**
     * Establece el texto del campo tfape.
     */
    public void setAp(String texto) {
        tfape.setText(texto);
    }

    /**
     * Establece el texto del campo tfnom.
     */
    public void setNom(String texto) {
        tfnom.setText(texto);
    }

    /**
     * Cierra la ventana actual.
     */
    public void cerrarVentana() {
        Stage stage = (Stage) btListo.getScene().getWindow();
        stage.close(); 
    }

    /**
     * Abre la ventana principal general.fxml.
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
     * Abre la ventana Eleccion.fxml y cierra la actual.
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
     * Guarda la información del nuevo contacto como una persona.
     */
    @FXML
    public void guardarContacto() {
        String linea = "person," + tfnom.getText() + "," + tfape.getText() + "," + tftel.getText() + "," + tfdir.getText() + "," + tfurl.getText() + "," + tfemail.getText() + "," + tfpais.getText() + "\n";
        Address direccion = new Address(tfdir.getText());
        Person persona = new Person("person", tfnom.getText(), tfape.getText(), tftel.getText(), direccion, tfemail.getText(), tfpais.getText());
        Contact.guardarContactos(linea);
        GeneralController.contactos.add(persona);
        Contact.cargarContactos("Contactos.txt");
        InfoContactoController.contactList.add(persona);
        InfoContactoController.contactList = Contact.cargarContactosCircular("Contactos.txt");
    }

    /**
     * Inicializa los eventos de los botones "Listo" y "Cancelar".
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

    /**
     * Devuelve la instancia actual del controlador.
     */
    public static AñadirVentanaController getInstance() {
        return instance;
    }

    /**
     * Constructor: asigna la instancia actual a la variable estática.
     */
    public AñadirVentanaController() {
        instance = this;
    }
}