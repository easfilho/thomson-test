package com.ilegra.resource;

import com.ilegra.enums.RegionEnum;
import com.ilegra.repository.LogEntity;
import com.ilegra.repository.LogRepository;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.path.json.JsonPath;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.core.MediaType;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static io.restassured.RestAssured.given;


@QuarkusTest
@QuarkusTestResource(H2DatabaseTestResource.class)
class MetricsResourceTest {

    @ConfigProperty(name = "env")
    String env;

    @Inject
    private LogRepository logRepository;

    @BeforeEach
    @Transactional
    void setUp() {
        if(!env.equals("tst")) {
            throw new RuntimeException("Wrong environment");
        }
        logRepository.deleteAll();
    }

    @Test
    public void shouldListMetrics() {
        insertData();
        JsonPath jsonPath = given()
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .when()
                .queryParam("temporalGrouper", "WEEK")
                .get("/laa/metrics")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .jsonPath();

        Assertions.assertEquals(1, jsonPath.getInt("metricUrlLessAccessed.quantity"));
        Assertions.assertEquals("/pets/exotic/parrots/10", jsonPath.getString("metricUrlLessAccessed.url"));

        Assertions.assertEquals(5, jsonPath.getInt("metricUrlMoreAccessedByTime.quantity"));
        Assertions.assertEquals("12:06", jsonPath.getString("metricUrlMoreAccessedByTime.time"));

        Assertions.assertEquals(9, jsonPath.getInt("topThreeUrlAccessedAroundWorld[0].quantity"));
        Assertions.assertEquals("/pets/exotic/cats/10", jsonPath.getString("topThreeUrlAccessedAroundWorld[0].url"));

        Assertions.assertEquals(4, jsonPath.getInt("topThreeUrlAccessedAroundWorld[1].quantity"));
        Assertions.assertEquals("/pets/exotic/dogs/10", jsonPath.getString("topThreeUrlAccessedAroundWorld[1].url"));

        Assertions.assertEquals(2, jsonPath.getInt("topThreeUrlAccessedAroundWorld[2].quantity"));
        Assertions.assertEquals("/pets/exotic/turtles/10", jsonPath.getString("topThreeUrlAccessedAroundWorld[2].url"));

        Assertions.assertEquals(9, jsonPath.getInt("topThreeUrlAccessedPerRegion[0].quantity"));
        Assertions.assertEquals("us-west-2", jsonPath.getString("topThreeUrlAccessedPerRegion[0].region"));

        Assertions.assertEquals(4, jsonPath.getInt("topThreeUrlAccessedPerRegion[1].quantity"));
        Assertions.assertEquals("us-east-1", jsonPath.getString("topThreeUrlAccessedPerRegion[1].region"));

        Assertions.assertEquals(3, jsonPath.getInt("topThreeUrlAccessedPerRegion[2].quantity"));
        Assertions.assertEquals("ap-south-1", jsonPath.getString("topThreeUrlAccessedPerRegion[2].region"));

        Assertions.assertEquals(3.0, jsonPath.getDouble("topThreeUrlAccessedPerTemporalParameter[0].averageAccess"));
        Assertions.assertEquals("/pets/exotic/cats/10", jsonPath.getString("topThreeUrlAccessedPerTemporalParameter[0].url"));

        Assertions.assertEquals(2.0, jsonPath.getDouble("topThreeUrlAccessedPerTemporalParameter[1].averageAccess"));
        Assertions.assertEquals("/pets/exotic/dogs/10", jsonPath.getString("topThreeUrlAccessedPerTemporalParameter[1].url"));

        Assertions.assertEquals(2.0, jsonPath.getDouble("topThreeUrlAccessedPerTemporalParameter[2].averageAccess"));
        Assertions.assertEquals("/pets/exotic/turtles/10", jsonPath.getString("topThreeUrlAccessedPerTemporalParameter[2].url"));
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void insertData() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        logRepository.persist(
                new LogEntity()
                        .setUrl("/pets/exotic/dogs/10")
                        .setDateVisited(LocalDateTime.parse("2020-04-17 11:49:01", formatter))
                        .setUserId("5b019db5-b3d0-46d2-9963-437860af707f")
                        .setRegionEnum(RegionEnum.AP_SOUTH_1)
        );

        logRepository.persist(
                new LogEntity()
                        .setUrl("/pets/exotic/turtles/10")
                        .setDateVisited(LocalDateTime.parse("2020-04-17 12:01:23", formatter))
                        .setUserId("5b019db5-b3d0-46d2-9963-437860af707f")
                        .setRegionEnum(RegionEnum.US_WEST_2)
        );

        logRepository.persist(
                new LogEntity()
                        .setUrl("/pets/exotic/cats/10")
                        .setDateVisited(LocalDateTime.parse("2020-04-17 10:05:23", formatter))
                        .setUserId("5b019db5-b3d0-46d2-9963-437860af707f")
                        .setRegionEnum(RegionEnum.US_WEST_2)
        );

        logRepository.persist(
                new LogEntity()
                        .setUrl("/pets/exotic/cats/10")
                        .setDateVisited(LocalDateTime.parse("2020-04-10 12:04:23", formatter))
                        .setUserId("5b019db5-b3d0-46d2-9963-437860af707f")
                        .setRegionEnum(RegionEnum.US_WEST_2)
        );

        logRepository.persist(
                new LogEntity()
                        .setUrl("/pets/exotic/cats/10")
                        .setDateVisited(LocalDateTime.parse("2020-04-10 12:03:23", formatter))
                        .setUserId("5b019db5-b3d0-46d2-9963-437860af707f")
                        .setRegionEnum(RegionEnum.US_WEST_2)
        );

        logRepository.persist(
                new LogEntity()
                        .setUrl("/pets/exotic/dogs/10")
                        .setDateVisited(LocalDateTime.parse("2020-04-09 12:05:23", formatter))
                        .setUserId("5b019db5-b3d0-46d2-9963-437860af707f")
                        .setRegionEnum(RegionEnum.AP_SOUTH_1)
        );

        logRepository.persist(
                new LogEntity()
                        .setUrl("/pets/exotic/dogs/10")
                        .setDateVisited(LocalDateTime.parse("2020-04-09 12:10:23", formatter))
                        .setUserId("5b019db5-b3d0-46d2-9963-437860af707f")
                        .setRegionEnum(RegionEnum.AP_SOUTH_1)
        );

        logRepository.persist(
                new LogEntity()
                        .setUrl("/pets/exotic/cats/10")
                        .setDateVisited(LocalDateTime.parse("2020-04-07 12:05:23", formatter))
                        .setUserId("5b019db5-b3d0-46d2-9963-437860af707f")
                        .setRegionEnum(RegionEnum.US_EAST_1)
        );

        logRepository.persist(
                new LogEntity()
                        .setUrl("/pets/exotic/cats/10")
                        .setDateVisited(LocalDateTime.parse("2020-04-07 12:04:23", formatter))
                        .setUserId("5b019db5-b3d0-46d2-9963-437860af707f")
                        .setRegionEnum(RegionEnum.US_EAST_1)
        );

        logRepository.persist(
                new LogEntity()
                        .setUrl("/pets/exotic/cats/10")
                        .setDateVisited(LocalDateTime.parse("2020-03-17 12:06:15", formatter))
                        .setUserId("5b019db5-b3d0-46d2-9963-437860af707f")
                        .setRegionEnum(RegionEnum.US_WEST_2)
        );

        logRepository.persist(
                new LogEntity()
                        .setUrl("/pets/exotic/cats/10")
                        .setDateVisited(LocalDateTime.parse("2020-03-17 12:06:15", formatter))
                        .setUserId("5b019db5-b3d0-46d2-9963-437860af707f")
                        .setRegionEnum(RegionEnum.US_WEST_2)
        );

        logRepository.persist(
                new LogEntity()
                        .setUrl("/pets/exotic/cats/10")
                        .setDateVisited(LocalDateTime.parse("2020-03-17 09:06:15", formatter))
                        .setUserId("5b019db5-b3d0-46d2-9963-437860af707f")
                        .setRegionEnum(RegionEnum.US_EAST_1)
        );

        logRepository.persist(
                new LogEntity()
                        .setUrl("/pets/exotic/cats/10")
                        .setDateVisited(LocalDateTime.parse("2020-03-17 09:06:15", formatter))
                        .setUserId("5b019db5-b3d0-46d2-9963-437860af707f")
                        .setRegionEnum(RegionEnum.US_EAST_1)
        );

        logRepository.persist(
                new LogEntity()
                        .setUrl("/pets/exotic/dogs/10")
                        .setDateVisited(LocalDateTime.parse("2020-04-17 12:06:15", formatter))
                        .setUserId("5b019db5-b3d0-46d2-9963-437860af707f")
                        .setRegionEnum(RegionEnum.US_WEST_2)
        );

        logRepository.persist(
                new LogEntity()
                        .setUrl("/pets/exotic/turtles/10")
                        .setDateVisited(LocalDateTime.parse("2020-04-17 12:06:15", formatter))
                        .setUserId("5b019db5-b3d0-46d2-9963-437860af707f")
                        .setRegionEnum(RegionEnum.US_WEST_2)
        );

        logRepository.persist(
                new LogEntity()
                        .setUrl("/pets/exotic/parrots/10")
                        .setDateVisited(LocalDateTime.parse("2020-04-17 12:06:15", formatter))
                        .setUserId("5b019db5-b3d0-46d2-9963-437860af707f")
                        .setRegionEnum(RegionEnum.US_WEST_2)
        );


//        2020-04-17 12:06:15	US_EAST_1	/pets/exotic/cats/10	5b019db5-b3d0-46d2-9963-437860af707f
//        2020-04-01 12:06:15	AP_SOUTH_1	/pets/exotic/parrots/10	5b019db5-b3d0-46d2-9963-437860af707f
//        2020-04-12 12:06:15	US_EAST_1	/pets/exotic/dogs/10	5b019db5-b3d0-46d2-9963-437860af707f
//        2020-04-17 12:06:15	US_EAST_1	/pets/exotic/turtles/10	5b019db5-b3d0-46d2-9963-437860af707f
//        2020-04-17 12:06:15	US_WEST_2	/pets/exotic/cats/10	5b019db5-b3d0-46d2-9963-437860af707f
//        2020-03-15 12:06:15	US_EAST_1	/pets/exotic/dogs/10	5b019db5-b3d0-46d2-9963-437860af707f
//        2020-04-17 12:06:15	AP_SOUTH_1	/pets/exotic/cats/10	5b019db5-b3d0-46d2-9963-437860af707f
//        2020-04-14 12:06:15	US_EAST_1	/pets/exotic/turtles/10	5b019db5-b3d0-46d2-9963-437860af707f
//        2020-04-17 12:06:15	US_WEST_2	/pets/exotic/dogs/10	5b019db5-b3d0-46d2-9963-437860af707f
//        2020-04-17 12:06:15	US_EAST_1	/pets/exotic/cats/10	5b019db5-b3d0-46d2-9963-437860af707f
//        2020-04-17 12:06:15	AP_SOUTH_1	/pets/exotic/turtles/10	5b019db5-b3d0-46d2-9963-437860af707f
    }


}