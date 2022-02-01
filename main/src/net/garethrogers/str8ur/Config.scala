package net.garethrogers.str8ur

import net.garethrogers.str8ur.servers.NettyHttpServer
import net.garethrogers.str8ur.routers.DefaultRouter
import net.garethrogers.str8ur.Controller
import scala.collection.mutable.ListBuffer

class Config:
  val server = NettyHttpServer()
  val router = DefaultRouter()

  val controllers = ListBuffer[Controller]()
