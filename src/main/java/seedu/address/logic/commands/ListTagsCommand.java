package seedu.address.logic.commands;

import java.util.ArrayList;
import java.util.List;

/**
 * Lists all tags tagged to persons in AddressBook. Does not show duplicate tags.
 */
public class ListTagsCommand extends Command {

    public static final String COMMAND_WORD = "listTags";
    public static final String MESSAGE_SUCCESS = "List all tags:\n%1$s";
    public static final String MESSAGE_NO_TAGS = "There are no tags available.";

    private List<String> getTagList (){
        //this.addressBook = new AddressBook(model.getAddressBook());
        List<String> listOfTags = new ArrayList<>();

        return listOfTags;
    }
    @Override
    public CommandResult execute() {
        List<String> listOfTags = getTagList();//new ArrayList<>();
        //ObservableList<Tag> listOfTags = addressBook.getTagList();
        //listOfTags.add("testing1");
        //listOfTags.add("testing2");


        if (listOfTags.isEmpty()) {
            return new CommandResult(MESSAGE_NO_TAGS);
        }

        //Collections.reverse(listOfTags);
        System.out.println(listOfTags);
        return new CommandResult(String.format(MESSAGE_SUCCESS, listOfTags));//String.join("\n", listOfTags)));
    }


}
