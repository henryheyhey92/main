package seedu.address.logic.commands;

import javafx.stage.Stage;
import seedu.address.LoginBox;

import seedu.address.storage.Storage;


/**
 * Clears the address book.
 */
public class LockCommand extends UndoableCommand {

    public static final String COMMAND_WORD = "lock";
    public static final String MESSAGE_SUCCESS = "Address book is unlock!";
    protected Storage storage;
    private boolean name = false;

    public LockCommand(boolean name){
        this.name = name;
    }

    @Override
    public CommandResult executeUndoableCommand() {
        return new CommandResult(MESSAGE_SUCCESS);
    }

}
