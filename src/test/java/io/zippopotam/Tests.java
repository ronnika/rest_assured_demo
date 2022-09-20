package io.zippopotam;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Tests {

    @Test
    public void requestZipCodeExpectBeverlyHills() {

        given().
        when().
                get("http://api.zippopotam.us/us/90210").
        then().
            assertThat().
                body("places[0].'place name'", equalTo("Beverly Hills"));
    }
}
