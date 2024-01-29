package com.ichwan.books.api;

import com.ichwan.api.dto.PersonRequest;
import com.ichwan.api.resource.PersonResource;
import com.ichwan.api.service.PersonService;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectSpy;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@QuarkusTest
@TestHTTPEndpoint(PersonResource.class)
public class PersonResourceTest {

    @InjectSpy
    PersonService personService;

    @Test
    void testGetPersonsEndpoint() {
        given().when().get()
                .then()
                .statusCode(200);

        Mockito.verify(personService, Mockito.times(1)).getAll();
    }

    @Test
    void testGetPersonByIdEndpoint() {
        given()
                .pathParam("id", 1L)
                .when().get("{id}")
                .then()
                .body("firstname", equalTo("Ichwan"))
                .statusCode(200);

        Mockito.verify(personService, Mockito.times(1)).getById(1L);
    }

    @Test
    void testCreatePersonEndpoint() {
        given().when().post()
                .then().statusCode(200);

        Mockito.verify(personService, Mockito.times(1))
                .create(new PersonRequest("Ahmad", "Abdullah", "Metro"));
    }
}
