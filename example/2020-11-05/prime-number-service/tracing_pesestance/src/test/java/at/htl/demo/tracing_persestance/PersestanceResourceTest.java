package at.htl.demo.tracing_persestance;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class PersestanceResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/resteasy/hello")
          .then()
             .statusCode(200)
             .body(is("hello"));
    }

}