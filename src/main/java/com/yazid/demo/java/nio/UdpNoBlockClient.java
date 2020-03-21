package com.yazid.demo.java.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.Date;
import java.util.Scanner;

/**
 * @author Yazid
 * @date 2020/03/13 0013 18:22
 */
public class UdpNoBlockClient {
    public static void main(String[] args) {
        DatagramChannel datagramChannel = null;
        try {
            datagramChannel = DatagramChannel.open();
            //开启非阻塞
            datagramChannel.configureBlocking(false);
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNext()) {
                String str = scanner.next();
                byteBuffer.put((new Date().toString() + ":\n" + str).getBytes());
                byteBuffer.flip();
                //发送udp数据报
                datagramChannel.send(byteBuffer, new InetSocketAddress("127.0.1.1", 6666));
                byteBuffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (datagramChannel != null) {
                    datagramChannel.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
