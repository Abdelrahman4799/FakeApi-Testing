package Cart;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static Cart.AllCartTest.base;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class SortedCartsTest {


    @Test
    public void CheckStatusCode()
    {
        given().baseUri(base).queryParam("sort" , "desc")
                .when().get("carts")
                .then().log().ifValidationFails()
                .assertThat().statusCode(200);
    }
    @Test
    public void CheckResponseTime()
    {
        given().baseUri(base).queryParam("sort" , "desc")
                .when().get("carts")
                .then().log().ifValidationFails()
                .assertThat().time(lessThan(800L));
    }
    @Test
    public void CheckReqFields()
    {
        given().baseUri(base).queryParam("sort" , "desc")
                .when().get("carts")
                .then().log().ifValidationFails()
                .assertThat().body("",everyItem(hasKey("id"))).and()
                .assertThat().body("",everyItem(hasKey("userId"))).and()
                .assertThat().body("",everyItem(hasKey("date"))).and()
                .assertThat().body("",everyItem(hasKey("products")));
    }

    @Test
    public void CheckOrder()
    {
       Response res =  given().baseUri(base).queryParam("sort" , "desc")
                .when().get("carts")
                .then().extract().response();

        given().baseUri(base).queryParam("sort" , "desc")
                .when().get("carts")
                .then().log().ifValidationFails()
                .assertThat().body("[0].id",greaterThan(res.path("[1].id"))).and()
                .assertThat().body("[1].id",greaterThan(res.path("[2].id"))).and()
                .assertThat().body("[2].id",greaterThan(res.path("[3].id"))).and()
                .assertThat().body("[3].id",greaterThan(res.path("[4].id"))).and()
                .assertThat().body("[4].id",greaterThan(res.path("[5].id")));
    }



}
