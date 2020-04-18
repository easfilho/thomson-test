package com.ilegra.resource;

import com.ilegra.repository.LogRepository;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.core.MediaType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;

@QuarkusTest
@QuarkusTestResource(H2DatabaseTestResource.class)
public class AccessResourceTest {

    @ConfigProperty(name = "env")
    String env;

    @Inject
    private LogRepository logRepository;
    private static final String URL_SAVE_LOG = "/laar/ingest";

    @BeforeEach
    @Transactional
    void setUp() {
        if(!env.equals("tst")) {
            throw new RuntimeException("Wrong environment");
        }
        logRepository.deleteAll();
        RestAssured.defaultParser = Parser.JSON;
    }

    @Test
    public void shouldInsertLog() {
        given()
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body("{" +
                              "\"log\": \"/pets/exotic/cats/10 1587134941 5b019db5-b3d0-46d2-9963-437860af707f 1\"" +
                              "}"
                )
                .when()
                .post(URL_SAVE_LOG)
                .then()
                .statusCode(201)
                .body("id", CoreMatchers.is(greaterThan(0)))
                .body("url", CoreMatchers.is("/pets/exotic/cats/10"))
                .body("dataVisited", CoreMatchers.is("2020-04-17T11:49:01"))
                .body("userId", CoreMatchers.is("5b019db5-b3d0-46d2-9963-437860af707f"))
                .body("region", CoreMatchers.is("us-east-1"));
    }

    @Test
    void shouldValidateBlankLog() {
        String message = given()
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body("{" +
                              "\"log\": null" +
                              "}"
                )
                .when()
                .post(URL_SAVE_LOG)
                .then()
                .statusCode(400)
                .contentType(ContentType.JSON)
                .extract()
                .response()
                .jsonPath()
                .getString("parameterViolations.message[0]");

        Assertions.assertEquals("Log can not be empty", message);
    }

    @Test
    void shouldValidateNullBody() {
        String message = given()
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .when()
                .post(URL_SAVE_LOG)
                .then()
                .statusCode(400)
                .contentType(ContentType.JSON)
                .extract()
                .response()
                .jsonPath()
                .getString("parameterViolations.message[0]");

        Assertions.assertEquals("Body can not be null", message);
    }
}