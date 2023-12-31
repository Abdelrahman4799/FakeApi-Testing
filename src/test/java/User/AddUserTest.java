package User;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.io.File;

import static Cart.AllCartTest.base;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

public class AddUserTest {
    File body = new File("src/test/resources/userData.json");
    @Test
    public void CheckStatusCode()
    {
        given().baseUri(base)
                .contentType(ContentType.JSON)
                .body(body)
                .when().post("users")
                .then().log().ifValidationFails()
                .assertThat().statusCode(200);

    }
    @Test
    public void CheckResTime()
    {
        given().baseUri(base)
                .contentType(ContentType.JSON)
                .body(body)
                .when().post("users")
                .then().log().ifValidationFails()
                .assertThat().time(lessThan(800L));
    }
    @Test
    public void CheckProductSchema()
    {
        given().baseUri(base)
                .contentType(ContentType.JSON)
                .body(body)
                .when().post("users")
                .then().log().ifValidationFails()
                .assertThat().body(matchesJsonSchemaInClasspath("single-product-schema.json"));
    }

    @Test
    public void CheckID()
    {
        given().baseUri(base)
                .contentType(ContentType.JSON)
                .body(body)
                .when().post("users")
                .then().log().ifValidationFails()
                .assertThat().body("id", is(not(empty())));
    }

    //post empty body should return error
    @Test
        public void CheckEmptyBody()
    {
        given().baseUri(base)
                .contentType(ContentType.JSON)
                .body("")
                .when().post("users")
                .then().log().ifValidationFails()
                .assertThat().body("id", is(empty()) );
    }
}
