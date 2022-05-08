package net.garethrogers.str8ur

import scala.collection.mutable.HashMap
import scala.collection.immutable.Queue

import collection.JavaConverters.asScalaSetConverter

import org.reflections.*
import org.reflections.scanners.Scanners.*;

import net.garethrogers.str8ur.servers.NettyHttpServer
import net.garethrogers.str8ur.httperrors.NotFound
import net.garethrogers.str8ur.Config

trait Str8urApp:
  val server = NettyHttpServer()
  val routes = RouteRegistry

  def handleRequest(request: HttpRequest): HttpResponse =
    routes.getRouteHandler(request.url) match
      case None => NotFound()
      case Some(handler) => 
        handler.get() match
          case res: HttpResponse => res
          case str: String => HttpResponse(200, HashMap[String, Queue[String]](), str)

  def startServer(): Unit =
    findAndRegisterEndpoints("net.garethrogers.exampleapp")
    // println(routes.routes)
    server.start(9000, this)

  private def findAndRegisterEndpoints(packageName: String): Unit =
    val reflections = Reflections(packageName)
    val endpoints = reflections.get(SubTypes.of(classOf[EndpointHandler]).asClass());
    endpoints.asScala.foreach(x => x.getField("MODULE$").get(null).asInstanceOf[EndpointHandler].registerEndpoint())
    // println(routes.routes)
