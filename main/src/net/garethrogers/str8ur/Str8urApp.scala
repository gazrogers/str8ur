package net.garethrogers.str8ur

import net.garethrogers.str8ur.servers.Server
import net.garethrogers.str8ur.routers.Router
import scala.collection.mutable.HashMap
import scala.collection.immutable.Queue

abstract class Str8urApp(val port: Int) extends Server with Router:
  initApp
  // initRouter
  start(port, this.asInstanceOf[Str8urApp])

  def initApp: Unit

  def handleRequest(request: HttpRequest): HttpResponse =
    getRouteFor(request) match
      case res: HttpResponse => res
      case str: String => HttpResponse(200, HashMap[String, Queue[String]](), str)
