package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 *  return the name that matches the key letter
 */
public class NameLetterContainsKeywordPredicate implements Predicate<ReadOnlyPerson> {
    private final List<String> keywords;

    public NameLetterContainsKeywordPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(ReadOnlyPerson person) {

        if (keywords.isEmpty()) {
            return keywords.stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getName().fullName, keyword));
        }
        if (keywords.get(0).length() > 1) {
            return keywords.stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getName().fullName, keyword));
        } else {
            String letter2 = String.valueOf(person.getName().fullName.charAt(0));
            //System.out.println(letter2.length());
            return keywords.stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(letter2, keyword));
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameLetterContainsKeywordPredicate // instanceof handles nulls
                && this.keywords.equals(((NameLetterContainsKeywordPredicate) other).keywords)); // state check
    }

}
