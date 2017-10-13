package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;


public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";
    public static final String MESSAGE_EMPTY = "Addressbook is currently empty please add at least 2 entries before sorting.";
    private String SORT_OPTION;
    private CommandResult result;

    public SortCommand (String argument) {
        this.SORT_OPTION=argument.trim();
    }

    public SortCommand (){}

    @Override
    public CommandResult execute()throws CommandException{
        requireNonNull(model);

            switch (SORT_OPTION) {

                case "/a":
                    Command command_address = new SortAddress(model);
                    result = command_address.execute();
                    break;

                case "/n":
                    Command command_name = new SortName(model);
                    result = command_name.execute();
                    break;

            }

            return result;

    }
}
