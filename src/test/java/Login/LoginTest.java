package Login;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.*;
import static Cart.AllCartTest.base;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;

public class LoginTest {
    File body = new File("src/test/resources/Login.json");
    @Test
    public void CheckStatusCode()
    {
        given().baseUri(base).contentType(ContentType.JSON)
                .body(body)
                .when().post("auth/login")
                .then().log().ifValidationFails()
                .assertThat().statusCode(200);
    }
    @Test
    public void CheckResTime()
    {
        given().baseUri(base)
                .contentType(ContentType.JSON)
                .body(body)
                .when().post("auth/login")
                .then().log().ifValidationFails()
                .assertThat().time(lessThan(800L));
    }
    @Test
    public void CheckResSize()
    {
        given().baseUri(base)
                .contentType(ContentType.JSON)
                .body(body)
                .when().post("auth/login")
                .then().log().ifValidationFails()
                .assertThat().body("size()",greaterThan(0));
    }
}
