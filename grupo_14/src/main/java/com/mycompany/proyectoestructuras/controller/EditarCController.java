package com.mycompany.proyectoestructuras.controller;

import com.mycompany.proyectoestructuras.Address;
import com.mycompany.proyectoestructuras.Company;
import com.mycompany.proyectoestructuras.Contact;
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


public class EditarCController implements Initializable {

    @FXML
    Button btListo;
    @FXML
    Button btCancelar;
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

    private Contact contactoOriginal;

    /**
     * Establece el campo de texto para la página web.
     */
    public void setTweb(TextField tweb) {
        this.tweb = tweb;
    }

    /**
     * Obtiene el campo de texto para la página web.
     */
    public TextField getTweb() {
        return tweb;
    }

    /**
     * Establece el campo de texto para el RUC.
     */
    public void setTruc(TextField truc) {
        this.truc = truc;
    }

    /**
     * Obtiene el campo de texto para el RUC.
     */
    public TextField getTruc() {
        return truc;
    }

    /**
     * Establece el campo de texto para el email.
     */
    public void setTfemail(TextField tfemail) {
        this.tfemail = tfemail;
    }

    /**
     * Obtiene el campo de texto para el email.
     */
    public TextField getTfemail() {
        return tfemail;
    }

    /**
     * Establece el campo de texto para el país.
     */
    public void setTfpais(TextField tfpais) {
        this.tfpais = tfpais;
    }

    /**
     * Obtiene el campo de texto para el país.
     */
    public TextField getTfpais() {
        return tfpais;
    }

    /**
     * Establece el campo de texto para la ciudad o URL.
     */
    public void setTfurl(TextField tfurl) {
        this.tfurl = tfurl;
    }

    /**
     * Obtiene el campo de texto para la ciudad o URL.
     */
    public TextField getTfurl() {
        return tfurl;
    }

    /**
     * Establece el campo de texto para la dirección.
     */
    public void setTfdir(TextField tfdir) {
        this.tfdir = tfdir;
    }

    /**
     * Obtiene el campo de texto para la dirección.
     */
    public TextField getTfdir() {
        return tfdir;
    }

    /**
     * Establece el campo de texto para el teléfono.
     */
    public void setTftel(TextField tftel) {
        this.tftel = tftel;
    }

    /**
     * Obtiene el campo de texto para el teléfono.
     */
    public TextField getTftel() {
        return tftel;
    }

    /**
     * Establece el campo de texto para el nombre.
     */
    public void setTfnom(TextField tfnom) {
        this.tfnom = tfnom;
    }

    /**
     * Obtiene el campo de texto para el nombre.
     */
    public TextField getTfnom() {
        return tfnom;
    }

    /**
     * Permite establecer una acción personalizada para el botón "Listo".
     */
    public void setOnSaveAction(EventHandler<ActionEvent> event) {
        btListo.setOnAction(event);
    }

    /**
     * Inicializa los eventos de los botones al cargar el controlador.
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
     * Guarda los cambios realizados al contacto original.
     */
    public void guardarContacto() {
        Address direccion = new Address(tfdir.getText());
        Contact contactoEditado = new Company(
            "Compania", 
            tfnom.getText(), 
            tftel.getText(), 
            truc.getText(), 
            direccion, 
            tfemail.getText(), 
            tfpais.getText(), 
            tweb.getText()
        );
        GeneralController.actualizarContacto(contactoOriginal, contactoEditado);
        InfoContactoController.actualizarArchivo();
    }

    /**
     * Establece los datos del contacto original en los campos de edición.
     */
    public void setContacto(Contact contacto) {
        this.contactoOriginal = contacto;

        tfnom.setText(contacto.getName() != null ? contacto.getName() : "");
        tftel.setText(contacto.getPhoneNumber() != null ? contacto.getPhoneNumber() : "");
        tfdir.setText(contacto.getAddress() != null && contacto.getAddress().getAddress() != null ? contacto.getAddress().getAddress() : "");
        tfurl.setText(contacto.getAddress() != null && contacto.getAddress().getCity() != null ? contacto.getAddress().getCity() : "");
        tfpais.setText(contacto.getCountry() != null ? contacto.getCountry() : "");
        tfemail.setText(contacto.getEmail() != null ? contacto.getEmail() : "");
        truc.setText(contacto instanceof Company && ((Company) contacto).getRUC() != null ? ((Company) contacto).getRUC() : "");
        tweb.setText(contacto instanceof Company && ((Company) contacto).getWebPage() != null ? ((Company) contacto).getWebPage() : "");
    }
}
