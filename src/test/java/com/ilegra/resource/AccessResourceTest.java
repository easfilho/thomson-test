package com.ilegra.resource;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.MediaType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;

@QuarkusTest
@QuarkusTestResource(H2DatabaseTestResource.class)
public class AccessResourceTest {

    @Test
    public void shouldInsertLog() {
        given()
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(
                    "{" +
                        "\"log\": \"/pets/exotic/cats/10 1037825323957 5b019db5-b3d0-46d2-9963-437860af707f 1\"" +
                    "}")
                .when()
                .post("/laar/ingest")
                .then()
                .statusCode(201)
                .body("id", CoreMatchers.is(greaterThan(0)))
                .body("url", CoreMatchers.is("/pets/exotic/cats/10"))
                .body("dataVisited", CoreMatchers.is("2002-11-20T18:48:43.957"))
                .body("userId", CoreMatchers.is("5b019db5-b3d0-46d2-9963-437860af707f"))
                .body("region", CoreMatchers.is("us-east-1"));
    }
}