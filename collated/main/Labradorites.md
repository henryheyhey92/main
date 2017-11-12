# Labradorites
###### /java/seedu/address/ui/BrowserPanel.java
``` java
    private void loadAddressPage(ReadOnlyPerson person) {
        loadPage(GOOGLE_MAPS_SEARCH_URL_PREFIX + person.getAddress().value.replaceAll(" ", "+"));
    }
```
###### /java/seedu/address/ui/BrowserPanel.java
``` java
    @Subscribe
    private void handlePersonPanelSelectionChangedEvent(PersonPanelSelectionChangedEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event));
        loadAddressPage(event.getNewSelection().person);
    }
}
```
###### /java/seedu/address/commons/util/StringUtil.java
``` java
    /**
     * Returns true if the {@code sentence} contains the {@code word}.
     *   Ignores case, matches if sentence contains word.
     *   <br>examples:<pre>
     *       containsAny("abcde", "abc") == true
     *       containsAny("ABcdef", "def") == true
     *       containsAny("ABcdef", "AC") == false //not a full word match
     *       </pre>
     * @param sentence cannot be null
     * @param word cannot be null, cannot be empty, must be a single word
     */
    public static boolean containsAny(String sentence, String word) {
        requireNonNull(sentence);
        requireNonNull(word);

        String preppedWord = word.trim();
        checkArgument(!preppedWord.isEmpty(), "Word parameter cannot be empty");
        checkArgument(preppedWord.split("\\s+").length == 1, "Word parameter should be a single word");

        String preppedSentence = sentence;
        String[] wordsInPreppedSentence = preppedSentence.split("\\s+");

        for (String wordInSentence: wordsInPreppedSentence) {
            if (wordInSentence.contains(preppedWord)) {
                return true;
            }
        }
        return false;
    }
```
###### /java/seedu/address/commons/events/ui/PersonAddressSelectionChangedEvent.java
``` java
public class PersonAddressSelectionChangedEvent extends BaseEvent {


    private final PersonCard newSelection;

    public PersonAddressSelectionChangedEvent(PersonCard newSelection) {
        this.newSelection = newSelection;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

    public PersonCard getNewSelection() {
        return newSelection;
    }
}
```
###### /java/seedu/address/logic/parser/FindTagCommandParser.java
``` java
/**
 * Parses input arguments and creates a new FindTagCommand object
 */
public class FindTagCommandParser implements Parser<FindTagCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindTagCommand
     * and returns an FindTagCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindTagCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindTagCommand.MESSAGE_USAGE));
        }

        String[] tagKeywords = trimmedArgs.split("\\s+");

        return new FindTagCommand(new TagContainsKeywordsPredicate(Arrays.asList(tagKeywords)));
    }

}
```
###### /java/seedu/address/logic/parser/FindPhoneCommandParser.java
``` java
/**
 * Parses input arguments and creates a new FindPhoneCommand object
 */
public class FindPhoneCommandParser implements Parser<FindPhoneCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindPhoneCommand
     * and returns an FindPhoneCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindPhoneCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPhoneCommand.MESSAGE_USAGE));
        }

        String[] phoneKeywords = trimmedArgs.split("\\s+");

        return new FindPhoneCommand(new PhoneContainsKeywordsPredicate(Arrays.asList(phoneKeywords)));
    }

}
```
###### /java/seedu/address/logic/commands/FindTagCommand.java
``` java
/**
 * Finds and lists all persons in address book whose tags contains any of the argument keywords.
 * Keyword matching is case sensitive.
 */
public class FindTagCommand extends Command {

    public static final String COMMAND_WORD = "findTag";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose tags contain "
            + "the specified keywords (case-sensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " neighbours";

    private final TagContainsKeywordsPredicate predicate;

    public FindTagCommand(TagContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute() {
        model.updateFilteredPersonList(predicate);
        return new CommandResult(getMessageForPersonListShownSummary(model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindTagCommand // instanceof handles nulls
                && this.predicate.equals(((FindTagCommand) other).predicate)); // state check
    }
}
```
###### /java/seedu/address/logic/commands/FindPhoneCommand.java
``` java
/**
 * Finds and lists all persons in address book whose phone numbers contains any of the argument keywords.
 */
public class FindPhoneCommand extends Command {

    public static final String COMMAND_WORD = "findPhone";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose phone contain any of "
            + "the specified numbers() and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " 999 111 99998888";

    private final PhoneContainsKeywordsPredicate predicate;

    public FindPhoneCommand(PhoneContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute() {
        model.updateFilteredPersonList(predicate);
        return new CommandResult(getMessageForPersonListShownSummary(model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindPhoneCommand // instanceof handles nulls
                && this.predicate.equals(((FindPhoneCommand) other).predicate)); // state check
    }
}
```
###### /java/seedu/address/logic/commands/ListTagsCommand.java
``` java
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
```
###### /java/seedu/address/model/person/TagContainsKeywordsPredicate.java
``` java
/**
 * Tests that a {@code ReadOnlyPerson}'s {@code Tag} matches any of the keywords given.
 */
public class TagContainsKeywordsPredicate implements Predicate<ReadOnlyPerson> {
    private final List<String> keywords;

    public TagContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(ReadOnlyPerson person) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.fullTag(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TagContainsKeywordsPredicate // instanceof handles nulls
                && this.keywords.equals(((TagContainsKeywordsPredicate) other).keywords)); // state check
    }

}
```
###### /java/seedu/address/model/person/ReadOnlyPerson.java
``` java
    String fullTag();
```
###### /java/seedu/address/model/person/Person.java
``` java
    @Override
    public String fullTag() {
        final StringBuilder builder = new StringBuilder();
        getTags().forEach(builder::append);

        return builder.toString().replace("][", " ").replaceAll("[\\[\\]]", "");
    }
```
###### /java/seedu/address/model/person/PhoneContainsKeywordsPredicate.java
``` java
/**
 * Tests that a {@code ReadOnlyPerson}'s {@code Phone} matches any of the keywords given.
 */
public class PhoneContainsKeywordsPredicate implements Predicate<ReadOnlyPerson> {
    private final List<String> keywords;

    public PhoneContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(ReadOnlyPerson person) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsAny(person.getPhone().value, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PhoneContainsKeywordsPredicate // instanceof handles nulls
                && this.keywords.equals(((PhoneContainsKeywordsPredicate) other).keywords)); // state check
    }

}
```
###### /java/seedu/address/model/ModelManager.java
``` java
    @Override
    public List<Tag> getTagsList(){
        List<Tag> unsortedListOfTags = new ArrayList<>();

        filteredPersons.forEach(persons -> unsortedListOfTags.addAll(persons.getTags()));

        //Removes duplicate tags to ensure all tags are unique
        List<Tag> listOfTags= unsortedListOfTags.stream().distinct().collect(Collectors.toList());
        //Sorts tags in alphabetical order
        listOfTags.sort(Comparator.comparing(Tag::getTagName));

        return listOfTags;
    }
```
###### /java/seedu/address/model/tag/Tag.java
``` java
    /*
     * Returns tag name.
     */
    public String getTagName() {
        return tagName;
    }
```
###### /java/seedu/address/model/Model.java
``` java
    /*
     * Returns list of all unique tags present in AddressBook that are sorted alphabetically
     */
    List<Tag> getTagsList();
```
