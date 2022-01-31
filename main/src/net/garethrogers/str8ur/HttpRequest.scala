package net.garethrogers.str8ur

import scala.collection.mutable.HashMap
import scala.collection.immutable.Queue

//
// It might be useful to be able to match on different cases
// If not, I can take this extra stuff out later...
//

sealed trait HttpRequest(val url: String, val headers: HashMap[String, Queue[String]], val body: String)
case class HttpRequestNoBody(
  override val url: String,
  override val headers: HashMap[String, Queue[String]]
) extends HttpRequest(url, headers, "")
case class HttpRequestWithBody(
  override val url: String,
  override val headers: HashMap[String, Queue[String]],
  override val body: String
) extends HttpRequest(url, headers, body)
