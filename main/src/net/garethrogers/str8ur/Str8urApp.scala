package net.garethrogers.str8ur

import net.garethrogers.str8ur.servers.Server
import net.garethrogers.str8ur.routers.Router

class Str8urApp(port: Int):
  println("Top level Str8urApp code")
  this match
    case server: (Server & Router) => server.start(port, this.asInstanceOf[Router])
    case _: Server => println("OOPSIE: Your application seems to be missing a Router. Bad show.")
    case _: Router => println("OOPSIE: Your application seems to be missing a Server. Serverless is a myth. Please try again.")
    case _ => println("Dude. Did you even RTFM?") // Lol, there is no FM