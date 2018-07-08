package controllers

import akka.stream.Materializer
import akka.util.ByteString
import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.http.HeaderNames
import play.api.libs.json.{JsString, JsValue, Json}
import play.api.test.Helpers._
import play.api.test._
import service.SecreteService


class SecreteControllerSpec extends PlaySpec with GuiceOneAppPerTest with Injecting {
  implicit lazy val materializer: Materializer = app.materializer

  "model.Secrete Controller GET " should {
    "should return secrete for given key" in {
      val secreteController = new SecreteController(stubControllerComponents(), new StubSecreteService)

      val response = secreteController.get("sdecfgbhjmkdsdfgbhjmk").apply(FakeRequest(GET, "/secrete"))

      status(response) mustBe OK
      contentAsString(response) mustBe "password"
    }
  }

  class StubSecreteService extends SecreteService(null) {
    override def save(secrete: Option[String]): String = "sdecfgbhjmkdsdfgbhjmk"

    override def get(key: String): Option[String] = Option("password")
  }

}

