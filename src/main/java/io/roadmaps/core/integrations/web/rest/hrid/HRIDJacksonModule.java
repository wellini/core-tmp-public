package io.roadmaps.core.integrations.web.rest.hrid;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import io.roadmaps.core.hrid.HRID;

import java.io.IOException;

public class HRIDJacksonModule extends SimpleModule {

    public HRIDJacksonModule() {
        super("RoadmapsHRIDJacksonModule");
        addSerializer(HRID.class, new HRIDJsonSerializer());
        addDeserializer(HRID.class, new HRIDJsonDeserializer());
    }

    public static class HRIDJsonSerializer extends StdSerializer<HRID> {

        public HRIDJsonSerializer() {
            this(null);
        }

        public HRIDJsonSerializer(Class<HRID> t) {
            super(t);
        }

        @Override
        public void serialize(HRID hrid, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            jsonGenerator.writeString(hrid.getSerializedId());
        }
    }

    public static class HRIDJsonDeserializer extends StdDeserializer<HRID> {

        public HRIDJsonDeserializer() {
            this(null);
        }

        public HRIDJsonDeserializer(Class<?> vc) {
            super(vc);
        }

        @Override
        public HRID deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
            String valueAsString = jsonParser.getValueAsString();
            return HRID.fromString(valueAsString);
        }
    }
}
