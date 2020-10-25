package com.wangzhi.socket;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * author 王智
 * description 多线程版本
 */
public class SocketMultithreading {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8801);
        while (true) {
            Socket accept = serverSocket.accept();
            new Thread(() -> {
                server(accept);
            }).start();
        }
    }

    private static void server(Socket accept) {
        try {
            Thread.sleep(20);
            PrintWriter printWriter = new PrintWriter(accept.getOutputStream(), false);
            printWriter.println("HTTP/1.1 200 OK");
            printWriter.println("Content-Type:text/html;charset=utf-8");
            printWriter.println();
            printWriter.write("hello,socket");
            printWriter.close();
            accept.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
