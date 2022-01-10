package net.garethrogers.str8ur.servers

import net.garethrogers.str8ur.routers.Router

trait Server:
  println("Top level server code")
  def start(port: Int, router: Router): Unit
