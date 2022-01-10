package net.garethrogers.str8ur.servers

import io.netty.channel.ChannelInitializer
import io.netty.channel.socket.SocketChannel
import io.netty.handler.codec.http.HttpServerCodec
import io.netty.handler.codec.http.HttpObjectAggregator
import io.netty.handler.codec.http.HttpContentCompressor
import net.garethrogers.str8ur.routers.Router

class HttpPipelineInitializer(router: Router) extends ChannelInitializer[SocketChannel]:
  override def initChannel(ch: SocketChannel): Unit =
    val pipeline = ch.pipeline
    pipeline.addLast("codec", HttpServerCodec())
    pipeline.addLast("aggregator", HttpObjectAggregator(512 * 1024))
    pipeline.addLast("compressor", HttpContentCompressor())
    pipeline.addLast("myhandler", HttpRequestHandler(router))
