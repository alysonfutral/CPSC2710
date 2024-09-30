package edu.au.cpsc.part1;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class Part1Controller {

  @FXML
  private TextField messageTextField, echoTextField, firstBidirectionalTextField, secondBidirectionalTextField;

  @FXML
  private ImageView secretOverlayImageView;

  @FXML
  private Slider secretSlider;

  @FXML
  private CheckBox selectMeCheckBox;

  @FXML
  private Label selectMeLabel;

  @FXML
  private TextField tweetTextField;

  @FXML
  private Label numberOfCharactersLabel, validityLabel;

  public void initialize() {
    // your bindings go here

    // "whatever is typed in messageTextField also shows in echoTextField"
    echoTextField.textProperty().bind(messageTextField.textProperty());

    // "whatever is typed in firstBidirectionalTextField shows
    //  in secondBidirectionalTextField and vise versa"
    firstBidirectionalTextField.textProperty().bindBidirectional(secondBidirectionalTextField.textProperty());

    // "Create a binding so that the opacity of the
    //  secretOverlapImageView is set by the value of the secretSlider"
    // valueProperty() - "The current value represented by this Slider"
    secretOverlayImageView.opacityProperty().bind(secretSlider.valueProperty());

    // "Create a binding so that the selectMeLabel will display
    // true/false depending on the state of selectMeCheckBox"
    // ** use asString() which can be used to return a property
    // selectedProperty() - Indicates whether the CheckBox is checked.
    selectMeLabel.textProperty().bind(selectMeCheckBox.selectedProperty().asString());

    // "Create a binding so that numberOfCharactersLabel displays the number of letters in the
    //  tweetTextField"
    // use asString() and length()
    numberOfCharactersLabel.textProperty().bind(tweetTextField.textProperty().length().asString());

    // "Create a binding so that validityLabel displays "Valid" if there are 10 characters or fewer in
    //  tweetTextField and "Invalid" otherwise"
    // use Bindings.when()
    validityLabel.textProperty().bind(
            Bindings.when(tweetTextField.textProperty().length().lessThanOrEqualTo(10))
                    .then("Valid")
                    .otherwise("Invalid")
    );
  }
}

/*
Resources:
https://edencoding.com/javafx-properties-and-binding-a-complete-guide/ "two-directional binding"
https://dev.java/learn/javafx/properties/ "Bi-directional binding"
https://docs.oracle.com/javafx/2/api/javafx/scene/control/CheckBox.html
https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/Slider.html
https://stackoverflow.com/questions/34546433/javafx-binding-a-textproperty-eg-label-to-a-simple-integer
 */