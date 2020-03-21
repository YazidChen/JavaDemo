package com.yazid.demo.java.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.util.Iterator;

/**
 * @author Yazid
 * @date 2020/03/13 0013 13:14
 */
public class NoBlockClient {
    public static void main(String[] args) {
        SocketChannel socketChannel = null;
        FileChannel fileChannel = null;
        try {
            socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 6666));
            //开启非阻塞
            socketChannel.configureBlocking(false);
            //开启选择器
            Selector selector = Selector.open();
            //向选择器注册通道
            socketChannel.register(selector, SelectionKey.OP_READ);
            fileChannel = FileChannel.open(Paths.get("D:/app/client/aaa.txt"));
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            while (fileChannel.read(byteBuffer) != -1) {
                byteBuffer.flip();
                socketChannel.write(byteBuffer);
                byteBuffer.clear();
            }
            //通知server写结束
            socketChannel.shutdownOutput();
            while (true) {
                //select()阻塞等待通道就绪，可以设置超时时间结束阻塞
                if (selector.select() <= 0) {
                    continue;
                }
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    if (selectionKey.isReadable()) {
                        SocketChannel responseChannel = (SocketChannel) selectionKey.channel();
                        ByteBuffer responseBuffer = ByteBuffer.allocate(1024);
                        int len = responseChannel.read(responseBuffer);
                        if (len > 0) {
                            responseBuffer.flip();
                            System.out.println(new String(responseBuffer.array(), 0, len));
                            responseBuffer.clear();
                        }
                    }
                    iterator.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (socketChannel != null) {
                    socketChannel.close();
                }
                if (fileChannel != null) {
                    fileChannel.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
