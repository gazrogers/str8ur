package net.garethrogers.str8ur.routers

import net.garethrogers.str8ur.Controller
import net.garethrogers.str8ur.HttpRequest
import net.garethrogers.str8ur.HttpResponse
import scala.collection.mutable.HashMap
import scala.collection.immutable.Queue
import java.lang.reflect.Method

trait RestRouter extends Router:
  type Route = (Controller, Method, Int)

  private lazy val routerControllerMap = HashMap.empty[String, Route]
  
  def getRouteFor(uri: String): HttpRequest => HttpResponse =
    (_: HttpRequest) => HttpResponse(404, HashMap[String, Queue[String]](), "")
