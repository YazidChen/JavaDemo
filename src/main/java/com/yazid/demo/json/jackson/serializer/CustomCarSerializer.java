package com.yazid.demo.json.jackson.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.yazid.demo.json.jackson.entity.CarEntity;

import java.io.IOException;

/**
 * @author Yazid
 * @date 2021/4/8 09:55
 */
public class CustomCarSerializer extends StdSerializer<CarEntity> {
    private static final long serialVersionUID = -4841137644216360492L;

    public CustomCarSerializer() {
        this(null);
    }

    public CustomCarSerializer(Class<CarEntity> t) {
        super(t);
    }

    @Override
    public void serialize(CarEntity carEntity, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("car_brand", carEntity.getType());
        jsonGenerator.writeEndObject();
    }
}
