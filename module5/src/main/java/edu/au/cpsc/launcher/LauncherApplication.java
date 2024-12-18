package edu.au.cpsc.launcher;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
public class LauncherApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new
                FXMLLoader(LauncherApplication.class.getResource("/edu.au.cpsc.launcher.style/launcher-app.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(getClass().getResource("/edu.au.cpsc.launcher.style/main.css").toExternalForm());
        stage.setTitle("Part 2");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}

