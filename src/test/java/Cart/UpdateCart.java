package Cart;

import org.testng.annotations.Test;

import static Cart.AllCartTest.base;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UpdateCart {

    String body
            = "{       \n" +
            "     userId:10,\n" +
            "    date:2020-02-03,\n" +
            "    products:[{productId:5,quantity:1},{productId:1,quantity:5}]\n" +
            "}";

    @Test
    public void CheckStatusCode()
    {
        given().baseUri(base).body(body)
                .when().put("carts/7")
                .then().log().ifValidationFails()
                .assertThat().statusCode(200);

    }


    @Test
    public void CheckIdNotEmpty()
    {
        given().baseUri(base).body(body)
                .when().put("carts/7")
                .then().log().ifValidationFails()
                .assertThat().body("" , is(not(empty())));

    }
    public void CheckReqSize()
    {
        given().baseUri(base).body(body)
                .when().put("carts/7")
                .then().log().ifValidationFails()
                .assertThat().body("id.size()" , equalTo(1) );
    }

    @Test
    public void CheckResTime()
    {
        given().baseUri(base).body(body)
                .when().put("carts/7")
                .then().log().ifValidationFails()
                .assertThat().time(lessThan(800L));
    }

}
