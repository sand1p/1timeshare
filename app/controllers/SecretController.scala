package controllers

import akka.util.ByteString
import javax.inject.{Inject, Singleton}
import model.Secret
import play.api.Logger
import play.api.libs.json.{Format, JsValue, Json}
import play.api.mvc._
import service.SecretService
import play.api.http.HttpEntity

/**
  *
  * @param cc => CotrollerComponents such as bodyParsers, ActionsBuilders, ExecutionContexts are passed in this
  *           Abstract controller extends BaseController which deals with Results, HTTP protocol , contentTypes Headers.
  */
@Singleton
class SecretController @Inject()(cc: ControllerComponents, secretService: SecretService) extends AbstractController(cc) {

  implicit val secretFormat: Format[Secret] = Json.format[Secret]

  def save = Action(parse.json) { request: Request[JsValue] =>
    Logger.debug(s" Request body : ${request.body}")
    val secretOptional: Option[Secret] = request.body.asOpt[Secret]
    secretOptional match {
      case Some(secret) =>
        val key = secretService.save(secret)
        val link = s"http://${request.host}${request.uri}$key"
        Logger.debug(s"Password share link : [ $link ]")
        Result(header = ResponseHeader(200, Map.empty), body = HttpEntity.Strict(ByteString(s"""{"link" : "$link"}"""), Some("application/json")) )
      case None =>
        Logger.error(s" Request is invalid : [ Bad Request ]")
        Result(header = ResponseHeader(400, Map.empty), body = HttpEntity.Strict(ByteString(s"""{"result" : "Invalid request"}"""), Some("application/json")) )
    }
  }

  def get(key: String) = Action { implicit request =>
    secretService.get(key) match {
      case Some(sec) =>
        Logger.debug("Sharing password")
        Result(header = ResponseHeader(200, Map.empty), body = HttpEntity.Strict(ByteString(s"""{"password" : "${sec.value}"}"""), Some("application/json")) )
      case None =>
        Logger.debug("Link expired")
        Result(header = ResponseHeader(404, Map.empty), body = HttpEntity.Strict(ByteString(s"""{"result" : "Link is no longer valid."}"""), Some("application/json")) )
    }
  }
}
