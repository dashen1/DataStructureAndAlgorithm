package com.example.algorithmadvanced.linux.network.socket.nio;

public class NIOClientHandle implements Runnable{
    private String ip;
    private int port;

    public NIOClientHandle(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    @Override
    public void run() {
        
    }

    public void sendMsg(String msg) {
    }
}
