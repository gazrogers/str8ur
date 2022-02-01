package net.garethrogers.exampleapp

import net.garethrogers.exampleapp.controllers.*
import net.garethrogers.exampleapp.MyConfig

import net.garethrogers.str8ur.Config
import net.garethrogers.str8ur.Str8urApp

class Example(port: Int, config: Config) extends Str8urApp(port, config)

object Example:
  @main def main(args: String*) =
    val port =
      if args.length > 0 then
        args(0).toInt
      else
        8080

    Example(port, MyConfig())
