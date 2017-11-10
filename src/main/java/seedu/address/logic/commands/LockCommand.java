package seedu.address.logic.commands;

import javafx.stage.Modality;
import javafx.stage.Stage;
import seedu.address.LoginBox;

import seedu.address.MainApp;
import seedu.address.storage.Storage;
import seedu.address.ui.Ui;

/**
 * Clears the address book.
 */
public class LockCommand extends UndoableCommand {

    private static Stage window = new Stage();
    public static final String COMMAND_WORD = "lock";
    public static final String MESSAGE_SUCCESS = "Address book is unlock!";
    public static final String MESSAGE_FAIL = "Address book is lock";
    public static LoginBox lock;
    //public static boolean answer;
    protected Storage storage;

    public LockCommand(){
        lock = new LoginBox();
        lock.display("AddressBook Login", 1);
    }

    @Override
    public CommandResult executeUndoableCommand() {
//        if(answer) {
            return new CommandResult(MESSAGE_SUCCESS);
//        }else{
//            return new CommandResult(MESSAGE_FAIL);
//        }
    }

}
