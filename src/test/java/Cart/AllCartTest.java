package Cart;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
//aaaaaaaaaaaaahaaaa
public class AllCartTest {
    public static String base = "https://fakestoreapi.com/";

    @Test
    public void CheckStatusCode()
    {
        given().baseUri(base)
                .when().get("carts")
                .then().log().ifValidationFails()
                .assertThat().statusCode(200);

    }

    @Test
    public void CheckIdNotEmpty()
    {
        given().baseUri(base)
                .when().get("carts")
                .then().log().ifValidationFails()
                .assertThat().body("id" , everyItem(is(not(empty()))));

    }
    @Test
    public void CheckIdNotNegative()
    {
        given().baseUri(base)
                .when().get("carts")
                .then().log().ifValidationFails()
                .assertThat().body("id" , everyItem(is(greaterThan(0))));

    }
    @Test
    public void CheckEveryItemHasId()
    {
        given().baseUri(base)
                .when().get("carts")
                .then().log().ifValidationFails()
                .assertThat().body("" , everyItem(hasKey("id")));

    }
    @Test
    public void CheckReqSize()
    {
        given().baseUri(base)
                .when().get("carts")
                .then().log().ifValidationFails()
                .assertThat().body("id.size()" , equalTo(7) );
    }

    @Test
    public void CheckResTime()
    {
        given().baseUri(base)
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
}
