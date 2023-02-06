package com.example.algorithmadvanced.linux.network.socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ClientSocket {

    public static void main(String[] args) throws IOException {
        /**
         * BIO是面向流的 1、直接从网卡读写数据 2、阻塞式 3、服务器一个线程只能服务一个客户端
         * NIO是面向缓冲区的 1、中间有个缓冲区，冲缓冲区读写数据 2、非阻塞式 3、服务器一个线程可以服务多个客户端
         * NIO三大核心组件：
         * Selector
         * Channel
         * Buffer
         * 操作类型：SelectionKey
         */
        Socket socket = null;
        ObjectInputStream inputStream = null;
        ObjectOutputStream outputStream = null;
        InetSocketAddress socketAddress = new InetSocketAddress("127.0.0.1", 1001);

        try {
            socket = new Socket();
            socket.connect(socketAddress);
            /**
             * 注意：read(),write(),connect(),bind(),accept()等方法都是阻塞式方法  This method will block if no input is available.
             * 因为是服务端socket先创建，所以服务端的inputStream和outputStream也是提前创建好的，
             * 所以在客户端这边，客户端得先写入数据，服务端才能读取到数据，否则会一直阻塞，直到有数据来才会继续执行后面的代码
             */
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());
            System.out.println("Client start...");
            outputStream.writeUTF("clientSocket");
            outputStream.flush();
            System.out.println(inputStream.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket!=null) socket.close();
            if (outputStream!=null) outputStream.close();
            if(inputStream!=null) inputStream.close();
        }
    }
}
