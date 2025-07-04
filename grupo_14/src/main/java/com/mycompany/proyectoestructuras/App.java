package com.mycompany.proyectoestructuras;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
public class App extends Application {
    public static String pathFiles="src/main/resources/com/mycompany/proyectoestructuras/files/";
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader= new FXMLLoader(App.class .getResource("general.fxml"));
        Parent root = fxmlLoader.load();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();        
    }
    public static void main(String[] args) {
        launch();
    }

}