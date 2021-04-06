package com.yazid.demo.json.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.yazid.demo.json.jackson.entity.*;
import com.yazid.demo.json.jackson.enums.TypeEnumWithValue;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Jackson annotation serialization
 *
 * @author Yazid
 * @date 2021/3/30 10:16
 */
public class SerializationAnnotationTest {
    /**
     * JsonAnyGetter
     * 未添加：{"name":"Yazid","properties":{"k1":"v1","k2":"v2"}}
     * 已添加：{"name":"Yazid","k1":"v1","k2":"v2"}
     *
     * @throws JsonProcessingException
     */
    @Test
    public void whenSerializingUsingJsonAnyGetter() throws JsonProcessingException {

        ExtendableEntity extendableEntity = new ExtendableEntity("Yazid");
        extendableEntity.add("k1", "v1");
        extendableEntity.add("k2", "v2");

        String result = new ObjectMapper().writeValueAsString(extendableEntity);
        System.out.println(result);

        assertTrue(result.contains("k1"));
        assertTrue(result.contains("v2"));
    }

    /**
     * JsonRawValue
     * 添加前：{"name":"Yazid","json":"{\"attr\":fasle}"}
     * 添加后：{"name":"Yazid","json":{"attr":fasle}}
     *
     * @throws JsonProcessingException
     */
    @Test
    public void whenSerializingUsingJsonRawValue() throws JsonProcessingException {

        RawEntity rawEntity = new RawEntity("Yazid", "{\"attr\":fasle}");
        String result = new ObjectMapper().writeValueAsString(rawEntity);
        System.out.println(result);

        assertTrue(result.contains("Yazid"));
        assertTrue(result.contains("{\"attr\":fasle}"));
    }

    /**
     * JsonValue
     * 添加前："TYPE1"
     * 添加后："Type A"
     *
     * @throws IOException
     */
    @Test
    public void whenSerializingUsingJsonValue() throws IOException {

        String enumAsString = new ObjectMapper()
                .writeValueAsString(TypeEnumWithValue.TYPE1);
        System.out.println(enumAsString);

        assertTrue(enumAsString.contains("\"Type A\""));
    }

    /**
     * JsonRootName(value = "user")
     * JsonSerialize(using = CustomDateSerializer.class)
     * 添加前：{"UserEntity":{"id":1,"name":"Yazid","joinTime":1617075106571}}
     * 添加后：{"user":{"id":1,"name":"Yazid","joinTime":"2021-03-30 11:31:11"}}
     *
     * @throws JsonProcessingException
     */
    @Test
    public void whenSerializingUsingJsonRootName() throws JsonProcessingException {

        UserEntity user = new UserEntity(1L, "Yazid", new Date());

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
        String result = mapper.writeValueAsString(user);
        System.out.println(result);

        assertTrue(result.contains("Yazid"));
        assertTrue(result.contains("user"));
    }

    /**
     * JsonIgnoreProperties
     * 类级别注解，序列化排除部分字段
     *
     * @throws JsonProcessingException
     */
    @Test
    public void whenSerializingUsingJsonIgnoreProperties()
            throws JsonProcessingException {

        IgnoreEntity ignoreEntity = new IgnoreEntity(1L, "Chen", "Yazid");

        String result = new ObjectMapper()
                .writeValueAsString(ignoreEntity);
        System.out.println(result);

        assertTrue(result.contains("Yazid"));
        assertFalse(result.contains("\"id\""));
    }

    /**
     * JsonIgnore
     * 字段级别注解，序列化排除部分字段
     *
     * @throws JsonProcessingException
     */
    @Test
    public void whenSerializingUsingJsonIgnore()
            throws JsonProcessingException {

        IgnoreEntity ignoreEntity = new IgnoreEntity(1L, "Chen", "Yazid");

        String result = new ObjectMapper()
                .writeValueAsString(ignoreEntity);
        System.out.println(result);

        assertTrue(result.contains("Yazid"));
        assertFalse(result.contains("Chen"));
        assertFalse(result.contains("\"id\""));
    }

    /**
     * JsonIgnoreType
     * 当前定义的字段类型的修饰字段不参与序列化
     *
     * @throws JsonProcessingException
     * @throws ParseException
     */
    @Test
    public void whenSerializingUsingJsonIgnoreType()
            throws JsonProcessingException, ParseException {

        IgnoreTypeEntity.Name name = new IgnoreTypeEntity.Name("Chen", "Yazid");
        IgnoreTypeEntity user = new IgnoreTypeEntity(1L, name);

        String result = new ObjectMapper()
                .writeValueAsString(user);
        System.out.println(result);

        assertTrue(result.contains("1"));
        assertFalse(result.contains("name"));
        assertFalse(result.contains("Yazid"));
    }

    /**
     * JsonInclude
     * 可以起到排除empty，null作用
     *
     * @throws JsonProcessingException
     */
    @Test
    public void whenSerializingUsingJsonInclude()
            throws JsonProcessingException {

        IncludeEntity includeEntity = new IncludeEntity(1L, null);

        String result = new ObjectMapper()
                .writeValueAsString(includeEntity);
        System.out.println(result);

        assertTrue(result.contains("1"));
        assertFalse(result.contains("name"));
    }
}
