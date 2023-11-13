package User;

import org.testng.annotations.Test;

import static Cart.AllCartTest.base;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.lessThan;

public class AllUsersTest {
    @Test
    public void CheckStatusCode()
    {
        given().baseUri(base)
                .when().get("users")
                .then().log().ifValidationFails()
                .assertThat().statusCode(200);

    }
    @Test
    public void CheckResTime()
    {
        given().baseUri(base)
                .when().get("users")
                .then().log().ifValidationFails()
                .assertThat().time(lessThan(4000L));
    }
    @Test
    public void CheckUserssSchema()
    {
        given().baseUri(base)
                .when().get("users")
                .then().log().ifValidationFails()
                .assertThat().body(matchesJsonSchemaInClasspath("users-schema.json"));
    }
}
