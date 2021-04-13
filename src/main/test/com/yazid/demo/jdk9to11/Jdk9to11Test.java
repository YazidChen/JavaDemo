package com.yazid.demo.jdk9to11;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Yazid
 * @date 2021/4/11 21:11
 */
public class Jdk9to11Test {

    /**
     * Lambda表达式中，var关键字标识变量，变量类型由编译器自行判断
     *
     * @author Yazid
     * @date 2021/4/11 21:29
     */
    @Test
    public void testVar() {
        Arrays.asList("Java", "Python", "Golang")
                .forEach((var s) -> System.out.println("Hello:" + s));
    }

    /**
     * 字符串处理方法
     */
    @Test
    public void testStringApi() {
        assertTrue(" ".isBlank());
        assertEquals("java", " java ".strip());
        assertEquals("java", " java".stripLeading());
        assertEquals("java", "java ".stripTrailing());
        assertEquals("JavaJavaJava", "Java".repeat(3));
        assertEquals(3, "A\nB\nC".lines().count());
    }

    /**
     * copyOf 方法会先判断来源集合是不是 AbstractImmutableList 类型的，如果是，就直接返回，如果不是，则调用 of 创建一个新的集合。
     * list2因为用的 new 创建的集合，不属于不可变 AbstractImmutableList 类的子类，所以 copyOf 方法又创建了一个新的实例，所以为false.
     */
    @Test
    public void testListApi() {
        var list = List.of("Java", "Python", "Golang");
        var copy = List.copyOf(list);

        assertSame(list, copy);

        var list2 = new ArrayList<String>();
        var copy2 = List.copyOf(list2);

        assertNotSame(list2, copy2);
    }

    /**
     * Stream新增方法
     */
    @Test
    public void testStreamApi() {
        //可为null的构造方法
        long count = Stream.ofNullable(null).count();

        assertEquals(0, count);

        //takeWhile 从开始计算，满足n < 3条件时结束；
        List<Integer> integerList1 = Stream.of(1, 2, 3, 4, 5)
                .takeWhile(n -> n < 3)
                .collect(Collectors.toList());

        assertEquals("[1, 2]", integerList1.toString());

        //dropWhile 从满足n < 3条件时开始计算到结束；
        List<Integer> integerList2 = Stream.of(1, 2, 3, 4, 5)
                .dropWhile(n -> n < 3)
                .collect(Collectors.toList());

        assertEquals("[3, 4, 5]", integerList2.toString());
    }

    /**
     * InputStream transferTo 方法可以将数据直接传输到 OutputStream
     *
     * @throws IOException
     */
    @Test
    public void testInputStreamTransferTo() throws IOException {
        var classLoader = ClassLoader.getSystemClassLoader();
        var inputStream = classLoader.getResourceAsStream("javastack.txt");
        if (inputStream == null) {
            return;
        }
        String path = Objects.requireNonNull(this.getClass().getResource("/")).getPath();
        File file = new File(path + "javastack2.txt");
        if (!file.exists() && !file.createNewFile()) {
            System.out.println("生成文件失败！");
        }
        try (var outputStream = new FileOutputStream(file)) {
            inputStream.transferTo(outputStream);
        }
    }

    /**
     * HttpClient 同步请求
     */
    @Test
    public void testHttpClientSend() {
        HttpClient httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .build();

        try {
            String urlEndpoint = "https://postman-echo.com/get";
            URI uri = URI.create(urlEndpoint + "?foo1=bar1&foo2=bar2");
            HttpRequest httpRequest = HttpRequest.newBuilder(uri).build();
            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            System.out.println("Status Code:" + response.statusCode());
            System.out.println("Headers:" + response.headers().allValues("content-type"));
            System.out.println("Body:" + response.body());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * HttpClient 异步请求
     */
    @Test
    public void testHttpClientAsyncSend() {
        List<URI> uris = Stream.of(
                "https://www.github.com/",
                "https://www.baidu.com/",
                "https://www.bilibili.com/"
        ).map(URI::create).collect(Collectors.toList());

        HttpClient httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .followRedirects(HttpClient.Redirect.ALWAYS)
                .build();

        CompletableFuture[] futures = uris.stream()
                .map(uri -> verifyUri(httpClient, uri))
                .toArray(CompletableFuture[]::new);

        CompletableFuture.allOf(futures).join();
    }

    /**
     * 校验uri
     *
     * @param httpClient
     * @param uri
     * @return
     */
    private CompletableFuture<Void> verifyUri(HttpClient httpClient, URI uri) {
        HttpRequest request = HttpRequest.newBuilder(uri)
                .timeout(Duration.ofSeconds(5))
                .build();
        return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::statusCode)
                .thenApply(statusCode -> statusCode == 200)
                .exceptionally(ex -> false)
                .thenAccept(valid -> {
                    if (valid) {
                        System.out.println("[SUCCESS] Verified " + uri);
                    } else {
                        System.out.println("[FAILURE] Could not " + "verify " + uri);
                    }
                });
    }
}
