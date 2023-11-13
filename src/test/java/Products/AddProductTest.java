package Products;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.io.File;

import static Cart.AllCartTest.base;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.lessThan;

public class AddProductTest {
    File body = new File("src/test/resources/productData.json");
    @Test
    public void CheckStatusCode()
    {
        given().baseUri(base)
                .contentType(ContentType.JSON)
                .body(body)
                .when().post("products")
                .then().log().ifValidationFails()
                .assertThat().statusCode(200);

    }
    @Test
    public void CheckResTime()
    {
        given().baseUri(base)
                .contentType(ContentType.JSON)
                .body(body)
                .when().post("products")
                .then().log().ifValidationFails()
                .assertThat().time(lessThan(800L));
    }
    @Test
    public void CheckProductSchema()
    {
        given().baseUri(base)
                .contentType(ContentType.JSON)
                .body(body)
                .when().post("products")
                .then().log().ifValidationFails()
                .assertThat().body(matchesJsonSchemaInClasspath("single-product-schema.json"));
    }
}
