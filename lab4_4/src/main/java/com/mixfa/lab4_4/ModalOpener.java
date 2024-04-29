package com.mixfa.lab4_4;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class ModalOpener {
    private static Stage stage;

    public static void init(Stage stage) {
        ModalOpener.stage = stage;
    }

    public static void openModal(String fxmlPath) {
        Stage dialog = new Stage();

        dialog.initOwner(stage);
        dialog.initModality(Modality.APPLICATION_MODAL);

        Scene newScene;
        try {
            newScene = new Scene(new FXMLLoader(DictionaryApp.class.getResource(fxmlPath)).load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        dialog.setScene(newScene);
        dialog.showAndWait();
    }
}