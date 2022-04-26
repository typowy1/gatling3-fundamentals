import io.gatling.core.Predef._
import io.gatling.http.Predef._

class MyFirstTest extends Simulation{

//  1 http config
  val httpConf = http.baseUrl("http://localhost:8082/app/")
    .header("Accept", "application/json")
    .proxy(Proxy("localhost", 8082))

//  2 scenario definition
  val scn = scenario("My first test")
  .exec(http("Get All Games")
    .get("videogames"))

//  3 load scenario
  setUp(
    scn.inject(atOnceUsers(users = 1))
      .protocols(httpConf)
  )
}
