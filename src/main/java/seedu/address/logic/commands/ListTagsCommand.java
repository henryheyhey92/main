package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

//@@author Labradorites
/**
 * Lists all tags tagged to persons in AddressBook or PersonListPanel depending on choice to the user.
 * Does not show duplicate tags.
 */
public class ListTagsCommand extends Command {

    public static final String CHOICE = "f";

    public static final String COMMAND_WORD = "listTags";
    public static final String MESSAGE_SUCCESS = "List all tags:\n%1$s";
    public static final String MESSAGE_NO_TAGS = "There are no tags available.";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds tags according to choice.\n"
            + "1. To list all tags available in AddressBook: " + COMMAND_WORD + "\n"
            + "2. To list all tags of persons currently shown below: " + COMMAND_WORD + " " + CHOICE + "\n";

    private String option;

    public ListTagsCommand(String argument) {
        this.option = argument;
    }

    @Override
    public CommandResult execute() {
        List<String> listOfTags = null;

        requireNonNull(model);

        switch (option) {
        case CHOICE:
            listOfTags = model.getTagsListAsString(model.getFilteredTagsList());
            break;
        default:
            listOfTags = model.getTagsListAsString(model.getNormalTagsList());
            break;
        }

        if (listOfTags.isEmpty()) {
            return new CommandResult(MESSAGE_NO_TAGS);
        }

        System.out.println(listOfTags);
        return new CommandResult(String.format(MESSAGE_SUCCESS, String.join("\n", listOfTags)));
    }
}
