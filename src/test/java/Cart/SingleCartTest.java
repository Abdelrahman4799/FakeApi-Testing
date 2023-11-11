package Cart;

import org.testng.annotations.Test;

import static Cart.AllCartTest.base;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class SingleCartTest {

    @Test
    public void CheckStatusCode()
    {
        given().baseUri(base)
                .when().get("carts/5")
                .then().log().ifValidationFails()
                .assertThat().statusCode(200);

    }

    @Test
    public void CheckIdNotEmpty()
    {
        given().baseUri(base)
                .when().get("carts/5")
                .then().log().ifValidationFails()
                .assertThat().body("id" , equalTo(5));

    }

    @Test
    public void CheckResponseTime()
    {
        given().baseUri(base)
                .when().get("carts/5")
                .then().log().ifValidationFails()
                .assertThat().time(lessThan(800L));
    }
    @Test
    public void CheckCartHasProducts()
    {
        given().baseUri(base)
                .when().get("carts/5")
                .then().log().ifValidationFails()
                .assertThat().body("products.size()",greaterThan(0));
    }
    @Test
    public void ValidateQuantity()
    {
        given().baseUri(base)
                .when().get("carts/5")
                .then().log().ifValidationFails()
                .assertThat().body("products.quantity",everyItem(greaterThan(0)));
    }

    @Test
    public void CheckReqFields()
    {
        given().baseUri(base)
                .when().get("carts/5")
                .then().log().ifValidationFails()
                .assertThat().body("",hasKey("id")).and()
                .assertThat().body("",hasKey("userId")).and()
                .assertThat().body("",hasKey("date")).and()
                .assertThat().body("",hasKey("products"));
    }
}
