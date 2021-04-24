import io.cucumber.java.ParameterType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookSearchSteps {
    Library library = new Library();
    List<Book> result = new ArrayList<>();

    @ParameterType("([0-9]{4})-([0-9]{2})-([0-9]{2})")
    public LocalDateTime iso8601Date(String year, String month, String day){
        return LocalDateTime.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day),0, 0);
    }

    @Given("a book with the title {string}, written by {string}, published in {iso8601Date}")
    @And("another book with the title {string}, written by {string}, published in {iso8601Date}")
    public void addNewBook(final String title, final String author, final LocalDateTime published) {
        Book book = new Book(title, author, Timestamp.valueOf(published));
        library.addBook(book);
    }

    @When("the customer searches for books published between {iso8601Date} and {iso8601Date}")
    public void setSearchParameters(final LocalDateTime from, final LocalDateTime to) {
        result = library.findBooks(Timestamp.valueOf(from), Timestamp.valueOf(to));
    }

    @Then("{int} books should have been found")
    public void booksShouldHaveBeenFound(int arg0) {
        for (Book b: result) {
            System.out.print(b.getTitle() + " ");
        }
        System.out.println();
        assertEquals(arg0, result.size());
    }

    @And("Book {int} should have the title {string}")
    public void bookShouldHaveTheTitleBook(int arg0, String arg1) {
        assertEquals(arg1, result.get(arg0-1).getTitle());
    }

    @When("the customer searches for books by the author {string}")
    public void theCustomerSearchesForBooksByTheAuthor(String arg0) {
        result = library.findBooksByAuthor(arg0);
    }

    @When("the customer searches for books by the title {string}")
    public void theCustomerSearchesForBooksByTheTitle(String arg0) {
        result = library.findBooksByTitle(arg0);
    }
}
