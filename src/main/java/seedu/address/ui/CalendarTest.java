package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;

/**
 * In-put birthday window of the App.
 */

//@@author NUSe0032202
public class CalendarTest extends UiPart<Region> {

    private static final Logger logger = LogsCenter.getLogger(CalendarTest.class);
    private final Stage dialogStage;
    private String birthdate;
    @FXML
    private DatePicker birthday;


    public CalendarTest() {
        super("Calendar.fxml");
        Scene scene = new Scene(getRoot(), 300, 100);
        dialogStage = createDialogStage("", null, scene);
        dialogStage.setScene(scene);

    }

    @FXML
    private void birthdayArgs() {
        birthdate = Integer.toString(birthday.getValue().getDayOfMonth()) + "/"
                + Integer.toString(birthday.getValue().getMonth().getValue()) + "/"
                + Integer.toString(birthday.getValue().getYear());
    }

    public String getBirthday() {
        return birthdate;
    }

    public void show() {
        logger.fine("Showing birthday picker.");
        dialogStage.showAndWait();
    }
}
//@@author
