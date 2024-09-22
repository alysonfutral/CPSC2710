module edu.au.cpsc.module5 {
    requires javafx.controls;
    requires javafx.fxml;

    //added for part 1
    opens edu.au.cpsc.miscstyle to javafx.fxml;
    exports edu.au.cpsc.miscstyle;

    //added for part 2 'launcher'
    opens edu.au.cpsc.launcher to javafx.fxml;
    exports edu.au.cpsc.launcher;

}