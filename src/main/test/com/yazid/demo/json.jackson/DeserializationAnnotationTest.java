package com.yazid.demo.json.jackson;

import com.fasterxml.jackson.databind.InjectableValues;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yazid.demo.json.jackson.entity.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Jackson annotation deserialization
 *
 * @author Yazid
 * @date 2021/3/30 14:21
 */
public class DeserializationAnnotationTest {
    /**
     * JsonCreator 反序列化
     * JsonProperty 以定义的字段名进行反序列化
     *
     * @throws IOException
     */
    @Test
    public void whenDeserializingUsingJsonCreator() throws IOException {
        String json = "{\"id\":1,\"theName\":\"Yazid\"}";

        CreatorEntity creatorEntity = new ObjectMapper()
                .readerFor(CreatorEntity.class)
                .readValue(json);

        assertEquals("Yazid", creatorEntity.getName());
    }

    /**
     * JacksonInject注解的字段会从注入中获取值，而非JSON数据中。
     *
     * @throws IOException
     */
    @Test
    public void whenDeserializingUsingJsonInject() throws IOException {
        String json = "{\"name\":\"Yazid\"}";

        InjectableValues inject = new InjectableValues
                .Std()
                .addValue(Long.class, 1L);
        InjectEntity injectEntity = new ObjectMapper()
                .reader(inject)
                .forType(InjectEntity.class)
                .readValue(json);

        assertEquals("Yazid", injectEntity.getName());
        assertEquals(1, injectEntity.getId());
    }

    /**
     * JsonAnySetter 反序列化将拍平的属性put入集合中
     *
     * @throws IOException
     */
    @Test
    public void whenDeserializingUsingJsonAnySetter() throws IOException {
        String json = "{\"name\":\"Yazid\",\"attr2\":\"val2\",\"attr1\":\"val1\"}";

        ExtendableEntity extendableEntity = new ObjectMapper()
                .readerFor(ExtendableEntity.class)
                .readValue(json);

        assertEquals("Yazid", extendableEntity.getName());
        assertEquals("val2", extendableEntity.getProperties().get("attr2"));
    }

    /**
     * JsonSetter 反序列化指定字段名
     *
     * @throws IOException
     */
    @Test
    public void whenDeserializingUsingJsonSetter() throws IOException {
        String json = "{\"id\":\"1\",\"theName\":\"Yazid\"}";

        SetterEntity setterEntity = new ObjectMapper()
                .readerFor(SetterEntity.class)
                .readValue(json);

        assertEquals("Yazid", setterEntity.getName());
        assertEquals(1L, setterEntity.getId());
    }

    /**
     * JsonDeserialize 反序列化指定规则
     *
     * @throws IOException
     */
    @Test
    public void whenDeserializingUsingJsonDeserialize() throws IOException {
        String json = "{\"name\":\"party\",\"eventDate\":\"2021-03-31 23:56:59\"}";

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        EventEntity event = new ObjectMapper()
                .readerFor(EventEntity.class)
                .readValue(json);

        assertEquals("2021-03-31 23:56:59", df.format(event.getEventDate()));
    }

    /**
     * JsonAlias
     * 反序列化字段对应多个规则
     *
     * @throws IOException
     */
    @Test
    public void whenDeserializingUsingJsonAlias() throws IOException {
        String json = "{\"fName\": \"John\", \"lastName\": \"Green\"}";

        AliasEntity aliasEntity = new ObjectMapper()
                .readerFor(AliasEntity.class)
                .readValue(json);

        assertEquals("John", aliasEntity.getFirstName());
    }

    /**
     * JsonTypeInfo、JsonSubTypes、JsonTypeName
     * 多态反序列化
     *
     * @throws IOException
     */
    @Test
    public void whenDeserializingPolymorphic()
            throws IOException {
        String json = "{\"animal\":{\"name\":\"lacy\",\"type\":\"cat\",\"likesCream\":true,\"lives\":1}}";

        ZooEntity zoo = new ObjectMapper()
                .readerFor(ZooEntity.class)
                .readValue(json);

        assertEquals("lacy", zoo.getAnimal().getName());
        assertEquals(ZooEntity.Cat.class, zoo.getAnimal().getClass());
    }
}
