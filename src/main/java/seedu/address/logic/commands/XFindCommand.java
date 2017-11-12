package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS_BOLD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL_BOLD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME_BOLD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE_BOLD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG_BOLD;

import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.XFindPredicate;


//@@author kikanng
/**
 * A more powerful find tool
 */
public class XFindCommand extends Command {

    public static final String COMMAND_WORD = "xFind";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": More powerful find feature, can find everything containing the keywords "
            + "Parameters: "
            + PREFIX_NAME_BOLD + "NAME or "
            + PREFIX_PHONE_BOLD + "PHONE or "
            + PREFIX_EMAIL_BOLD + "EMAIL or "
            + PREFIX_ADDRESS_BOLD + "ADDRESS or "
            + PREFIX_TAG_BOLD + "TAG\n"
            + "if you want to only search for items start with the keywords, use "
            + PREFIX_NAME + PREFIX_PHONE + PREFIX_EMAIL + PREFIX_ADDRESS + PREFIX_TAG + "instead\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME_BOLD + "John Alan "
            + PREFIX_PHONE_BOLD + "98765432 "
            + PREFIX_EMAIL_BOLD + "johnd@example.com "
            + PREFIX_ADDRESS_BOLD + "Clementi "
            + PREFIX_TAG_BOLD + "friends";

    public static final String MESSAGE_BOUND = COMMAND_WORD + ": only one parameter is allowed";

    private final XFindPredicate predicate;

    public XFindCommand(XFindPredicate predicate) {
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
                || (other instanceof XFindCommand // instanceof handles nulls
                && this.predicate.equals(((XFindCommand) other).predicate)); // state check
    }
}
