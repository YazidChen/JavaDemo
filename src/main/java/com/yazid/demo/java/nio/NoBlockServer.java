package com.yazid.demo.java.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Iterator;

/**
 * @author Yazid
 * @date 2020/03/13 0013 13:21
 */
public class NoBlockServer {
    public static void main(String[] args) {
        ServerSocketChannel server = null;
        try {
            server = ServerSocketChannel.open();
            //开启非阻塞
            server.configureBlocking(false);
            server.bind(new InetSocketAddress(6666));
            //开启选择器
            Selector selector = Selector.open();
            //向选择器注册通道
            server.register(selector, SelectionKey.OP_ACCEPT);
            while (true) {
                //select()阻塞等待通道就绪，可以设置超时时间结束阻塞
                if (selector.select() <= 0) {
                    continue;
                }
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    if (selectionKey.isAcceptable()) {
                        SocketChannel client = server.accept();
                        client.configureBlocking(false);
                        client.register(selector, SelectionKey.OP_READ);
                    } else if (selectionKey.isReadable()) {
                        SocketChannel client = (SocketChannel) selectionKey.channel();
                        FileChannel fileChannel = FileChannel.open(Paths.get("D:/app/server/bbb.txt"), StandardOpenOption.WRITE,
                                StandardOpenOption.CREATE);
                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                        while (client.read(byteBuffer) != -1) {
                            byteBuffer.flip();
                            fileChannel.write(byteBuffer);
                            byteBuffer.clear();
                        }
                        fileChannel.close();
                        //给client发确认消息
                        byteBuffer.put("save txt success!".getBytes());
                        byteBuffer.flip();
                        client.write(byteBuffer);
                        byteBuffer.clear();
                    }
                    selectionKey.cancel();
                    iterator.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (server != null) {
                    server.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
