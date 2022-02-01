package net.garethrogers.str8ur.routers

import net.garethrogers.str8ur.HttpRequest
import net.garethrogers.str8ur.HttpResponse

trait Router:
  def initRouter: Unit
  def getRouteFor(request: HttpRequest): HttpResponse | String
