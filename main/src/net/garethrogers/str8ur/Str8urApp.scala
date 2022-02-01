package net.garethrogers.str8ur

import net.garethrogers.str8ur.Config
import scala.collection.mutable.HashMap
import scala.collection.immutable.Queue

abstract class Str8urApp(val port: Int, config: Config):
  config.router.addControllers(config.controllers)
  config.server.start(port, this.asInstanceOf[Str8urApp])

  def handleRequest(request: HttpRequest): HttpResponse =
    config.router.getRouteFor(request) match
      case res: HttpResponse => res
      case str: String => HttpResponse(200, HashMap[String, Queue[String]](), str)
