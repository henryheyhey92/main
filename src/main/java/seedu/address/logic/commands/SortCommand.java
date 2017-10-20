package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;

/**
 * Sorts the addressbook base on name or address
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "s";
    public static final String CHOICE_NAME = "n";
    public static final String CHOICE_ADDRESS = "a";

    public static final String OPTION_ADDRESS = "a";
    public static final String OPTION_NAME = "n";
    public static final String
            MESSAGE_EMPTY = "Addressbook is currently empty please add at least 2 entries before sorting.";
    public static final String POSSIBLE_CHOICES = "Invalid choice input,the valid choices to be sorted are:\n"
                         + "1. " + CHOICE_NAME + " (name)\n"
                         + "2. " + CHOICE_ADDRESS + " (address)\n";

    private String option;
    private CommandResult result;

    public SortCommand(){}

    public SortCommand(String argument) {
        this.option = argument.trim();
    }

    @Override
    public CommandResult execute() throws CommandException {
        requireNonNull(model);

        switch (option) {
        case OPTION_ADDRESS:
            Command address = new SortAddress(model);
            result = address.execute();
            break;

        case OPTION_NAME:
            Command name = new SortName(model);
            result = name.execute();
            break;

        default:
            break;
        }

        return result;

    }
}
