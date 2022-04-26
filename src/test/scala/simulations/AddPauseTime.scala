package simulations

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration.DurationInt

class AddPauseTime extends Simulation{

  val httpConf = http.baseUrl("http://localhost:8082/app/")
    .header("Accept", "application/json")

  val scn = scenario("Video Game DB - 3 calls")
    .exec(http("Get all video games - 1st call")
    .get("videogames"))
    .pause(5)

    .exec(http("Get specific game")
    .get("videogames/1"))
    .pause(1, 20) // randomowa pausa pomiedzy 1 i 20 secund

    .exec(http("Get all video games - 2nd call")
    .get("videogames"))
    .pause(3000.milliseconds)

  setUp(
    scn.inject(atOnceUsers(1))
  ).protocols(httpConf)

}
