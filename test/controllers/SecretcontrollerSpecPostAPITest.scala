package controllers

import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.test.Helpers._
import play.api.test._
import stubs.StubSecretService
import java.util.Date

import akka.stream.Materializer
import play.api.libs.json.Json

class SecretcontrollerSpecPostAPITest extends PlaySpec with GuiceOneAppPerTest {
  implicit lazy val materializer: Materializer = app.materializer

  def validPostSecretControllerRequestReturnsValidLink() = {
    "controllers.SecretController.save POST" should {
      "return valid link for password" in {

        val secreteController = new SecretController(stubControllerComponents(), new StubSecretService)
        val date = new Date().getTime
        val password: String = "something"
        val jsonBody: String =
          s"""{
             |   "value": "abc",
             |   "expireAt" : $date
             | }
             """.stripMargin

        val response = secreteController.save().apply(FakeRequest(POST, "/secrete/").withBody(Json.parse(jsonBody)))
        status(response) mustBe (OK)
      }
    }
  }

  validPostSecretControllerRequestReturnsValidLink()

}