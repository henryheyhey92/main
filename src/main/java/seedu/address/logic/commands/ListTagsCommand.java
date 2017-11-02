package seedu.address.logic.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javafx.collections.ObservableList;
import seedu.address.model.person.ReadOnlyPerson;

/**
 * Lists all tags tagged to persons in AddressBook. Does not show duplicate tags.
 */
public class ListTagsCommand extends Command {

    public static final String COMMAND_WORD = "listTags";
    public static final String MESSAGE_SUCCESS = "List all tags:\n%1$s";
    public static final String MESSAGE_NO_TAGS = "There are no tags available.";
    /*
    private List<String> getTagList (){
        List<String> listOfTags = new ArrayList<>();

        ObservableList<ReadOnlyPerson> listOfPersons = model.getFilteredPersonList();

        listOfPersons.forEach(persons-> persons.getTags().forEach(tags -> listOfTags.add(tags.toString()
                .replaceAll("[^a-zA-Z]", "")))); //removes all non-letters

        //returns list with duplicates removed and sorted
        listOfTags.stream().distinct().sorted().collect(Collectors.toList());
        return listOfTags;
    }*/
    @Override
    public CommandResult execute() {
        List<String> listOfTags = model.getTagsList();

        if (listOfTags.isEmpty()) {
            return new CommandResult(MESSAGE_NO_TAGS);
        }

        System.out.println(listOfTags);
        return new CommandResult(String.format(MESSAGE_SUCCESS, String.join("\n", listOfTags)));
    }
}
