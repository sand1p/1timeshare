package controllers

import java.util.Date

import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.libs.json.Json
import play.api.test.Helpers._
import play.api.test._
import stubs.StubSecretService

class SecretControllerTest extends PlaySpec with GuiceOneAppPerTest {

  def shouldReturnPasswordForValidLink() = {
    "controllers.SecreteController GET " should {
      "returns secret for given valid key" in {
        val secretController = new SecretController(stubControllerComponents(), new StubSecretService)

        val response = secretController.get("sdecfgbhjmkdsdfgbhjmk").apply(FakeRequest(GET, "/secret"))

        status(response) mustBe OK
        contentAsString(response) mustBe """{"password" : "password"}"""
      }
    }
  }

  def shouldReturnValidLinkToSharePassword() = {
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
        status(response) mustBe OK
      }
    }
  }
  shouldReturnPasswordForValidLink()
  shouldReturnValidLinkToSharePassword()

}

