package net.garethrogers.str8ur.servers

import io.netty.channel.SimpleChannelInboundHandler
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.http.FullHttpRequest
import io.netty.handler.codec.http.HttpUtil
import io.netty.handler.codec.http.HttpVersion
import io.netty.handler.codec.http.HttpResponseStatus
import io.netty.handler.codec.http.DefaultFullHttpResponse
import io.netty.buffer.Unpooled
import io.netty.util.CharsetUtil
import scala.jdk.CollectionConverters.*
import scala.collection.mutable.HashMap
import scala.collection.immutable.Queue
import net.garethrogers.str8ur.routers.Router
import net.garethrogers.str8ur.HttpRequestWithBody
import net.garethrogers.str8ur.HttpRequestNoBody

class HttpRequestHandler(router: Router) extends SimpleChannelInboundHandler[FullHttpRequest]:
  override def channelReadComplete(ctx: ChannelHandlerContext): Unit =
    ctx.flush

  override def channelRead0(ctx: ChannelHandlerContext, request: FullHttpRequest): Unit =
    if HttpUtil.is100ContinueExpected(request) then
      ctx.write(DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.CONTINUE))
    val headers = HashMap.empty[String, Queue[String]]
    request.headers.entries.asScala.foreach { requestHeader =>
      val tempVal = headers.getOrElse(requestHeader.getKey, Queue.empty[String])
      headers.update(requestHeader.getKey, tempVal.enqueue(requestHeader.getValue))
    }
    val myReq = if request.content.readableBytes > 0 then
      router.getRouteFor(request.uri)(HttpRequestWithBody(request.uri, headers, request.content.toString(CharsetUtil.UTF_8)))
      "l" // HttpRequestWithBody(request.uri, headers, request.content.toString(CharsetUtil.UTF_8))
    else
      router.getRouteFor(request.uri)(HttpRequestNoBody(request.uri, headers))
      "s" // HttpRequestNoBody(request.uri, headers)
    // Call into the framework here
    // val myResponse = str8ur(myReq)
    // Currently just spitting out the created internal request object
    val content = Unpooled.copiedBuffer(myReq.toString, CharsetUtil.UTF_8)
    val response = DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, content)
    ctx.writeAndFlush(response)
    ctx.close

  override def exceptionCaught(ctx: ChannelHandlerContext, cause: Throwable): Unit =
    cause.printStackTrace
    ctx.close
