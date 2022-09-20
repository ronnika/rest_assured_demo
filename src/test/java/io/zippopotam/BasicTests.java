package io.zippopotam;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class BasicTests {

    @Test
    public void requestZipCodeExpectBeverlyHills() {

        given().
        when().
                get("http://api.zippopotam.us/us/90210").
        then().
            assertThat().
                body("places[0].'place name'", equalTo("Beverly Hills"));
    }

    @Test
    public void requestZipCodeHasItem_expectBeverlyHills() {

        given().
                when().
                get("http://api.zippopotam.us/us/90210").
                then().
                assertThat().
                body("places.'place name'", hasItem("Beverly Hills"));
    }

    @Test
    public void requestZipCodeHasSize_expectNumberOfPlaceNames() {

        given().
                when().
                get("http://api.zippopotam.us/us/90210").
                then().
                assertThat().
                body("places.'place name'", hasSize(1));
    }

    @Test
    public void requestZipCodeExpect200() {

        given().
                when().
                get("http://api.zippopotam.us/us/90210").
                then().
                assertThat().
                statusCode(200);
    }

    @Test
    public void requestZipCodeContentType_expectJson() {

        given().
                when().
                get("http://api.zippopotam.us/us/90210").
                then().
                assertThat().
                contentType(ContentType.JSON);
    }

    @Test
    public void requestZipCode_logResponseAndRequest() {

        given().
                log().all().
                when().
                get("http://api.zippopotam.us/us/90210").
                then().
                log().body();
    }

}
