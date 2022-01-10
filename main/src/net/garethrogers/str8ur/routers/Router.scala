package net.garethrogers.str8ur.routers

import net.garethrogers.str8ur.HttpRequest
import net.garethrogers.str8ur.HttpResponse

trait Router:
  println("Top level Router code")
  
  def getRouteFor(uri: String): HttpRequest => HttpResponse
