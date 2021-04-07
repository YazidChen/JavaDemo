package com.yazid.demo.json.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.yazid.demo.json.jackson.entity.*;
import com.yazid.demo.json.jackson.enums.TypeEnumWithValue;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

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
    public void whenSerializingUsingJsonAnyGetter()
            throws JsonProcessingException {
        ExtendableEntity extendableEntity = new ExtendableEntity("Yazid");
        extendableEntity.add("k1", "v1");
        extendableEntity.add("k2", "v2");

        String result = new ObjectMapper()
                .writeValueAsString(extendableEntity);
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
    public void whenSerializingUsingJsonRawValue()
            throws JsonProcessingException {
        RawEntity rawEntity = new RawEntity("Yazid", "{\"attr\":fasle}");
        String result = new ObjectMapper()
                .writeValueAsString(rawEntity);
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
    public void whenSerializingUsingJsonValue()
            throws IOException {
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
    public void whenSerializingUsingJsonRootName()
            throws JsonProcessingException {
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
            throws JsonProcessingException {
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

    /**
     * JsonAutoDetect
     * 按字段描述符进行序列化，仅限于没有set、get方法的class
     *
     * @throws JsonProcessingException
     */
    @Test
    public void whenSerializingUsingJsonAutoDetect() throws JsonProcessingException {
        PrivateEntity privateEntity = new PrivateEntity(1L, "Chen", "Yazid");
        String result = new ObjectMapper()
                .writeValueAsString(privateEntity);
        System.out.println(result);

        assertTrue(result.contains("1"));
        assertTrue(result.contains("Yazid"));
    }

    /**
     * JsonTypeInfo、JsonSubTypes、JsonTypeName
     * 多态序列化
     * {"animal":{"type":"dog","name":"lacy","barkVolume":0.01}}
     *
     * @throws JsonProcessingException
     */
    @Test
    public void whenSerializingPolymorphic()
            throws JsonProcessingException {
        ZooEntity.Dog dog = new ZooEntity.Dog("lacy", 0.01);
        ZooEntity zoo = new ZooEntity(dog);

        String result = new ObjectMapper()
                .writeValueAsString(zoo);
        System.out.println(result);

        assertTrue(result.contains("type"));
        assertTrue(result.contains("dog"));
    }

    /**
     * JsonFormat
     * 格式定义
     *
     * @throws JsonProcessingException
     * @throws ParseException
     */
    @Test
    public void whenSerializingUsingJsonFormat()
            throws JsonProcessingException, ParseException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        df.setTimeZone(TimeZone.getTimeZone("UTC"));

        String toParse = "2021-04-06 12:30:00";
        Date date = df.parse(toParse);
        EventWithFormatEntity event = new EventWithFormatEntity("party", date);

        String result = new ObjectMapper()
                .writeValueAsString(event);
        System.out.println(result);

        assertTrue(result.contains(toParse));
    }

    /**
     * JsonUnwrapped
     * 平铺
     *
     * @throws JsonProcessingException
     * @throws ParseException
     */
    @Test
    public void whenSerializingUsingJsonUnwrapped()
            throws JsonProcessingException {
        UnwrappedEntity.Name name = new UnwrappedEntity.Name("Chen", "Yazid");
        UnwrappedEntity user = new UnwrappedEntity(1L, name);

        String result = new ObjectMapper()
                .writeValueAsString(user);
        System.out.println(result);

        assertTrue(result.contains("Yazid"));
        assertFalse(result.contains("name"));
    }

    /**
     * JsonView
     * 定义视图，writerWithView方法定义序列化哪个视图
     *
     * @throws JsonProcessingException
     */
    @Test
    public void whenSerializingUsingJsonView()
            throws JsonProcessingException {
        ViewItemEntity item = new ViewItemEntity(2L, "book", "Yazid");

        String result = new ObjectMapper()
                .writerWithView(ViewsEntity.Public.class)
                .writeValueAsString(item);
        System.out.println(result);

        assertTrue(result.contains("book"));
        assertTrue(result.contains("2"));
        assertFalse(result.contains("Yazid"));
    }

    /**
     * JsonManagedReference,JsonBackReference
     * 解决序列化时无限递归问题
     * {"id":2,"itemName":"book","owner":{"id":1,"name":"Yazid"}}
     *
     * @throws JsonProcessingException
     */
    @Test
    public void whenSerializingUsingJacksonReferenceAnnotation()
            throws JsonProcessingException {
        UserWithRefEntity user = new UserWithRefEntity(1L, "Yazid");
        ItemWithRefEntity item = new ItemWithRefEntity(2L, "book", user);
        user.addItem(item);

        String result = new ObjectMapper()
                .writeValueAsString(item);
        System.out.println(result);

        assertTrue(result.contains("book"));
        assertTrue(result.contains("Yazid"));
        assertFalse(result.contains("userItems"));
    }

    /**
     * JsonIdentityInfo
     * 解决序列化时无限递归问题
     *
     * @throws JsonProcessingException
     */
    @Test
    public void whenSerializingUsingJsonIdentityInfo()
            throws JsonProcessingException {
        UserWithIdentity user = new UserWithIdentity(1L, "Yazid");
        ItemWithIdentity item = new ItemWithIdentity(2L, "book", user);
        user.addItem(item);

        String result = new ObjectMapper()
                .writeValueAsString(item);
        System.out.println(result);

        assertTrue(result.contains("book"));
        assertTrue(result.contains("Yazid"));
        assertTrue(result.contains("userItems"));
    }

    /**
     * JsonFilter
     * 过滤指定条件进行序列化
     *
     * @throws JsonProcessingException
     */
    @Test
    public void whenSerializingUsingJsonFilter()
            throws JsonProcessingException {
        FilterEntity filterEntity = new FilterEntity(1L, "Yazid");

        FilterProvider filterProvider
                = new SimpleFilterProvider().addFilter(
                "myFilter",
                SimpleBeanPropertyFilter.filterOutAllExcept("name"));

        String result = new ObjectMapper()
                .writer(filterProvider)
                .writeValueAsString(filterEntity);
        System.out.println(result);

        assertTrue(result.contains("Yazid"));
        assertFalse(result.contains("\"id\""));
    }

    /**
     * JacksonAnnotationsInside
     * 自定义Jackson注解
     *
     * @throws JsonProcessingException
     */
    @Test
    public void whenSerializingUsingCustomAnnotation()
            throws JsonProcessingException {
        CustomAnnotationEntity customAnnotationEntity
                = new CustomAnnotationEntity(1L, "Yazid", null);

        String result = new ObjectMapper()
                .writeValueAsString(customAnnotationEntity);
        System.out.println(result);

        assertTrue(result.contains("Yazid"));
        assertTrue(result.contains("1"));
        assertFalse(result.contains("createDate"));
    }

    /**
     * JsonIgnoreType
     * addMixIn方法利用被JsonIgnoreType修饰的类指定需要忽略的类
     *
     * @throws JsonProcessingException
     */
    @Test
    public void whenSerializingUsingMixInAnnotation()
            throws JsonProcessingException {
        ItemEntity item = new ItemEntity(1L, "book", null);

        String result = new ObjectMapper()
                .writeValueAsString(item);
        System.out.println(result);
        assertTrue(result.contains("owner"));

        ObjectMapper mapper = new ObjectMapper();
        mapper.addMixIn(UserEntity.class, MyMixInForIgnoreType.class);

        result = mapper.writeValueAsString(item);
        System.out.println(result);
        assertFalse(result.contains("owner"));
    }

    /**
     * disable(MapperFeature.USE_ANNOTATIONS)
     * 忽略所有注解
     *
     * @throws JsonProcessingException
     */
    @Test
    public void whenDisablingAllAnnotations()
            throws JsonProcessingException {
        IgnoreEntity ignoreEntity = new IgnoreEntity(1L, "Chen", "Yazid");

        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(MapperFeature.USE_ANNOTATIONS);
        String result = mapper
                .writeValueAsString(ignoreEntity);
        System.out.println(result);

        assertTrue(result.contains("Yazid"));
        assertTrue(result.contains("\"id\""));
    }
}
