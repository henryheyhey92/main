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
    public static LoginBox lock;
    protected Storage storage;
    private boolean name = false;

    /**
     * lock the application when command is inputted
     * @param name
     */
    public LockCommand(boolean name) {
        this.name = name;
        lock = new LoginBox();
        lock.display("AddressBook" , 1 );
    }

    @Override
    public CommandResult executeUndoableCommand() {
        return new CommandResult(MESSAGE_SUCCESS);
    }

}
