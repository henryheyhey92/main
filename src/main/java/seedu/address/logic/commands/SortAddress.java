package seedu.address.logic.commands;

import java.util.ArrayList;
import java.util.Comparator;

import seedu.address.model.Model;
import seedu.address.model.person.ReadOnlyPerson;
import seedu.address.model.person.UniquePersonList;

/**
 * Actual logic for sorting by address
 */
public class SortAddress extends SortCommand implements Comparator<ReadOnlyPerson> {

    public static final String
            MESSAGE_SUCCESS_ADDRESS = "The address book has been sorted alphabetically according to address";
    public static final int OPTION_ADDRESS = 2;

    /*
     * Look up table of addresses for comparison
     */
    private static final ArrayList<CharSequence> table = new ArrayList();

    private Model model;
    private int saveOption;

    static {
        table.add("ang mo kio");
        table.add("geylang");
        table.add("tampines");
        table.add("serangoon");
        table.add("aljunied");
        table.add("jurong");
        table.add("clementi");
        table.add("pasir ris");
        table.add("bukit batok");
    }

    public SortAddress() {}

    public SortAddress(Model target, int saveOption) {
        this.model = target;
        this.saveOption = saveOption;
    }

    @Override
    public CommandResult execute() throws UniquePersonList.AddressBookIsEmpty {
        model.sortAddressBook(OPTION_ADDRESS, saveOption);
        return new CommandResult(MESSAGE_SUCCESS_ADDRESS);
    }

    @Override
    public int compare(ReadOnlyPerson a, ReadOnlyPerson b) {
        int index = 0;
        for (CharSequence compare : table) {
            if (a.getAddress().value.toLowerCase().contains(compare)) {
                index = table.indexOf(compare);
            }
        }
        String compareFirst = (String) table.get(index);

        for (CharSequence compare : table) {
            if (b.getAddress().value.toLowerCase().contains(compare)) {
                index = table.indexOf(compare);
            }
        }

        String compareSecond = (String) table.get(index);

        return compareFirst.compareToIgnoreCase(compareSecond);
    }
}
