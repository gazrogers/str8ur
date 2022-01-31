package net.garethrogers.str8ur.routers

import net.garethrogers.str8ur.HttpRequest
import net.garethrogers.str8ur.HttpResponse
import scala.collection.mutable.HashMap
import scala.collection.immutable.Queue

trait OtherRouter extends Router:
  println("Top level OtherRouter code")
  
  def getRouteFor(uri: String): HttpRequest => HttpResponse =
    (_: HttpRequest) => HttpResponse(404, HashMap[String, Queue[String]](), "")
