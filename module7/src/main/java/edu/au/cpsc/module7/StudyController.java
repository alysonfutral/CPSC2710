/*
Controller class holds all methods for fxml files.
Methods for each new window is separated by ********
 */

package edu.au.cpsc.module7;

import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StudyController {

    @FXML
    private TextArea notesTextArea;

    @FXML
    private ListView<String> notesListView;

    @FXML
    private TextArea contactsTextArea;

    @FXML
    private ListView<String> contactsListView;

    @FXML
    private WebView webView;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox contentVBox;

    // keep track of new textareas
    private List<TextArea> textAreas = new ArrayList<>();

    // store notes in list
    private ObservableList<String> notesList = FXCollections.observableArrayList();

    // store contacts in list
    private ObservableList<String> contactsList = FXCollections.observableArrayList();

    // handles external opening of game website
    private HostServices hostServices;

    // allows controller to use hostservices
    public void setHostServices(HostServices hostServices) {
        this.hostServices = hostServices;
    }
    // keep track of the currently selected textarea
    private TextArea selectedTextArea;

    // keep track of clicks to fill progress bar
    private int buttonClickCount = 0;
    private void incrementProgress() {
        buttonClickCount++;
        double progress = buttonClickCount / 4.0;

        progressBar.setProgress(progress);

        // reset after 4 buttons have been clicked
        if (buttonClickCount >= 4) {
            buttonClickCount = 0;
        }
    }

    // closes application (in the menu bar)
    @FXML
    private void closeApplication(ActionEvent event) {
        Platform.exit();
    }

    // ********************************************************************************************************************************************
    // opens contacts window
    public void openContacts(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("contacts.fxml"));
            Scene scene = new Scene(loader.load());

            Stage contactsWindow = new Stage();
            contactsWindow.setScene(scene);
            contactsWindow.show();

            incrementProgress(); // increaments progress bar (used in all open window methods)
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // add contacts method updates the list with contacts
    @FXML
    private void saveContacts() {
        String contact = contactsTextArea.getText();

        if (contact.isEmpty()) {
            showAlert("Hang On!", "You did not enter a contact!");
        } else {
            contactsList.add(contact);
            contactsListView.setItems(contactsList);
            contactsTextArea.clear();
        }
    }

    // if the contact in the listview is selected, this will allow it to be deleted
    @FXML
    private void deleteContacts() {
        String selectedContact = contactsListView.getSelectionModel().getSelectedItem();

        if (selectedContact != null) {
            contactsList.remove(selectedContact);
            contactsListView.setItems(contactsList);
        } else {
            showAlert("Hang On!", "Please select a note to delete!");
        }
    }


    // ********************************************************************************************************************************************
    // opens notes window
    public void openNotes(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("notes.fxml"));
            Scene scene = new Scene(loader.load());

            Stage notesWindow = new Stage();
            notesWindow.setScene(scene);
            notesWindow.show();

            incrementProgress();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // when the user inputs data into the textarea, the save button will save it into the listarea
    @FXML
    private void saveNote() {
        String note = notesTextArea.getText().trim();

        if (note.isEmpty()) {
            showAlert("Wait a second!", "You did not enter a note!");
        } else {
            notesList.add(note);
            notesListView.setItems(notesList);
            notesTextArea.clear();
        }
    }

    // deletes the selected note
    @FXML
    private void deleteNote() {
        String selectedNote = notesListView.getSelectionModel().getSelectedItem();

        if (selectedNote != null) {
            notesList.remove(selectedNote);
            notesListView.setItems(notesList);
        } else {
            showAlert("Hang On!", "Please select a note to delete!");
        }
    }

    // ********************************************************************************************************************************************
    // opens terms window
    public void openFlashCards(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("terms.fxml"));
            AnchorPane root = loader.load();
            StudyController controller = loader.getController();

            Stage flashCardWindow = new Stage();
            // use root node (AnchorPane)
            flashCardWindow.setScene(new Scene(root));
            // Access the ScrollPane from the StudyController that was loaded along with the FXML.
            ScrollPane scrollPane = controller.scrollPane;
            // check if ScrollPane is not null
            if (scrollPane != null && scrollPane.getContent() instanceof AnchorPane) {
                // cast ScrollPane's data to anchorpane if content is an anchorpane
                AnchorPane anchorPane = (AnchorPane) scrollPane.getContent();
                // clear nodes inside anchorpane
                anchorPane.getChildren().clear();
            }

            flashCardWindow.show();
            incrementProgress();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void addNewCard(ActionEvent event) {
        TextArea textArea = new TextArea();
        textArea.setPromptText("Enter Important Terms...");

        textArea.setStyle("-fx-background-color: pink;"); // easier to change color of textarea here
        // when the the mouse is in the textarea and selects a textarea, an action such as 'delete' can be performed
        textArea.setOnMouseClicked(e -> {
            selectedTextArea = textArea;
        });

        // add a new textarea inside the vbox for new terms
        contentVBox.getChildren().add(textArea);
        textAreas.add(textArea);
    }

    // deletes selected term textarea in the contentvbox
    @FXML
    public void deleteCard(ActionEvent event) {
        if (selectedTextArea != null) {
            contentVBox.getChildren().remove(selectedTextArea);
            textAreas.remove(selectedTextArea);
            selectedTextArea = null;
        } else {
            showAlert("Wait a second!", "No card is selected to delete!");
        }
    }

    // ********************************************************************************************************************************************
    // opens relax window
    public void openRelax(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("relax.fxml"));
            Scene scene = new Scene(loader.load());

            StudyController controller = loader.getController();
            controller.setHostServices(this.hostServices);

            Stage notesWindow = new Stage();
            notesWindow.setScene(scene);
            notesWindow.show();

            incrementProgress();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openRelaxingVideo(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("relaxing-video.fxml"));
            Scene scene = new Scene(loader.load());

            // get WebView from FXML
            WebView webView = (WebView) loader.getNamespace().get("webView");
            // relaxing video url (Bob Ross painting)
            String videoUrl = "https://www.youtube.com/embed/7t6ue0pEcNE?list=PLwg3R0eVyU8maknr7Y4Qi3M8tII3MSKJ-";
            // loads url to webview
            webView.getEngine().load(videoUrl);

            Stage youtubeVideo = new Stage();
            youtubeVideo.setScene(scene);
            youtubeVideo.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // opens relaxing game window with hostservices when button is clicked
    @FXML
    private void openRelaxingGame() {
        String url = "https://www.mind.org.uk/need-urgent-help/how-can-i-distract-myself/games-and-puzzles/";
        if (hostServices != null) {
            hostServices.showDocument(url);
        } else {
            showAlert("Error", "Games Unavailable");
        }
    }

    // ********************************************************************************************************************************************
    // provides error message if user does not input, select, certain areas of application
    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}

/*
Resources:
https://cr.openjdk.org/~jjg/8186466/api.00/javafx/application/Application.html#getHostServices--
https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/ProgressBar.html
https://www.youtube.com/watch%3Fv%3DbSy0XYG5odo&ved=2ahUKEwiZ49ezq4CJAxWqTTABHTESGiIQtwJ6BAgNEAI&usg=AOvVaw3Ck2xt5l2Ja6-gw30Wvdw8
https://www.geeksforgeeks.org/javafx-alert-with-examples/
 */
