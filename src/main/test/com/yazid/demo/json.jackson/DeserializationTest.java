package com.yazid.demo.json.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.yazid.demo.json.jackson.entity.CarEntity;
import com.yazid.demo.json.jackson.serializer.CustomCarDeserializer;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Yazid
 * @date 2021/4/7 14:35
 */
public class DeserializationTest {
    /**
     * JSON to Java Object
     *
     * @throws JsonProcessingException
     */
    @Test
    public void testReadValue()
            throws JsonProcessingException {
        String json = "{ \"color\" : \"Black\", \"type\" : \"BMW\" }";

        CarEntity carEntity = new ObjectMapper().readValue(json, CarEntity.class);

        assertEquals("Black", carEntity.getColor());
        assertEquals("BMW", carEntity.getType());
    }

    /**
     * JSON to Jackson JsonNode
     */
    @Test
    public void testJson2JsonNode()
            throws JsonProcessingException {
        String json = "{ \"color\" : \"Black\", \"type\" : \"BMW\" }";

        JsonNode jsonNode = new ObjectMapper().readTree(json);

        assertEquals("Black", jsonNode.get("color").asText());
    }

    /**
     * JsonArray to Java List
     *
     * @throws JsonProcessingException
     */
    @Test
    public void testJsonArray2List()
            throws JsonProcessingException {
        String jsonCarArray =
                "[{ \"color\" : \"Black\", \"type\" : \"BMW\" }, { \"color\" : \"Red\", \"type\" : \"FIAT\" }]";

        List<CarEntity> list = new ObjectMapper()
                .readValue(
                        jsonCarArray,
                        new TypeReference<List<CarEntity>>() {
                        });

        assertEquals(2, list.size());
    }

    /**
     * JSON to Java Map
     *
     * @throws JsonProcessingException
     */
    @Test
    public void testJson2Map() throws JsonProcessingException {
        String json = "{ \"color\" : \"Black\", \"type\" : \"BMW\" }";

        Map<String, Object> map = new ObjectMapper()
                .readValue(
                        json,
                        new TypeReference<Map<String, Object>>() {
                        });

        assertEquals("Black", map.get("color"));
        assertEquals("BMW", map.get("type"));
    }

    /**
     * configure配置
     * FAIL_ON_UNKNOWN_PROPERTIES 是否忽略未知字段
     * FAIL_ON_NULL_FOR_PRIMITIVES 是否允许原始值为空值
     * FAIL_ON_NUMBERS_FOR_ENUM 是否允许将枚举值序列化/反序列化为数字
     *
     * @throws JsonProcessingException
     */
    @Test
    public void testConfigure()
            throws JsonProcessingException {
        String json
                = "{ \"color\" : \"Black\", \"type\" : \"Fiat\", \"year\" : \"1970\" }";

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        CarEntity car = objectMapper.readValue(json, CarEntity.class);

        assertEquals("Black", car.getColor());

        JsonNode jsonNode = objectMapper.readTree(json);
        JsonNode jsonNodeYear = jsonNode.get("year");

        assertEquals("1970", jsonNodeYear.asText());
    }

    /**
     * extends StdDeserializer<T>
     * 自定义反序列化规则
     *
     * @throws JsonProcessingException
     */
    @Test
    public void testCustomDeserializer() throws JsonProcessingException {
        String json = "{ \"color\" : \"Black\", \"type\" : \"BMW\" }";

        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule simpleModule = new SimpleModule(
                "CustomCarDeserializer",
                new Version(1, 0, 0, null, null, null));
        simpleModule.addDeserializer(CarEntity.class, new CustomCarDeserializer());
        objectMapper.registerModule(simpleModule);

        CarEntity carEntity = objectMapper
                .readValue(json, CarEntity.class);
        System.out.println(carEntity);

        assertEquals("Black", carEntity.getColor());
    }

    /**
     * Json to Java Array
     *
     * @throws JsonProcessingException
     */
    @Test
    public void testJson2Array()
            throws JsonProcessingException {
        String jsonCarArray =
                "[{ \"color\" : \"Black\", \"type\" : \"BMW\" }, { \"color\" : \"Red\", \"type\" : \"FIAT\" }]";

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY, true);

        CarEntity[] cars = objectMapper
                .readValue(jsonCarArray, CarEntity[].class);
        Arrays.stream(cars).forEach(System.out::println);

        assertEquals(2, cars.length);
    }
}
