package com.yazid.demo.java.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;

/**
 * @author Yazid
 * @date 2020/03/13 0013 11:11
 */
public class BlockClient {
    public static void main(String[] args) {
        SocketChannel socketChannel = null;
        FileChannel fileChannel = null;
        try {
            //获取client通道，连接server
            socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 6666));
            //获取文件通道
            fileChannel = FileChannel.open(Paths.get("D:/app/client/aaa.txt"));
            //创建Buffer
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            while (fileChannel.read(byteBuffer) != -1) {
                //切换成读模式
                byteBuffer.flip();
                socketChannel.write(byteBuffer);
                //清空并切换成写模式
                byteBuffer.clear();
            }
            //告诉server文件已发完，关闭输出通道
            socketChannel.shutdownOutput();
            //接收来自server的消息
            int len = 0;
            while ((len = socketChannel.read(byteBuffer)) != -1) {
                byteBuffer.flip();
                System.out.println(new String(byteBuffer.array(), 0, len));
                byteBuffer.clear();
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
