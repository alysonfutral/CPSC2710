package edu.au.cpsc.miscstyle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
public class Part1Application extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new
                FXMLLoader(Part1Application.class.getResource("/edu/au/cpsc/miscstyle/style/part1.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(getClass().getResource("/edu/au/cpsc/miscstyle/style/main.css").toExternalForm());
        stage.setTitle("Part 1");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}

