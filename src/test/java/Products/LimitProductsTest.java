package Products;

import org.testng.annotations.Test;

import static Cart.AllCartTest.base;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

public class LimitProductsTest {
    @Test
    public void CheckStatusCode()
    {
        given().baseUri(base).queryParam("limit",5)
                .when().get("products")
                .then().log().ifValidationFails()
                .assertThat().statusCode(200);

    }
    @Test
    public void CheckResTime()
    {
        given().baseUri(base).queryParam("limit",5)
                .when().get("products")
                .then().log().ifValidationFails()
                .assertThat().time(lessThan(800L));
    }
    @Test
    public void CheckResSize()
    {
        given().baseUri(base).queryParam("limit",5)
                .when().get("products")
                .then().log().ifValidationFails()
                .assertThat().body("size()",equalTo(5));
    }
    @Test
    public void CheckProductsSchema()
    {
        given().baseUri(base).queryParam("limit",5)
                .when().get("products")
                .then().log().ifValidationFails()
                .assertThat().body(matchesJsonSchemaInClasspath("products-schema.json"));
    }
}
