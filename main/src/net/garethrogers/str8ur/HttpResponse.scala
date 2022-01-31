package net.garethrogers.str8ur

import scala.collection.mutable.HashMap
import scala.collection.immutable.Queue

class HttpResponse(val code: Int, val headers: HashMap[String, Queue[String]], val body: String)
