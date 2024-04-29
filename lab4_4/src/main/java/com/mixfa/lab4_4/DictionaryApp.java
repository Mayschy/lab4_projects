package com.mixfa.lab4_4;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class DictionaryApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        ModalOpener.init(stage);

        FXMLLoader fxmlLoader = new FXMLLoader(DictionaryApp.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}