package net.garethrogers.exampleapp

import net.garethrogers.str8ur.HttpResponse
import net.garethrogers.str8ur.EndpointHandler

object TestEndpoint2 extends EndpointHandler:
  def endpoint = "/bob"

  override def get(params: String*) = "BOBOBOBOB"
