/*
FXML based only project, no manual creation of JavaFX controls
This project uses a total of 7 controls and includes 4 separate windows
View the Readme.md file for instructions
 */

package edu.au.cpsc.module7;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StudyApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StudyApplication.class.getResource("study-app.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 650, 600);

        // HostServices used for relaxing games, connects user to website games
        StudyController controller = fxmlLoader.getController();
        controller.setHostServices(getHostServices());

        stage.setTitle("My Personal Study Application");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}