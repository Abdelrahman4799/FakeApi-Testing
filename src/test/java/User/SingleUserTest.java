package User;

import org.testng.annotations.Test;

import static Cart.AllCartTest.base;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class SingleUserTest {
    @Test
    public void CheckStatusCode()
    {
        given().baseUri(base)
                .when().get("users/5")
                .then().log().ifValidationFails()
                .assertThat().statusCode(200);
    }
    @Test
    public void CheckResTime()
    {
        given().baseUri(base)
                .when().get("users/5")
                .then().log().ifValidationFails()
                .assertThat().time(lessThan(800L));
    }
    @Test
    public void CheckUserSchema()
    {
        given().baseUri(base)
                .when().get("users/5")
                .then().log().ifValidationFails()
                .assertThat().body(matchesJsonSchemaInClasspath("single-user-schema.json"));
    }
    @Test
    public void CheckResData()
    {
        given().baseUri(base)
                .when().get("users/5")
                .then().log().ifValidationFails()
                .assertThat().body("id",equalTo(5));
    }
}
