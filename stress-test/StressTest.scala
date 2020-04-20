package computerdatabase

class StressTestThomsonReuters extends Simulation {

  val httpProtocol = http
    .baseUrl("http://localhost:8080")
    .doNotTrackHeader("1")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .acceptEncodingHeader("gzip, deflate")
    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:16.0) Gecko/20100101 Firefox/16.0")

  val headers = Map("Content-Type" -> "application/json")

  val scn = scenario("Thomson Reuters Stress Tests")
    .exec(http("Save log")
      .post("/v1/laar/ingest")
      .headers(headers)
      .body(StringBody(
        """{
        "log": "/pets/guaipeca/dogs/1 1037825323957 5b019db5-b3d0-46d2-9963-437860af707g 2"
        }""".stripMargin)))
    .pause(2)
    .exec(http("Get metrics")
      .get("/v1/laa/metrics?temporalGrouper=WEEK")
      .headers(headers))
  setUp(scn.inject(atOnceUsers(300)).protocols(httpProtocol))
}