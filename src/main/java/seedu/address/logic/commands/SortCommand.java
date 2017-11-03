package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;

/**
 * Sorts the addressbook base on name or address
 */
//@@author NUSe0032202
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "s";
    public static final String CHOICE_NAME = "n";
    public static final String CHOICE_ADDRESS = "a";

    public static final String
            MESSAGE_EMPTY = "Addressbook is currently empty please add at least 2 entries before sorting.";
    public static final String POSSIBLE_CHOICES = "Invalid choice input,the valid choices to be sorted are:\n"
            + "1. " + CHOICE_NAME + " (name)\n"
            + "2. " + CHOICE_ADDRESS + " (address)\n";
    public static final String SAVE_OPTION = "Invalid input for save option, to save please use keyword -> se ";
    public static final int SAVE = 1;
    protected int saveOption;
    private String option;
    private CommandResult result;

    public SortCommand() {
    }

    public SortCommand(String argument, int saveOption) {
        this.option = argument.trim();
        this.saveOption = saveOption;
    }

    @Override
    public CommandResult execute() throws CommandException {
        requireNonNull(model);

        switch (option) {
        case CHOICE_ADDRESS:
            SortCommand address = new SortAddress(model, saveOption);
            result = address.execute();
            break;

        case CHOICE_NAME:
            SortCommand name = new SortName(model, saveOption);
            result = name.execute();
            break;

        default:
            break;
        }
        return result;
    }
}
//@@author