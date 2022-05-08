package net.garethrogers.exampleapp

import net.garethrogers.str8ur.Str8urApp

object Example extends Str8urApp:
  @main def main(args: String*) =
    // setup configuration stuff

    // then tell the framework to start
    startServer()
