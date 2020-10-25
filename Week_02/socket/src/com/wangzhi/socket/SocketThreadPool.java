package com.wangzhi.socket;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * authors 王智
 * description 单线程的socket
 */
public class SocketThreadPool {
    public static void main(String[] args) throws IOException {
         ExecutorService executorService = Executors.newFixedThreadPool(40);
         // 不能执行。。。。。
         /*int corePoolSize = 20;
         int maximumPoolSize = 40;
         long keepAliveTime = 2000;
         TimeUnit unit = TimeUnit.MILLISECONDS;
         BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>();
         ThreadPoolExecutor socketPool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, r -> new Thread("socket线程池"));*/
        // 创建serverSocket，绑定端口
        ServerSocket serverSocket = new ServerSocket(8803);
        while (true) {
            Socket accept = serverSocket.accept();
            executorService.execute(() -> sendResponse(accept));
        }
    }

    private static void sendResponse(Socket accept) {
        try {
            Thread.sleep(20);
            PrintWriter printWriter = new PrintWriter(accept.getOutputStream(), true);
            printWriter.println("HTTP/1.1 200 OK");
            printWriter.println("Content-Type:text/html;charset=utf-8");
            printWriter.println();
            printWriter.write("hello, socket");
            printWriter.close();
            accept.close();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }
}
