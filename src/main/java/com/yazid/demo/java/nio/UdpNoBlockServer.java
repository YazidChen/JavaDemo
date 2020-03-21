package com.yazid.demo.java.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;

/**
 * @author Yazid
 * @date 2020/03/13 0013 18:21
 */
public class UdpNoBlockServer {
    public static void main(String[] args) {
        try {
            DatagramChannel datagramChannel = DatagramChannel.open();
            //开启非阻塞
            datagramChannel.configureBlocking(false);
            datagramChannel.bind(new InetSocketAddress(6666));
            //开启选择器
            Selector selector = Selector.open();
            //向选择器注册通道
            datagramChannel.register(selector, SelectionKey.OP_READ);
            //select()阻塞等待通道就绪，可以设置超时时间结束阻塞
            while (selector.select() > 0) {
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    if (selectionKey.isReadable()) {
                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                        //接受udp数据报
                        datagramChannel.receive(byteBuffer);
                        byteBuffer.flip();
                        System.out.println(new String(byteBuffer.array(), 0, byteBuffer.limit()));
                        byteBuffer.clear();
                    }
                }
                iterator.remove();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
