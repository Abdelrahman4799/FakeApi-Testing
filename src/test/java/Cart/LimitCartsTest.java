package Cart;

import org.testng.annotations.Test;

import static Cart.AllCartTest.base;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class LimitCartsTest {

    @Test
    public void CheckStatusCode()
    {
        given().baseUri(base).queryParam("limit" , 5)
                .when().get("carts")
                .then().log().ifValidationFails()
                .assertThat().statusCode(200);
    }
    @Test
    public void CheckResponseTime()
    {
        given().baseUri(base).queryParam("limit" , 5)
                .when().get("carts")
                .then().log().ifValidationFails()
                .assertThat().time(lessThan(800L));
    }

    @Test
    public void CheckReqFields()
    {
        given().baseUri(base).queryParam("limit" , 5)
                .when().get("carts")
                .then().log().ifValidationFails()
                .assertThat().body("",everyItem(hasKey("id"))).and()
                .assertThat().body("",everyItem(hasKey("userId"))).and()
                .assertThat().body("",everyItem(hasKey("date"))).and()
                .assertThat().body("",everyItem(hasKey("products")));
    }


    //test fails :: returned 7 items when request 0
    @Test
    public void CheckLimitZero()
    {
        given().baseUri(base).queryParam("limit" , 0)
                .when().get("carts")
                .then().log().all()
                .assertThat().body("size()", is(0));
    }


}
