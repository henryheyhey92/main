# kikanng
###### /java/seedu/address/commons/events/ui/ChangeThemeEvent.java
``` java

/**
 * Chanage the color theme og the GUI
 */
public class ChangeThemeEvent extends BaseEvent {

    private String theme;

    public ChangeThemeEvent(String theme) {
        this.theme = theme;
    }

    public String getTheme() {
        return theme;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
```
###### /java/seedu/address/commons/util/StringUtil.java
``` java
    /**
     * Returns true if the {@code sentence} start with {@code characters}.
     *   Ignores case, full word match is not required.
     *   <br>examples:<pre>
     *       containsWordIgnoreCase("Abcdef", "abc") == true
     *       containsWordIgnoreCase("Abcdef", "bc") == false //not start with "bc"
     *       </pre>
     * @param sentence cannot be null
     * @param chars cannot be null, cannot be empty
     */
    public static boolean startWithCharIgnoreCase(String sentence, String chars) {
        requireNonNull(sentence);
        requireNonNull(chars);

        String preppedChars = chars.trim();
        checkArgument(!preppedChars.isEmpty(), "Word parameter cannot be empty");
        checkArgument(preppedChars.split("\\s+").length == 1, "Word parameter should be a single word");
        String regex = String.format("^" + preppedChars + ".*$");

        if (sentence.matches(regex)) {
            return true;
        }
        return false;
    }
```
###### /java/seedu/address/logic/commands/ChangeThemeCommand.java
``` java
/**
 * Change GUI color theme.
 */
public class ChangeThemeCommand extends Command {

    public static final String COMMAND_WORD = "theme";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Change GUI color theme.\n"
            + "Example: " + COMMAND_WORD + " light/dark";

    public static final String SHOWING_HELP_MESSAGE = "Changed color theme.";

    private static String theme;

    public ChangeThemeCommand(String theme) {
        this.theme = theme;
    }

    @Override
    public CommandResult execute() {
        EventsCenter.getInstance().post(new ChangeThemeEvent(theme));
        return new CommandResult(SHOWING_HELP_MESSAGE);
    }
}
```
###### /java/seedu/address/logic/commands/FindEmailCommand.java
``` java
/**
 * Finds and lists all persons in address book whose email contains any of the argument keywords.
 * Keyword matching is not case sensitive.
 */
public class FindEmailCommand extends Command {

    public static final String COMMAND_WORD = "findEmail";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds persons whose email contain any of the "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " john doe johnny";

    private final EmailContainsKeywordsPredicate predicate;

    public FindEmailCommand(EmailContainsKeywordsPredicate predicate) {
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
                || (other instanceof FindEmailCommand // instanceof handles nulls
                && this.predicate.equals(((FindEmailCommand) other).predicate)); // state check
    }
}
```
###### /java/seedu/address/logic/parser/ChangeThemeCommandParser.java
``` java
/**
 * Parses input arguments and creates a new ChangeThemeCommand object
 */
public class ChangeThemeCommandParser implements Parser<ChangeThemeCommand> {

    /**
     * Parses the given {@code String} of argument in the context of the ChangeThemeCommand
     * and returns an ChangeThemeCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ChangeThemeCommand parse(String args) throws ParseException {
        String[] possibleArgs = {"light", "dark"};
        String[] cssFiles = {"LightTheme.css", "DarkTheme.css"};

        String arg = args.trim();
        if (arg.isEmpty() || !(arg.equals("light") || arg.equals("dark"))) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ChangeThemeCommand.MESSAGE_USAGE));
        }

        boolean legalArg = false;
        for (int i = 0; i < possibleArgs.length; i++) {
            if (arg.equals(possibleArgs[i])) {
                arg = cssFiles[i];  // arg now become the css file path
                legalArg = true;
                break;
            }
        }

        if (!legalArg) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ChangeThemeCommand.MESSAGE_USAGE));
        }

        return new ChangeThemeCommand(arg);
    }

}
```
###### /java/seedu/address/logic/parser/FindEmailCommandParser.java
``` java
/**
 * Parses input arguments and creates a new FindEmailCommand object
 */
public class FindEmailCommandParser implements Parser<FindEmailCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindEmailCommand
     * and returns an FindEmailCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindEmailCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindEmailCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FindEmailCommand(new EmailContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }

}
```
###### /java/seedu/address/model/person/EmailContainsKeywordsPredicate.java
``` java
/**
 * Tests that a {@code ReadOnlyPerson}'s {@code Name} matches any of the keywords given.
 */
public class EmailContainsKeywordsPredicate implements Predicate<ReadOnlyPerson> {
    private final List<String> keywords;

    public EmailContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(ReadOnlyPerson person) {
        // return keywords.stream()
        // .anyMatch(keyword -> StringUtil.startWithCharIgnoreCase(person.getAddress().toString(), keyword));
        return keywords.stream()
        .anyMatch(keyword -> StringUtil.startWithCharIgnoreCase(person.getEmail().toString(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EmailContainsKeywordsPredicate // instanceof handles nulls
                && this.keywords.equals(((EmailContainsKeywordsPredicate) other).keywords)); // state check
    }

}
```
###### /java/seedu/address/ui/MainWindow.java
``` java
    /**
     * Set color theme
     */
    private void setTheme(String themeUrl) {
        this.getPrimaryStage().getScene().getStylesheets().clear();
        this.getPrimaryStage().getScene().getStylesheets().add(MainApp.class
                .getResource("/view/" + themeUrl).toExternalForm());
    }

    @Subscribe
    private void handleChangeThemeEvent(ChangeThemeEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event));
        setTheme(event.getTheme());
    }

    /**
     * Change color theme to light
     */
    @FXML
    public void handleLightTheme() {
        setTheme("LightTheme.css");
    }

    /**
     * Change color theme to dark
     */
    @FXML
    public void handleDarkTheme() {
        setTheme("DarkTheme.css");
    }
```
