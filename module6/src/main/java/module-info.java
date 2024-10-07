module edu.au.cpsc.module6 {
    requires javafx.controls;
    requires javafx.fxml;

    // Open both part1 and module6 packages for reflection and JavaFX access
    opens edu.au.cpsc.part1 to javafx.graphics, javafx.fxml;
    exports edu.au.cpsc.part1;

    opens edu.au.cpsc.module6 to javafx.fxml;
    exports edu.au.cpsc.module6;
}
