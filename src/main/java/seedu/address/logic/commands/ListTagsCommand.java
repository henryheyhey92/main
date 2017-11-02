package seedu.address.logic.commands;

import java.util.ArrayList;
import java.util.List;

import seedu.address.model.tag.Tag;

/**
 * Lists all tags tagged to persons in AddressBook. Does not show duplicate tags.
 */
public class ListTagsCommand extends Command {

    public static final String COMMAND_WORD = "listTags";
    public static final String MESSAGE_SUCCESS = "List all tags:\n%1$s";
    public static final String MESSAGE_NO_TAGS = "There are no tags available.";

    @Override
    public CommandResult execute() {
        List<Tag> listOfTags = model.getTagsList();
        List<String> tagsString = new ArrayList<>();

        //removes all [] from tags
        listOfTags.forEach(tag -> tagsString.add(tag.toString().replaceAll("[\\[\\]]", "")));

        if (listOfTags.isEmpty()) {
            return new CommandResult(MESSAGE_NO_TAGS);
        }

        System.out.println(listOfTags);
        return new CommandResult(String.format(MESSAGE_SUCCESS, String.join("\n", tagsString)));
    }
}
