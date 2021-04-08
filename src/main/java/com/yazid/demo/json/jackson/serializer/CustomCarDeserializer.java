package com.yazid.demo.json.jackson.serializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.yazid.demo.json.jackson.entity.CarEntity;

import java.io.IOException;

/**
 * @author Yazid
 * @date 2021/4/8 11:17
 */
public class CustomCarDeserializer extends StdDeserializer<CarEntity> {

    public CustomCarDeserializer() {
        this(null);
    }

    public CustomCarDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public CarEntity deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        CarEntity carEntity = new CarEntity();

        ObjectCodec codec = jsonParser.getCodec();
        JsonNode node = codec.readTree(jsonParser);

        JsonNode colorNode = node.get("color");
        String color = colorNode.asText();

        carEntity.setColor(color);
        return carEntity;
    }
}
