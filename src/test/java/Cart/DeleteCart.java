package Cart;

import org.testng.annotations.Test;

import static Cart.AllCartTest.base;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class DeleteCart {
    @Test
    public void CheckStatusCode()
    {
        given().baseUri(base)
                .when().delete("carts/7")
                .then().log().ifValidationFails()
                .assertThat().statusCode(200);

    }


    @Test
    public void CheckIdNotEmpty()
    {
        given().baseUri(base)
                .when().delete("carts/7")
                .then().log().ifValidationFails()
                .assertThat().body("" , is(not(empty())));

    }
    public void CheckReqSize()
    {
        given().baseUri(base)
                .when().delete("carts/7")
                .then().log().ifValidationFails()
                .assertThat().body("id.size()" , equalTo(1) );
    }

    @Test
    public void CheckResTime()
    {
        given().baseUri(base)
                .when().delete("carts/7")
                .then().log().ifValidationFails()
                .assertThat().time(lessThan(800L));
    }

}

