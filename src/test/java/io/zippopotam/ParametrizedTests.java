package io.zippopotam;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ParametrizedTests {

    @CsvSource(value = {
            "us,90210,Beverly Hills",
            "us,90601,Whittier",
            "ca,B2R,Waverley",
    })
    @ParameterizedTest(name = "For country and zip expected place")
    public void requestZipCode_expectPlaceName(String country, String zipCode, String placeName) {
        given().
                pathParam("country", country).
                pathParam("zipCode", zipCode).
        when().
                get("http://api.zippopotam.us/{country}/{zipCode}").
        then().
                assertThat().
                body("places[0].'place name'", equalTo(placeName));
    }
}
