package net.garethrogers.str8ur.servers

import net.garethrogers.str8ur.Str8urApp

trait Server:
  def start(port: Int, app: Str8urApp): Unit =
    println("If you are seeing this message, you need to add a server trait to your application")
