package io.zippopotam;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RequestAndResponseSpecTests extends BaseTest {

    @Test
    public void requestZipCodeHasSize_expectNumberOfPlaceNames() {

        given().
                spec(requestSpec).
        when().
                get("us/90210").
        then().
                spec(responseSpec).
        and().
                assertThat().
                body("places.'place name'", hasSize(1));
    }

    @Test
    public void requestZipCodeHasSize_extractPlaceNameAndAssert() {

        String placeName =

        given().
                spec(requestSpec).
        when().
                get("us/90210").
        then().
                extract().
                path("places[0].'place name'");

        assertEquals("Beverly Hills", placeName);
    }
}
