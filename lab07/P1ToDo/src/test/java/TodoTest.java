import org.junit.*;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class TodoTest {

    @Test
    public void testStatusCode() {
        given().when()
        .get("https://jsonplaceholder.typicode.com/todos")
        .then().assertThat()
        .statusCode(200);
    }

    @Test
    public void testTitle4() {
        given().when()
        .get("https://jsonplaceholder.typicode.com/todos/4")
        .then().assertThat()
        .statusCode(200)
        .and().body("title", equalTo("et porro tempora"))
        .and().body("id", equalTo(4));
    }

    @Test
    public void testId198And199() {
        given().when()
        .get("https://jsonplaceholder.typicode.com/todos")
        .then().assertThat()
        .statusCode(200)
        .and().body("id", hasItems(198, 199));
    }
}
