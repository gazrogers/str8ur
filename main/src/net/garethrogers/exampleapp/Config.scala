package net.garethrogers.exampleapp

import net.garethrogers.exampleapp.controllers.TestController1
import net.garethrogers.exampleapp.controllers.TestController2
import net.garethrogers.str8ur.Config
import net.garethrogers.str8ur.Controller
import scala.collection.mutable.ListBuffer

class MyConfig extends Config:
  override val controllers = ListBuffer[Controller](
    TestController1(),
    TestController2(4)
  )
