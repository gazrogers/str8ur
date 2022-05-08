package net.garethrogers.str8ur

import net.garethrogers.str8ur.HttpResponse
import net.garethrogers.str8ur.httperrors.MethodNotAllowed

trait EndpointHandler:
  def endpoint: String

  final def registerEndpoint(): Unit = RouteRegistry.registerRoute(endpoint, this)

  def get(params: String*): HttpResponse | String = MethodNotAllowed()
  def post(params: String*): HttpResponse | String = MethodNotAllowed()
  def put(params: String*): HttpResponse | String = MethodNotAllowed()
  def head(params: String*): HttpResponse | String = MethodNotAllowed()
  def options(params: String*): HttpResponse | String = MethodNotAllowed()
  def delete(params: String*): HttpResponse | String = MethodNotAllowed()
  def connect(params: String*): HttpResponse | String = MethodNotAllowed()
  def trace(params: String*): HttpResponse | String = MethodNotAllowed()
  def patch(params: String*): HttpResponse | String = MethodNotAllowed()
