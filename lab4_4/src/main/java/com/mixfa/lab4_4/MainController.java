package com.mixfa.lab4_4;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;


public class MainController implements Initializable {
    public TableColumn<Map.Entry<String, String>, String> ukrColumn;
    public TableColumn<Map.Entry<String, String>, String> engColumn;
    public TextField searchField;
    public TableView<Map.Entry<String, String>> tableView;

    private static final ObjectMapper mapper = new ObjectMapper();
    private static final FileChooser.ExtensionFilter jsonFilter = new FileChooser.ExtensionFilter("json file", "*.json");
    private final ObservableMap<String, String> dictionary = FXCollections.observableHashMap();

    public void addEntry() {
        ModalOpener.openModal("add-entry.fxml");

        if (DataLayer.newEntryUrk != null && DataLayer.newEntryEng != null)
            dictionary.put(DataLayer.newEntryUrk, DataLayer.newEntryEng);
    }

    public void save() throws Exception {
        var window = searchField.getScene().getWindow();
        var fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(jsonFilter);
        var file = fileChooser.showSaveDialog(window);
        if (file != null)
            mapper.writeValue(file, dictionary);
    }

    public void load() throws Exception {
        var window = searchField.getScene().getWindow();
        var fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(jsonFilter);
        var file = fileChooser.showOpenDialog(window);
        if (file != null)
            dictionary.putAll(mapper.readValue(file, Map.class));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ukrColumn.setCellValueFactory(
                dictionaryPairStringCellDataFeatures -> new SimpleStringProperty(
                        dictionaryPairStringCellDataFeatures.getValue().getKey()
                )
        );

        engColumn.setCellValueFactory(
                dictionaryPairStringCellDataFeatures -> new SimpleStringProperty(
                        dictionaryPairStringCellDataFeatures.getValue().getValue()
                )
        );

        dictionary.addListener((MapChangeListener<String, String>) change -> {
            var entries = change.getMap().entrySet().stream()
                    .map(it -> Map.entry((String) it.getKey(), (String) it.getValue()))
                    .toList();

            tableView.getItems().clear();
            tableView.getItems().addAll(entries);
        });

    }

    public void onSearchEdited() {
        var text = searchField.getText();

        if (text.isEmpty() && text.isBlank()) {
            tableView.getItems().clear();
            tableView.getItems().addAll(dictionary.entrySet());
        } else {
            var filtered = dictionary.entrySet().stream().filter((entry -> entry.getValue().contains(text) || entry.getKey().contains(text))).toList();

            tableView.getItems().clear();
            tableView.getItems().addAll(filtered);
        }
    }
}