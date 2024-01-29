package com.ichwan.books.api;

import com.ichwan.api.dto.PersonRequest;
import com.ichwan.api.resource.PersonResource;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
@TestHTTPEndpoint(PersonResource.class)
public class PersonResourceTest {

    PersonRequest person;

    @BeforeEach
    void setUp() {
        person = new PersonRequest("Ichwan", "Sholihin", "Natar");
    }

    @Test
    void testGetPersonsEndpoint() {
        given().when().get()
                .then()
                .statusCode(200);
    }

    @Test
    void testGetPersonByIdEndpoint() {
        given()
                .pathParam("id", 1)
                .when().get("/{id}")
                .then()
                .statusCode(200);
    }
}
