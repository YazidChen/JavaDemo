package com.yazid.demo.java.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

/**
 * @author Yazid
 * @date 2020/03/13 0013 21:40
 */
public class PipeDemo {
    public static void main(String[] args) {
        try {
            Pipe pipe = Pipe.open();
            ByteBuffer buf = ByteBuffer.allocate(1024);
            Pipe.SinkChannel sinkChannel = pipe.sink();
            buf.put("通过单向管道发送数据".getBytes());
            buf.flip();
            //2. 将缓冲区中的数据写入管道
            sinkChannel.write(buf);
            //3. 读取缓冲区中的数据
            Pipe.SourceChannel sourceChannel = pipe.source();
            buf.flip();
            int len = sourceChannel.read(buf);
            System.out.println(new String(buf.array(), 0, len));
            sourceChannel.close();
            sinkChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
