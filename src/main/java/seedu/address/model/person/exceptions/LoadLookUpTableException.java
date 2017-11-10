package seedu.address.model.person.exceptions;

public class LoadLookUpTableException extends Exception {
    public LoadLookUpTableException() {
        super("Error with loading data for the lookup table");
    }
}
