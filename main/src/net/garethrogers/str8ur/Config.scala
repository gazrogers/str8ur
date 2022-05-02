package net.garethrogers.str8ur

import net.garethrogers.str8ur.servers.NettyHttpServer
import net.garethrogers.str8ur.routers.ContrActRouter
import net.garethrogers.str8ur.Controller
import scala.collection.mutable.ListBuffer

class Config:
  val server = NettyHttpServer()
  val router = ContrActRouter()

  val controllers = ListBuffer[Controller]()
