package systemtests;

import static seedu.address.logic.commands.SortAddress.MESSAGE_SUCCESS_ADDRESS;
import static seedu.address.logic.commands.SortCommand.CHOICE_ADDRESS;
import static seedu.address.logic.commands.SortCommand.CHOICE_NAME;
import static seedu.address.logic.commands.SortName.MESSAGE_SUCCESS_NAME;

import org.junit.Test;

import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.commands.SortName;
import seedu.address.model.Model;
import seedu.address.model.person.UniquePersonList;

public class SortCommandSystemTest extends AddCommandSystemTest{

    @Test
    public void sort() throws UniquePersonList.AddressBookIsEmpty{

        /*Case: sort the addressbook by name, command with leading and trailing spaces -> sorted by name */
       // Model expectedModelName = getModel();
        //String commandSortName = " "  + SortCommand.COMMAND_WORD +" "+CHOICE_NAME;
        //expectedModelName.sortAddressBook(1);
        //assertCommandSuccess(commandSortName,expectedModelName,MESSAGE_SUCCESS_NAME);

         /*Case: sort the addressbook by address, command with leading and trailing spaces -> sorted by address */
        Model expectedModelAddress = getModel();
        String commandSortAddress = " "  + SortCommand.COMMAND_WORD +" "+CHOICE_ADDRESS;
        expectedModelAddress.sortAddressBook(2);
        assertCommandSuccess(commandSortAddress,expectedModelAddress,MESSAGE_SUCCESS_ADDRESS);

    }

    public void assertCommandSuccess(String command,Model expectedModel,String expectedResultMessage){
        executeCommand(command);
        assertApplicationDisplaysExpected("",expectedResultMessage,expectedModel);
        assertCommandBoxShowsDefaultStyle();
    }
}
