package Products;

import org.testng.annotations.Test;

import static Cart.AllCartTest.base;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

public class CategoryTest {
    @Test
    public void CheckStatusCode()
    {
        given().baseUri(base)
                .when().get("/products/category/jewelery")
                .then().log().ifValidationFails()
                .assertThat().statusCode(200);
    }
    @Test
    public void CheckResTime()
    {
        given().baseUri(base)
                .when().get("/products/category/jewelery")
                .then().log().ifValidationFails()
                .assertThat().time(lessThan(800L));
    }
    @Test
    public void CheckResData()
    {
        given().baseUri(base)
                .when().get("/products/category/jewelery")
                .then().log().ifValidationFails()
                .assertThat().body("category",hasItem("jewelery"));
    }
}
