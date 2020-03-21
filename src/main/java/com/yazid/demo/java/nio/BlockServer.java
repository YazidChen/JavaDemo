package com.yazid.demo.java.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @author Yazid
 * @date 2020/03/13 0013 11:21
 */
public class BlockServer {
    public static void main(String[] args) {
        ServerSocketChannel server = null;
        SocketChannel client = null;
        FileChannel fileChannel = null;
        try {
            //获取server通道
            server = ServerSocketChannel.open();
            //绑定本地端口
            server.bind(new InetSocketAddress(6666));
            //获取文件通道
            fileChannel = FileChannel.open(Paths.get("D:/app/server/bbb.txt"), StandardOpenOption.WRITE,
                    StandardOpenOption.CREATE);
            //获取连接到server的client通道
            client = server.accept();
            //创建buffer
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            while (client.read(byteBuffer) != -1) {
                //切换成读模式
                byteBuffer.flip();
                fileChannel.write(byteBuffer);
                //清空并切换成写模式
                byteBuffer.clear();
            }
            //给client发确认消息
            byteBuffer.put("save txt success!".getBytes());
            byteBuffer.flip();
            client.write(byteBuffer);
            byteBuffer.clear();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileChannel != null) {
                    fileChannel.close();
                }
                if (client != null) {
                    client.close();
                }
                if (server != null) {
                    server.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
