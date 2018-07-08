package controllers

import java.util.Date

import akka.stream.Materializer
import model.Secret
import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.test.Helpers._
import play.api.test._
import service.SecretService


class SecretControllerSpec extends PlaySpec with GuiceOneAppPerTest with Injecting {
  implicit lazy val materializer: Materializer = app.materializer

  "model.Secrete Controller GET " should {
    "should return secret for given key" in {
      val secretController = new SecretController(stubControllerComponents(), new StubSecretService)

      val response = secretController.get("sdecfgbhjmkdsdfgbhjmk").apply(FakeRequest(GET, "/secret"))

      status(response) mustBe OK
      contentAsString(response) mustBe "password"
    }
  }

  class StubSecretService extends SecretService(null) {
    override def save(secret: Secret): String = "sdecfgbhjmkdsdfgbhjmk"

    override def get(key: String): Option[Secret] = Option(Secret("password", new Date))
  }

}

