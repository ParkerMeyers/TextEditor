package com.parkermeyers.texteditor;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage st) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("view.fxml"));

        Parent root = fxmlLoader.load();
        Controller controller = fxmlLoader.getController();
        controller.init(st);
        st.setTitle("HTML Text Editor");
        st.getIcons().add(new Image(getClass().getResourceAsStream("images/icon.png")));
        st.setScene(new Scene(root, 700, 500));
        st.show();
    }
}