package controllers

import javax.inject._
import play.api.mvc._
import play.mvc.Results.ok


@Singleton
class HomeController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  def index() = ok("Hello world")
}
