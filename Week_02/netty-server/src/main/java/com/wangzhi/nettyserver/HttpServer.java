package com.wangzhi.nettyserver;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.EpollChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.util.SelfSignedCertificate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.security.cert.CertificateException;

public class HttpServer {

    private static Logger logger = LoggerFactory.getLogger(HttpServer.class);

    private boolean ssl;

    private Integer port;

    public HttpServer(boolean ssl, Integer port) {
        this.port = port;
        this.ssl = ssl;
    }

    public void run() throws Exception {
        final SslContext sslContext;
        if (ssl) {
            // 测试目的的SSL证书
            SelfSignedCertificate selfSignedCertificate = new SelfSignedCertificate();
            sslContext = SslContext.newServerContext(selfSignedCertificate.certificate(), selfSignedCertificate.privateKey());
        } else {
            sslContext = null;
        }
        // 调用用户的handler， 逻辑隔离 NIO Acceptor和 NIO I/O线程
        EventLoopGroup bossGroup = new NioEventLoopGroup(3);
        EventLoopGroup workGroup = new NioEventLoopGroup(1000);
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.option(ChannelOption.SO_BACKLOG, 128)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .option(ChannelOption.SO_REUSEADDR, true)
                    .option(ChannelOption.SO_RCVBUF, 32 * 1024)
                    .option(ChannelOption.SO_SNDBUF, 32 * 1024)
                    .option(EpollChannelOption.SO_REUSEPORT, true)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);
            bootstrap.group(bossGroup, workGroup).channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO)).childHandler(new HttpInitializer(sslContext));
            Channel ch = bootstrap.bind(port).sync().channel();
            logger.info("开启netty http服务器，监听地址和端口为 " + (ssl ? "https" : "http") + "://127.0.0.1:" + port + '/');
            ch.closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }


    }
}
