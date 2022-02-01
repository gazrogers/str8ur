package net.garethrogers.exampleapp.controllers

import net.garethrogers.str8ur.HttpRequest
import net.garethrogers.str8ur.HttpResponse
import net.garethrogers.str8ur.Controller
import scala.collection.mutable.HashMap
import scala.collection.immutable.Queue

class TestController1 extends Controller:
  def test(param1: String, param2: String, param3: String)(req: HttpRequest): String = s"${param1}"
