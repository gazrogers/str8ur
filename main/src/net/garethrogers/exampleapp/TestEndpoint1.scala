package net.garethrogers.exampleapp

import net.garethrogers.str8ur.HttpResponse
import net.garethrogers.str8ur.EndpointHandler

object TestEndpoint1 extends EndpointHandler:
  def endpoint = "/"

  override def get(params: String*) = "Hello world"
