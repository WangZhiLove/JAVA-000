package com.wangzhi.socket;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * authors 王智
 * description 单线程的socket
 */
public class SocketSingleThread {
    public static void main(String[] args) throws IOException {
        // 创建serverSocket，绑定端口
        ServerSocket serverSocket = new ServerSocket(8082);
        while (true) {
            Socket accept = serverSocket.accept();
            sendResponse(accept);
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
