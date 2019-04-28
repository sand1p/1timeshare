package controllers

import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.test.Helpers._
import play.api.test._
import stubs.StubSecretService

class SecretControllerSpecGetAPITest extends PlaySpec with GuiceOneAppPerTest {

  def validGetSecreteControllerCallReturnsPassword() = {
    "controllers.SecreteController GET " should {
      "returns secret for given valid key" in {
        val secretController = new SecretController(stubControllerComponents(), new StubSecretService)

        val response = secretController.get("sdecfgbhjmkdsdfgbhjmk").apply(FakeRequest(GET, "/secret"))

        status(response) mustBe OK
        contentAsString(response) mustBe """{"password" : "password"}"""
      }
    }
  }

  validGetSecreteControllerCallReturnsPassword()

}

