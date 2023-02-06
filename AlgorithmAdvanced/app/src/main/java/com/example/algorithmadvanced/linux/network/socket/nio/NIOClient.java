package com.example.algorithmadvanced.linux.network.socket.nio;

import java.util.Scanner;

public class NIOClient {

    private static NIOClientHandle nioClientHandle;

    public static void main(String[] args) {
        start();
        Scanner scanner = new Scanner(System.in);
        while (NIOClient.sendMsg(scanner.next())) ;
    }

    public static boolean sendMsg(String msg) {
        nioClientHandle.sendMsg(msg);
        return true;
    }

    public static void start() {
        nioClientHandle = new NIOClientHandle("", 8888);
        new Thread(nioClientHandle, "client").start();
    }
}
