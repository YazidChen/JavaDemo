package com.yazid.demo.java.io;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @author Yazid
 * @date 2020/03/12 0012 14:02
 */
public class NIOTest {
    @Test
    public void buffer() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        System.out.println("init-limit缓冲区中可操作数据大小:" + byteBuffer.limit());
        System.out.println("init-position缓冲区中下一个可操作的位置:" + byteBuffer.position());
        System.out.println("init-capacity缓冲区容量:" + byteBuffer.capacity());
        System.out.println("init-mark标记:" + byteBuffer.mark());
        System.out.println("___________________________");
        String content = "abc";
        byteBuffer.put(content.getBytes());
        System.out.println("put-limit缓冲区中可操作数据大小:" + byteBuffer.limit());
        System.out.println("put-position缓冲区中下一个可操作的位置:" + byteBuffer.position());
        System.out.println("put-capacity缓冲区容量:" + byteBuffer.capacity());
        System.out.println("put-mark标记:" + byteBuffer.mark());
        System.out.println("___________________________");
        byteBuffer.flip();
        System.out.println("flip-limit缓冲区中可操作数据大小:" + byteBuffer.limit());
        System.out.println("flip-position缓冲区中下一个可操作的位置:" + byteBuffer.position());
        System.out.println("flip-capacity缓冲区容量:" + byteBuffer.capacity());
        System.out.println("flip-mark标记:" + byteBuffer.mark());
        System.out.println("___________________________");
        //创建一个limit()大小的字节数组
        byte[] bytes = new byte[byteBuffer.limit()];
        //将读取的数据装进我们的字节数组中
        byteBuffer.get(bytes);
        //输出数据
        System.out.println(new String(bytes, 0, bytes.length));
        System.out.println("get-limit缓冲区中可操作数据大小:" + byteBuffer.limit());
        System.out.println("get-position缓冲区中下一个可操作的位置:" + byteBuffer.position());
        System.out.println("get-capacity缓冲区容量:" + byteBuffer.capacity());
        System.out.println("get-mark标记:" + byteBuffer.mark());
        System.out.println("___________________________");
        byteBuffer.clear();
        System.out.println("clear-limit缓冲区中可操作数据大小:" + byteBuffer.limit());
        System.out.println("clear-position缓冲区中下一个可操作的位置:" + byteBuffer.position());
        System.out.println("clear-capacity缓冲区容量:" + byteBuffer.capacity());
        System.out.println("clear-mark标记:" + byteBuffer.mark());
    }

    @Test
    public void channel() {
        FileInputStream fileInputStream = null;
        FileChannel fileChannel = null;
        //通过本地IO方式获取通道
        try {
            fileInputStream = new FileInputStream("D:\\aaa.txt");
            fileChannel = fileInputStream.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            fileChannel.read(byteBuffer);
            byteBuffer.flip();
            byte[] bytes = new byte[byteBuffer.limit()];
            byteBuffer.get(bytes);
            System.out.println("本地IO方式:" + new String(bytes, 0, bytes.length));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //JDK1.7以后通过静态方法open获取通道
        try {
            fileChannel = FileChannel.open(Paths.get("D:\\aaa.txt"), StandardOpenOption.READ);
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            fileChannel.read(byteBuffer);
            byteBuffer.flip();
            byte[] bytes = new byte[byteBuffer.limit()];
            byteBuffer.get(bytes);
            System.out.println("静态方法open方式:" + new String(bytes, 0, bytes.length));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void fileChannelCopyBuffer() {
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        FileChannel inChannel = null;
        FileChannel outChannel = null;
        try {
            fileInputStream = new FileInputStream("D:\\aaa.txt");
            fileOutputStream = new FileOutputStream("D:\\bbb.txt");
            inChannel = fileInputStream.getChannel();
            outChannel = fileOutputStream.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            while (inChannel.read(byteBuffer) != -1) {
                byteBuffer.flip();
                outChannel.write(byteBuffer);
                byteBuffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void mappedByteBufferCopy() {
        FileChannel inChannel = null;
        FileChannel outChannel = null;
        try {
            inChannel = FileChannel.open(Paths.get("D:\\aaa.txt"), StandardOpenOption.READ);
            outChannel = FileChannel.open(Paths.get("D:\\ccc.txt"), StandardOpenOption.WRITE,
                    StandardOpenOption.READ,
                    StandardOpenOption.CREATE);
            //内存映射文件
            MappedByteBuffer inMappedBuf = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
            MappedByteBuffer outMappedBuf = outChannel.map(FileChannel.MapMode.READ_WRITE, 0, inChannel.size());
            //直接对缓冲区数据进行读写操作
            byte[] bytes = new byte[inMappedBuf.limit()];
            inMappedBuf.get(bytes);
            outMappedBuf.put(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inChannel != null) {
                    inChannel.close();
                }
                if (outChannel != null) {
                    outChannel.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void bufferTransfer() {
        FileChannel inChannel = null;
        FileChannel outChannel = null;
        try {
            inChannel = FileChannel.open(Paths.get("D:\\aaa.txt"), StandardOpenOption.READ);
            outChannel = FileChannel.open(Paths.get("D:\\ccc.txt"), StandardOpenOption.WRITE,
                    StandardOpenOption.READ,
                    StandardOpenOption.CREATE);
            inChannel.transferTo(0, inChannel.size(), outChannel);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inChannel != null) {
                    inChannel.close();
                }
                if (outChannel != null) {
                    outChannel.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 分散读取和聚集写入
     */
    @Test
    public void scatterAndGather() {
        FileChannel inChannel = null;
        FileChannel outChannel = null;
        try {
            inChannel = FileChannel.open(Paths.get("D:\\aaa.txt"), StandardOpenOption.READ);
            ByteBuffer bus1 = ByteBuffer.allocate(5);
            ByteBuffer bus2 = ByteBuffer.allocate(1024);
            //分散读取
            ByteBuffer[] bus = {bus1, bus2};
            inChannel.read(bus);
            for (ByteBuffer b : bus) {
                b.flip();
            }
            System.out.println(new String(bus[0].array(), 0, bus[0].limit()));
            System.out.println(new String(bus[1].array(), 0, bus[1].limit()));
            //聚集写入
            RandomAccessFile randomAccessFile = new RandomAccessFile("D:\\bbb.txt", "rw");
            outChannel = randomAccessFile.getChannel();
            outChannel.write(bus);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
