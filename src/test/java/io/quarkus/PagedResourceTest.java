package io.quarkus;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class PagedResourceTest {

    @Test
    public void testWithoutQueryParams() {
        when().get()
                .then().body("index", is(0))
                .and().body("size", is(20));
    }

    @Test
    public void testWithQueryParams() {
        given().queryParam("page", 5)
                .and().queryParam("size", 25)
                .when().get()
                .then().body("index", is(5))
                .and().body("size", is(25));
    }
}