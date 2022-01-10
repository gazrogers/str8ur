package net.garethrogers.exampleapp

import net.garethrogers.str8ur.Str8urApp
import net.garethrogers.str8ur.servers.NettyHttpServer
import net.garethrogers.str8ur.routers.DefaultRouter
import net.garethrogers.str8ur.Controller

class TestController(val x: Int) extends Controller

class Example(port: Int) extends Str8urApp(port) with NettyHttpServer with DefaultRouter:
  println("Top level Example code")
  addController(TestController(8))

object Example:
  @main def main(args: String*) =
    val port =
      if args.length > 0 then
        args(0).toInt
      else
        8080

    new Example(port)
