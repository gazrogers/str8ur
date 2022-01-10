package net.garethrogers.str8ur.servers

import io.netty.bootstrap.ServerBootstrap
import io.netty.channel.ChannelOption
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.nio.NioServerSocketChannel
import net.garethrogers.str8ur.routers.Router

trait NettyHttpServer extends Server:
  def start(port: Int, router: Router): Unit =
    val bossGroup = NioEventLoopGroup()
    val workerGroup = NioEventLoopGroup()
    try
      val b = ServerBootstrap()
      b.group(bossGroup, workerGroup)
        .channel(classOf[NioServerSocketChannel])
        .childHandler(HttpPipelineInitializer(router))
        .option(ChannelOption.SO_BACKLOG, 128)
        .childOption(ChannelOption.SO_KEEPALIVE, true)

      val f = b.bind(port).sync
      f.channel.closeFuture.sync
    finally
      workerGroup.shutdownGracefully()
      bossGroup.shutdownGracefully()
