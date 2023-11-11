package Cart;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import static Cart.AllCartTest.base;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class CartsInDateRangeTest {
    SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
    Response res =  given().baseUri(base).queryParam("sort" , "desc")
            .when().get("carts")
            .then().extract().response();
    List<String> dates = res.path("date");
    @Test
    public void CheckStatusCode()
    {
        given().baseUri(base).queryParams("startdate" , "2019-12-10"  , "enddate","2020-10-10")
                .when().get("carts")
                .then().log().ifValidationFails()
                .assertThat().statusCode(200);
    }
    @Test
    public void CheckResponseTime()
    {
        given().baseUri(base).queryParams("startdate" , "2019-12-10"  , "enddate","2020-10-10")
                .when().get("carts")
                .then().log().ifValidationFails()
                .assertThat().time(lessThan(800L));
    }
    @Test
    public void CheckReqFields()
    {
        given().baseUri(base).queryParams("startdate" , "2019-12-10"  , "enddate","2020-10-10")
                .when().get("carts")
                .then().log().ifValidationFails()
                .assertThat().body("",everyItem(hasKey("id"))).and()
                .assertThat().body("",everyItem(hasKey("userId"))).and()
                .assertThat().body("",everyItem(hasKey("date"))).and()
                .assertThat().body("",everyItem(hasKey("products")));
    }


    @Test
    public void CheckDateRange() throws ParseException {
        for (String date : dates) {
            assert (sdformat.parse(date).after(sdformat.parse("2019-12-10")));
            assert (sdformat.parse(date).before(sdformat.parse("2020-10-10")));

        }
    }

}
