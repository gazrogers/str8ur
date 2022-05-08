package net.garethrogers.str8ur

import scala.collection.mutable.Map
import net.garethrogers.str8ur.EndpointHandler

private class RouteRegistry:
  val root = RegistryNode()
  override def toString() = root.toString

private class RegistryNode:
  // map of routes that end at this node
  val routes = Map[String, EndpointHandler]()
  // map of routes that have at least one more non-regex part
  val children = Map[String, RegistryNode]()

  def add(routeParts: List[String], handler: EndpointHandler): Unit =
    if routeParts.isEmpty then
      routes.update("", handler)
    else if routeParts.head.startsWith(":") then
      routes.update(routeParts.reduce((x:String, y: String) => s"$x/$y"), handler)
    else
      children.getOrElseUpdate(routeParts.head, RegistryNode()).add(routeParts.tail, handler)

  override def toString() = s"Routes: $routes \nChildren: $children"

object RouteRegistry:
  val routes = RouteRegistry()

  def registerRoute(route: String, handler: EndpointHandler): Unit =
    val routeParts = route.split("/").toList.filter(_.nonEmpty)
    routes.root.add(routeParts, handler)

  def getRouteHandler(route: String): Option[EndpointHandler] =
    val routeParts = route.split("/").toList.filter(_.nonEmpty)
    // Match as many fixed parts as possible
    val (endpoints, remainingRouteParts) = getRouteHandlerImpl(routeParts, routes.root)
    // filter out all where the route lengths don't match
    // TODO: allow some kind of catch-all so users can match all the rest of the route parts
    //       (maybe a flag in the EndpointHandler class?)
    val filteredEndpoints = endpoints.filter((endpoint, _) => (if endpoint.isEmpty then 0 else endpoint.split("/").size) == remainingRouteParts.size)
    if filteredEndpoints.isEmpty then
      None
    else
      println(filteredEndpoints)
      Some(filteredEndpoints.head._2)

  def getRouteHandlerImpl(routeParts: List[String], routeRegistry: RegistryNode): (Map[String, EndpointHandler], List[String]) =
    if routeRegistry.children.contains(if routeParts.isEmpty then "" else routeParts.head) then
      getRouteHandlerImpl(routeParts.tail, routeRegistry.children.get(routeParts.head).get)
    else
      (routeRegistry.routes, routeParts)



