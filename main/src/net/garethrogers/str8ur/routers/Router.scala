package net.garethrogers.str8ur.routers

import net.garethrogers.str8ur.HttpRequest
import net.garethrogers.str8ur.HttpResponse

trait Router:
  def initRouter: Unit =
    println("If you are seeing this message, you need to add a router trait to your application")

  def getRouteFor(request: HttpRequest): HttpResponse | String
