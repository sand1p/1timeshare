package controllers

import javax.inject.{Inject, Singleton}
import model.Secret
import play.Logger
import play.api.libs.json.{JsValue, _}
import play.api.mvc._
import service.SecretService

/**
  *
  * @param cc => CotrollerComponents such as bodyParsers, ActionsBuilders, ExecutionContexts are passed in this
  *           Abstract controller extends BaseController which deals with Results, HTTP protocol , contentTypes Headers.
  */
@Singleton
class SecretController @Inject()(cc: ControllerComponents, secretService: SecretService) extends AbstractController(cc) {

  implicit val secretFormat: Format[Secret] = Json.format[Secret]

  def save = Action(parse.json) { request: Request[JsValue] =>
    val secretOptional: Option[Secret] = request.body.asOpt[Secret]
    secretOptional match {
      case Some(secret) =>
        val key = secretService.save(secret)
        val link = s"http://${request.host}${request.uri}$key"
        Logger.debug(s"Password share link : [ $link ]")
        Ok(link)
      case None =>
        Logger.debug(s" Request is invalid : [ Bad Request ]")
        BadRequest
    }
  }

  def get(key: String) = Action { implicit request =>
    secretService.get(key) match {
      case Some(sec) =>
        Logger.debug("Sharing password")
        Ok(sec.value)
      case None =>
        Logger.debug("Link expired")
        NotFound
    }
  }
}
