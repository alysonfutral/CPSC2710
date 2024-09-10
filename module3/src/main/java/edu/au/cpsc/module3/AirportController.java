package edu.au.cpsc.module3;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.web.WebView;

public class AirportController {

    @FXML
    private TextField ident;

    @FXML
    private TextField iataCode;

    @FXML
    private TextField localCode;

    @FXML
    private TextField type;

    @FXML
    private TextField name;

    @FXML
    private TextField elevation;

    @FXML
    private TextField country;

    @FXML
    private TextField region;

    @FXML
    private TextField municipality;

    @FXML
    private Button search;

    @FXML
    private WebView webView;

}
