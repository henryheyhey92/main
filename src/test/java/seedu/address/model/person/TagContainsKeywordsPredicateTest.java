package seedu.address.model.person;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import seedu.address.testutil.PersonBuilder;

//@@author Labradorites

/******/
public class TagContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("neighbour");
        List<String> secondPredicateKeywordList = Arrays.asList("123", "456");

        TagContainsKeywordsPredicate firstPredicate = new TagContainsKeywordsPredicate(firstPredicateKeywordList);
        TagContainsKeywordsPredicate secondPredicate = new TagContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TagContainsKeywordsPredicate firstPredicateCopy = new TagContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_phoneContainsKeywords_returnsTrue() {
        // Exact keyword
        TagContainsKeywordsPredicate predicate = new TagContainsKeywordsPredicate(Arrays.asList("99901234"));
        assertTrue(predicate.test(new PersonBuilder().withTag("99901234").build()));

        // Contains one keyword
        predicate = new TagContainsKeywordsPredicate(Collections.singletonList("22"));
        assertTrue(predicate.test(new PersonBuilder().withTag("11221234").build()));

        // Multiple keywords
        predicate = new TagContainsKeywordsPredicate(Arrays.asList("33", "44"));
        assertTrue(predicate.test(new PersonBuilder().withTag("11331244").build()));

        // Only one matching keyword
        predicate = new TagContainsKeywordsPredicate(Arrays.asList("55", "66"));
        assertTrue(predicate.test(new PersonBuilder().withTag("00551111").build()));
    }

    @Test
    public void test_phoneDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        TagContainsKeywordsPredicate predicate = new TagContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withTag("01234567").build()));

        // Non-matching keyword
        predicate = new TagContainsKeywordsPredicate(Arrays.asList("666"));
        assertFalse(predicate.test(new PersonBuilder().withTag("91234567").build()));

        // Keywords match name, email and address, but does not match phone
        predicate = new TagContainsKeywordsPredicate(Arrays.asList("Alice", "09876", "alice@email.com", "Main", "Street"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withTag("12345")
                .withEmail("alice@email.com").withAddress("Main Street").build()));
    }
}
