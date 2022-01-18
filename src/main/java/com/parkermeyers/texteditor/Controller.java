package com.parkermeyers.texteditor;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.web.HTMLEditor;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private final FileChooser fileChooser = new FileChooser();
    @FXML
    private HTMLEditor htmlEditor;
    private Stage stage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser
                .getExtensionFilters()
                .addAll(
                        new FileChooser.ExtensionFilter("TextEditor (.TXTEDTR)", "*.txtedtr"),
                        new FileChooser.ExtensionFilter("HTML (.html)", "*.html"),
                        new FileChooser.ExtensionFilter("TXT (.txt)", "*.txt"),
                        new FileChooser.ExtensionFilter("All Files", "*.*"));
    }

    public void init(Stage myStage) {
        this.stage = myStage;
    }

    @FXML
    public void exit() {
        if (htmlEditor.getHtmlText().isEmpty()) {
            Platform.exit();
            return;
        }

        Alert alert = new Alert(
                Alert.AlertType.CONFIRMATION,
                "Exit without saving?",
                ButtonType.YES,
                ButtonType.NO,
                ButtonType.CANCEL
        );

        alert.setTitle("Confirm");
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            Platform.exit();
        }
        if (alert.getResult() == ButtonType.NO) {
            save();
            Platform.exit();
        }
    }

    @FXML
    private void save() {
        try {
            fileChooser.setTitle("Save As");
            File file = fileChooser.showSaveDialog(stage);

            if (file != null) {
                PrintWriter savedText = new PrintWriter(file);
                BufferedWriter out = new BufferedWriter(savedText);
                out.write(htmlEditor.getHtmlText());
                out.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: " + e);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void openFile() {
        fileChooser.setTitle("Open File");
        File file = fileChooser.showOpenDialog(stage);

        if (file != null) {
            htmlEditor.setHtmlText("");
            readText(file);
        }
    }

    private void readText(File file) {
        String text;

        try (BufferedReader buffReader = new BufferedReader(new FileReader(file))) {
            while ((text = buffReader.readLine()) != null) {
                htmlEditor.setHtmlText(text + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void newFile() {
        htmlEditor.setHtmlText("");
    }

    @FXML
    public void about() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("About");
        alert.setHeaderText("A JavaFX Project by Parker Meyers");
        alert.setContentText("https://github.com/ParkerMeyers");
        alert.showAndWait();
    }
}