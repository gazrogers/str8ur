package net.garethrogers.exampleapp

import net.garethrogers.exampleapp.controllers.*

import net.garethrogers.str8ur.Str8urApp
import net.garethrogers.str8ur.servers.NettyHttpServer
import net.garethrogers.str8ur.routers.DefaultRouter

class Example(port: Int) extends Str8urApp(port) with NettyHttpServer with DefaultRouter:
  def initApp = 
    addControllers(
      TestController1(),
      TestController2(4)
    )

object Example:
  @main def main(args: String*) =
    val port =
      if args.length > 0 then
        args(0).toInt
      else
        8080

    Example(port)
