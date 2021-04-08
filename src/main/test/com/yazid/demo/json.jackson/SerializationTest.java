package com.yazid.demo.json.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.yazid.demo.json.jackson.entity.BookEntity;
import com.yazid.demo.json.jackson.entity.CarEntity;
import com.yazid.demo.json.jackson.entity.DateFormatEntity;
import com.yazid.demo.json.jackson.serializer.CustomCarSerializer;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Yazid
 * @date 2021/4/7 14:35
 */
public class SerializationTest {
    /**
     * Object to JSON
     *
     * @throws JsonProcessingException
     */
    @Test
    public void testWriteValueAsString()
            throws JsonProcessingException {
        CarEntity car = new CarEntity("blue", "BMW");

        String result = new ObjectMapper()
                .writeValueAsString(car);
        System.out.println(result);

        assertTrue(result.contains("blue"));
    }

    /**
     * extends StdSerializer<T>
     * 自定义序列化规则
     *
     * @throws JsonProcessingException
     */
    @Test
    public void testCustomSerializer()
            throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule simpleModule = new SimpleModule("CustomCarSerializer", new Version(1, 0, 0, null, null, null));
        simpleModule.addSerializer(CarEntity.class, new CustomCarSerializer());
        objectMapper.registerModule(simpleModule);

        CarEntity carEntity = new CarEntity("Black", "BMW");
        String result = objectMapper.writeValueAsString(carEntity);
        System.out.println(result);

        assertFalse(result.contains("Black"));
        assertTrue(result.contains("BMW"));
    }

    /**
     * setDateFormat
     *
     * @throws JsonProcessingException
     */
    @Test
    public void testDateFormatSerializer()
            throws JsonProcessingException {
        Date date = new Date();
        DateFormatEntity dateFormatEntity = new DateFormatEntity(1L, "Yazid", date);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss SSS");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(df);

        String result = objectMapper.writeValueAsString(dateFormatEntity);
        System.out.println(result);

        assertTrue(result.contains(df.format(date)));
    }

    /**
     * 以Optional修饰的字段正确的序列化方式
     * 未使用Jdk8Module:{"title":"AA","subTitle":{"present":true}}
     * 使用Jdk8Module:{"title":"AA","subTitle":"aa"}
     * 反序列化同理
     *
     * @throws JsonProcessingException
     */
    @Test
    public void testOptionalSerializer()
            throws JsonProcessingException {
        BookEntity book = new BookEntity("AA", Optional.of("aa"));
        System.out.println(book);

        String result = new ObjectMapper()
                .writeValueAsString(book);
        System.out.println(result);

        assertFalse(result.contains("aa"));

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new Jdk8Module());

        String registerResult = objectMapper.writeValueAsString(book);
        System.out.println(registerResult);

        assertTrue(registerResult.contains("aa"));
    }

    /**
     * 正常结果：
     * {"key":"value"}
     * <p>
     * writerWithDefaultPrettyPrinter结果:
     * {
     * "key" : "value"
     * }
     *
     * @throws JsonProcessingException
     */
    @Test
    public void testMap2Json() throws JsonProcessingException {
        Map<String, String> map = new HashMap<>(4);
        map.put("key", "value");

        ObjectMapper objectMapper = new ObjectMapper();
        String result = objectMapper
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(map);
        System.out.println(result);

        Map<String, CarEntity> map1 = new HashMap<>(4);
        CarEntity carEntity = new CarEntity("Black", "BMW");
        map1.put("a", carEntity);
        String result1 = new ObjectMapper().writeValueAsString(map1);
        System.out.println(result1);
    }
}
