package net.garethrogers.str8ur.routers

import net.garethrogers.str8ur.Controller
import net.garethrogers.str8ur.HttpRequest
import net.garethrogers.str8ur.HttpResponse
import scala.collection.mutable.HashMap

trait DefaultRouter extends Router:
  val controllers: HashMap[String, Controller] = HashMap[String, Controller]()

  def getRouteFor(uri: String): HttpRequest => HttpResponse =
    (_: HttpRequest) => println(uri);println(s"Y: ${controllers.contains(uri)}");HttpResponse()

  def addController(controller: Controller): Unit =
    controllers.addOne(classOf[Controller].getName, controller)
    println(controllers.toString)
