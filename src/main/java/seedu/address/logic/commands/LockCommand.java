package seedu.address.logic.commands;

import javafx.stage.Stage;
import seedu.address.LoginBox;

import seedu.address.storage.Storage;


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
    private boolean name = false;

    //    public LockCommand(){
////        lock = new LoginBox();
////        lock.display("AddressBook Login", 1);
//
//    }
    public LockCommand(boolean name){
        this.name = name;
    }
    public boolean LockCommandget(){
        return this.name;
    }


    @Override
    public CommandResult executeUndoableCommand() {
            return new CommandResult(MESSAGE_SUCCESS);
    }

}
