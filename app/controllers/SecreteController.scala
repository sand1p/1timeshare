package controllers

import javax.inject.{Inject, Singleton}
import model.Secrete
import play.api.libs.json.{JsValue, _}
import play.api.mvc._
import service.SecreteService

/**
  *
  * @param cc => CotrollerComponents such as bodyParsers, ActionsBuilders, ExecutionContexts are passed in this
  *           Abstract controller extends BaseController which deals with Results, HTTP protocol , contentTypes Headers.
  */
@Singleton
class SecreteController @Inject()(cc: ControllerComponents, secreteService: SecreteService) extends AbstractController(cc) {

  implicit val secreteFormat: Format[Secrete] = Json.format[Secrete]

  def save() = Action(parse.json) { request: Request[JsValue] =>
    val secrete: Option[Secrete] = request.body.asOpt[Secrete]
      println("sec " + secrete)
    val key = secreteService.save(secrete.map(a => a.value))
    val link = s"http://${request.host}${request.uri}$key"
    Ok(link)
  }

  def get(key: String) = Action { implicit request =>
    secreteService.get(key) match {
      case Some(sec) => Ok(sec)
      case None => NotFound
    }
  }
}
