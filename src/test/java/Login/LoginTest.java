package Login;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.*;
import static Cart.AllCartTest.base;
import static org.hamcrest.Matchers.*;

public class LoginTest {
    File body = new File("src/test/resources/Login.json");
    File invalidBody = new File("src/test/resources/invalidLogin.json");

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

    @Test
    public void loginWithInvalid()
    {
        given().baseUri(base).contentType(ContentType.JSON)
                .body(invalidBody)
                .when().post("auth/login")
                .then().log().ifValidationFails()
                .assertThat().statusCode(equalTo(401))
                .and().body(containsString("incorrect"));
    }

    @Test
    public void loginWithEmpty()
    {
        File emptyUser = new File("src/test/resources/emptyLogin.json");


        given().baseUri(base).contentType(ContentType.JSON)
                .body(emptyUser)
                .when().post("auth/login")
                .then().log().ifValidationFails()
                .assertThat().statusCode(equalTo(400))
                .and().body(containsString("not provided"));
    }
}
