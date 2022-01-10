package net.garethrogers.str8ur

import scala.collection.mutable.HashMap
import scala.collection.immutable.Queue

//
// It might be useful to be able to match on different cases
// If not, I can take this extra stuff out later...
//

sealed trait HttpRequest
case class HttpRequestNoBody(url: String, headers: HashMap[String, Queue[String]]) extends HttpRequest
case class HttpRequestWithBody(url: String, headers: HashMap[String, Queue[String]], body: String) extends HttpRequest
