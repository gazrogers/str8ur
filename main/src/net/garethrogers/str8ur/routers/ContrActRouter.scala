package net.garethrogers.str8ur.routers

import net.garethrogers.str8ur.Controller
import net.garethrogers.str8ur.HttpRequest
import net.garethrogers.str8ur.HttpResponse
import scala.collection.mutable.HashMap
import scala.collection.immutable.Queue
import java.lang.reflect.Modifier
import java.lang.reflect.Method
import net.garethrogers.str8ur.httperrors.NotFound
import scala.collection.mutable.ListBuffer

class ContrActRouter:
  type Route = (Controller, Method, Int)

  private lazy val routerControllerMap = HashMap.empty[String, Route]

  def getRouteFor(request: HttpRequest): HttpResponse | String =
    val (controller, action, params) = splitUrl(request.url)
    findRoute(controller, action).flatMap(route =>
      if route._3 > params.size then
        Left("Insufficient parameters")
      else
        Right(route)
    ) match
      case Right(foundRoute) => invokeRoute(foundRoute.asInstanceOf[Route], params, request)
      case _ => NotFound() // add logging of error here later

  private def findRoute(controller: String, action: String): Either[String, Route] = 
    val routeLocation = '/' + controller + '/' + action
    routerControllerMap.get(routeLocation) match
      case Some(x: Route) => Right(x)
      case _ => Left(s"Route '${routeLocation}' not found")

  private def splitUrl(url: String): (String, String, List[String]) = 
    url.split('/').toList.filter(_.nonEmpty) match
      case Nil => ("index", "index", List())
      case List(x) => (x, "index", List())
      case List(x, y) => (x, y, List())
      case List(x, y, z*) => (x, y, z.toList)

  private def invokeRoute(route: Route, params: List[String], request: HttpRequest): HttpResponse | String =
    val (controller, method, paramCount) = route
    val callParams = params.take(paramCount) :+ request
    method.invoke(controller, callParams: _*).asInstanceOf[HttpResponse | String]

  /** Scan the passed controllers for routes
   * 
   *  Scans the class for public methods which return an HttpResponse
   *  If all the parameters except the last are of type String, add it to routes
   * 
   *  @param passedControllers the controllers to scan for routes
   */
  def addControllers(passedControllers: => ListBuffer[Controller]): Unit =
    passedControllers.foreach(controller =>
      val classDef = controller.getClass
      val cName = classDef.getSimpleName.toLowerCase
      classDef.getDeclaredMethods.foreach(method =>
        if Modifier.isPublic(method.getModifiers)
            && (method.getReturnType == classOf[HttpResponse]
            || method.getReturnType == classOf[String]) then
          val routePrefix = '/' + cName + '/' + method.getName.toLowerCase
          val paramTypes = method.getParameterTypes.dropRight(1)
          if paramTypes.forall(_ == classOf[String]) then
            routerControllerMap.addOne(routePrefix, (controller, method, paramTypes.size))
          else
            println(paramTypes.foreach(println))
      )
    )
