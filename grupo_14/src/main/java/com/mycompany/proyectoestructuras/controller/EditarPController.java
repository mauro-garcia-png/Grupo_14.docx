package com.mycompany.proyectoestructuras.controller;

import com.mycompany.proyectoestructuras.Address;
import com.mycompany.proyectoestructuras.Contact;
import com.mycompany.proyectoestructuras.Person;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class EditarPController implements Initializable {

    @FXML
    Button btListo;
    @FXML
    Button btCancelar;
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

    private Contact contactoOriginal;

    /**
     * Establece el campo de texto del email.
     */
    public void setTfemail(TextField tfemail) {
        this.tfemail = tfemail;
    }

    /**
     * Obtiene el campo de texto del email.
     */
    public TextField getTfemail() {
        return tfemail;
    }

    /**
     * Establece el campo de texto del país.
     */
    public void setTfpais(TextField tfpais) {
        this.tfpais = tfpais;
    }

    /**
     * Obtiene el campo de texto del país.
     */
    public TextField getTfpais() {
        return tfpais;
    }

    /**
     * Establece el campo de texto de la ciudad o URL.
     */
    public void setTfurl(TextField tfurl) {
        this.tfurl = tfurl;
    }

    /**
     * Obtiene el campo de texto de la ciudad o URL.
     */
    public TextField getTfurl() {
        return tfurl;
    }

    /**
     * Establece el campo de texto de la dirección.
     */
    public void setTfdir(TextField tfdir) {
        this.tfdir = tfdir;
    }

    /**
     * Obtiene el campo de texto de la dirección.
     */
    public TextField getTfdir() {
        return tfdir;
    }

    /**
     * Establece el campo de texto del teléfono.
     */
    public void setTftel(TextField tftel) {
        this.tftel = tftel;
    }

    /**
     * Obtiene el campo de texto del teléfono.
     */
    public TextField getTftel() {
        return tftel;
    }

    /**
     * Establece el campo de texto del apellido.
     */
    public void setTfape(TextField tfape) {
        this.tfape = tfape;
    }

    /**
     * Obtiene el campo de texto del apellido.
     */
    public TextField getTfape() {
        return tfape;
    }

    /**
     * Establece el campo de texto del nombre.
     */
    public void setTfnom(TextField tfnom) {
        this.tfnom = tfnom;
    }

    /**
     * Obtiene el campo de texto del nombre.
     */
    public TextField getTfnom() {
        return tfnom;
    }

    /**
     * Asigna una acción personalizada al botón "Listo".
     */
    public void setOnSaveAction(EventHandler<ActionEvent> event) {
        btListo.setOnAction(event);
    }

    /**
     * Devuelve la instancia actual del controlador.
     */
    public static EditarPController getInstance() {
        return instance;
    }

    /**
     * Constructor que asigna la instancia actual a una variable estática.
     */
    public EditarPController() {
        instance = this;
    }

    private static EditarPController instance;

    /**
     * Inicializa los botones "Listo" y "Cancelar" con sus respectivos eventos.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btListo.setOnMouseClicked(event -> {
            guardarContacto();
            volverVentana();
            cerrarVentana();
        });

        btCancelar.setOnMouseClicked(event -> {
            volverVentana();
        });
    }

    /**
     * Cierra la ventana actual.
     */
    public void cerrarVentana() {
        Stage stage = (Stage) btListo.getScene().getWindow();
        stage.close(); 
    }

    /**
     * Vuelve a la ventana de información de contacto.
     */
    public void volverVentana() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/mycompany/proyectoestructuras/infoContacto.fxml"));
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
     * Guarda los cambios realizados al contacto original de tipo Persona.
     */
    public void guardarContacto() {
        Address direccion = new Address(tfdir.getText());
        Contact contactoEditado = new Person("Person", tfnom.getText(), tfape.getText(), tftel.getText(), direccion, tfemail.getText(), tfpais.getText());
        GeneralController.actualizarContacto(contactoOriginal, contactoEditado);
        InfoContactoController.actualizarArchivo();
    }

    /**
     * Establece los valores del contacto original en los campos del formulario.
     */
    @FXML
    public void setContacto(Contact contacto) {
        this.contactoOriginal = contacto;

        tfnom.setText(contacto.getName() != null ? contacto.getName() : "");
        tfape.setText(contacto instanceof Person && ((Person) contacto).getLastName() != null ? ((Person) contacto).getLastName() : "");
        tftel.setText(contacto.getPhoneNumber() != null ? contacto.getPhoneNumber() : "");
        tfdir.setText(contacto.getAddress() != null && contacto.getAddress().getAddress() != null ? contacto.getAddress().getAddress() : "");
        tfurl.setText(contacto.getAddress() != null && contacto.getAddress().getCity() != null ? contacto.getAddress().getCity() : "");
        tfpais.setText(contacto.getCountry() != null ? contacto.getCountry() : "");
        tfemail.setText(contacto.getEmail() != null ? contacto.getEmail() : "");
    }
}
