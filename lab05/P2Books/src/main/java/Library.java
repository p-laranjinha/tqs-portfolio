import java.util.*;
import java.util.stream.Collectors;

public class Library {
    private final List<Book> store = new ArrayList<>();

    public void addBook(final Book book) {
        store.add(book);
    }

    public List<Book> findBooks(final Date from, final Date to) {
        return store.stream().filter(book -> {
            return from.before(book.getPublished()) && to.after(book.getPublished());
        }).sorted(Comparator.comparing(Book::getPublished).reversed()).collect(Collectors.toList());
    }

    public List<Book> findBooksByAuthor(String name) {
        return store.stream().filter(book -> {
            return book.getAuthor().contains(name);
        }).sorted(Comparator.comparing(Book::getAuthor)).collect(Collectors.toList());
    }

    public List<Book> findBooksByTitle(String name) {
        return store.stream().filter(book -> {
            return book.getTitle().contains(name);
        }).sorted(Comparator.comparing(Book::getTitle)).collect(Collectors.toList());
    }
}
