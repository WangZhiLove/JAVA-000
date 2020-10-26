package com.wangzhi.nettyserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*@SpringBootApplication*/
public class NettyServerApplication {

	public static void main(String[] args) {
		HttpServer server = new HttpServer(false,8088);
		try {
			server.run();
		}catch (Exception ex){
			ex.printStackTrace();
		}
	}

}
